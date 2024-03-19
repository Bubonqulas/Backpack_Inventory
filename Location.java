// Location.java
/*
  Title: Location class
  Author: Hassan Darky
  Date: March 15th, 2024
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Location {
    static Scanner scanner = new Scanner(System.in);
    private String filename;
    private String locations;
    private ArrayList<Item> storage = new ArrayList<Item>();

    // Constructor that initializes location with its name and filename
    public Location(String locations, String filename) {
        this.locations = locations;
        this.filename = filename;

    }

    // Method to add an item
    public void addItem(Item item) {
        storage.add(item);

    }

    // Method to move an item from one location to another
    public static void moveItem(Location currentLocation, Location moveDestination, int moveItem) {
        // Checks if the current location and destination are the same
        if (currentLocation == moveDestination) {
            System.out.println("\nItem is already at this loaction.");
            return;
        }
        // Checks if the index for the item to move is within the currentlocation
        if (moveItem < 0 || moveItem >= currentLocation.getStorage().size()) {
            System.out.println("Invalid item index.");
            return;
        }

        Item itemToMove = currentLocation.getStorage().get(moveItem);
        System.out.println("\n" + itemToMove.getItemName() + " has been moved from " +
                currentLocation.getName() + " to " + moveDestination.getName() + ".\n");
        // Removes the item from the current location and adds it to the destination
        currentLocation.getStorage().remove(moveItem);
        moveDestination.addItem(itemToMove);
        displayInventory(moveDestination);
        displayInventory(currentLocation);
        
    }

    // Method to display the inventory at the location
    public static void displayInventory(Location location) {
        System.out.println("\nContents in your " + location.getName() + ":\n-----");

        for (int i = 0; i < location.storage.size(); i++) {
            System.out.println((i + 1) + ". " + location.storage.get(i));

        }

    }

    // partially taken from mr artyms code from last year and modified with the help
    // of stack overflow and various youtube videos
    public void loadItems() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                storage.add(new Item(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // partially taken from mr artyms code from last year and modified with the help
    // of stack overflow and various youtube videos
    public void saveItems() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Item item : storage) {
                writer.println(item.getItemName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter for location name
    public String getName() {
        return locations;
    }

    // Getter for storage
    public ArrayList<Item> getStorage() {
        return storage;
    }

}
