package view;

import controller.TaskController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Task;
import model.TaskPriority;
import model.TaskStatus;
import service.TagService;
import service.TaskService;

public class MainFX extends Application{
    private TaskController taskController = new TaskController(new TaskService(), new TagService());

    @Override
    public void start(Stage stage) {
        stage.setScene(showMenuScene(stage));
        stage.show();
    }

    private Scene showMenuScene(Stage stage) {
        Label lblMenu = new Label("Menu");
        Button btnManageTasks = new Button("Gerenciar tarefas");
        Button btnAddTag = new Button("Adicionar tag a uma tarefa");
        Button btnListTags = new Button("Listar tags criadas");
        Button btnChangeTag = new Button("Alterar uma tag");
        Button btnDeleteTag = new Button("Deletar uma tag");

        btnManageTasks.setOnAction(e -> stage.setScene(showTaskScene(stage)));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(lblMenu, btnManageTasks, btnAddTag, btnListTags, btnChangeTag, btnDeleteTag);
        return new Scene(layout, 400, 400);
    }

    public Scene showTaskScene(Stage stage) {
        Button btnCreateTask = new Button("Criar uma tarefa");
        Button btnListTasks = new Button("Listar tarefas criadas");
        Button btnChangeTaskStatus = new Button("Mudar o status de uma tarefa");
        Button btnChangeTask = new Button("Modificar uma tarefa");
        Button btnSearchTask = new Button("Procurar por tarefas");
        Button btnDeleteTask = new Button("Deletar uma tarefa");
        Button btnListTagFromTask = new Button("Mostrar as tags de uma tarefa");
        Button btnRemoveTagFromTask = new Button("Remover tag de uma tarefa");
        Button btnReturn = new Button("Voltar ao menu");

        btnCreateTask.setOnAction(e -> stage.setScene(showTaskCreation(stage)));
        btnListTasks.setOnAction(e -> showTaskListWindow());
        btnChangeTaskStatus.setOnAction(e -> stage.setScene(showChangeTaskStatus(stage)));
        btnChangeTask.setOnAction(e -> stage.setScene(showChangeTask(stage)));

        btnReturn.setOnAction(e -> stage.setScene(showMenuScene(stage)));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(btnCreateTask, btnListTasks, btnChangeTaskStatus, btnChangeTask, btnSearchTask, btnDeleteTask, btnListTagFromTask, btnRemoveTagFromTask, btnReturn);
        layout.setAlignment(Pos.CENTER);

        return new Scene(layout, 400, 400);
    }

    private Scene showTaskCreation(Stage stage) {

        Label lblTaskTitle = new Label("Titulo da Tarefa:");
        TextField txtTaskTitle = new TextField();
        Label lblTaskDescription = new Label("Digite a descrição da task");
        TextField txtTaskDescriptioin = new TextField();
        Label lblTaskpriority = new Label("Selecione a prioridade da tarefa:");

        ToggleGroup priorityGroup = new ToggleGroup();

        RadioButton rbLow = new RadioButton("Baixo");
        rbLow.setToggleGroup(priorityGroup);
        rbLow.setUserData(TaskPriority.LOW);
        rbLow.setSelected(true);

        RadioButton rbMedium = new RadioButton("Médio");
        rbMedium.setToggleGroup(priorityGroup);
        rbMedium.setUserData(TaskPriority.MEDIUM);

        RadioButton rbHigh = new RadioButton("Alta");
        rbHigh.setToggleGroup(priorityGroup);
        rbHigh.setUserData(TaskPriority.HIGH);

        RadioButton rbUrgent = new RadioButton("Urgente");
        rbUrgent.setToggleGroup(priorityGroup);
        rbUrgent.setUserData(TaskPriority.URGENT);

        Button btnCreateTask = new Button("Salvar");
        Button btnReturn = new Button("Voltar");
        HBox buttonsLine = new HBox(20);
        buttonsLine.getChildren().addAll(btnCreateTask, btnReturn);

        btnCreateTask.setOnAction(e -> {

            RadioButton selectedPriority = (RadioButton) priorityGroup.getSelectedToggle();

            if (selectedPriority != null && !txtTaskTitle.getText().isBlank() && !txtTaskDescriptioin.getText().isBlank()) {

                TaskPriority priority = (TaskPriority) selectedPriority.getUserData();

                taskController.createTask(txtTaskTitle.getText(), txtTaskDescriptioin.getText(), priority);

                txtTaskTitle.setText("");
                txtTaskDescriptioin.setText("");

                Alert mensage = new Alert(Alert.AlertType.INFORMATION);
                mensage.setTitle("Sucesso na criação");
                mensage.setHeaderText(null);
                mensage.setContentText("Tarefa criada com sucesso");
                mensage.show();
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Erro de validação");
                error.setContentText("Você deve preencher todos os campos");
                error.show();
            }
        });

        btnReturn.setOnAction(e -> stage.setScene(showTaskScene(stage)));

        VBox layout = new VBox(20);
        layout.getChildren().addAll(lblTaskTitle, txtTaskTitle,lblTaskDescription, txtTaskDescriptioin, lblTaskpriority, rbLow, rbMedium, rbHigh, rbUrgent, buttonsLine);
        layout.setAlignment(Pos.CENTER);
        return new Scene(layout, 400, 400);
    }

