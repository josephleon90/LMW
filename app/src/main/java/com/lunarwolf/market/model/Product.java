package com.lunarwolf.market.model;

import java.io.Serializable;

/**
 * Created by josephleon on 1/18/15.
 */
public class Product implements Serializable{

  private String id;
  private String description;
  private float price;
  private String [] sizes;
  private Integer [] colors;
  private String photoName;

  public Product(String id, String description, float price,
                 String photoName, String [] sizes, Integer [] colors) {
    this.id = id;
    this.description = description;
    this.price = price;
    this.photoName = photoName;
    this.sizes = sizes;
    this.colors = colors;
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

  public String getPhotoName() {
    return photoName;
  }

  public void setPhotoName(String photoName) {
    this.photoName = photoName;
  }

  public String[] getSizes() {
    return sizes;
  }

  public void setSizes(String[] sizes) {
    this.sizes = sizes;
  }

  public Integer[] getColors() {
    return colors;
  }

  public void setColors(Integer[] colors) {
    this.colors = colors;
  }
}
