package view;

import controller.TaskController;
import model.Task;

import java.util.Collection;
import java.util.Scanner;

public class TaskView {
    private Scanner scanner;
    private TaskController taskController;

    public TaskView(TaskController taskController) {
        this.scanner = new Scanner(System.in);
        this.taskController = taskController;
    }

    public void taskMenu() {
        boolean run = true;

        while (run) {
            System.out.println("Escolha uma das opções abaixo");
            System.out.println("[1] Criar uma tarefa");
            System.out.println("[2] Listar tarefas criadas");
            System.out.println("[3] Mudar o status de uma tarefa");
            System.out.println("[4] Modificar uma tarefa");
            System.out.println("[5] Procurar por tarefas");
            System.out.println("[6] Apagar uma tarefa");
            System.out.println("[7] Mostrar as tags de uma tarefa");
            System.out.println("[8] Remover tag de uma tarefa");
            System.out.println("[0] Voltar");

            int choice = readInt();

            switch (choice) {
                case 0 -> run = false;
                case 1 -> createTask();
                case 2 -> listTask();
                case 3 -> changeTaskStatus();
                case 4 -> modifyTask();
                case 5 -> searchTasks();
                case 6 -> deleteTask();
                case 7 -> showTag();
                case 8 -> deleteTagFromTask();
                default -> throw new IllegalArgumentException("Invalid choice");
            }


        }
    }

    private void createTask() {
        System.out.println("Digite o Titulo da tarefa:");
        String title = scanner.nextLine();
        System.out.println("Digite a descrição da tarefa:");
        String description = scanner.nextLine();

        int choicePriority = askAboutPriority();

        System.out.println(
                taskController.createTask(title, description, choicePriority)
        );
    }

    private void listTask() {
        Collection<Task> tasks = taskController.listTasks();

        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void changeTaskStatus() {
        System.out.println("Digite o ID da tarefa que deseja alterar:");
        int id = scanner.nextInt();
        scanner.nextLine();

        int choiceStatus = askAboutStatus();

        System.out.println(
                taskController.changeTaskStatus(id, choiceStatus)
        );
    }

    private void modifyTask() {
        System.out.println("Digite o id da tarefa:");
        int id = readInt();
        System.out.println("Digite o novo título:");
        String newTitle = scanner.nextLine();
        System.out.println("Digite a nova descrição");
        String newDescription = scanner.nextLine();

        int choicePriority = askAboutPriority();

        System.out.println(
                taskController.modifyTask(id, newTitle, newDescription, choicePriority)
        );
    }

    private void searchTasks() {
        System.out.println("Qual método do busca você deseja usar?");
        System.out.println("[1] Busca por ID");
        System.out.println("[2] Busca por Título");
        System.out.println("[3] Busca por Status");
        System.out.println("[4] Busca por Prioridade");
        int choice = readInt();

        switch (choice) {
            case 1 -> {
                System.out.println("Digite o ID da tarefa");
                int id = readInt();
                System.out.println(
                        taskController.searchTask(id)
                );
            }
            case 2 -> {
                System.out.println("Digite o título da tarefa");
                String title = scanner.nextLine();
                System.out.println(
                        taskController.searchTaskByTitle(title)
                );
            }
            case 3 -> {
                int choiceStatus = askAboutStatus();

                for (Task task : taskController.searchTaskByStatus(choiceStatus)) {
                    System.out.println(task);
                }
            }
            case 4 -> {
                int priority = askAboutPriority();
                for (Task task : taskController.searchTaskByPriority(priority)) {
                    System.out.println(task);
                }
            }
            default -> throw new IllegalArgumentException("invalid choice");
        }
    }

    private void deleteTask() {
        System.out.println("Digite o id da tarefa:");
        int id = readInt();

        taskController.deleteTask(id);
        System.out.println("Tarefa apagada com sucesso");
    }

    private void showTag() {
        System.out.println("Digite o id da task");
        int taskId = readInt();

        System.out.println(taskController.getTagsByTask(taskId));
    }

    private void deleteTagFromTask() {
        System.out.println("Digite o id da task");
        int taskId = readInt();
        System.out.println("Digite o nome da tag");
        String tagName = scanner.nextLine();

        System.out.println(taskController.deleteTagFromTask(taskId, tagName));
    }

    private int askAboutPriority() {
        System.out.println("Qual vai ser a nova prioridade?");
        System.out.println("[1] Baixo");
        System.out.println("[2] Médio");
        System.out.println("[3] Alto");
        System.out.println("[4] Urgente");

        return readInt();
    }

    private int askAboutStatus() {
        System.out.println("Digite o Status da tarefa");
        System.out.println("[1] Pendente");
        System.out.println("[2] Em progresso");
        System.out.println("[3] Concluída");

        return readInt();
    }

    private int readInt() {
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }
}
