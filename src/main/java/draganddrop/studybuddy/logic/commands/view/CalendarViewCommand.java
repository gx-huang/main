package draganddrop.studybuddy.logic.commands.view;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.logging.Logger;

import draganddrop.studybuddy.commons.core.LogsCenter;
import draganddrop.studybuddy.logic.commands.Command;
import draganddrop.studybuddy.logic.commands.CommandResult;
import draganddrop.studybuddy.model.Model;

/**
 * brings up calendar immediately with task from input date shown.
 * Might not need to hae this class since model isnt changed.
 */
public class CalendarViewCommand extends Command {

    private final Logger logger = LogsCenter.getLogger(CalendarViewCommand.class);
    public static final String CALENDAR_VIEW_SUCCESS = "Now showing tasks for date:  %1$s";
    private LocalDate selectedDate;

    /**
     *
     */
    public CalendarViewCommand(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(String.format(CALENDAR_VIEW_SUCCESS, selectedDate.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CalendarViewCommand); // state check
    }

}

