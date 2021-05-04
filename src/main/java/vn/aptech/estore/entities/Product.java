package vn.aptech.estore.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
    private Integer discount;
    private String status;
    private Integer viewCount;

    private Integer categoryId;
    private Integer brandId;
    private Integer supplierId;

    public Double getNewPrice() {
        return this.unitPrice - ((this.unitPrice * this.discount) / 100);
    }

    public void print() {
        System.out.println("/------------------------------/");
        System.out.println("ID           : " + getId());
        System.out.println("Name         : " + getName());
        System.out.println("Price        : " + getUnitPrice());
        System.out.println("Quantity     : " + getQuantity());
        System.out.println("Discount     : " + getDiscount());
        System.out.println("/------------------------------/\n");
    }
}
