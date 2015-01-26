package com.lunarwolf.market.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lunarwolf.market.R;
import com.lunarwolf.market.model.Cart;
import com.lunarwolf.market.model.Product;
import com.lunarwolf.market.util.Helper;
import com.lunarwolf.market.view.adapter.SpinnerColorAdapter;
import com.lunarwolf.market.viewmodel.ProductViewModel;


public class ProductActivity extends ActionBarActivity {

  public static final String ITEM = "item";
  private static final String ITEM_AMOUNT_FRAGMENT_TAG = "item amount";
  private Spinner color;
  private Spinner size;
  private TextView description;
  private Button price;
  private Button actualPurchase;
  private ImageView photo;

  public ProductViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_product_detail);
    findViews();
    viewModel = new ProductViewModel();
    Product product = getProductFromIntent();
    Cart cart = getCartFromIntent();

    if(product != null && cart != null){
      viewModel.setProduct(product);

      if(product.getColors()!= null)
      color.setAdapter(new SpinnerColorAdapter(this,R.layout.item_color,
              product.getColors()));

      if(product.getSizes()!= null)
      size.setAdapter(new ArrayAdapter(getApplicationContext(),
              R.layout.item_size ,product.getSizes()));

      description.setText(product.getDescription());

      price.setText(Helper.currencyFormatter(product.getPrice()));

      String actualPurchaseAmount = String.format(
              getString(R.string.actualPurchase),
              Helper.currencyFormatter(cart.getTotal())
      );
      actualPurchase.setText(actualPurchaseAmount);

      photo.setImageResource(getResources().getIdentifier(
                      product.getPhotoName(),
                      "drawable",
                      getPackageName()
              ));
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_product_detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void findViews(){
    color = (Spinner) findViewById(R.id.color);
    size = (Spinner) findViewById(R.id.size);
    description = (TextView) findViewById(R.id.description);
    actualPurchase = (Button) findViewById(R.id.actualPurchase);
    price = (Button) findViewById(R.id.price);
    photo = (ImageView) findViewById(R.id.photo);
  }

  private Product getProductFromIntent() {
    return (Product) getIntent().getSerializableExtra(CatalogActivity
            .SELECTED_PRODUCT);
  }

  public void addItem(View view){
    DialogFragment newFragment = new ItemAmountFragment();
    newFragment.show(getSupportFragmentManager(), ITEM_AMOUNT_FRAGMENT_TAG);
  }

  public void actualPurchase(View view){
    startActivity(new Intent(this, ShoppingCartActivity.class));
  }

  private Cart getCartFromIntent() {
    return (Cart) getIntent().getSerializableExtra(CatalogActivity.CART);
  }

}
