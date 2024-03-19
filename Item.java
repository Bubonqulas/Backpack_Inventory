public class Item {
    private String items;

    // Constructor that initializes the item name
    public Item(String items) {
        this.items = items;
    }

    // Getter to retrieve the item name
    public String getItemName() {
        return items;
    }

    // had daniels help with this one, but it returns the name of the item rather
    // then the address
    public String toString() {
        return items;
    }
}
