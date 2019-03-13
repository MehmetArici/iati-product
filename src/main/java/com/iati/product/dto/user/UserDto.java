package com.iati.product.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.iati.product.domain.Product;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String username;

    private String password;

    private boolean enabled = true;

    private boolean blocked;

    private Long amount;

    private Set<Product> products;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
