package com.example.javapruefunglearning;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gardener App");

        // Load the initial view (for example, the plant management view)
        showPlantView();
    }

    public void showPlantView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PlantView.fxml"));
            BorderPane plantView = loader.load();

            Scene scene = new Scene(plantView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Error loading plant view", "There was an error loading the plant view.");
        }
    }

    // Method to switch to PropertyView
    public void showPropertyView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("PropertyView.fxml"));
            BorderPane propertyView = loader.load();

            Scene scene = new Scene(propertyView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Error loading property view", "There was an error loading the property view.");
        }
    }

    // Method to switch to PlantMaintenanceView
    public void showPlantMaintenanceView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MaintenanceView.fxml"));
            BorderPane maintenanceView = loader.load();

            Scene scene = new Scene(maintenanceView);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showErrorDialog("Error loading maintenance view", "There was an error loading the maintenance view.");
        }
    }

    private void showErrorDialog(String title, String content) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}