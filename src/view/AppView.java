package view;

import model.Task;
import model.TaskStatus;
import service.TaskService;

import java.util.Collection;
import java.util.Scanner;

public class AppView {
    private Scanner scanner;
    private TaskService taskService;

    public AppView() {
        this.scanner = new Scanner(System.in);
        this.taskService = new TaskService();
    }

    public void run() {
        boolean run = true;

        while (run) {
            System.out.println("Escolha uma das opções abaixo");
            System.out.println("[1] Criar uma tarefa");
            System.out.println("[2] Listar tarefas criadas");
            System.out.println("[3] Mudar o status de uma tarefa");
            System.out.println("[4] Modificar uma tarefa");
            System.out.println("[5] Apagar uma tarefa");

            int choice = readInt();

            switch(choice) {
                case 1 -> createTask();
                case 2 -> listTask();
                case 3 -> changeTaskStatus();
                case 4 -> modifyTask();
                case 5 -> deleteTask();
            }
        }
    }

    private void createTask() {
        System.out.println("Digite o Titulo da tarefa:");
        String title = scanner.nextLine();
        System.out.println("Digite a descrição da tarefa:");
        String description = scanner.nextLine();

        System.out.println(
                taskService.createTask(title, description)
        );
    }

    private void listTask() {
        Collection<Task> tasks = taskService.listTasks();

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void changeTaskStatus() {
        System.out.println("Digite o ID da tarefa que deseja alterar:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Qual o novo status da tarefa?");
        System.out.println("[1] Pendente");
        System.out.println("[2] Em progresso");
        System.out.println("[3] Concluída");

        int choiceStatus = readInt();

        System.out.println(
                taskService.changeTaskStatus(id, TaskStatus.fromOption(choiceStatus))
        );
    }

    private void modifyTask() {
        System.out.println("Digite o id da tarefa:");
        int id = readInt();
        System.out.println("Digite o novo título:");
        String newTitle = scanner.nextLine();
        System.out.println("Digite a nova descrição");
        String newDescription = scanner.nextLine();

        System.out.println(
                taskService.modifyTask(id, newTitle, newDescription)
        );
    }

    private void deleteTask() {
        System.out.println("Digite o id da tarefa:");
        int id = readInt();

        taskService.deleteTask(id);
        System.out.println("Tarefa apagada com sucesso");
    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
