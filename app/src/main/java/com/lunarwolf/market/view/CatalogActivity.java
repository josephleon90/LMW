package com.lunarwolf.market.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.lunarwolf.market.R;
import com.lunarwolf.market.model.CartItem;
import com.lunarwolf.market.model.Product;
import com.lunarwolf.market.util.Helper;
import com.lunarwolf.market.view.adapter.CatalogAdapter;
import com.lunarwolf.market.viewmodel.CatalogViewModel;


public class CatalogActivity extends ActionBarActivity {

  public static final String SELECTED_PRODUCT = "SELECTED_PRODUCT";
  public static final int ITEM_DETAIL_REQUEST = 1;
  public static final String CART_EXTRA = "cart";

  private CatalogViewModel viewModel;

  private Button actualPurchase;
  private GridView catalog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_catalog);
    findViews();

    viewModel = new CatalogViewModel();

    catalog.setAdapter(new CatalogAdapter(this, viewModel.getProducts(this)));
    catalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v, int position,
                              long id) {
        Intent intent = new Intent(
                CatalogActivity.this,
                ProductActivity.class
        );
        intent.putExtra(SELECTED_PRODUCT, (Product) v.getTag());
        intent.putExtra(CART_EXTRA, viewModel.getCart());
        startActivityForResult(intent, ITEM_DETAIL_REQUEST);
      }
    });
  }


  @Override
  protected void onResume() {
    super.onResume();
    String actualPurchaseAmount = String.format(
            getString(R.string.actualPurchase),
            Helper.currencyFormatter(viewModel.getCart().getTotal())
    );
    actualPurchase.setText(actualPurchaseAmount);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_catalog, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.ok) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode,
                                  Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (resultCode == Activity.RESULT_OK) {

      if (requestCode == ITEM_DETAIL_REQUEST) {
        CartItem item = (CartItem) data.getSerializableExtra(ProductActivity
                .ITEM);
        viewModel.addItemToPurchase(item);
      }
    } else if (resultCode == Activity.RESULT_CANCELED)
      Log.w("RESULT CANCELED", CatalogActivity.class.getSimpleName());
  }

  private void findViews(){
    catalog = (GridView) findViewById(R.id.catalogGridview);
    actualPurchase = (Button) findViewById(R.id.actualPurchase);
  }

  public void actualPurchase(View view){
    Intent intent = new Intent(this, ShoppingCartActivity.class);
    intent.putExtra(CART_EXTRA, viewModel.getCart());
    startActivity(intent);
  }
}
