package com.lunarwolf.market.model;

import com.lunarwolf.market.util.Helper;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josephleon on 1/25/15.
 */
public class Cart implements Serializable {

  private float iva;
  private float subtotal;
  private float total;
  private ArrayList<CartItem> items;

  public Cart() {
    items = new ArrayList<>();
  }

  public void addItem(CartItem item) {
    items.add(item);
    calculate();
  }

  private void calculate() {
    cleanTotals();
    for (CartItem cartItem : items) {
      subtotal = subtotal + cartItem.getAmount() * cartItem.getProduct()
              .getPrice();
      iva = iva + cartItem.getProduct().getPrice() * 0.12f;
    }
    Helper.round(iva);
    total = subtotal + iva;
  }

  public float getTotal() {
    return total;
  }

  public float getIva() {
    return iva;
  }

  public float getSubtotal() {
    return subtotal;
  }

  public ArrayList<CartItem> getItems() {
    return items;
  }

  private void cleanTotals() {
    iva = 0;
    total = 0;
    subtotal = 0;
  }
}
