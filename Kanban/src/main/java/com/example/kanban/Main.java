package com.example.kanban;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.Normalizer;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        var scene = new Scene(new Pane());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setPrimaryStage(stage);
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MAIN);
        stage.setTitle("Kanban!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}