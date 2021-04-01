package vn.aptech.ecommerce.entities;

import java.io.Serializable;

public class Product extends BaseEntity implements Serializable {

    private String name;
    private String thumbnailUrl;
    private String description;
    private Integer quantity;
    private Double unitPrice; // Gia cu
    private Integer discount;
    private String status;
    private Integer viewCount;

    private Integer categoryId;
    private Integer brandId;
    private Integer supplierId;

    public Product() {
        super();
        this.discount = 0;
        this.viewCount = 0;
        this.status = "available";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getNewPrice() {
        return this.unitPrice - ((this.unitPrice * this.discount) / 100);
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", thumbnailUrl=" + thumbnailUrl + ", description=" + description + ", quantity=" + quantity + ", unitPrice=" + unitPrice + ", discount=" + discount + ", status=" + status + ", viewCount=" + viewCount + ", categoryId=" + categoryId + ", brandId=" + brandId + ", supplierId=" + supplierId + '}';
    }
    
}
