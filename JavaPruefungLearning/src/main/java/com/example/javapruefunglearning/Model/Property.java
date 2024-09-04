package com.example.javapruefunglearning.Model;

public class Property {
    private int id;
    private String name;    // Name of the property owner
    private String street;  // Street address
    private String zip;     // Postal code
    private String city;    // City
    private String phone;   // Phone number

    public Property() {
    }

    public Property(String name, String street, String zip, String city, String phone) {
        this.name = name;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.phone = phone;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}