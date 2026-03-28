package model;

public enum TaskStatus {
    PENDING(1),
    IN_PROGRESS(2),
    COMPLETE(3);

    private final int option;

    TaskStatus(int option) {
        this.option = option;
    }

    public static TaskStatus fromOption(int option) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.option == option) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status option");
    }
}
