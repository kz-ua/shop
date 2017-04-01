package com.cn.kz.tech.shop.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * Created by kz on 18.03.17.
 */
@Entity
public class Price extends BaseObject {
    private int price;
    private Date date;
    private Collection<OrderItem> orderItemsByPriceId;
    private Product productByProductId;

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (id != price1.id) return false;
        if (price != price1.price) return false;
        if (date != null ? !date.equals(price1.date) : price1.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + price;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "priceByPriceId")
    public Collection<OrderItem> getOrderItemsByPriceId() {
        return orderItemsByPriceId;
    }

    public void setOrderItemsByPriceId(Collection<OrderItem> orderItemsByPriceId) {
        this.orderItemsByPriceId = orderItemsByPriceId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }
}
