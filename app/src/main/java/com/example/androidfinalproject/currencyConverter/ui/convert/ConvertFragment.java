package com.example.androidfinalproject.currencyConverter.ui.convert;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.androidfinalproject.R;
import com.example.androidfinalproject.currencyConverter.CurrencyDBHelper;
import com.example.androidfinalproject.currencyConverter.CurrencyObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * This class implement convert fragment
 */
public class ConvertFragment extends Fragment {

    public static final String ACTIVITY_NAME = "CURRENCY_CONVERTER";
    //Some url endpoint that you may have
    String myUrl = "https://api.exchangeratesapi.io/latest?base=";
    private ArrayList<CurrencyObject> currencyList = new ArrayList<>();
    JSONObject resp;
    private ProgressBar progressBar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_convert, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        Spinner spinner_currency_in = (Spinner) root.findViewById(R.id.spinner_currency_in);
        Spinner spinner_currency_out = (Spinner) root.findViewById(R.id.spinner_currency_out);

        EditText edt = root.findViewById(R.id.input_amount);
        CurrencyDBHelper dbHelper = new CurrencyDBHelper(this.getActivity());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try {
            CurrencyService getRequest = new CurrencyService();
            String base = "CAD";
            resp = getRequest.execute(myUrl + base).get();
            ArrayList<String> currencySymbol = new ArrayList<>();
            for (Iterator<String> iter = resp.keys(); iter.hasNext(); ) {
                String key = iter.next();
                currencySymbol.add(key);
                currencyList.add(new CurrencyObject(key, resp.getString(key), base));
            }
            initSpinner(spinner_currency_in, currencySymbol);
            initSpinner(spinner_currency_out, currencySymbol);
            spinner_currency_out.setSelection(3);
            edt.setText("1");
            onConvert(root, 1);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Button btn_convert = root.findViewById(R.id.btn_convert);
        btn_convert.setOnClickListener(clik ->
        {
            try {
                onConvert(root, Integer.parseInt(edt.getText().toString()));
            } catch (JSONException | ParseException e) {
                e.printStackTrace();
            }
        });

        Button btn_favourite = root.findViewById(R.id.btn_save);
        btn_favourite.setOnClickListener(click ->{
            ContentValues cv = new ContentValues();
            // a new row in the database
            cv.put(CurrencyDBHelper.COL_BASE, spinner_currency_in.getSelectedItem().toString());
            cv.put(CurrencyDBHelper.COL_RESULT, spinner_currency_out.getSelectedItem().toString());
            long id = db.insert(CurrencyDBHelper.TABLE_FAVOURITE, "NullColumnName", cv);
        });
        return root;
    }

    /**
     * get currency symbol from Spinner and the rate
     * @param root
     * @param amount
     * @throws JSONException
     * @throws ParseException
     */
    private void onConvert(View root, int amount) throws JSONException, ParseException {

        Spinner spinner_currency_in = (Spinner) root.findViewById(R.id.spinner_currency_in);
        Spinner spinner_currency_out = (Spinner) root.findViewById(R.id.spinner_currency_out);

        Double result = convert(amount, spinner_currency_in.getSelectedItem().toString(), spinner_currency_out.getSelectedItem().toString());
        TextView output_amount = root.findViewById(R.id.output_amount);
        output_amount.setText(String.valueOf(result));
    }

    /**
     * Convert amount form one currency to another
     * @param amount
     * @param from
     * @param to
     * @return
     * @throws JSONException
     * @throws ParseException
     */
    private Double convert(int amount, String from, String to) throws JSONException, ParseException {
        DecimalFormat format = new DecimalFormat(".00");
        Double result = amount * resp.getDouble(to) / resp.getDouble(from);
        return (Double) format.parse(format.format(result));
    }

    /**
     * init spinner value which contain currency symbol
     * @param spinner
     * @param currencySymbol
     */
    private void initSpinner(Spinner spinner, ArrayList<String> currencySymbol) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this.getActivity(), android.R.layout.simple_spinner_item, getStringArray(currencySymbol));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    /**
     * Function to convert ArrayList<String> to String[]
     * @param arr
     * @return
     */
    public String[] getStringArray(ArrayList<String> arr) {

        // Convert ArrayList to object array
        Object[] objArr = arr.toArray();

        // convert Object array to String array
        String[] str = Arrays
                .copyOf(objArr, objArr
                                .length,
                        String[].class);

        return str;
    }

    /**
     * This class to call api to get currency
     */
    public class CurrencyService extends AsyncTask<String, Integer, JSONObject> {
        public static final String REQUEST_METHOD = "GET";
        public static final int READ_TIMEOUT = 15000;
        public static final int CONNECTION_TIMEOUT = 15000;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * call api to get data
         * @param params
         * @return
         */
        @Override
        protected JSONObject doInBackground(String... params) {
            String stringUrl = params[0];
            JSONObject result;
            String inputLine;
            try {
                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);
                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                        myUrl.openConnection();
                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();
                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(connection.getInputStream());
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                result = new JSONObject(stringBuilder.toString()).getJSONObject("rates");
            } catch (IOException e) {
                e.printStackTrace();
                result = null;
            } catch (JSONException e) {
                e.printStackTrace();
                result = null;
            }
            return result;
        }

        /**
         * Show toast message after request complete
         * @param jsonObject
         */
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            }, 2000);

            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Successful load currency", Toast.LENGTH_LONG);
            toast.show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

        }
    }
}