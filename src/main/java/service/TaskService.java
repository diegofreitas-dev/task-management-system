package service;


import model.Tag;
import model.Task;
import model.TaskPriority;
import model.TaskStatus;

import java.util.*;

public class TaskService {
    private Map<Integer, Task> tasks;
    private int countID = 1;

    public TaskService() {
        tasks = new HashMap<>();
    }

    public Task createTask(String title, String description, TaskPriority priority) {
        Task task = new Task(countID++, title, description, priority);

        tasks.put(task.getID(), task);
        return task;
    }

    public Task changeTaskStatus(int id, TaskStatus newStatus) {
        Task task = searchTask(id);
        task.updateStatus(newStatus);
        return task;
    }

    public void deleteTask(int id) {
        Task task = searchTask(id);

        tasks.remove(id, task);
    }

    public Collection<Task> listTasks() {
        return tasks.values();
    }

    public Task modifyTask(int id, String title, String description, TaskPriority priority) {
        Task task = searchTask(id);
        task.updateTitle(title);
        task.updateDescription(description);
        task.updatePriority(priority);

        return task;
    }

    public Task addTag(int id, Tag newTag) {
        Task task = searchTask(id);
        task.addTags(newTag);

        return task;
    }

    public Task deleteTag(int id, Tag tag) {
        Task task = searchTask(id);
        task.deleteTag(tag);

        return task;
    }

    public List<Tag> getTags(int id) {
        Task task = tasks.get(id);

        return task.getTags();
    }

    public List<Task> searchTaskByTitle(String title) {
        return this.tasks.values().stream()
                .filter(task -> task.getTitle().equalsIgnoreCase(title))
                .toList();
    }

    public List<Task> searchTaskByStatus(TaskStatus status) {

        return this.tasks.values().stream()
                .filter(task -> task.getStatus() == status)
                .toList();
    }

    public List<Task> searchTaskByPriority(TaskPriority priority) {
        return this.tasks.values().stream()
                .filter(task -> task.getPriority() == priority)
                .toList();
    }

    public List<Task> searchTaskByTag(Tag tag) {
        return this.tasks.values().stream()
                .filter(task -> task.getTags().contains(tag))
                .toList();
    }

    public Task searchTask(int id) {
        Task task = tasks.get(id);

        if (task != null) {
            return task;
        }

        throw new IllegalArgumentException("Invalid ID, Task not found");
    }
}
