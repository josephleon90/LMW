package com.lunarwolf.market.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lunarwolf.market.view.adapter.CatalogAdapter;
import com.lunarwolf.market.model.Product;
import com.lunarwolf.market.R;
import com.lunarwolf.market.viewmodel.CatalogViewModel;


public class CatalogActivity extends ActionBarActivity {

  public static final String SELECTED_PRODUCT = "SELECTED_PRODUCT";

  private CatalogViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_catalog);

    viewModel = new CatalogViewModel();

    GridView catalog = (GridView) findViewById(R.id.catalogGridview);
    catalog.setAdapter(new CatalogAdapter(this, viewModel.getProducts(this)));

    catalog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      public void onItemClick(AdapterView<?> parent, View v, int position,
                              long id) {
        Intent intent = new Intent(
                CatalogActivity.this,
                ProductDetailActivity.class
        );
        intent.putExtra(SELECTED_PRODUCT, (Product) v.getTag());
        startActivity(intent);
      }
    });
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
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
