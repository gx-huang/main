package draganddrop.studybuddy.ui;

import draganddrop.studybuddy.logic.Logic;
import draganddrop.studybuddy.logic.commands.CommandResult;
import draganddrop.studybuddy.logic.commands.exceptions.CommandException;
import draganddrop.studybuddy.logic.interactiveprompt.ExitTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.InteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.InvalidInputInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.add.AddTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.add.CreateModuleInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.delete.ClearTasksInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.delete.DeleteTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.edit.ArchiveTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.edit.CompleteTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.edit.EditModuleInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.edit.EditTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.edit.SetGoalInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.edit.UnarchiveTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.sort.SortTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.view.CalendarViewInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.view.FilterTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.view.FindTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.view.HelpInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.view.ListTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.view.RefreshTaskInteractivePrompt;
import draganddrop.studybuddy.logic.interactiveprompt.view.ViewRenamedTaskInteractivePrompt;
import draganddrop.studybuddy.logic.parser.exceptions.ParseException;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;

/**
 * The UI component that is responsible for receiving user command inputs.
 */
public class CommandBox extends UiPart<Region> {

    public static final String ERROR_STYLE_CLASS = "error";
    private static final String FXML = "CommandBox.fxml";
    //private static final String[] interactiveCommandTypes =
    //{"add", "edit", "delete", "archive", "done", "view renamed",
    //"clear", "bye", "sort", "refresh", "help",
    //"filter", "create mods", "edit mods", "find", "list", "unarchive", "goal", "calendar"};
    private final CommandExecutor commandExecutor;
    private InteractivePrompt currentInteractivePrompt;
    @FXML
    private TextField commandTextField;

    public CommandBox(CommandExecutor commandExecutor) {
        super(FXML);
        currentInteractivePrompt = null;
        this.commandExecutor = commandExecutor;
        // calls #setStyleToDefault() whenever there is a change to the text of the command box.
        commandTextField.textProperty().addListener((unused1, unused2, unused3) -> setStyleToDefault());
    }

    /**
     * Handles the Enter button pressed event.
     */
    @FXML
    public void handleCommandEntered() {
        try {
            // allow InteractivePrompt type with different case and space in front or behind
            String userInput = commandTextField.getText().toLowerCase().trim();
            if (currentInteractivePrompt == null) {
                switch (userInput) {
                case "add":
                    currentInteractivePrompt = new AddTaskInteractivePrompt();
                    break;
                case "edit":
                    currentInteractivePrompt = new EditTaskInteractivePrompt();
                    break;
                case "delete":
                    currentInteractivePrompt = new DeleteTaskInteractivePrompt();
                    break;
                case "archive":
                    currentInteractivePrompt = new ArchiveTaskInteractivePrompt();
                    break;
                case "view renamed":
                    currentInteractivePrompt = new ViewRenamedTaskInteractivePrompt();
                    break;
                case "done":
                    currentInteractivePrompt = new CompleteTaskInteractivePrompt();
                    break;
                case "sort":
                    currentInteractivePrompt = new SortTaskInteractivePrompt();
                    break;
                case "help":
                    currentInteractivePrompt = new HelpInteractivePrompt();
                    break;
                case "bye":
                    currentInteractivePrompt = new ExitTaskInteractivePrompt();
                    break;
                case "find":
                    currentInteractivePrompt = new FindTaskInteractivePrompt();
                    break;
                case "refresh":
                    currentInteractivePrompt = new RefreshTaskInteractivePrompt();
                    break;
                case "list":
                    currentInteractivePrompt = new ListTaskInteractivePrompt();
                    break;
                case "clear":
                    currentInteractivePrompt = new ClearTasksInteractivePrompt();
                    break;
                case "unarchive":
                    currentInteractivePrompt = new UnarchiveTaskInteractivePrompt();
                    break;
                case "filter":
                    currentInteractivePrompt = new FilterTaskInteractivePrompt();
                    break;

                //mod related functions will not be shown on UI but available as shortcut
                case "create mods":
                    currentInteractivePrompt = new CreateModuleInteractivePrompt();
                    break;
                case "edit mods":
                    currentInteractivePrompt = new EditModuleInteractivePrompt();
                    break;
                //mod related functions will not be shown on UI but available as shortcut
                case "goal":
                    currentInteractivePrompt = new SetGoalInteractivePrompt();
                    break;
                //change tabs
                case "calendar":
                    currentInteractivePrompt = new CalendarViewInteractivePrompt();
                    break;
                default:
                    currentInteractivePrompt = new InvalidInputInteractivePrompt();
                    break;
                }
            }

            CommandResult commandResult = commandExecutor
                .execute(currentInteractivePrompt, commandTextField.getText());
            if (commandResult != null) {
                currentInteractivePrompt = null;
            }
            commandTextField.setText("");
        } catch (CommandException | ParseException | NullPointerException e) {
            commandTextField.setText(e.getMessage());
            setStyleToIndicateCommandFailure();
        }
    }

    /**
     * Sets the command box style to use the default style.
     */
    private void setStyleToDefault() {
        commandTextField.getStyleClass().remove(ERROR_STYLE_CLASS);
    }

    /**
     * Sets the command box style to indicate a failed command.
     */
    private void setStyleToIndicateCommandFailure() {
        ObservableList<String> styleClass = commandTextField.getStyleClass();

        if (styleClass.contains(ERROR_STYLE_CLASS)) {
            return;
        }

        styleClass.add(ERROR_STYLE_CLASS);
    }

    /**
     * Represents a function that can execute commands.
     */
    @FunctionalInterface
    public interface CommandExecutor {
        /**
         * Executes the command and returns the result.
         *
         * @see Logic#execute(String)
         */
        CommandResult execute(InteractivePrompt currentInteractivePrompt, String commandText)
            throws CommandException, ParseException;
    }

    public void run(InteractivePrompt interactivePrompt) {
        currentInteractivePrompt = interactivePrompt;
    }
}
