package com.cn.kz.tech.shop.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by kz on 18.03.17.
 */
@Entity
public class Product extends BaseObject {
    private String name;
    private String description;
    private Collection<OrderItem> orderItemsByProductId;
    private Collection<Price> pricesByProductId;
    private Category categoryByCategoryId;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<OrderItem> getOrderItemsByProductId() {
        return orderItemsByProductId;
    }

    public void setOrderItemsByProductId(Collection<OrderItem> orderItemsByProductId) {
        this.orderItemsByProductId = orderItemsByProductId;
    }

    @OneToMany(mappedBy = "productByProductId")
    public Collection<Price> getPricesByProductId() {
        return pricesByProductId;
    }

    public void setPricesByProductId(Collection<Price> pricesByProductId) {
        this.pricesByProductId = pricesByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    public Category getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(Category categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }
}
