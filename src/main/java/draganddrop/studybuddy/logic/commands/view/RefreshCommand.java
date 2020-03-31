package draganddrop.studybuddy.logic.commands.view;

import static java.util.Objects.requireNonNull;

import java.util.List;

import draganddrop.studybuddy.commons.core.Messages;
import draganddrop.studybuddy.logic.commands.Command;
import draganddrop.studybuddy.logic.commands.CommandResult;
import draganddrop.studybuddy.model.Model;
import draganddrop.studybuddy.model.task.Task;

/**
 * Archives an entry in the study buddy.
 */
public class RefreshCommand extends Command {

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        for (int i = 0; i < lastShownList.size(); i++) {
            Task task = lastShownList.get(i);
            boolean isStatusExpired = task.isStatusExpired();
            if (isStatusExpired) {
                Task temp = task;
                model.deleteTask(task);
                temp.freshStatus();
                model.addTask(temp);
                model.sortTasks("Creation DateTime");
            }
            if (task.isDueSoon()) {
                if (model.getFilteredDueSoonTaskList().contains(task)) {
                    continue;
                } else {
                    model.addDueSoonTask(task);
                }
            } else {
                if (model.getFilteredDueSoonTaskList().contains(task)) {
                    model.deleteDueSoonTask(task);
                } else {
                    continue;
                }
            }
        }
        model.sortDueSoonTasks();
        return new CommandResult(String.format(Messages.MESSAGE_DUE_SOON_TASK_SUCCESS));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof RefreshCommand); // state check
    }

}
