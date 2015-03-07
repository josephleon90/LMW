package com.lunarwolf.market.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.lunarwolf.market.R;
import com.lunarwolf.market.mailclient.GmailSender;
import com.lunarwolf.market.model.Cart;
import com.lunarwolf.market.model.CartItem;
import com.lunarwolf.market.model.Customer;

public class ContactFormActivity extends ActionBarActivity {

  private EditText name;
  private EditText address;
  private EditText phone;
  private EditText email;

  private Customer customer;
  private Cart cart;

  private ProgressDialog progressDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_form);
    findViews();
    cart = getOrderFromIntent();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_contact_form, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.ok) {
      shouldSendEmail();
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  private void findViews() {
    name = (EditText) findViewById(R.id.name);
    address = (EditText) findViewById(R.id.address);
    phone = (EditText) findViewById(R.id.phone);
    email = (EditText) findViewById(R.id.email);
  }

  private Customer getCustomerFromLayout() {
    return new Customer(
            name.getText().toString(),
            address.getText().toString(),
            phone.getText().toString(),
            email.getText().toString()
    );
  }

  public Cart getOrderFromIntent() {
    return (Cart) getIntent().getSerializableExtra(CatalogActivity.CART_EXTRA);
  }

  public void showProgressDialog() {
    progressDialog = ProgressDialog.show(
            this,
            getString(R.string.processing),
            getString(R.string.pleaseWait)
    );
  }

  public void dismissProgressDialog() {
    progressDialog.dismiss();
  }

  public void shouldSendEmail() {
    String validationResult = validateForm();

    if (!validationResult.isEmpty())
      showMessage(validationResult, getString(R.string.order));
    else if (!isNetworkAvailable())
      showMessage(getString(R.string.noNetwork), getString(R.string.order));
    else {
      customer = getCustomerFromLayout();
      showProgressDialog();
      new SendEmailTask().execute(customer, cart);
    }
  }

  public String validateForm() {
    String nameS = name.getText().toString();
    String addressS = address.getText().toString();
    String phoneS = phone.getText().toString();
    String emailS = email.getText().toString();

    if (nameS.isEmpty())
      return getString(R.string.noValidName);
    else if (addressS.isEmpty())
      return getString(R.string.noValidAddress);
    if (phoneS.isEmpty())
      return getString(R.string.novalidPhone);
    else if (emailS.isEmpty())
      return getString(R.string.novalidEmail);
    else return "";
  }

  public String buildEmailBody(Customer customer, Cart cart) {

    String customerDetail = "Información del Cliente\n";
    customerDetail += "Nombres: " + customer.getName() + "\n";
    customerDetail += "Teléfono: " + customer.getPhone() + "\n";
    customerDetail += "Dirección: " + customer.getAddress() + "\n";
    customerDetail += "Email: " + customer.getEmail() + "\n\n";

    String orderDetail = "Detalle del Pedido\n";
    String detail = "";
    for (CartItem item : cart.getItems()) {
      String itemLine = item.getProduct().getDescription() + " x" + item.getAmount() + "\n";
      detail += itemLine;
    }
    orderDetail += detail;
    return customerDetail + orderDetail;
  }

  public void showMessage(final String message, String tittle) {
    AlertDialog.Builder builder = new AlertDialog.Builder(ContactFormActivity.this);
    builder.setTitle(tittle);
    builder.setMessage(message);
    builder.setCancelable(true);
    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int id) {
        if (message.equals(getString(R.string.orderSentsuccessfully))) {
          Intent i = new Intent(ContactFormActivity.this, CatalogActivity.class);
          i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent
                  .FLAG_ACTIVITY_CLEAR_TASK);
          startActivity(i);
        } else
          dialog.cancel();
      }
    });

    AlertDialog alert = builder.create();
    alert.show();
  }

  private boolean isNetworkAvailable() {
    ConnectivityManager connectivityManager
            = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }

  private class SendEmailTask extends AsyncTask<Object, Void, String> {

    @Override
    protected String doInBackground(Object... params) {
      Customer cus = (Customer) params[0];
      Cart cart = (Cart) params[1];
      String body = buildEmailBody(cus, cart);
      try {
        GmailSender sender = new GmailSender("lunarwolfmarket@gmail.com", "lwmarket");
        sender.sendMail(
                "Nuevo pedido de LunarWolf Market",
                body,
                "lunarwolfmarket@gmail.com",
                "joseph_jiy@hotmail.com"
        );
      } catch (Exception e) {
        Log.e("SendMail", e.getMessage(), e);
        return getString(R.string.sorryOrderCouldntSend);
      }

      return getString(R.string.orderSentsuccessfully);
    }

    @Override
    protected void onPostExecute(String result) {
      dismissProgressDialog();
      showMessage(result, getString(R.string.order));
    }
  }
}
