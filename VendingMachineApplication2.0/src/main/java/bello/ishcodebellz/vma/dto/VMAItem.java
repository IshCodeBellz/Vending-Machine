package bello.ishcodebellz.vma.dto;

import java.math.BigDecimal;

public class VMAItem {

    private String name;
    private BigDecimal price;
    private int quantity;

    public VMAItem(String name, String priceString, String quantityString) {
        this.name = name;
        this.price = new BigDecimal(priceString);
        this.quantity = Integer.parseInt(quantityString);
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return " |Name: " + name + " |Price: $" + price + " |Qty Available: " + quantity;
    }
}
