module com.example.diploms {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.diploms to javafx.fxml;
    exports com.example.diploms;
}