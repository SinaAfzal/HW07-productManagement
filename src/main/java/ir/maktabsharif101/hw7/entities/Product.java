package ir.maktabsharif101.hw7.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SuppressWarnings("unused")
public class Product {
    private Integer id;
    private String productName;
    private Timestamp createDate;
    private int categoryId;
    private int brandId;

    public Product() {
    }

    public Product(Integer id, String productName, int categoryId, int brandId) {
        this.id = id;
        this.productName = productName;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.createDate=Timestamp.valueOf(LocalDateTime.now());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }
}
