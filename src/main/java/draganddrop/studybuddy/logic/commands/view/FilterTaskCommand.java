package draganddrop.studybuddy.logic.commands.view;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;
import java.util.logging.Logger;

import draganddrop.studybuddy.commons.core.LogsCenter;
import draganddrop.studybuddy.commons.core.Messages;
import draganddrop.studybuddy.logic.commands.Command;
import draganddrop.studybuddy.logic.commands.CommandResult;
import draganddrop.studybuddy.model.Model;
import draganddrop.studybuddy.model.task.Task;

/**
 * Refreshes the due soon list as well as status tags.
 */
public class FilterTaskCommand extends Command {

    private final Logger logger = LogsCenter.getLogger(FilterTaskCommand.class);
    private final Predicate<Task> predicate;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public FilterTaskCommand(Predicate<Task> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        logger.info("Attempting to filter tasks");
        model.updateFilteredTaskList(predicate);
        return new CommandResult(String.format(Messages.MESSAGE_FILTER_TASK_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterTaskCommand); // state check
    }

}
