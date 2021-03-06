package draganddrop.studybuddy.model.task;

import static draganddrop.studybuddy.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ONE;
import static draganddrop.studybuddy.logic.commands.CommandTestUtil.VALID_TASK_NAME_ONE;
import static draganddrop.studybuddy.testutil.TypicalTasks.getSampleTasks;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import draganddrop.studybuddy.testutil.TaskBuilder;

/**
 * Test class for Task.
 *
 * @@author Souwmyaa Sabarinathann
 */
public class TaskTest {

    private Task task1 = getSampleTasks()[0];
    private Task task2 = getSampleTasks()[1];

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(task1.isSameTask(task1));

        // null -> returns false
        assertFalse(task1.isSameTask(null));

        // different name -> returns false
        Task editedTask1 = new TaskBuilder(task1).withName(VALID_TASK_NAME_ONE).build();
        assertFalse(task1.isSameTask(editedTask1));

        // different description -> returns false
        editedTask1 = new TaskBuilder(task1).withDescription(VALID_DESCRIPTION_ONE).build();
        assertFalse(task1.isSameTask(editedTask1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Task task1Copy = new TaskBuilder(task1).build();
        assertEquals(task1, task1Copy);

        // same object -> returns true
        assertEquals(task1, task1);

        // null -> returns false
        assertNotNull(task1);

        // different type -> returns false
        assertNotEquals(5, task1);

        // different task -> returns false
        assertNotEquals(task1, task2);

        // different name -> returns false
        Task editedTask1 = new TaskBuilder(task1).withName(VALID_TASK_NAME_ONE).build();
        assertNotEquals(task1, editedTask1);

        // different description -> returns false
        editedTask1 = new TaskBuilder(task1).withDescription(VALID_DESCRIPTION_ONE).build();
        assertNotEquals(task1, editedTask1);
    }
}

