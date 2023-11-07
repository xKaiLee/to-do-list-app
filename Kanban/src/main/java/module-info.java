module com.example.kanban {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.kanban to javafx.fxml;
    exports com.example.kanban;
}