package model;

public class Task {
    private final int ID;
    private String title;
    private String description;
    private TaskStatus status = TaskStatus.PENDING;

    public Task(int id, String title, String description) {
       this.ID = id;
       this.title = title;
       this.description = description;
    }

    public int getID() {return ID;}

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateStatus(TaskStatus newStatus) {
        status = newStatus;
    }

    @Override
    public String toString() {
        return "ID: " + ID + "; Title: " + title +
                "; Description: " + description +
                "Status: " + status;
    }
}
