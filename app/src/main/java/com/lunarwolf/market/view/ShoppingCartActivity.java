package com.lunarwolf.market.view;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lunarwolf.market.R;
import com.lunarwolf.market.model.Cart;
import com.lunarwolf.market.model.CartItem;
import com.lunarwolf.market.util.Helper;

import java.util.ArrayList;

public class ShoppingCartActivity extends ActionBarActivity {

  private LinearLayout itemsList;
  private TextView total;
  private TextView iva;

  private Cart cart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shopping_cart);

    itemsList = (LinearLayout) findViewById(R.id.itemsList);
    total = (TextView) findViewById(R.id.totalBill);
    iva = (TextView) findViewById(R.id.ivaTax);

    cart = getCartFromIntent();
    fillItemsList(cart.getItems());
    showTotals(cart);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.ok) {
      Intent i = new Intent(this, ContactFormActivity.class);
      i.putExtra(CatalogActivity.CART_EXTRA, cart);
      startActivity(i);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  public void fillItemsList(ArrayList<CartItem> items) {

    for (int i = 0; i < items.size(); i++) {
      View rowView = getLayoutInflater().inflate(R.layout.item_cart, null);

      TextView name = (TextView) rowView.findViewById(R.id.name);
      TextView subtotal = (TextView) rowView.findViewById(R.id.subtotal);
      TextView amount = (TextView) rowView.findViewById(R.id.amount);

      CartItem item = items.get(i);

      name.setText(item.getProduct().getDescription());
      subtotal.setText(Helper.currencyFormatter(item.getSubtotal()));
      amount.setText("x " + item.getAmount());

      if (rowView != null)
        itemsList.addView(rowView);
    }

  }

  public Cart getCartFromIntent() {
    return (Cart) getIntent().getSerializableExtra(CatalogActivity.CART_EXTRA);
  }

  private void showTotals(Cart cart) {
    iva.setText(Helper.currencyFormatter(cart.getIva()));
    total.setText(Helper.currencyFormatter(cart.getTotal()));
  }
}
