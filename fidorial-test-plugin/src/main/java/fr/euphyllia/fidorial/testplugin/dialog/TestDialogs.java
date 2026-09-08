package fr.euphyllia.fidorial.testplugin.dialog;

import fr.euphyllia.fidorial.testplugin.TestPlugin;
import fr.fidorial.dialog.ConfirmationDialog;
import fr.fidorial.dialog.Dialog;
import fr.fidorial.dialog.DialogAction;
import fr.fidorial.dialog.DialogActionButton;
import fr.fidorial.dialog.DialogAfterAction;
import fr.fidorial.dialog.DialogBase;
import fr.fidorial.dialog.DialogBody;
import fr.fidorial.dialog.DialogDefinition;
import fr.fidorial.dialog.DialogInput;
import fr.fidorial.dialog.DialogListDialog;
import fr.fidorial.dialog.DialogRegistry;
import fr.fidorial.dialog.MultiActionDialog;
import fr.fidorial.dialog.NoticeDialog;
import fr.fidorial.dialog.ServerLinksDialog;
import fr.fidorial.item.ItemStack;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.CompoundBinaryTag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jspecify.annotations.Nullable;

import java.util.List;

/**
 * Sample dialogs, one per vanilla type, exercised by {@code /testdialog}.
 *
 * <p>Two things are worth noticing while reading this class.</p>
 *
 * <p>First, everything textual is an Adventure {@link Component}, so colors, hover events and
 * translations behave exactly as they do in chat.</p>
 *
 * <p>Second, the dialogs are registered from
 * {@link TestPlugin#onLoad} rather than lazily. The dialog
 * registry travels to a client during the configuration phase, so anything registered after a
 * player has joined stays invisible to that player until they reconnect. Registering at load is the
 * only way {@link #ROOT} can list the others by key.</p>
 */
public final class TestDialogs {

    /**
     * Namespace every sample lives in.
     */
    public static final String NAMESPACE = "fidorial_test";

    /**
     * Identifier of the event a submitted dialog posts back to the server.
     *
     * @see TestPlugin
     */
    public static final Key SUBMIT_ID = Key.key(NAMESPACE, "submit");

    /**
     * The simplest possible dialog: a title, a message, one button.
     */
    public static final Key NOTICE = Key.key(NAMESPACE, "notice");

    /**
     * A yes/no dialog whose buttons run server code through an Adventure click callback.
     */
    public static final Key CONFIRMATION = Key.key(NAMESPACE, "confirmation");

    /**
     * Every input control at once, submitted through a dynamic custom action.
     */
    public static final Key FORM = Key.key(NAMESPACE, "form");

    /**
     * A grid of buttons, each firing a different kind of action.
     */
    public static final Key MULTI_ACTION = Key.key(NAMESPACE, "multi_action");

    /**
     * The server links, if the server advertises any.
     */
    public static final Key SERVER_LINKS = Key.key(NAMESPACE, "server_links");

    /**
     * A menu leading to all of the above, added to the pause screen.
     */
    public static final Key ROOT = Key.key(NAMESPACE, "root");

    private TestDialogs() {
        throw new UnsupportedOperationException("TestDialogs cannot be instantiated.");
    }

    /**
     * Registers every sample and hangs the root menu off the pause screen and the quick actions
     * hotkey.
     *
     * @param dialogs the server dialog registry
     * @param logger  where the outcome is reported
     */
    public static void registerAll(final DialogRegistry dialogs, final ComponentLogger logger) {
        dialogs.overwrite(NOTICE, notice());
        dialogs.overwrite(CONFIRMATION, confirmation());
        dialogs.overwrite(FORM, form());
        dialogs.overwrite(MULTI_ACTION, multiAction());
        dialogs.overwrite(SERVER_LINKS, serverLinks());
        dialogs.overwrite(ROOT, root());

        dialogs.addToPauseScreen(ROOT);
        dialogs.addToQuickActions(ROOT);

        logger.info("[TestPlugin] {} recorded dialogues, root menu = {}",
                6, ROOT.asString());
    }

