package com.example.javapruefunglearning.Controller;

import com.example.javapruefunglearning.Model.Database;
import com.example.javapruefunglearning.Model.Maintenance;
import com.example.javapruefunglearning.Model.Plant;
import com.example.javapruefunglearning.Model.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.Optional;

public class MaintenanceController {
    @FXML
    private TableView<Maintenance> maintenanceTable;
    @FXML
    private TableColumn<Maintenance, Integer> idColumn;
    @FXML
    private TableColumn<Maintenance, Property> propertyColumn;
    @FXML
    private TableColumn<Maintenance, Plant> plantColumn;
    @FXML
    private TableColumn<Maintenance, Integer> quantityColumn;
    @FXML
    private TableColumn<Maintenance, LocalDate> lastMaintenanceColumn;
    @FXML
    private TableColumn<Maintenance, LocalDate> nextMaintenanceColumn;

    @FXML
    private ComboBox<Property> propertyComboBox;
    @FXML
    private ComboBox<Plant> plantComboBox;
    @FXML
    private TextField quantityField;
    @FXML
    private DatePicker lastMaintenanceDatePicker;
    @FXML
    private DatePicker nextMaintenanceDatePicker;

    private ObservableList<Maintenance> maintenanceList = FXCollections.observableArrayList();
    private ObservableList<Property> propertyList = FXCollections.observableArrayList();
    private ObservableList<Plant> plantList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        propertyColumn.setCellValueFactory(new PropertyValueFactory<>("property"));
        plantColumn.setCellValueFactory(new PropertyValueFactory<>("plant"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        lastMaintenanceColumn.setCellValueFactory(new PropertyValueFactory<>("lastMaintenance"));
        nextMaintenanceColumn.setCellValueFactory(new PropertyValueFactory<>("nextMaintenance"));

        maintenanceTable.setItems(maintenanceList);
        loadPlantMaintenance();
        loadProperties();
        loadPlants();
    }

    private void loadPlantMaintenance() {
        maintenanceList.clear();
        maintenanceList.addAll(Database.getAllPlantMaintenance());
    }

    private void loadProperties() {
        propertyList.clear();
        propertyList.addAll(Database.getAllProperties());
        propertyComboBox.setItems(propertyList);
    }

    private void loadPlants() {
        plantList.clear();
        plantList.addAll(Database.getAllPlants());
        plantComboBox.setItems(plantList);
    }

    @FXML
    private void handleAddMaintenance() {
        if (propertyComboBox.getValue() != null && plantComboBox.getValue() != null) {
            Maintenance newMaintenance = new Maintenance();
            newMaintenance.setProperty(propertyComboBox.getValue());
            newMaintenance.setPlant(plantComboBox.getValue());
            newMaintenance.setQuantity(Integer.parseInt(quantityField.getText()));
            newMaintenance.setLastMaintenance(lastMaintenanceDatePicker.getValue());
            newMaintenance.setNextMaintenance(nextMaintenanceDatePicker.getValue());

            Database.addPlantMaintenance(newMaintenance);
            maintenanceList.add(newMaintenance);
            clearFields();
        } else {
            showAlert("Incomplete Information", "Missing Property or Plant", "Please select a property and plant.");
        }
    }

    @FXML
    private void handleEditMaintenance() {
        Maintenance selectedMaintenance = maintenanceTable.getSelectionModel().getSelectedItem();
        if (selectedMaintenance != null) {
            selectedMaintenance.setProperty(propertyComboBox.getValue());
            selectedMaintenance.setPlant(plantComboBox.getValue());
            selectedMaintenance.setQuantity(Integer.parseInt(quantityField.getText()));
            selectedMaintenance.setLastMaintenance(lastMaintenanceDatePicker.getValue());
            selectedMaintenance.setNextMaintenance(nextMaintenanceDatePicker.getValue());

            Database.updatePlantMaintenance(selectedMaintenance);
            maintenanceTable.refresh();
            clearFields();
        } else {
            showAlert("No Selection", "No Maintenance Selected", "Please select maintenance to edit.");
        }
    }

    @FXML
    private void handleDeleteMaintenance() {
        Maintenance selectedMaintenance = maintenanceTable.getSelectionModel().getSelectedItem();
        if (selectedMaintenance != null) {
            Optional<ButtonType> result = showConfirmationDialog("Delete Maintenance", "Are you sure you want to delete this maintenance?");
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Database.deletePlantMaintenance(selectedMaintenance.getId());
                maintenanceList.remove(selectedMaintenance);
            }
        } else {
            showAlert("No Selection", "No Maintenance Selected", "Please select maintenance to delete.");
        }
    }

    private void clearFields() {
        propertyComboBox.setValue(null);
        plantComboBox.setValue(null);
        quantityField.clear();
        lastMaintenanceDatePicker.setValue(null);
        nextMaintenanceDatePicker.setValue(null);
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
