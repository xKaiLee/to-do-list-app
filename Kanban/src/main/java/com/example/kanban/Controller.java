package com.example.kanban;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    @FXML
    private MenuBar closeMenu;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Button newTaskButton;
    @FXML
    public ListView<String> toDoListView;
    @FXML
    public ListView<String> inProgressListView;
    @FXML
    public ListView<String> doneListView;
    @FXML
    public TextField textField;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Stage primaryStage;
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    @FXML
    private void showForm(ActionEvent event) throws IOException {
        ViewSwitcher.switchTo(View.EDIT);
    }
    @FXML
    private void handleClose() {
        Stage stage = (Stage) closeMenu.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.setContentText("This application was created by Dawid Przytuła.");
        alert.showAndWait();
    }
    @FXML
    private void removeTask() {
        String selectedTaskToDo = toDoListView.getSelectionModel().getSelectedItem();
        String selectedTaskInProgress = inProgressListView.getSelectionModel().getSelectedItem();
        String selectedTaskDone = doneListView.getSelectionModel().getSelectedItem();
        List<Task> tasksToRemove = new ArrayList<>();
        if(!(selectedTaskToDo == null)){
            for (Task task : DataModel.toDoList) {
                if (selectedTaskToDo.equals(task.getTitle())) {
                    tasksToRemove.add(task);
                }
            }
            for (Task task : tasksToRemove) {
                toDoListView.getItems().remove(task.getTitle());
                DataModel.toDoList.remove(task);
            }
            tasksToRemove.clear();
        }
        if(!(selectedTaskInProgress == null)){
            for (Task task : DataModel.inProgressList) {
                if (selectedTaskInProgress.equals(task.getTitle())) {
                    tasksToRemove.add(task);
                }
            }
            for (Task task : tasksToRemove) {
                inProgressListView.getItems().remove(task.getTitle());
                DataModel.inProgressList.remove(task);
            }
            tasksToRemove.clear();

        }
        if(!(selectedTaskDone == null)){
            for (Task task : DataModel.doneList) {
                if (selectedTaskDone.equals(task.getTitle())) {
                    tasksToRemove.add(task);
                }
            }
            for (Task task : tasksToRemove) {
                doneListView.getItems().remove(task.getTitle());
                DataModel.doneList.remove(task);
            }
            tasksToRemove.clear();
        }

    }

    //adds tasks to individual listViews
    @FXML
    private void handleRefresh() {
        toDoListView.getItems().removeAll();
        doneListView.getItems().removeAll();
        inProgressListView.getItems().removeAll();
        for (Task task : DataModel.toDoList) {
            if (!toDoListView.getItems().contains(task.getTitle())) {
                toDoListView.getItems().add(task.getTitle());
            }
        }
        for (Task task : DataModel.doneList) {
            if (!doneListView.getItems().contains(task.getTitle())) {
                doneListView.getItems().add(task.getTitle());
            }
        }
        for (Task task : DataModel.inProgressList) {
            if (!inProgressListView.getItems().contains(task.getTitle())) {
                inProgressListView.getItems().add(task.getTitle());
            }
        }

    }



    @FXML
    private void showTask() throws IOException {
        toDoListView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                alert.setTitle("Information about task");
                alert.setHeaderText("Task");
                String selectedTask = toDoListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedTask);
                for (Task task : DataModel.toDoList) {
                        if(selectedTask == task.getTitle()){
                            alert.setContentText(task.toString());
                            alert.showAndWait();
                        }
                    }
            }
        });
        inProgressListView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                alert.setTitle("Information about task");
                alert.setHeaderText("Task");
                String selectedTask = inProgressListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedTask);
                for (Task task : DataModel.inProgressList) {
                    if(selectedTask == task.getTitle()){
                        alert.setContentText(task.toString());
                        alert.showAndWait();
                    }
                }
            }
        });
        doneListView.setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                alert.setTitle("Information about task");
                alert.setHeaderText("Task");
                String selectedTask = doneListView.getSelectionModel().getSelectedItem();
                System.out.println(selectedTask);
                for (Task task : DataModel.doneList) {
                    if(selectedTask == task.getTitle()){
                        alert.setContentText(task.toString());
                        alert.showAndWait();
                    }
                }
            }
        });
     }

    @FXML
    private void onDragDetected() {
        toDoListView.setOnDragDetected(event -> {
            Dragboard dragboard = toDoListView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(toDoListView.getSelectionModel().getSelectedItem());
            dragboard.setContent(content);

        });
        inProgressListView.setOnDragDetected(event -> {
            Dragboard dragboard = inProgressListView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(inProgressListView.getSelectionModel().getSelectedItem());
            dragboard.setContent(content);
        });
        doneListView.setOnDragDetected(event -> {
            Dragboard dragboard = doneListView.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();
            content.putString(doneListView.getSelectionModel().getSelectedItem());
            dragboard.setContent(content);

        });
    }

    @FXML
    private void onDragOver(){
        inProgressListView.setOnDragOver(event -> {
            if(event.getGestureSource() != inProgressListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });
        doneListView.setOnDragOver(event -> {
            if(event.getGestureSource() != doneListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });
        toDoListView.setOnDragOver(event -> {
            if(event.getGestureSource() != toDoListView && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
        });
    }
    @FXML
    private void onDragDropped() {
        inProgressListView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasString()) {
                inProgressListView.getItems().add(db.getString());
                for (Task task : DataModel.toDoList){
                    if (task.getTitle().equals(db.getString())){
                        DataModel.inProgressList.add(task);
                        DataModel.toDoList.remove(task);
                        toDoListView.getItems().remove(db.getString());
                    }
                }
                for (Task task : DataModel.doneList){
                    if (task.getTitle().equals(db.getString())){
                        DataModel.inProgressList.add(task);
                        DataModel.doneList.remove(task);
                        doneListView.getItems().remove(db.getString());
                    }
                }
                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
        toDoListView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasString()) {
                toDoListView.getItems().add(db.getString());
                for (Task task : DataModel.inProgressList){
                    if (task.getTitle().equals(db.getString())){
                        DataModel.toDoList.add(task);
                        DataModel.inProgressList.remove(task);
                        inProgressListView.getItems().remove(db.getString());
                    }
                }
                for (Task task : DataModel.doneList){
                    if (task.getTitle().equals(db.getString())){
                        DataModel.toDoList.add(task);
                        DataModel.doneList.remove(task);
                        doneListView.getItems().remove(db.getString());
                    }
                }
                success = true;
            }
            System.out.println("udalo siee");
            event.setDropCompleted(success);
            event.consume();

        });
        doneListView.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if(db.hasString()) {
                doneListView.getItems().add(db.getString());
                for (Task task : DataModel.toDoList){
                    if (task.getTitle().equals(db.getString())){
                        DataModel.doneList.add(task);
                        DataModel.toDoList.remove(task);
                        toDoListView.getItems().remove(db.getString());
                    }
                }
                for (Task task : DataModel.inProgressList){
                    if (task.getTitle().equals(db.getString())){
                        DataModel.doneList.add(task);
                        DataModel.inProgressList.remove(task);
                        inProgressListView.getItems().remove(db.getString());
                    }
                }
                success = true;

            }
            System.out.println("udalo siee");
            event.setDropCompleted(success);
            event.consume();
        });
    }
    @FXML
    private void readDataFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Task List Files", "*.dat"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(selectedFile))) {
                DataModel.toDoList = (List<Task>) ois.readObject();
                DataModel.inProgressList = (List<Task>) ois.readObject();
                DataModel.doneList = (List<Task>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        handleRefresh();
    }

    @FXML
    private void saveDataToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Task List Files", "*.dat"));
        File selectedFile = fileChooser.showSaveDialog(primaryStage);
        if (selectedFile != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(selectedFile))) {
                oos.writeObject(DataModel.toDoList);
                oos.writeObject(DataModel.inProgressList);
                oos.writeObject(DataModel.doneList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


