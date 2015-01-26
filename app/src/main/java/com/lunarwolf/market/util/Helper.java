package com.lunarwolf.market.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.util.Locale;

/**
 * Created by josephleon on 1/19/15.
 */
public class Helper {

  public static int convertDpToPixel(float dp, Context context){
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float px = dp * (metrics.densityDpi / 160f);
    return Math.round(px);
  }

  public static String currencyFormatter(float number){
    return "$" + String.format(Locale.US, "%.02f", number);
  }

  public static double round(float num){
    return Math.round(num * 100.0) / 100.0;
  }

}
