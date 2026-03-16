import controller.TaskController;
import service.TaskService;
import view.AppView;

public class Main {
    public static void main(String[] args) {
        TaskController taskController = new TaskController(new TaskService());

        AppView view = new AppView(taskController);

        view.run();
    }
}
