package draganddrop.studybuddy.logic.interactiveprompt;

/**
 * Terms used in Interactive Prompt to indicate the stage in the sequence of interaction.
 */
public enum InteractivePromptTerms {
    INIT,
    OPTION_TYPE,
    TASK_NAME,
    TASK_INDEX,
    TASK_TYPE,
    TASK_DATETIME,
    TASK_DESCRIPTION,
    TASK_MODULE,
    TASK_WEIGHT,
    TASK_ESTIMATED_TIME_COST,
    MODULE_NAME,
    MODULE_CODE,
    SORT_KEYWORD,
    READY_TO_EXECUTE,
    ADD_DUPLICATE,
    // for find
    FIND_KEYWORD,
    // for edit
    TASK_NUMBER,
    TASK_FIELD,
    NEW_VALUE,
    CHOICE,
    STATUS_TYPE,
    TASKS_TYPE,
    //for edit mod
    PICK,
    CHANGE_MOD_NAME,
    CHANGE_MOD_CODE,
    DELETE_MOD,
    // for setting goals
    GOAL,
    //for calendar view
    DATE;
}
