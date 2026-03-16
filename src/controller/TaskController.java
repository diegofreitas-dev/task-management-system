package controller;

import model.Task;
import model.TaskPriority;
import model.TaskStatus;
import service.TaskService;

import java.util.Collection;
import java.util.List;

public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public Task createTask(String title, String description, int priority) {
        return taskService.createTask(title, description, TaskPriority.fromOption(priority));
    }

    public Task changeTaskStatus(int id, int newStatus) {
        return taskService.changeTaskStatus(id, TaskStatus.fromOption(newStatus));
    }

    public void deleteTask(int id) {
        taskService.deleteTask(id);
    }

    public Collection<Task> listTasks() {
        return taskService.listTasks();
    }

    public Task modifyTask(int id, String title, String description, int priority) {
        return taskService.modifyTask(id, title, description, TaskPriority.fromOption(priority));
    }

    public List<Task> searchTaskByTitle(String title) {
        return taskService.searchTaskByTitle(title);
    }

    public List<Task> searchTaskByStatus(int status) {
        return taskService.searchTaskByStatus(TaskStatus.fromOption(status));
    }

    public List<Task> searchTaskByPriority(int priority) {
        return taskService.searchTaskByPriority(TaskPriority.fromOption(priority));
    }

    public Task searchTask(int id) {
        return taskService.searchTask(id);
    }


}
