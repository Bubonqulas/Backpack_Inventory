// Managment.java
/*
  Title: Managment class
  Author: Hassan Darky
  Date: March 15th, 2024
 */


import java.util.InputMismatchException;
import java.util.Scanner;

public class Managment {
    // Creating the three location

    static Location locker = new Location("Locker", "locker.txt");
    static Location backpack = new Location("Backpack", "backpack.txt");
    static Location pencilCase = new Location("Pencil Case", "pencilcase.txt");

    public static void main(String[] args) {

        // --Output--
        System.out.println("Welcome to your inventory!\n");
        // Loading items for each location
        locker.loadItems();
        backpack.loadItems();
        pencilCase.loadItems();

        // --Processing--
        // Calls location method
        locationMenu(locker, backpack, pencilCase);

    }

    // Method to display location menu
    public static void locationMenu(Location locker, Location backpack, Location pencilCase) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(
                    "Where would you like to go?\n-----\n~ Locker\n~ Backpack\n~ Pencilcase\n~ Save and Quit (quit)");
            String input = scanner.nextLine().trim().toLowerCase();
            input = input.replaceAll("\\s", ""); // removes spaces from between words
            if (input.equals("quit")) {
                System.out.println("Saving and exiting program.");
                // Saves items to text file
                locker.saveItems();
                backpack.saveItems();
                pencilCase.saveItems();
                break;
            }
            Location currentLocation = null;
            // Switch case to determine the chosen location
            switch (input) {
                case "locker":
                    currentLocation = locker;
                    break;
                case "backpack":
                    currentLocation = backpack;
                    break;
                case "pencilcase":
                    currentLocation = pencilCase;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please type in locker, backpack or pencilcase.\n");
                    continue;
            }
            Location.displayInventory(currentLocation);
            // Calling the choiceMenu method to perform actions in the chosen location
            choiceMenu(currentLocation);

        }
        scanner.close();

    }

    // Method to display choice menu for actions in a location
    public static void choiceMenu(Location currentLocation) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            // Displays the options in the location
            System.out.println(
                    "\nWhat would you like to do?\n-----\n~ Add and item(add)\n~ Remove an item(remove)\n~ Move an item(move)\n~ Quit to previous page(quit)");
            String input = scanner.nextLine();
            input = input.trim().toLowerCase();
            if (input.equals("quit")) {
                System.out.println("Exiting to previous page.");

                break;
            }
            switch (input) {
                // Case to add an item
                case "add":
                    System.out.println("What item would you like to add?");
                    String itemAdd = scanner.nextLine().trim().toLowerCase();
                    currentLocation.addItem(new Item(itemAdd));
                    System.out.println("\n" + itemAdd + " has been added to your " + currentLocation.getName() + "\n");
                    Location.displayInventory(currentLocation);
                    break;

                // Case to remove an item
                case "remove":
                    System.out.println("What item would you like to remove?(enter item #)");
                    int removeItem;
                    // Try catch to make sure the input is a number and prevent the program from breaking
                    try {
                        removeItem = scanner.nextInt() - 1;
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\n An error occured. Please try again and enter a valid item number.\n");
                        continue;
                    }
                    if (removeItem < 0 || removeItem >= currentLocation.getStorage().size()) {
                        System.out.println("\nInvalid item number. Please try again and enter a valid item number.\n");
                        continue;
                    }

                    System.out.println(
                            currentLocation.getStorage().get(removeItem) + " was removed from "
                                    + currentLocation.getName() + "\n");

                    currentLocation.getStorage().remove(removeItem);

                    Location.displayInventory(currentLocation);

                    break;

                // Case to move an item
                case "move":
                    System.out.println("Which item would you like to move?");
                    int moveItem;
                    try {
                        moveItem = scanner.nextInt() - 1;
                        scanner.nextLine();
                    } catch (InputMismatchException e) {
                        System.out.println("\n An error occured. Please try again and enter a valid item number.\n");
                        continue;
                    }
                    if (moveItem < 0 || moveItem >= currentLocation.getStorage().size()) {
                        System.out.println("\nInvalid item number. Please try again and enter a valid item number.\n");
                        continue;
                    }
                    System.out.println("Where would you like to move the item?(locker, backpack, pencilcase)");
                    String destination = scanner.nextLine().trim().toLowerCase();
                    destination = destination.replaceAll("\\s", ""); // removes spaces from between words
                    Location moveDestination = null;
                    switch (destination) {
                        case "locker":
                            moveDestination = locker;
                            break;
                        case "backpack":
                            moveDestination = backpack;
                            break;
                        case "pencilcase":
                            moveDestination = pencilCase;
                            break;
                        default:
                            System.out.println("\nInvalid choice. Please type in locker, backpack or pencilcase.\n");
                            continue;
                    }
                    Location.moveItem(currentLocation, moveDestination, moveItem);

                    break;

                // Quit to previous page
                case "quit":
                    System.out.println("Exiting to previous page.");
                    scanner.close();
                    break;
                default:
                    System.out.println("\nInvalid choice. Please type in add, remove, move or quit.\n");
                    continue;
            }

        }

    }

}
