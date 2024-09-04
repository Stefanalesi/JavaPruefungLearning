module com.example.javapruefunglearning {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.javapruefunglearning to javafx.fxml;
    exports com.example.javapruefunglearning;
}