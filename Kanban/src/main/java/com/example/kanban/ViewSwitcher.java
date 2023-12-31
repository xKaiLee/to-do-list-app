package com.example.kanban;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Map;

public class ViewSwitcher {
    private static Map<View, Parent> cache = new HashMap<>();
    private static Scene scene;
    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    //changing scenes
    public static void switchTo(View view){
        if(scene == null){
            System.out.println("No scene was set");
        }
        try {
            Parent root;
            if(cache.containsKey(view)){
                root = cache.get(view);
                //System.out.println("Loading from cache");
            }
            else {
                //System.out.println("Loading from FXML");
                root = FXMLLoader.load(ViewSwitcher.class.getResource(view.getFileName()));
                cache.put(view, root);
            }
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
