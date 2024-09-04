package com.example.javapruefunglearning.Controller;

import com.example.javapruefunglearning.Model.Database;
import com.example.javapruefunglearning.Model.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Optional;

public class PropertyController {
    @FXML
    private TableView<Property> propertyTable;
    @FXML
    private TableColumn<Property, Integer> idColumn;
    @FXML
    private TableColumn<Property, String> nameColumn;
    @FXML
    private TableColumn<Property, String> streetColumn;
    @FXML
    private TableColumn<Property, String> zipColumn;
    @FXML
    private TableColumn<Property, String> cityColumn;
    @FXML
    private TableColumn<Property, String> phoneColumn;

    @FXML
    private TextField nameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField zipField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField phoneField;

    private ObservableList<Property> propertyList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        zipColumn.setCellValueFactory(new PropertyValueFactory<>("zip"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        propertyTable.setItems(propertyList);
        loadProperties();
    }

    private void loadProperties() {
        propertyList.clear();
        propertyList.addAll(Database.getAllProperties());
    }

    @FXML
    private void handleAddProperty() {
        Property newProperty = new Property();
        newProperty.setName(nameField.getText());
        newProperty.setStreet(streetField.getText());
        newProperty.setZip(zipField.getText());
        newProperty.setCity(cityField.getText());
        newProperty.setPhone(phoneField.getText());

        Database.addProperty(newProperty);
        propertyList.add(newProperty);
        clearFields();
    }

    @FXML
    private void handleEditProperty() {
        Property selectedProperty = propertyTable.getSelectionModel().getSelectedItem();
        if (selectedProperty != null) {
            selectedProperty.setName(nameField.getText());
            selectedProperty.setStreet(streetField.getText());
            selectedProperty.setZip(zipField.getText());
            selectedProperty.setCity(cityField.getText());
            selectedProperty.setPhone(phoneField.getText());

            Database.updateProperty(selectedProperty);
            propertyTable.refresh();
            clearFields();
        } else {
            showAlert("No Selection", "No Property Selected", "Please select a property to edit.");
        }
    }

    @FXML
    private void handleDeleteProperty() {
        Property selectedProperty = propertyTable.getSelectionModel().getSelectedItem();
        if (selectedProperty != null) {
            Optional<ButtonType> result = showConfirmationDialog("Delete Property", "Are you sure you want to delete this property?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Database.deleteProperty(selectedProperty.getId());
                propertyList.remove(selectedProperty);
            }
        } else {
            showAlert("No Selection", "No Property Selected", "Please select a property to delete.");
        }
    }

    private void clearFields() {
        nameField.clear();
        streetField.clear();
        zipField.clear();
        cityField.clear();
        phoneField.clear();
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
