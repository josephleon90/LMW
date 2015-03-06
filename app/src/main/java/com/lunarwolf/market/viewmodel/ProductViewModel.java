package com.lunarwolf.market.viewmodel;

import com.lunarwolf.market.model.Cart;
import com.lunarwolf.market.model.Product;

import java.io.Serializable;

/**
 * Created by josephleon on 1/25/15.
 */
public class ProductViewModel implements Serializable{
  private Product product;
  private Cart cart;

  public ProductViewModel(Cart cart, Product product){
    this.product = product;
    this.cart = cart;
  }
  public void setProduct(Product product) {
    this.product = product;
  }

  public void addThisItemToPurchase() {

  }

  public Product getProduct() {
    return product;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }
}
