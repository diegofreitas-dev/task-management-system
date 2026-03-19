package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task {
    private final int ID;
    private String title;
    private String description;
    private TaskStatus status = TaskStatus.PENDING;
    private TaskPriority priority;
    private List<Tag> tags;

    public Task(int id, String title, String description, TaskPriority priority) {
       this.ID = id;
       this.title = title;
       this.description = description;
       this.priority = priority;
       this.tags = new ArrayList<>();
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

    public void addTags(Tag newtag) {
        boolean exist = tags.stream()
                .anyMatch(tag -> tag == newtag);

        if (!exist) {
            tags.add(newtag);
        }
    }

    public void deleteTag(Tag tag) {
        this.tags.removeIf(t -> t == tag);
    }

    public List<Tag> getTags() {
        return Collections.unmodifiableList(tags);
    }

    @Override
    public String toString() {
        return "ID: " + ID + "; Title: " + title +
                "; Description: " + description +
                "; Priority: " + priority.toString() +
                "; Status: " + status;
    }
}
