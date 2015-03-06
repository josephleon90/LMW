package com.lunarwolf.market.viewmodel;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.lunarwolf.market.R;
import com.lunarwolf.market.model.Cart;
import com.lunarwolf.market.model.CartItem;
import com.lunarwolf.market.model.Product;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by josephleon on 1/19/15.
 */
public class CatalogViewModel implements Serializable {
  private ArrayList<Product> products;

  private Cart cart;

  public CatalogViewModel() {
    products = new ArrayList<>();
    cart = new Cart();
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
    String[] photos;
    String[] color1;
    String[] color2;
    String[] color3;

    Resources rs = context.getResources();

    ids = rs.getStringArray(R.array.productIds);
    descriptions = rs.getStringArray(R.array.productDes);
    prices = rs.getStringArray(R.array.productPrices);
    smallSizes = rs.getStringArray(R.array.productSmallSizes);
    mediumSizes = rs.getStringArray(R.array.productMediumSizes);
    largeSizes = rs.getStringArray(R.array.productLargeSizes);
    extraLargeSizes = rs.getStringArray(R.array.productExtraLargeSizes);
    photos = rs.getStringArray(R.array.productPhotoNames);
    color1 = rs.getStringArray(R.array.productBlack);
    color2 = rs.getStringArray(R.array.productBlue);
    color3 = rs.getStringArray(R.array.productRed);

    for (int i = 0; i < photos.length; i++) {

      try {
//      Get colors of product on array
        ArrayList<Integer> colorsList = new ArrayList<>();
        if (color1[i].equals("true"))
          colorsList.add(rs.getColor(R.color.black));
        if (color2[i].equals("true"))
          colorsList.add(rs.getColor(R.color.blue));
        if (color3[i].equals("true"))
          colorsList.add(rs.getColor(R.color.red));

        Integer[] colorsArray = new Integer[colorsList.size()];
        for (int j = 0; j < colorsList.size(); j++)
          colorsArray[j] = colorsList.get(j);

//      Get sizes of product on array
        ArrayList<String> sizeList = new ArrayList<>();
        if (smallSizes[i].equals("true"))
          sizeList.add(context.getString(R.string.small));
        if (mediumSizes[i].equals("true"))
          sizeList.add(context.getString(R.string.medium));
        if (largeSizes[i].equals("true"))
          sizeList.add(context.getString(R.string.large));
        if (extraLargeSizes[i].equals("true"))
          sizeList.add(context.getString(R.string.extraLarge));

        String[] sizeArray = new String[sizeList.size()];
        for (int j = 0; j < sizeList.size(); j++)
          sizeArray[j] = sizeList.get(j);

        Product product = new Product(
                ids[i],
                descriptions[i],
                Float.parseFloat(prices[i]),
                photos[i],
                sizeArray,
                colorsArray
        );

        products.add(product);
      } catch (ArrayIndexOutOfBoundsException e) {
        Log.e("Wrong index array", CatalogViewModel.class.getSimpleName());

        Product product = new Product(
                null,
                null,
                0,
                photos[i],
                null,
                null
        );
        products.add(product);
      }
    }

    return products;

  }

  public void addItemToPurchase(CartItem item) {
    cart.addItem(item);
  }

  public Cart getCart() {
    return cart;
  }
}
