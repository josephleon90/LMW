package com.lunarwolf.market.view;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lunarwolf.market.R;

public class ContactActivity extends ActionBarActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact);
  }

  public void handleSocialNetworks(View view){
    switch (view.getId()){
      case R.id.facebook:
        openByBrowser(getString(R.string.facebookPage));
        break;
      case R.id.twitter:
        openByBrowser(getString(R.string.twitterPage));
        break;
      case R.id.instagram:
        openByBrowser(getString(R.string.instagramPage));
        break;
    }
  }

  private void openByBrowser(String string) {
    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(string));
    startActivity(browserIntent);
  }
}
