package com.lunarwolf.market.view.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lunarwolf.market.R;

/**
 * Created by josephleon on 1/25/15.
 */
public class SpinnerColorAdapter extends ArrayAdapter {

  private Activity context;
  private Integer[] colors;


  public SpinnerColorAdapter(Activity context, int colorItem, Integer[] color) {
    super(context, colorItem, color);
    this.colors = color;
    this.context = context;
  }

  public View getCustomView(int position, View convertView, ViewGroup parent) {

    LayoutInflater inflater = context.getLayoutInflater();
    View layout = inflater.inflate(R.layout.item_color, parent, false);
    TextView colorTextView = (TextView) layout.findViewById(R.id.colorCircle);
    colorTextView.setBackgroundColor(this.colors[position]);

    return layout;
  }

  // It gets a View that displays in the drop down popup the data at the
  // specified position
  @Override
  public View getDropDownView(int position, View convertView,
                              ViewGroup parent) {
    return getCustomView(position, convertView, parent);
  }

  // It gets a View that displays the data at the specified position
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    return getCustomView(position, convertView, parent);
  }
}
