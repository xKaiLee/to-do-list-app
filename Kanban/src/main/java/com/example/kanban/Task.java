package com.example.kanban;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String title;
    private LocalDate date;
    private TaskPriority taskPriority;
    private String description;
    public Task(String name, LocalDate date, TaskPriority taskPriority, String description){
        this.title = name;
        this.date = date;
        this.taskPriority = taskPriority;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\nExpiration date: " + date + "\nPriority: " + taskPriority + "\nDescripction: " + description + "\n";
    }
}
