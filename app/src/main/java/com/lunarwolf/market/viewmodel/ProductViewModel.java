package com.lunarwolf.market.viewmodel;

import com.lunarwolf.market.model.Product;

/**
 * Created by josephleon on 1/25/15.
 */
public class ProductViewModel {
  private Product product;

  public void setProduct(Product product) {
    this.product = product;
  }

  public void addThisItemToPurchase() {

  }

  public Product getProduct() {
    return product;
  }
}
