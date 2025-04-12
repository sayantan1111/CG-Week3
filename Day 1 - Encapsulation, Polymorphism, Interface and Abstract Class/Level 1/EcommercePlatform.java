import java.util.ArrayList;
import java.util.List;

interface Taxable {
    double calculateTax();
    String getTaxDetails();
}

abstract class Product {
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public abstract double calculateDiscount();

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public void displayBaseDetails() {
        System.out.println("ID: " + productId + ", Name: " + name + ", Price: $" + price);
        System.out.println("Discount Amount: $" + String.format("%.2f", calculateDiscount()));
    }
}

class Electronics extends Product implements Taxable {
    private static final double TAX_RATE = 0.15; // 15%
    private static final double DISCOUNT_RATE = 0.10; // 10%

    public Electronics(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * DISCOUNT_RATE;
    }

    @Override
    public double calculateTax() {
        return getPrice() * TAX_RATE;
    }

    @Override
    public String getTaxDetails() {
        return "Electronics Tax (" + (TAX_RATE * 100) + "%)";
    }
}

class Clothing extends Product implements Taxable {
    private static final double TAX_RATE = 0.05; // 5%
    private static final double DISCOUNT_RATE = 0.20; // 20%

    public Clothing(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * DISCOUNT_RATE;
    }

    @Override
    public double calculateTax() {
        return getPrice() * TAX_RATE;
    }

    @Override
    public String getTaxDetails() {
        return "Apparel Tax (" + (TAX_RATE * 100) + "%)";
    }
}

class Groceries extends Product {
    private static final double DISCOUNT_RATE = 0.05; // 5%

    public Groceries(int id, String name, double price) {
        super(id, name, price);
    }

    @Override
    public double calculateDiscount() {
        return getPrice() * DISCOUNT_RATE;
    }
}

public class EcommercePlatform {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Electronics(101, "Smart TV", 999.99));
        products.add(new Clothing(202, "Jeans", 49.95));
        products.add(new Groceries(303, "Apples", 3.50));

        products.get(1).setPrice(55.00); // Update price via setter

        for (Product p : products) {
            System.out.println("--- Product: " + p.getName() + " ---");
            p.displayBaseDetails();
            if (p instanceof Taxable) {
                Taxable taxableItem = (Taxable) p;
                System.out.println(taxableItem.getTaxDetails() + ": $" + String.format("%.2f", taxableItem.calculateTax()));
            } else {
                System.out.println("Tax: Not Applicable");
            }
            System.out.println("Final Price (after discount, before tax): $" + String.format("%.2f", (p.getPrice() - p.calculateDiscount())));
        }
    }
}