package com.example.javapruefunglearning.Controller;

import com.example.javapruefunglearning.Model.Database;
import com.example.javapruefunglearning.Model.Plant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class PlantController {
    @FXML
    private TableView<Plant> plantTable;
    @FXML
    private TableColumn<Plant, Integer> idColumn;
    @FXML
    private TableColumn<Plant, String> typeColumn;
    @FXML
    private TableColumn<Plant, String> nameColumn;
    @FXML
    private TableColumn<Plant, Double> heightColumn;
    @FXML
    private TableColumn<Plant, Double> widthColumn;
    @FXML
    private TableColumn<Plant, Integer> intervalColumn;
    @FXML
    private TableColumn<Plant, String> imagePathColumn;

    @FXML
    private TextField typeField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField heightField;
    @FXML
    private TextField widthField;
    @FXML
    private TextField intervalField;
    @FXML
    private TextField imagePathField;

    private ObservableList<Plant> plantList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        heightColumn.setCellValueFactory(new PropertyValueFactory<>("height"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<>("width"));
        intervalColumn.setCellValueFactory(new PropertyValueFactory<>("interval"));
        imagePathColumn.setCellValueFactory(new PropertyValueFactory<>("imagePath"));

        plantTable.setItems(plantList);
        loadPlants();
    }

    private void loadPlants() {
        plantList.clear();
        plantList.addAll(Database.getAllPlants());
    }

    @FXML
    private void handleAddPlant() {
        Plant newPlant = new Plant();
        newPlant.setType(typeField.getText());
        newPlant.setName(nameField.getText());
        newPlant.setHeight(Double.parseDouble(heightField.getText()));
        newPlant.setWidth(Double.parseDouble(widthField.getText()));
        newPlant.setInterval(Integer.parseInt(intervalField.getText()));
        newPlant.setImagePath(imagePathField.getText());

        Database.addPlant(newPlant);
        plantList.add(newPlant);
        clearFields();
    }

    @FXML
    private void handleEditPlant() {
        Plant selectedPlant = plantTable.getSelectionModel().getSelectedItem();
        if (selectedPlant != null) {
            selectedPlant.setType(typeField.getText());
            selectedPlant.setName(nameField.getText());
            selectedPlant.setHeight(Double.parseDouble(heightField.getText()));
            selectedPlant.setWidth(Double.parseDouble(widthField.getText()));
            selectedPlant.setInterval(Integer.parseInt(intervalField.getText()));
            selectedPlant.setImagePath(imagePathField.getText());

            Database.updatePlant(selectedPlant);
            plantTable.refresh();
            clearFields();
        } else {
            showAlert("No Selection", "No Plant Selected", "Please select a plant to edit.");
        }
    }

    @FXML
    private void handleDeletePlant() {
        Plant selectedPlant = plantTable.getSelectionModel().getSelectedItem();
        if (selectedPlant != null) {
            Optional<ButtonType> result = showConfirmationDialog("Delete Plant", "Are you sure you want to delete this plant?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Database.deletePlant(selectedPlant.getId());
                plantList.remove(selectedPlant);
            }
        } else {
            showAlert("No Selection", "No Plant Selected", "Please select a plant to delete.");
        }
    }

    private void clearFields() {
        typeField.clear();
        nameField.clear();
        heightField.clear();
        widthField.clear();
        intervalField.clear();
        imagePathField.clear();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private Optional<ButtonType> showConfirmationDialog(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert.showAndWait();
    }
}
