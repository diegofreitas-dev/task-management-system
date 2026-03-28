package model;

public enum TaskPriority {
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    URGENT(4);

    private final int option;

    TaskPriority(int option) {
        this.option = option;
    }

    public static TaskPriority fromOption(int option) {
        for (TaskPriority priority : TaskPriority.values()) {
            if (option == priority.option) {
                return priority;
            }
        }
        throw new IllegalArgumentException("Invalid priority");
    }
}
