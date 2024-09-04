package com.example.javapruefunglearning.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:h2:./database/plantDB";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Plant Methods
    public static void addPlant(Plant plant) {
        String sql = "INSERT INTO plants (type, name, height, width, interval, imagePath) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, plant.getType());
            pstmt.setString(2, plant.getName());
            pstmt.setDouble(3, plant.getHeight());
            pstmt.setDouble(4, plant.getWidth());
            pstmt.setInt(5, plant.getInterval());
            pstmt.setString(6, plant.getImagePath());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                plant.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePlant(Plant plant) {
        String sql = "UPDATE plants SET type = ?, name = ?, height = ?, width = ?, interval = ?, imagePath = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, plant.getType());
            pstmt.setString(2, plant.getName());
            pstmt.setDouble(3, plant.getHeight());
            pstmt.setDouble(4, plant.getWidth());
            pstmt.setInt(5, plant.getInterval());
            pstmt.setString(6, plant.getImagePath());
            pstmt.setInt(7, plant.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlant(int plantId) {
        String sql = "DELETE FROM plants WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, plantId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Plant> getAllPlants() {
        List<Plant> plants = new ArrayList<>();
        String sql = "SELECT * FROM plants";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Plant plant = new Plant();
                plant.setId(rs.getInt("id"));
                plant.setType(rs.getString("type"));
                plant.setName(rs.getString("name"));
                plant.setHeight(rs.getDouble("height"));
                plant.setWidth(rs.getDouble("width"));
                plant.setInterval(rs.getInt("interval"));
                plant.setImagePath(rs.getString("imagePath"));
                plants.add(plant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plants;
    }

    // Property Methods
    public static void addProperty(Property property) {
        String sql = "INSERT INTO properties (name, street, zip, city, phone) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, property.getName());
            pstmt.setString(2, property.getStreet());
            pstmt.setString(3, property.getZip());
            pstmt.setString(4, property.getCity());
            pstmt.setString(5, property.getPhone());
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                property.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateProperty(Property property) {
        String sql = "UPDATE properties SET name = ?, street = ?, zip = ?, city = ?, phone = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, property.getName());
            pstmt.setString(2, property.getStreet());
            pstmt.setString(3, property.getZip());
            pstmt.setString(4, property.getCity());
            pstmt.setString(5, property.getPhone());
            pstmt.setInt(6, property.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProperty(int propertyId) {
        String sql = "DELETE FROM properties WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, propertyId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Property> getAllProperties() {
        List<Property> properties = new ArrayList<>();
        String sql = "SELECT * FROM properties";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                property.setStreet(rs.getString("street"));
                property.setZip(rs.getString("zip"));
                property.setCity(rs.getString("city"));
                property.setPhone(rs.getString("phone"));
                properties.add(property);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return properties;
    }

    // Plant Maintenance Methods
    public static void addPlantMaintenance(Maintenance maintenance) {
        String sql = "INSERT INTO plant_maintenance (property_id, plant_id, quantity, last_maintenance, next_maintenance) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, maintenance.getProperty().getId());
            pstmt.setInt(2, maintenance.getPlant().getId());
            pstmt.setInt(3, maintenance.getQuantity());
            pstmt.setDate(4, Date.valueOf(maintenance.getLastMaintenance()));
            pstmt.setDate(5, Date.valueOf(maintenance.getNextMaintenance()));
            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                maintenance.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePlantMaintenance(Maintenance maintenance) {
        String sql = "UPDATE plant_maintenance SET property_id = ?, plant_id = ?, quantity = ?, last_maintenance = ?, next_maintenance = ? WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maintenance.getProperty().getId());
            pstmt.setInt(2, maintenance.getPlant().getId());
            pstmt.setInt(3, maintenance.getQuantity());
            pstmt.setDate(4, Date.valueOf(maintenance.getLastMaintenance()));
            pstmt.setDate(5, Date.valueOf(maintenance.getNextMaintenance()));
            pstmt.setInt(6, maintenance.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePlantMaintenance(int maintenanceId) {
        String sql = "DELETE FROM plant_maintenance WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maintenanceId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Maintenance> getAllPlantMaintenance() {
        List<Maintenance> maintenanceList = new ArrayList<>();
        String sql = "SELECT * FROM plant_maintenance";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Maintenance maintenance = new Maintenance();
                maintenance.setId(rs.getInt("id"));
                maintenance.setProperty(getPropertyById(rs.getInt("property_id")));
                maintenance.setPlant(getPlantById(rs.getInt("plant_id")));
                maintenance.setQuantity(rs.getInt("quantity"));
                maintenance.setLastMaintenance(rs.getDate("last_maintenance").toLocalDate());
                maintenance.setNextMaintenance(rs.getDate("next_maintenance").toLocalDate());
                maintenanceList.add(maintenance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maintenanceList;
    }

    // Helper methods to get Property and Plant by ID
    private static Property getPropertyById(int id) {
        String sql = "SELECT * FROM properties WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Property property = new Property();
                property.setId(rs.getInt("id"));
                property.setName(rs.getString("name"));
                property.setStreet(rs.getString("street"));
                property.setZip(rs.getString("zip"));
                property.setCity(rs.getString("city"));
                property.setPhone(rs.getString("phone"));
                return property;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Plant getPlantById(int id) {
        String sql = "SELECT * FROM plants WHERE id = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Plant plant = new Plant();
                plant.setId(rs.getInt("id"));
                plant.setType(rs.getString("type"));
                plant.setName(rs.getString("name"));
                plant.setHeight(rs.getDouble("height"));
                plant.setWidth(rs.getDouble("width"));
                plant.setInterval(rs.getInt("interval"));
                plant.setImagePath(rs.getString("imagePath"));
                return plant;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}