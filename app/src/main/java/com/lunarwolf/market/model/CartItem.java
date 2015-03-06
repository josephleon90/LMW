package com.lunarwolf.market.model;

import java.io.Serializable;

/**
 * Created by josephleon on 1/25/15.
 */
public class CartItem implements Serializable{

  private int amount;
  private Product product;
  private float subtotal;
  private float iva;
  private float total;

  public CartItem(int amount, Product product) {
    this.amount = amount;
    this.product = product;
    subtotal = product.getPrice() * amount;
    iva = product.getPrice() * 0.12f;
    total = iva + subtotal;
  }

  public float getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(float subtotal) {
    this.subtotal = subtotal;
  }

  public float getIva() {
    return iva;
  }

  public void setIva(float iva) {
    this.iva = iva;
  }

  public float getTotal() {
    return total;
  }

  public void setTotal(float total) {
    this.total = total;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
