package com.example.javapruefunglearning.Model;

import javafx.beans.property.*;

public class Plant {
    private final IntegerProperty id;
    private final StringProperty type;      // Type of plant (e.g., Baum, Strauch)
    private final StringProperty name;      // Name of the plant
    private final DoubleProperty height;    // Typical height in meters
    private final DoubleProperty width;     // Typical width in meters
    private final IntegerProperty interval; // Maintenance interval in days
    private final StringProperty imagePath; // Path to the plant image (optional)

    public Plant() {
        this.id = new SimpleIntegerProperty();
        this.type = new SimpleStringProperty();
        this.name = new SimpleStringProperty();
        this.height = new SimpleDoubleProperty();
        this.width = new SimpleDoubleProperty();
        this.interval = new SimpleIntegerProperty();
        this.imagePath = new SimpleStringProperty();
    }

    public Plant(String type, String name, double height, double width, int interval, String imagePath) {
        this();
        setType(type);
        setName(name);
        setHeight(height);
        setWidth(width);
        setInterval(interval);
        setImagePath(imagePath);
    }

    // Getters and setters for id
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    // Getters and setters for type
    public String getType() {
        return type.get();
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public StringProperty typeProperty() {
        return type;
    }

    // Getters and setters for name
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    // Getters and setters for height
    public double getHeight() {
        return height.get();
    }

    public void setHeight(double height) {
        this.height.set(height);
    }

    public DoubleProperty heightProperty() {
        return height;
    }

    // Getters and setters for width
    public double getWidth() {
        return width.get();
    }

    public void setWidth(double width) {
        this.width.set(width);
    }

    public DoubleProperty widthProperty() {
        return width;
    }

    // Getters and setters for interval
    public int getInterval() {
        return interval.get();
    }

    public void setInterval(int interval) {
        this.interval.set(interval);
    }

    public IntegerProperty intervalProperty() {
        return interval;
    }

    // Getters and setters for imagePath
    public String getImagePath() {
        return imagePath.get();
    }

    public void setImagePath(String imagePath) {
        this.imagePath.set(imagePath);
    }

    public StringProperty imagePathProperty() {
        return imagePath;
    }
}