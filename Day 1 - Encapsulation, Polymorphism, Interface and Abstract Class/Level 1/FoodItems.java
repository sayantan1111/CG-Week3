import java.util.ArrayList;
import java.util.List;

abstract class FoodItem {
    private String itemName;
    private double price;
    private int quantity;

    public FoodItem(String itemName, double price, int quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        }
    }

    public void getItemDetails() {
        System.out.println("Item: " + itemName);
        System.out.println("Price per unit: " + price);
        System.out.println("Quantity: " + quantity);
    }

    public abstract double calculateTotalPrice();
}

class VegItem extends FoodItem {
    public VegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return getPrice() * getQuantity();
    }
}

class NonVegItem extends FoodItem {
    private double nonVegCharge = 5.0;

    public NonVegItem(String itemName, double price, int quantity) {
        super(itemName, price, quantity);
    }

    @Override
    public double calculateTotalPrice() {
        return (getPrice() * getQuantity()) + (nonVegCharge * getQuantity());
    }
}

interface Discountable {
    double applyDiscount(double totalAmount);
    String getDiscountDetails();
}

class BulkOrderDiscount implements Discountable {
    private double discountPercentage = 0.1;
    private int bulkQuantityThreshold = 5;

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount * (1 - discountPercentage);
    }

    @Override
    public String getDiscountDetails() {
        return "Bulk order discount applied: " + (discountPercentage * 100) + "% off for orders with more than " + bulkQuantityThreshold + " items.";
    }
}

class FoodItems {
    public static void main(String[] args) {
        List<FoodItem> orderItems = new ArrayList<>();
        orderItems.add(new VegItem("Veg Burger", 5.0, 2));
        orderItems.add(new NonVegItem("Chicken Burger", 7.0, 1));
        orderItems.add(new VegItem("Fries", 3.0, 3));

        double totalOrderPrice = 0;
        for (FoodItem item : orderItems) {
            item.getItemDetails();
            totalOrderPrice += item.calculateTotalPrice();
            System.out.println("Total price for this item: " + item.calculateTotalPrice());
            System.out.println("--------------------");
        }

        System.out.println("Total order price before discount: " + totalOrderPrice);

        Discountable bulkDiscount = new BulkOrderDiscount();
        if (orderItems.stream().mapToInt(FoodItem::getQuantity).sum() > 5) {
            totalOrderPrice = bulkDiscount.applyDiscount(totalOrderPrice);
            System.out.println(bulkDiscount.getDiscountDetails());
        }

        System.out.println("Final total order price: " + totalOrderPrice);
    }
}