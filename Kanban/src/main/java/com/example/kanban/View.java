package com.example.kanban;

public enum View {
    MAIN("MainView.fxml"),
    EDIT("EditTaskView.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName(){
        return fileName;
    }
}
