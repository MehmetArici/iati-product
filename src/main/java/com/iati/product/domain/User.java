package com.iati.product.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity{


    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private boolean enabled = true;

    @JsonIgnore
    private boolean blocked;

    @JsonIgnore
    private Long amount;

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

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
//    @JsonIgnore
//    @OneToMany(mappedBy = "owner")
//    private List<Book> books;

//    @JsonIgnore
//    @OneToMany(mappedBy = "owner")
//    private List<BookCollection> bookCollections;
}
