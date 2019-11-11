package com.example.androidfinalproject.currencyConverter.ui.save;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidfinalproject.R;
import com.example.androidfinalproject.currencyConverter.CurrencyConverterAdapter;
import com.example.androidfinalproject.currencyConverter.CurrencyDBHelper;
import com.example.androidfinalproject.currencyConverter.CurrencyFavourite;

import java.util.ArrayList;

/**
 * This class implement save fragment
 */
public class SaveFragment extends Fragment {

    BaseAdapter myAdapter;
    private ArrayList<CurrencyFavourite> currencyList = new ArrayList<>();

    /**
     * Get data favourite currency from database
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_save, container, false);

        CurrencyDBHelper dbHelper = new CurrencyDBHelper(this.getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //query all the results from the database:
        String [] columns = {CurrencyDBHelper.COL_ID, CurrencyDBHelper.COL_BASE, CurrencyDBHelper.COL_RESULT};
        Cursor results = db.query(false, CurrencyDBHelper.TABLE_FAVOURITE, columns, null, null, null, null, null, null);

        //find the column indices:
        int baseColumnIndex = results.getColumnIndex(CurrencyDBHelper.COL_BASE);
        int resultColIndex = results.getColumnIndex(CurrencyDBHelper.COL_RESULT);
        int idColIndex = results.getColumnIndex(CurrencyDBHelper.COL_ID);

        String [] colNames = results.getColumnNames();

        //iterate over the results, return true if there is a next item:
        while(results.moveToNext())
        {
            String result = results.getString(resultColIndex);
            String base = results.getString(baseColumnIndex);
            long id = results.getLong(idColIndex);

            //add the new Contact to the array list:
            currencyList.add(new CurrencyFavourite(base, result, id));
        }

        ListView theList = root.findViewById(R.id.lv_currency);
        theList.setAdapter(myAdapter = new CurrencyConverterAdapter(this, currencyList));
        theList.setClickable(true);
        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(),
                        "Click ListItem Number : " + position, Toast.LENGTH_LONG)
                        .show();
            }
        });
        myAdapter.notifyDataSetChanged();

        return root;
    }

}