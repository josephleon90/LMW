package com.lunarwolf.market.view.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lunarwolf.market.R;
import com.lunarwolf.market.model.Cart;
import com.lunarwolf.market.model.CartItem;
import com.lunarwolf.market.util.Helper;

import java.util.ArrayList;

/**
 * Created by Joseph on 4/3/15.
 */

public class ShoppingCartAdapter extends ArrayAdapter<CartItem> {

  private Cart cart;
  private ArrayList<CartItem> items;
  private Activity context;

  public ShoppingCartAdapter(Activity context,
                                 Cart cart) {
    super(context, 0, cart.getItems());
    this.context = context;
    this.items = cart.getItems();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {

    View rowView = context.getLayoutInflater().inflate(R.layout.item_cart, parent, false);

    TextView name = (TextView) rowView.findViewById(R.id.name);
    TextView subtotal = (TextView) rowView.findViewById(R.id.subtotal);
    TextView amount = (TextView) rowView.findViewById(R.id.amount);

    CartItem item = items.get(position);

    name.setText(item.getProduct().getDescription());
    subtotal.setText(Helper.currencyFormatter(item.getSubtotal()));
    amount.setText(item.getAmount());

    return rowView;
  }

}