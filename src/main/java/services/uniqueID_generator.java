package services;

public class uniqueID_generator {

    private static int idCounter = 1000; // Start from 1000 (you can adjust this)

    // Method to generate a new unique ID
    public static int generateUniqueID() {
        return idCounter++;
    }
}