    public void showTaskListWindow() {
        Stage tableStage = new Stage();
        tableStage.setTitle("Lista de tarefas");

        Button btnRefresh = new Button("Atualizar tabela");

        TableView<Task> table = new TableView<>();

        TableColumn<Task, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colId.setMinWidth(100);

        TableColumn<Task, String> colTitle = new TableColumn<>("Titulo");
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colTitle.setMinWidth(100);

        TableColumn<Task, String> colDescription = new TableColumn<>("Descrição");
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

        TableColumn<Task, TaskStatus> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setMinWidth(100);

        TableColumn<Task, TaskPriority> colPriority = new TableColumn<>("Prioridade");
        colPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        colPriority.setMinWidth(100);

        table.getColumns().addAll(colId, colTitle, colDescription, colStatus, colPriority);

        table.setItems(taskController.listTasks());

        btnRefresh.setOnAction(e -> {
            table.setItems(taskController.listTasks());
        });

        VBox layout = new VBox(20, new Label("Tarefas atuais"), btnRefresh, table);
        layout.setPadding(new Insets(10));

        Scene scene = new Scene(layout, 600, 400);
        tableStage.setScene(scene);
        tableStage.show();
    }

    public Scene showChangeTaskStatus(Stage stage) {

        Label lblTaskId = new Label("Digite o id da tarefa que deseja mudar o status:");
        TextField txtTaskId = new TextField();

        ToggleGroup statusGroup = new ToggleGroup();

        RadioButton rbPendent = new RadioButton("Pendente");
        rbPendent.setToggleGroup(statusGroup);
        rbPendent.setUserData(TaskStatus.PENDING);
        rbPendent.setSelected(true);

        RadioButton rbInProgress = new RadioButton("Em progresso");
        rbInProgress.setToggleGroup(statusGroup);
        rbInProgress.setUserData(TaskStatus.IN_PROGRESS);

        RadioButton rbCompleted = new RadioButton("Completo");
        rbCompleted.setToggleGroup(statusGroup);
        rbCompleted.setUserData(TaskStatus.COMPLETE);

        Button btnConfirm = new Button("Comfirmar");
        Button btnReturn = new Button("Voltar");
        HBox buttonsLine = new HBox(20);
        buttonsLine.getChildren().addAll(btnConfirm, btnReturn);

        btnConfirm.setOnAction(e -> {
            RadioButton selectedStatus = (RadioButton) statusGroup.getSelectedToggle();

            if (txtTaskId.getText().isBlank()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("O id não pode ser nulo");
            } else {

                int taskId = Integer.parseInt(txtTaskId.getText());

                taskController.changeTaskStatus(taskId, (TaskStatus) selectedStatus.getUserData());

                Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                confirmation.setContentText("Status alterado com sucesso");
                confirmation.show();
            }
        });

        btnReturn.setOnAction(e -> stage.setScene(showTaskScene(stage)));

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(lblTaskId, txtTaskId, rbPendent, rbInProgress, rbCompleted, buttonsLine);

        return new Scene(layout, 400, 400);
    }

    public Scene showChangeTask(Stage stage) {

        Label lblId = new Label("Qual o id da tarefa?");
        TextField txtId = new TextField();
        Label lblNewTitle = new Label("Digite o novo título:");
        TextField txtNewTitle = new TextField();
        Label lblNewDescription = new Label("Digite a nova descrição");
        TextField txtNewDescription = new TextField();

        ToggleGroup priorityGroup = new ToggleGroup();

        RadioButton rbLow = new RadioButton("Baixo");
        rbLow.setToggleGroup(priorityGroup);
        rbLow.setUserData(TaskPriority.LOW);
        rbLow.setSelected(true);

        RadioButton rbMedium = new RadioButton("Médio");
        rbMedium.setToggleGroup(priorityGroup);
        rbMedium.setUserData(TaskPriority.MEDIUM);

        RadioButton rbHigh = new RadioButton("Alta");
        rbHigh.setToggleGroup(priorityGroup);
        rbHigh.setUserData(TaskPriority.HIGH);

        RadioButton rbUrgent = new RadioButton("Urgente");
        rbUrgent.setToggleGroup(priorityGroup);
        rbUrgent.setUserData(TaskPriority.URGENT);

        Button btnChangeTask = new Button("Salvar");
        Button btnReturn = new Button("Voltar");
        HBox buttonsLine = new HBox(20);
        buttonsLine.getChildren().addAll(btnChangeTask, btnReturn);

        btnChangeTask.setOnAction(e -> {

            if (!txtId.getText().isBlank() && !txtNewTitle.getText().isBlank() && !txtNewDescription.getText().isBlank()) {

                try {
                    RadioButton selectedPriority = (RadioButton) priorityGroup.getSelectedToggle();
                    int taskId = Integer.parseInt(txtId.getText());
                    taskController.modifyTask(taskId, txtNewTitle.getText(), txtNewDescription.getText(), (TaskPriority) selectedPriority.getUserData());

                    Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmation.setHeaderText(null);
                    confirmation.setContentText("Tarefa alterada com sucesso");
                    confirmation.show();
                } catch (NumberFormatException ex) {
                    System.out.println(ex.getMessage());
                } catch (IllegalArgumentException ex) {
                    System.out.println(ex.getMessage());
                }
            }

        });

        btnReturn.setOnAction(e -> stage.setScene(showTaskScene(stage)));

        VBox layout = new VBox(20);
        layout .setAlignment(Pos.CENTER);
        layout.getChildren().addAll(lblId, txtId, lblNewTitle, txtNewTitle, lblNewDescription, txtNewDescription, rbLow, rbMedium, rbHigh, rbUrgent, buttonsLine);

        return new Scene(layout, 400, 400);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
