package com.lunarwolf.market.model;

import java.io.Serializable;

/**
 * Created by josephleon on 1/25/15.
 */
public class ItemCart implements Serializable{

  private int amount;
  private Product product;

  public ItemCart(int amount, Product product) {
    this.amount = amount;
    this.product = product;
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
