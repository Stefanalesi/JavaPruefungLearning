-- Drop tables if they exist to avoid errors on re-initialization
DROP TABLE IF EXISTS plant_maintenance;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS plants;

-- Create the `plants` table
CREATE TABLE plants (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        type VARCHAR(50) NOT NULL,  -- Type of plant (e.g., Baum, Strauch)
                        name VARCHAR(100) NOT NULL, -- Name of the plant
                        height DOUBLE NOT NULL,     -- Typical height in meters
                        width DOUBLE NOT NULL,      -- Typical width in meters
                        interval INT NOT NULL,      -- Maintenance interval in days
                        imagePath VARCHAR(255)      -- Path to the plant image (optional)
);

-- Create the `properties` table
CREATE TABLE properties (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL, -- Name of the property owner
                            street VARCHAR(100) NOT NULL, -- Street address
                            zip VARCHAR(10) NOT NULL,     -- Postal code
                            city VARCHAR(50) NOT NULL,    -- City
                            phone VARCHAR(20)             -- Phone number
);

-- Create the `plant_maintenance` table
CREATE TABLE plant_maintenance (
                                   id INT AUTO_INCREMENT PRIMARY KEY,
                                   property_id INT NOT NULL,   -- Foreign key to `properties.id`
                                   plant_id INT NOT NULL,      -- Foreign key to `plants.id`
                                   quantity INT NOT NULL,      -- Number of plants
                                   last_maintenance DATE,      -- Date of last maintenance
                                   next_maintenance DATE,      -- Date of next maintenance

    -- Foreign key constraints
                                   FOREIGN KEY (property_id) REFERENCES properties(id) ON DELETE CASCADE,
                                   FOREIGN KEY (plant_id) REFERENCES plants(id) ON DELETE CASCADE
);
