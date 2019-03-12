package com.iati.product.dto.product;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iati.product.domain.ProductStatus;
import com.iati.product.dto.BaseDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto extends BaseDto {

    private String name;

    private String type;

    private int price;

    private String description;

    private String provider;

    private Boolean isDiscounted;

    private int quantity;

    private ProductStatus status;

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Boolean getDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(Boolean discounted) {
        isDiscounted = discounted;
    }
}
