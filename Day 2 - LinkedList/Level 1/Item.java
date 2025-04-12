class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class Inventory {
    Item head;

    public Inventory() {
        this.head = null;
    }

    public void addItemAtBeginning(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addItemAtEnd(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newItem;
    }

    public void addItemAtPosition(int position, String itemName, int itemId, int quantity, double price) {
        if (position <= 0) {
            addItemAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            if (position == 1) {
                head = newItem;
            }
            return;
        }
        Item current = head;
        int count = 1;
        while (count < position - 1 && current != null) {
            current = current.next;
            count++;
        }
        if (current == null) {
            return;
        }
        newItem.next = current.next;
        current.next = newItem;
    }

    public void removeItem(int itemId) {
        if (head == null) {
            return;
        }
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        Item current = head;
        while (current.next != null && current.next.itemId != itemId) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void updateQuantity(int itemId, int newQuantity) {
        Item current = head;
        while (current != null) {
            if (current.itemId == itemId) {
                current.quantity = newQuantity;
                return;
            }
            current = current.next;
        }
    }

    public void searchItemById(int itemId) {
        Item current = head;
        while (current != null) {
            if (current.itemId == itemId) {
                System.out.println("Item Name: " + current.itemName);
                System.out.println("Item ID: " + current.itemId);
                System.out.println("Quantity: " + current.quantity);
                System.out.println("Price: " + current.price);
                return;
            }
            current = current.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void searchItemByName(String itemName) {
        Item current = head;
        while (current != null) {
            if (current.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Item Name: " + current.itemName);
                System.out.println("Item ID: " + current.itemId);
                System.out.println("Quantity: " + current.quantity);
                System.out.println("Price: " + current.price);
                return;
            }
            current = current.next;
        }
        System.out.println("Item with Name " + itemName + " not found.");
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        Item current = head;
        while (current != null) {
            totalValue += current.quantity * current.price;
            current = current.next;
        }
        return totalValue;
    }

    private Item getMiddle(Item head) {
        if (head == null) return head;
        Item slow = head;
        Item fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Item merge(Item left, Item right, boolean ascending, String sortBy) {
        if (left == null) return right;
        if (right == null) return left;
        Item result = null;
        if (sortBy.equalsIgnoreCase("name")) {
            if ((ascending && left.itemName.compareToIgnoreCase(right.itemName) <= 0) || (!ascending && left.itemName.compareToIgnoreCase(right.itemName) > 0)) {
                result = left;
                result.next = merge(left.next, right, ascending, sortBy);
            } else {
                result = right;
                result.next = merge(left, right.next, ascending, sortBy);
            }
        } else if (sortBy.equalsIgnoreCase("price")) {
            if ((ascending && left.price <= right.price) || (!ascending && left.price > right.price)) {
                result = left;
                result.next = merge(left.next, right, ascending, sortBy);
            } else {
                result = right;
                result.next = merge(left, right.next, ascending, sortBy);
            }
        }
        return result;
    }

    private Item mergeSort(Item head, boolean ascending, String sortBy) {
        if (head == null || head.next == null) {
            return head;
        }
        Item middle = getMiddle(head);
        Item secondHalf = middle.next;
        middle.next = null;
        Item left = mergeSort(head, ascending, sortBy);
        Item right = mergeSort(secondHalf, ascending, sortBy);
        return merge(left, right, ascending, sortBy);
    }

    public void sortByItemName(boolean ascending) {
        head = mergeSort(head, ascending, "name");
    }

    public void sortByPrice(boolean ascending) {
        head = mergeSort(head, ascending, "price");
    }

    public void displayInventory() {
        Item current = head;
        if (current == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        while (current != null) {
            System.out.println("Item Name: " + current.itemName);
            System.out.println("Item ID: " + current.itemId);
            System.out.println("Quantity: " + current.quantity);
            System.out.println("Price: " + current.price);
            System.out.println("--------------------");
            current = current.next;
        }
        System.out.println("Total Inventory Value: " + calculateTotalValue());
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        inventory.addItemAtEnd("Laptop", 101, 5, 1200.00);
        inventory.addItemAtBeginning("Mouse", 102, 10, 25.00);
        inventory.addItemAtPosition(2, "Keyboard", 103, 7, 75.00);
        inventory.displayInventory();
        inventory.updateQuantity(102, 15);
        inventory.removeItem(103);
        System.out.println("\nInventory after update and removal:");
        inventory.displayInventory();
        inventory.searchItemById(101);
        inventory.searchItemByName("mouse");
        System.out.println("\nInventory sorted by Name (ascending):");
        inventory.sortByItemName(true);
        inventory.displayInventory();
        System.out.println("\nInventory sorted by Price (descending):");
        inventory.sortByPrice(false);
        inventory.displayInventory();
    }
}