package com.lunarwolf.market.model;

import java.io.Serializable;

/**
 * Created by josephleon on 1/18/15.
 */
public class Product implements Serializable{

  private String id;
  private String description;
  private float price;
  private boolean smallSize;
  private boolean mediumSize;
  private boolean largeSize;
  private boolean extraLargeSize;
  private String photoName;

  public Product(String id, String description, float price,
                 boolean smallSize, boolean mediumSize, boolean largeSize,
                 boolean extraLargeSize, String photoName) {
    this.id = id;
    this.description = description;
    this.price = price;
    this.smallSize = smallSize;
    this.mediumSize = mediumSize;
    this.largeSize = largeSize;
    this.extraLargeSize = extraLargeSize;
    this.photoName = photoName;
  }

  public Product(){
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  public boolean isSmallSize() {
    return smallSize;
  }

  public void setSmallSize(boolean smallSize) {
    this.smallSize = smallSize;
  }

  public boolean isMediumSize() {
    return mediumSize;
  }

  public void setMediumSize(boolean mediumSize) {
    this.mediumSize = mediumSize;
  }

  public boolean isLargeSize() {
    return largeSize;
  }

  public void setLargeSize(boolean largeSize) {
    this.largeSize = largeSize;
  }

  public boolean isExtraLargeSize() {
    return extraLargeSize;
  }

  public void setExtraLargeSize(boolean extraLargeSize) {
    this.extraLargeSize = extraLargeSize;
  }

  public String getPhotoName() {
    return photoName;
  }

  public void setPhotoName(String photoName) {
    this.photoName = photoName;
  }
}
