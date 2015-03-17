package com.lunarwolf.market.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.lunarwolf.market.model.Product;
import com.lunarwolf.market.util.Helper;

import java.util.ArrayList;

/**
 * Created by josephleon on 1/18/15.
 */
public class CatalogAdapter extends BaseAdapter {
  private Context mContext;
  private ArrayList<Product> mProducts;

  public CatalogAdapter(Context c, ArrayList<Product> products) {
    mContext = c;
    mProducts = products;
  }

  public int getCount() {
    return mProducts.size();
  }

  public Object getItem(int position) {
    return null;
  }

  public long getItemId(int position) {
    return 0;
  }

  // create a new ImageView for each item referenced by the Adapter
  public View getView(int position, View convertView, ViewGroup parent) {
    ImageView imageView;
    if (convertView == null) {  // if it's not recycled, initialize some attributes
      imageView = new ImageView(mContext);
      int imageSize = Helper.convertDpToPixel(100, mContext);
      int imagePadding = Helper.convertDpToPixel(4, mContext);
      imageView.setLayoutParams(new GridView.LayoutParams(imageSize, imageSize));
      imageView.setScaleType(ImageView.ScaleType.FIT_XY);
      imageView.setPadding(imagePadding, imagePadding, imagePadding, imagePadding);
    } else {
      imageView = (ImageView) convertView;
    }

    Product product = mProducts.get(position);
    int idPhoto = mContext.getResources()
            .getIdentifier(
                    product.getPhotoName(),
                    "drawable",
                    mContext.getPackageName()
            );

    try {
      imageView.setImageResource(idPhoto);
    } catch (Exception e) {
      e.printStackTrace();
    }

    imageView.setTag(product);
    return imageView;
  }

}
