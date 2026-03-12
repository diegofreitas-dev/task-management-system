package service;


import model.Task;
import model.TaskStatus;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private Map<Integer, Task> tasks;
    private int countID = 1;

    public TaskService() {
        tasks = new HashMap<>();
    }

    public Task createTask(String title, String description) {
        Task task = new Task(countID++, title, description);

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

    public Task modifyTask(int id, String title, String description) {
        Task task = searchTask(id);
        task.updateTitle(title);
        task.updateDescription(description);

        return task;
    }

    private Task searchTask(int id) {
        Task task = tasks.get(id);

        if (task != null) {
            return task;
        }

        throw new IllegalArgumentException("Invalid ID, Task not found");
    }
}
