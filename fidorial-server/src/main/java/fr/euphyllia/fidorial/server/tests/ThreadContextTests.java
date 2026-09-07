package fr.euphyllia.fidorial.server.tests;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.util.threading.ThreadContexts;
import fr.euphyllia.fidorial.server.world.BlockStateRegistry;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.testing.ScenarioTestHelper;
import fr.fidorial.testing.annotation.ScenarioTest;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.World;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ThreadContextTests {

    private static final BlockPos TEST_POS = new BlockPos(0, -60, 0);

    @ScenarioTest(timeoutTicks = 40)
    public static void setBlockStateIdTripsFromForeignThread(final ScenarioTestHelper helper) {
        final World world = helper.world();
        final int stoneId = stoneId();

        helper.sequence()
                .execute(() -> {
                    final Throwable thrown = runOnForeignThreadCapturing(() ->
                            world.setBlockStateId(TEST_POS, stoneId));

                    helper.assertTrue(thrown instanceof ThreadContexts.ThreadViolationException,
                            "Expected ThreadViolationException from a foreign thread, but got "
                                    + (thrown == null ? "no exception" : thrown.getClass().getName()));
                })
                .build();
    }

    @ScenarioTest(timeoutTicks = 40)
    public static void setBlockStateIdPassesOnOwningThread(final ScenarioTestHelper helper) {
        final World world = helper.world();
        final int stoneId = stoneId();

        helper.sequence()
                .execute(() -> world.setBlockStateId(TEST_POS, stoneId))
                .waitUntil(() -> helper.assertBlockAt(TEST_POS, BlockTypeKeys.STONE.key()))
                .build();
    }

    @ScenarioTest(timeoutTicks = 40)
    public static void getChunkDoesNotTripFromForeignThread(final ScenarioTestHelper helper) {
        final World world = helper.world();
        final int chunkX = TEST_POS.x() >> 4;
        final int chunkZ = TEST_POS.z() >> 4;

        helper.sequence()
                .execute(() -> {
                    // this should never throw as getChunk is not thread checked
                    final Throwable thrown = runOnForeignThreadCapturing(() -> {
                        try {
                            world.getChunkAsync(chunkX, chunkZ).join();
                        } catch (final Exception e) {
                            throw new RuntimeException(e);
                        }
                    });

                    helper.assertTrue(thrown == null,
                            "Expected getChunkAsync to succeed from a foreign thread, but got "
                                    + (thrown == null ? "nothing" : thrown.getClass().getName()));
                })
                .build();
    }

    private static int stoneId() {
        final BlockStateRegistry registry = FidorialServer.getInstance().blockStateRegistry();
        return registry.networkId(BlockState.of(BlockTypeKeys.STONE.key()));
    }

    private static Throwable runOnForeignThreadCapturing(final Runnable action) {
        final AtomicReference<Throwable> thrown = new AtomicReference<>();
        final CountDownLatch done = new CountDownLatch(1);

        final Thread foreign = new Thread(() -> {
            try {
                action.run();
            } catch (final Throwable t) {
                thrown.set(t);
            } finally {
                done.countDown();
            }
        }, "thread-check-test-foreign-thread");
        foreign.setDaemon(true);
        foreign.start();

        try {
            if (!done.await(5, TimeUnit.SECONDS)) {
                return new IllegalStateException("Foreign thread did not complete in time");
            }
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
            return e;
        }

        return thrown.get();
    }
}
