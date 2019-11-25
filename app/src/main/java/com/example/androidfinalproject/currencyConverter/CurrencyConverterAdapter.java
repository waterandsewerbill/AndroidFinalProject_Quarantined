package com.example.androidfinalproject.currencyConverter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.androidfinalproject.R;
import com.example.androidfinalproject.currencyConverter.ui.convert.ConvertFragment;
import com.example.androidfinalproject.currencyConverter.ui.save.SaveFragment;

import java.util.ArrayList;

/**
 * This class implement adapter for currency listview
 * @author :Wajahat
 */
public class CurrencyConverterAdapter extends BaseAdapter {
    private final ArrayList<CurrencyFavourite> currencyList;
    private SaveFragment mContext;

    public CurrencyConverterAdapter(SaveFragment context, ArrayList<CurrencyFavourite> currencyList) {
        super();
        this.mContext = context;
        this.currencyList = currencyList;
    }

    /**
     * This method count number of item
     *
     * @return
     */
    public int getCount() {
        return this.currencyList.size();
    } //This function tells how many objects to show

    /**
     * This method get Item at position
     *
     * @param position
     * @return
     */
    public CurrencyFavourite getItem(int position) {
        return this.currencyList.get(position);
    }  //This returns the string at position p

    public long getItemId(int p) {
        return p;
    } //This returns the database id of the item at position p

    /**
     * This method get view
     * Show list of favourite currency and delete, view detail a currency
     * @param p
     * @param currencyView
     * @param parent
     * @return
     */
    public View getView(int p, View currencyView, ViewGroup parent) {
        View thisRow = currencyView;
        CurrencyFavourite currencyFavourite = getItem(p);
        thisRow = ((SaveFragment) this.mContext).getLayoutInflater().inflate(R.layout.currency_row_layout, null);


        TextView txt_base = thisRow.findViewById(R.id.txt_base);
        txt_base.setText(currencyFavourite.getBase());

        TextView txt_currency = thisRow.findViewById(R.id.txt_currency);
        txt_currency.setText(currencyFavourite.getResult());

        TextView txt_rate = thisRow.findViewById(R.id.txt_rate);
        txt_rate.setText("<=>");
        Button btn_delete = thisRow.findViewById(R.id.btn_delete);
        View copyRow = thisRow;

        CurrencyDBHelper dbHelper = new CurrencyDBHelper((Activity) copyRow.getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        btn_delete.setOnClickListener(click -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(copyRow.getContext());
            builder.setMessage(R.string.dialog_delete_message)
                    .setTitle(R.string.dialog_title);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    db.delete(dbHelper.TABLE_FAVOURITE, dbHelper.COL_ID + " = ?", new String[]{String.valueOf(currencyFavourite.getId())});
                    currencyList.remove(currencyFavourite);
                    notifyDataSetChanged();
                    Toast.makeText(copyRow.getContext(),
                            R.string.toast_deleted, Toast.LENGTH_LONG)
                            .show();
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the delete dialog
                }
            });
            builder.show();
        });

        /**
         * Listen item on click. Send selected item to convertFragment to do convert
         */
        thisRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("currency", Context.MODE_PRIVATE);
                SharedPreferences.Editor sharePreferencesEdit = sharedPreferences.edit();
                sharePreferencesEdit.putFloat("amount", 1);
                sharePreferencesEdit.putString("base", currencyFavourite.getBase());
                sharePreferencesEdit.putString("result", currencyFavourite.getResult());
                sharePreferencesEdit.commit();
                // Retrieve the NavController from any Fragment created by a NavHostFragment by passing in Context
                final NavController navController = NavHostFragment.findNavController(mContext);
                // Alternatively, retrieve the NavController from any View within the NavHostFragment
                final NavController viewNavController = Navigation.findNavController(v);
                // And set the listener
                navController.navigate(R.id.nav_home);
            }
        });
        return thisRow;
    }
}
