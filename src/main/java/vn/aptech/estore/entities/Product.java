package vn.aptech.estore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.NumberFormat;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity implements Serializable {

    private String name;
    private String thumbnailUrl;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private Double discount;
    private String status;

    private Category category;
    private Brand brand;
    private Supplier supplier;

    public Double getNewPrice() {
        return this.unitPrice - (this.unitPrice * this.discount);
    }

    public String getPercentageDiscount() {
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);
        return defaultFormat.format(discount);
    }

    public void print() {
        System.out.println("/------------------------------/");
        System.out.println("ID           : " + getId());
        System.out.println("Name         : " + getName());
        System.out.println("Price        : " + getUnitPrice());
        System.out.println("Quantity     : " + getQuantity());
        System.out.println("Discount     : " + getDiscount());
        System.out.println("Category     : " + getCategory().getName());
        System.out.println("Brand        : " + getBrand().getName());
        System.out.println("Supplier     : " + getSupplier().getName());
        System.out.println("/------------------------------/\n");
    }
}
