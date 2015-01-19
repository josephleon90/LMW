package com.lunarwolf.market.viewmodel;

import android.content.Context;
import android.content.res.Resources;

import com.lunarwolf.market.R;
import com.lunarwolf.market.model.Product;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josephleon on 1/19/15.
 */
public class CatalogViewModel implements Serializable {
  private ArrayList<Product> products;

  public CatalogViewModel() {
    products = new ArrayList<>();
  }

  public ArrayList<Product> getProducts(Context context) {

    if (!products.isEmpty())
      return products;

    String[] ids;
    String[] descriptions;
    String[] prices;
    String[] smallSizes;
    String[] mediumSizes;
    String[] largeSizes;
    String[] extraLargeSizes;
    String[] photo;

    Resources rs = context.getResources();

    ids = rs.getStringArray(R.array.productIds);
    descriptions = rs.getStringArray(R.array.productDes);
    prices = rs.getStringArray(R.array.productPrices);
    smallSizes = rs.getStringArray(R.array.productSmallSizes);
    mediumSizes = rs.getStringArray(R.array.productMediumSizes);
    largeSizes = rs.getStringArray(R.array.productLargeSizes);
    extraLargeSizes = rs.getStringArray(R.array.productExtraLargeSizes);
    photo = rs.getStringArray(R.array.productPhotoNames);

    for (int i = 0; i < prices.length; i++) {
      Product product = new Product(
              ids[i],
              descriptions[i],
              Float.parseFloat(prices[i]),
              Boolean.parseBoolean(smallSizes[i]),
              Boolean.parseBoolean(mediumSizes[i]),
              Boolean.parseBoolean(largeSizes[i]),
              Boolean.parseBoolean(extraLargeSizes[i]),
              photo[i]
      );

      products.add(product);
    }

    return products;
  }
}