    /**
     * Drops every sample again, so a reload does not leave stale entries behind.
     *
     * @param dialogs the server dialog registry
     */
    public static void unregisterAll(final DialogRegistry dialogs) {
        for (final Key key : List.of(ROOT, SERVER_LINKS, MULTI_ACTION, FORM, CONFIRMATION, NOTICE)) {
            dialogs.removeFromMenus(key);
            dialogs.unregister(key);
        }
    }

    /**
     * {@return a plain information screen}
     *
     * <p>Nothing is set beyond the defaults: the button reads {@code gui.ok} and merely dismisses
     * the screen.</p>
     */
    public static NoticeDialog notice() {
        return NoticeDialog.of(DialogBase.builder(Component.text("Welcome", NamedTextColor.GOLD))
                .externalTitle(Component.text("Notice"))
                .body(Component.text("This is an informational dialog."))
                .body(DialogBody.message(
                        Component.text("The Adventure components work here:")
                                .append(Component.text("gras", NamedTextColor.RED)
                                        .decorate(TextDecoration.BOLD))
                                .append(Component.text(", colors, translate.")),
                        280))
                .build());
    }

    /**
     * {@return a yes/no screen wired to server-side callbacks}
     *
     * <p>Both buttons use {@link DialogAction#callback}, which is Adventure's own click callback
     * mechanism. No identifier to invent, no event to subscribe to: the lambda simply runs. The
     * trade-off is that a callback expires, and that it receives the audience only, never the input
     * values.</p>
     */
    public static ConfirmationDialog confirmation() {
        return new ConfirmationDialog(
                DialogBase.builder(Component.text("Confirm ?", NamedTextColor.YELLOW))
                        .externalTitle(Component.text("Confirmation"))
                        .body(Component.text("The two buttons execute server-side code."))
                        .build(),
                DialogActionButton.builder(Component.text("Yes", NamedTextColor.GREEN))
                        .tooltip(Component.text("Send a confirmation message."))
                        .action(DialogAction.callback(audience ->
                                audience.sendMessage(Component.text(
                                        "[TestPlugin] Callback YES triggers.", NamedTextColor.GREEN))))
                        .build(),
                DialogActionButton.builder(Component.text("No", NamedTextColor.RED))
                        .action(DialogAction.callback(audience ->
                                audience.sendMessage(Component.text(
                                        "[TestPlugin] Callback NO triggered.", NamedTextColor.RED))))
                        .build());
    }

    /**
     * {@return a form exercising all four input control types}
     *
     * <p>The submit button uses a dynamic custom action, so the value of every control travels back
     * under its own key and lands in a
     * {@link fr.fidorial.event.player.PlayerDialogActionEvent}. The {@code additions} compound
     * carries a fixed field alongside them, which is how several buttons of the same dialog can
     * share one identifier and still be told apart.</p>
     */
    public static MultiActionDialog form() {
        final DialogBase base = DialogBase.builder(Component.text("Form", NamedTextColor.AQUA))
                .externalTitle(Component.text("Form"))
                .body(Component.text("Fill in the fields and then submit."))
                .input(DialogInput.text("username", Component.text("Username"))
                        .initial("Steve")
                        .maxLength(16)
                        .build())
                .input(DialogInput.text("message", Component.text("Message"))
                        .multiline(4, 60)
                        .maxLength(256)
                        .build())
                .input(DialogInput.checkbox("notify", Component.text("Notify me")))
                .input(DialogInput.singleOption("color", Component.text("Color"))
                        .option(DialogInput.SingleOption.Entry
                                .of("red", Component.text("Red", NamedTextColor.RED)).asInitial())
                        .option("green", Component.text("Green", NamedTextColor.GREEN))
                        .option("blue", Component.text("Blue", NamedTextColor.BLUE))
                        .build())
                .input(DialogInput.numberRange("volume", Component.text("Volume"), 0f, 100f)
                        .step(5f)
                        .initial(50f)
                        .build())
                .build();

        return new MultiActionDialog(
                base,
                List.of(
                        DialogActionButton.of(
                                Component.text("Validate", NamedTextColor.GREEN),
                                DialogAction.dynamicCustom(SUBMIT_ID, CompoundBinaryTag.builder()
                                        .putString("bouton", "validate")
                                        .build())),
                        DialogActionButton.of(
                                Component.text("Macro test"),
                                DialogAction.dynamicRunCommand("say $(message)"))),
                2,
                DialogActionButton.of(Component.text("Cancel")));
    }

