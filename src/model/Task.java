package model;

public class Task {
    private final int ID;
    private String title;
    private String description;
    private TaskStatus status = TaskStatus.PENDING;
    private TaskPriority priority;

    public Task(int id, String title, String description, TaskPriority priority) {
       this.ID = id;
       this.title = title;
       this.description = description;
       this.priority = priority;
    }

    public int getID() {return ID;}

    public String getTitle() {return title;}

    public TaskStatus getStatus() {return status;}

    public TaskPriority getPriority() {return priority;}

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateStatus(TaskStatus newStatus) {
        status = newStatus;
    }

    public void updatePriority(TaskPriority newPriority) {priority = newPriority;}

    @Override
    public String toString() {
        return "ID: " + ID + "; Title: " + title +
                "; Description: " + description +
                "; Priority: " + priority.toString() +
                "; Status: " + status;
    }
}
