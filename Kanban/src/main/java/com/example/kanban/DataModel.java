package com.example.kanban;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//storing data for saving
public class DataModel implements Serializable {
    public static List<Task> toDoList = new ArrayList<>();
    public static List<Task> inProgressList = new ArrayList<>();
    public static List<Task> doneList = new ArrayList<>();

}
