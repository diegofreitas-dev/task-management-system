package view;

import controller.TaskController;
import model.Tag;

import java.util.Collection;
import java.util.Scanner;

public class AppView {
    private final Scanner scanner;
    TaskView taskView;
    private final TaskController taskController;

    public AppView(TaskController taskController, TaskView taskView) {
        this.scanner = new Scanner(System.in);
        this.taskController = taskController;
        this.taskView = taskView;
    }

    public void run() {
        boolean run = true;

        while (run) {
            System.out.println("Escolha uma das opções abaixo");
            System.out.println("[1] Gerenciar Tarefas");
            System.out.println("[2] adicionar tag a uma tarefa");
            System.out.println("[3] Listar tags criadas");
            System.out.println("[4] Alterar uma tag");
            System.out.println("[5] Apagar uma tag");
            System.out.println("[0] Encerrar programa");

            int choice = readInt();

            switch(choice) {
                case 0 -> run = false;
                case 1 -> taskView.taskMenu();
                case 2 -> createTag();
                case 3 -> listTags();
                case 4 -> updateTag();
                default -> throw new IllegalArgumentException("invalid choice");
            }
        }
    }

    private void createTag() {
        System.out.println("Qual o nome da tag:");
        String tagName = scanner.nextLine();
        System.out.println("Qual o id da task que deseja atribuir?");
        int taskId = readInt();
        System.out.println(taskController.addTag(taskId, tagName));
    }

    private void listTags() {
        Collection<Tag> tags = taskController.listTags();

        for (Tag tag : tags) {
            System.out.println(tag);
        }
    }

    private void updateTag() {
        System.out.println("digite o id da task que você deseja alterar");
        int tagId = readInt();
        System.out.println("Qual o novo nome que você deseja colocar:");
        String newName = scanner.nextLine();

        System.out.println(taskController.updateTag(tagId, newName));
    }

    private void deleteTag() {
        System.out.println("Digite o ID da tag:");
        int tagId = readInt();

        System.out.println(taskController.deleteTag(tagId));
    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
