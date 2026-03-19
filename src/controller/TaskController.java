package controller;

import model.Tag;
import model.Task;
import model.TaskPriority;
import model.TaskStatus;
import service.TagService;
import service.TaskService;

import java.util.Collection;
import java.util.List;

public class TaskController {
    private TaskService taskService;
    private TagService tagService;

    public TaskController(TaskService taskService, TagService tagService) {
        this.taskService = taskService;
        this.tagService = tagService;
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

    public Task addTag(int taskId, String tagName) {
        Tag tag = tagService.createOrGetTag(tagName);
        Task task = taskService.searchTask(taskId);
        task.addTags(tag);

        return task;
    }

    public Task deleteTagFromTask(int taskId, String tagName) {
        Tag tag = tagService.createOrGetTag(tagName);
        return taskService.deleteTag(taskId, tag);
    }

    public Tag deleteTag(int tagId) {
        return tagService.deleteTag(tagId);
    }

    public List<Tag> getTagsByTask(int taskId) {
        return taskService.getTags(taskId);
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

    public Collection<Tag> listTags() {
        return tagService.getTags();
    }

    public Tag updateTag(int id, String newName) {
        return tagService.updateTag(id, newName);
    }

}
