package com.example.androidfinalproject.currencyConverter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidfinalproject.R;
import com.example.androidfinalproject.currencyConverter.ui.convert.ConvertFragment;
import com.example.androidfinalproject.currencyConverter.ui.save.SaveFragment;

import java.util.ArrayList;

/**
 * This class implement adapter for currency listview
 */
public class CurrencyConverterAdapter extends BaseAdapter {
    private ArrayList<CurrencyFavourite> currencyList;
    private SaveFragment mContext;

    public CurrencyConverterAdapter(SaveFragment context, ArrayList<CurrencyFavourite> currencyList) {
        super();
        this.mContext = context;
        this.currencyList = currencyList;
    }

    /**
     * This method count number of item
     * @return
     */
    public int getCount() {
        return this.currencyList.size();  } //This function tells how many objects to show

    /**
     * This method get Item at position
     * @param position
     * @return
     */
    public CurrencyFavourite getItem(int position) {
        return this.currencyList.get(position);  }  //This returns the string at position p

    public long getItemId(int p) {
        return p; } //This returns the database id of the item at position p

    /**
     * This method get view
     * @param p
     * @param recycled
     * @param parent
     * @return
     */
    public View getView(int p, View recycled, ViewGroup parent)
    {
        View thisRow = recycled;
        CurrencyFavourite currencyFavourite = getItem(p);
        thisRow = ((SaveFragment) this.mContext).getLayoutInflater().inflate(R.layout.currency_row_layout, null);


        TextView txt_base = thisRow.findViewById(R.id.txt_base);
        txt_base.setText(currencyFavourite.getBase());

        TextView txt_currency = thisRow.findViewById(R.id.txt_currency);
        txt_currency.setText(currencyFavourite.getResult());

        TextView txt_rate = thisRow.findViewById(R.id.txt_rate);
        txt_rate.setText("<=>");
        Button btn_delete = thisRow.findViewById(R.id.btn_delete);
        View finalThisRow = thisRow;
        btn_delete.setOnClickListener(click ->{
            AlertDialog.Builder builder = new AlertDialog.Builder(finalThisRow.getContext());
            builder.setMessage(R.string.dialog_delete_message)
                    .setTitle(R.string.dialog_title);
            builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User clicked OK button
                }
            });
            builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });
            builder.show();
        });
        thisRow.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("aa","bbb");
        }
    });
        return thisRow;
    }
}
