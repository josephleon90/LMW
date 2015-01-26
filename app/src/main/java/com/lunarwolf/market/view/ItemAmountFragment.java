package com.lunarwolf.market.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.lunarwolf.market.R;
import com.lunarwolf.market.model.ItemCart;
import com.lunarwolf.market.model.Product;

/**
 * Created by josephleon on 1/25/15.
 */
public class ItemAmountFragment extends DialogFragment {

  private ProductActivity act;
  private EditText amount;

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    act = (ProductActivity) getActivity();
    LayoutInflater inflater = act.getLayoutInflater();
    View view = inflater.inflate(R.layout.fragment_amount, null);
    amount = (EditText) view.findViewById(R.id.editText);

    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder.setTitle(getString(R.string.enterAmount));
    builder.setView(view).setPositiveButton(R.string.accept,
            new ConfirmClick())
            .setNegativeButton(R.string.cancel, new CancelClick());
    return builder.create();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    //Show keyboard
    amount.requestFocus();
    getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams
            .SOFT_INPUT_STATE_VISIBLE);
    return super.onCreateView(inflater, container, savedInstanceState);
  }


  final private class ConfirmClick implements DialogInterface.OnClickListener {

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      String amountString = amount.getText().toString();

      if (amountString.equals("")) {
        Toast.makeText(
                act,
                getString(R.string.incorrectAmount),
                Toast.LENGTH_LONG
        ).show();
      } else {

        Intent intent = new Intent(act, ItemAmountFragment.class);
        Product product = act.viewModel.getProduct();
        int amountInt = Integer.parseInt(amountString);
        ItemCart itemCart = new ItemCart(amountInt, product);
        intent.putExtra(ProductActivity.ITEM, itemCart);
        act.setResult(Activity.RESULT_OK, intent);
        act.finish();

        Toast.makeText(
                act,
                getString(R.string.itemAdded),
                Toast.LENGTH_LONG
        ).show();
      }
    }
  }

  final private class CancelClick implements DialogInterface.OnClickListener {
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
      dialogInterface.cancel();
    }
  }

}
