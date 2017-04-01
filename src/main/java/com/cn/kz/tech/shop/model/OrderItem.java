package com.cn.kz.tech.shop.model;

import javax.persistence.*;

/**
 * Created by kz on 18.03.17.
 */
@Entity
@Table(name = "order_item", schema = "shop")
public class OrderItem extends BaseObject {
    private short amount;
    private Order orderByOrderId;
    private Product productByProductId;
    private Price priceByPriceId;

    @Basic
    @Column(name = "amount")
    public short getAmount() {
        return amount;
    }

    public void setAmount(short amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (id != orderItem.id) return false;
        if (amount != orderItem.amount) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) amount;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    public Product getProductByProductId() {
        return productByProductId;
    }

    public void setProductByProductId(Product productByProductId) {
        this.productByProductId = productByProductId;
    }

    @ManyToOne
    @JoinColumn(name = "price_id", referencedColumnName = "id", nullable = false)
    public Price getPriceByPriceId() {
        return priceByPriceId;
    }

    public void setPriceByPriceId(Price priceByPriceId) {
        this.priceByPriceId = priceByPriceId;
    }
}
