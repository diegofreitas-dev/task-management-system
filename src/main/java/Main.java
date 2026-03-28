import controller.TaskController;
import service.TagService;
import service.TaskService;
import view.AppView;
import view.TaskView;

public class Main {
    public static void main(String[] args) {
        TaskController taskController = new TaskController(new TaskService(), new TagService());
        TaskView taskView = new TaskView(taskController);
        AppView view = new AppView(taskController, taskView);

        view.run();
    }
}
