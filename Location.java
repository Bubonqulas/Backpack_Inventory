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

    public Location(String locations, String filename) {
        this.locations = locations;
        this.filename = filename;

    }

    public void addItem(Item item) {
        storage.add(item);

    }

    public static void moveItem(Location currentLocation, Location destination, String itemName) {
       
    }

    public  void removeItem(Item itemName) {
        storage.remove(itemName);
   
        
    }

    public static void displayInventory(Location location) {
        System.out.println("\nContents in your " + location.getName()+":\n");
        for (Item item : location.getStorage()) {
            System.out.println(item.getItemName());
        }
        System.out.println();
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

    public String getName() {
        return locations;
    }

    public ArrayList<Item> getStorage() {
        return storage;
    }


}
