import java.util.Scanner;

public class Managment {
    public static void main(String[] args) {
        Location locker = new Location("Locker", "locker.txt");
        Location backpack = new Location("Backpack", "backpack.txt");
        Location pencilCase = new Location("Pencil Case", "pencilcase.txt");

        System.out.println("Welcome to your inventory!\n");
        locker.loadItems();
        backpack.loadItems();
        pencilCase.loadItems();
        locationMenu(locker, backpack, pencilCase);

    }

    public static void locationMenu(Location locker, Location backpack, Location pencilCase) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Where would you like to go?\nLocker\nBackpack\nPencilcase\nQuit");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("quit")) {
                System.out.println("Saving and exiting program.");
                locker.saveItems();
                ;
                backpack.saveItems();
                pencilCase.saveItems();
                break;
            }
            Location currentLocation = null;
            switch (input) {
                case "locker":
                    currentLocation = locker;
                    break;
                case "backpack":
                    currentLocation = backpack;
                    break;
                case "pencil case":
                    currentLocation = pencilCase;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please type in locker, backpack or pencilcase.\n");
                    continue;
            }
            Location.displayInventory(currentLocation);
            choiceMenu(currentLocation);

        }
        scanner.close();

    }

    public static void choiceMenu(Location currentLocation) {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(
                    "What would you like to do?\nAdd and item(add)\nRemove an item(remove)\nMove an item(move)\nQuit to previous page(quit)");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("quit")) {
                System.out.println("Exiting to previous page.");

                break;
            }
            switch (input) {
                case "add":
                    System.out.println("What item would you like to add?");
                    String itemAdd = scanner.nextLine().trim().toLowerCase();
                    currentLocation.addItem(new Item(itemAdd));
                    System.out.println(itemAdd + " has been added to " + currentLocation);

                    break;
                case "remove":
                    // ask mr hager how we would do this since remove item is a sting but we need an
                    // object called Item?
                    // if we dont keep it as item it messes with the move item method and the add
                    // item method
                  //  System.out.println("What item would you like to remove?");
                  //  String removeItem = scanner.nextLine().trim().toLowerCase();
                    // currentLocation.removeItem(removeItem);
                 //   System.out.println(removeItem + " was removed from " + currentLocation);
                    break;
                case "move":
                    // how can we finish this method with the same isse as remove item.
                  //  System.out.println("What item would you like to move?");
                 //  String moveItem = scanner.nextLine().trim().toLowerCase();
                  ///  System.out.println("Where would you like to move this item?");
                  //  String destination = scanner.nextLine().trim().toLowerCase();

                    break;
                case "quit":
                    System.out.println("Exiting to previous page.");

                    break;
                default:
                    System.out.println("\nInvalid choice. Please type in add, remove, move or quit.\n");
                    continue;
            }
            scanner.close();
        }
    }
}