package fr.euphyllia.fidorial.server.tests;

import fr.euphyllia.fidorial.server.FidorialServer;
import fr.euphyllia.fidorial.server.world.BlockStateRegistry;
import fr.euphyllia.fidorial.server.world.chunk.BlockState;
import fr.fidorial.registry.keys.BlockTypeKeys;
import fr.fidorial.testing.ScenarioTestHelper;
import fr.fidorial.testing.annotation.ScenarioTest;
import fr.fidorial.world.BlockPos;
import fr.fidorial.world.World;
import net.kyori.adventure.key.Key;

public final class BlockStateTests {

    private static final Key STONE = Key.key("stone");
    private static final Key COBBLESTONE = Key.key("cobblestone");

    @ScenarioTest(timeoutTicks = 20)
    public static void belowWorldIsAir(final ScenarioTestHelper helper) {
        final World world = helper.world();
        final BlockStateRegistry registry = FidorialServer.getInstance().blockStateRegistry();
        final BlockPos pos = new BlockPos(0, world.dimensionType().minY() - 1, 0);

        final BlockState actual = registry.byId(world.getBlockStateId(pos));
        helper.assertTrue(actual.isAir(),
                "Expected " + pos + " to be air (unset), but found " + actual.name());
    }

    @ScenarioTest(timeoutTicks = 40)
    public static void settingBlockStateUpdatesWorld(final ScenarioTestHelper helper) {
        final World world = helper.world();
        final BlockPos pos = new BlockPos(0, -60, 0);
        final int stoneId = stoneId();

        helper.sequence()
                .execute(() -> world.setBlockStateId(pos, stoneId))
                .waitUntil(() -> helper.assertBlockAt(pos, STONE))
                .build();
    }

    //@ScenarioTest(timeoutTicks = 80) disabled for now as setBlockStateId doesnt send light updates
    public static void reopeningVerticalShaftRestoresSkylight(final ScenarioTestHelper helper) {
        final World world = helper.world();
        final int airId = FidorialServer.getInstance().blockStateRegistry().networkId(BlockState.of(BlockTypeKeys.AIR.key()));
        final int cobblestoneId = cobblestoneId();
        final int x = 8;
        final int z = 8;
        final int blockerY = 0;
        final int targetY = -60;

        helper.sequence()
                .execute(() -> {
                    for (int y = world.minY() + world.height() - 1; y >= targetY; y--) {
                        world.setBlockStateId(new BlockPos(x, y, z), airId);
                    }
                })
                .waitUntil(() -> assertSkyLight(helper, new BlockPos(x, targetY, z), 15))
                .execute(() -> world.setBlockStateId(new BlockPos(x, blockerY, z), cobblestoneId))
                .waitUntil(() -> assertSkyLight(helper, new BlockPos(x, targetY, z), 0))
                .execute(() -> world.setBlockStateId(new BlockPos(x, blockerY, z), airId))
                .waitUntil(() -> assertSkyLight(helper, new BlockPos(x, targetY, z), 15))
                .build();
    }

    private static int stoneId() {
        final BlockStateRegistry registry = FidorialServer.getInstance().blockStateRegistry();
        return registry.networkId(BlockState.of(STONE));
    }

    private static int cobblestoneId() {
        final BlockStateRegistry registry = FidorialServer.getInstance().blockStateRegistry();
        return registry.networkId(BlockState.of(COBBLESTONE));
    }

    private static void assertSkyLight(final ScenarioTestHelper helper, final BlockPos pos, final int expected) {
        final int actual = helper.world().skyLight(pos);
        helper.assertTrue(
                actual == expected,
                "Expected skylight " + expected + " at " + pos + " but found " + actual );
    }
}