    /**
     * {@return a grid showing one button per action kind}
     *
     * <p>Also the only sample using an item body element, and the only one that stays open after a
     * click: {@link DialogAfterAction#NONE} keeps the screen up, which is why the builder clears
     * the pause flag on its own.</p>
     */
    public static MultiActionDialog multiAction() {
        final DialogBase base = DialogBase.builder(Component.text("Actions", NamedTextColor.LIGHT_PURPLE))
                .externalTitle(Component.text("Actions"))
                .body(Component.text("One button per action type."))
                .body(DialogBody.item(new ItemStack(Key.key("diamond"), 3))
                        .description(Component.text("An item-type body element."))
                        .showDecoration(true)
                        .build())
                .afterAction(DialogAfterAction.NONE)
                .build();

        return new MultiActionDialog(
                base,
                List.of(
                        DialogActionButton.of(
                                Component.text("Commands"),
                                DialogAction.runCommand("apitest")),
                        DialogActionButton.of(
                                Component.text("Suggest"),
                                DialogAction.suggestCommand("/testdialog show notice")),
                        DialogActionButton.of(
                                Component.text("Copy"),
                                DialogAction.copyToClipboard("fidorial")),
                        DialogActionButton.of(
                                Component.text("Open a link"),
                                DialogAction.openUrl("https://minecraft.wiki/w/Dialog")),
                        DialogActionButton.of(
                                Component.text("Custom event"),
                                DialogAction.custom(SUBMIT_ID)),
                        DialogActionButton.of(
                                Component.text("Go to the form"),
                                DialogAction.showDialog(Dialog.reference(FORM)))),
                2,
                DialogActionButton.of(Component.text("CLose")));
    }

    /**
     * {@return the server links screen}
     *
     * <p>Its contents come from the links the server advertises, not from this definition, so it
     * renders empty when there are none.</p>
     */
    public static ServerLinksDialog serverLinks() {
        return ServerLinksDialog.of(DialogBase.builder(Component.text("Server links"))
                        .externalTitle(Component.text("Links"))
                        .build())
                .exitAction(DialogActionButton.of(Component.text("Back")));
    }

    /**
     * {@return a menu listing the other samples}
     *
     * <p>The entries are references rather than inline copies, which is exactly what registering
     * buys. Each button is labelled with the target's
     * {@link DialogBase#externalTitle() external title}, hence the care taken to set one on every
     * sample.</p>
     */
    public static DialogListDialog root() {
        return DialogListDialog.of(
                        DialogBase.builder(Component.text("Dialogue tests", NamedTextColor.GOLD))
                                .externalTitle(Component.text("TestPlugin"))
                                .body(Component.text("Choose a dialogue to open."))
                                .build(),
                        List.of(
                                Dialog.reference(NOTICE),
                                Dialog.reference(CONFIRMATION),
                                Dialog.reference(FORM),
                                Dialog.reference(MULTI_ACTION),
                                Dialog.reference(SERVER_LINKS)))
                .exitAction(DialogActionButton.of(Component.text("Back")));
    }

    /**
     * Looks a sample up by its short name, as typed on the command line.
     *
     * @param name the short name, for instance {@code notice}
     * @return the matching definition, or {@code null} when the name is unknown
     */
    public static @Nullable DialogDefinition byName(final String name) {
        return switch (name) {
            case "notice" -> notice();
            case "confirmation" -> confirmation();
            case "form" -> form();
            case "multi_action" -> multiAction();
            case "server_links" -> serverLinks();
            case "root" -> root();
            default -> null;
        };
    }

    /**
     * {@return the short names {@link #byName(String)} accepts}
     */
    public static List<String> names() {
        return List.of("notice", "confirmation", "form", "multi_action", "server_links", "root");
    }
}
