package com.example.kanban;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;

public class FormController{
    private Parent root;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField titleTextField;
    @FXML
    private TextArea descTextArea;
    @FXML
    private DatePicker expDatePicker;
    @FXML
    private ComboBox<TaskPriority> priorityBox = new ComboBox<>();
    @FXML
    private LocalDate myDate;
    private TaskPriority taskPriority;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);


    public void handleClose(ActionEvent event) throws IOException {
        ViewSwitcher.switchTo(View.MAIN);
    }

    public void getDate(ActionEvent event) throws IOException {
        addPriority();
        myDate = expDatePicker.getValue();
    }
    public void addPriority(){
        if(priorityBox.getItems().isEmpty()){
            priorityBox.getItems().add(TaskPriority.LOW);
            priorityBox.getItems().add(TaskPriority.MEDIUM);
            priorityBox.getItems().add(TaskPriority.HIGH);
        }
    }
    public void getPriority(ActionEvent event) throws IOException{

        taskPriority = priorityBox.getValue();
    }

    //checks the requirements for adding a task
    public boolean checkData(){
        alert.setTitle("Error");
        alert.setHeaderText(null);
        for (Task task : DataModel.toDoList) {
            if(titleTextField.getText().equals(task.getTitle())){
                alert.setContentText("This title already exists");
                alert.showAndWait();
                return false;
            }
        }
        if (titleTextField.getText().length() <= 3 && titleTextField.getText().length() >= 20) {
            alert.setContentText("Title length must be between 3 and 20 characters");
            alert.showAndWait();
            return false;
        } else if(myDate == null){
            alert.setContentText("You have to select date");
            alert.showAndWait();
            return false;
        } else if(taskPriority == null){
            alert.setContentText("You have to choose priority");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void handleSave(){
        {
            if(checkData()){
            DataModel.toDoList.add(new Task(titleTextField.getText(), myDate, taskPriority, descTextArea.getText()));
            clearFields();
            ViewSwitcher.switchTo(View.MAIN);
            }
        }
    }

    public void clearFields(){
        titleTextField.setText("");
        expDatePicker.setValue(null);
        descTextArea.setText("");
        priorityBox.setValue(null);
    }
}
