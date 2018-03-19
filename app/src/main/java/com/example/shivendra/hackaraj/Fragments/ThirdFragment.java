package com.example.shivendra.hackaraj.Fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.shivendra.hackaraj.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdFragment extends Fragment {
    //    JSONObject veggiesData;
//    JSONArray veggiesListJ;
    Spinner city;
    Spinner item;
    Button citySelect;
    ListView listVeg;
    String[] vegNPrice;
    ArrayAdapter adapter;
    ProgressDialog mProgressDialog;

    public ThirdFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
//        veggiesData = new JSONObject();
//        veggiesListJ = new JSONArray();
        city = view.findViewById(R.id.spinner);
        item = view.findViewById(R.id.spinner1);
        listVeg = view.findViewById(R.id.vegList);
        citySelect = view.findViewById(R.id.citySelect);
        citySelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedCity = city.getSelectedItem().toString();
                String selectedItem = item.getSelectedItem().toString();
                new JsoupAsyncTask().execute(selectedCity,selectedItem);
//                while (vegNPrice == null){}
//                if(vegNPrice != null){
//                    adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,vegNPrice);
//                    listVeg.setAdapter(adapter);
//                }
            }
        });
        return view;
    }

    private class JsoupAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("Fetching Data");
            mProgressDialog.setMessage("Hold your horses...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String userAgnt = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
            String url = "http://market.todaypricerates.com/" + strings[0] + "-" + strings[1] + "-price-in-Rajasthan";
//            Log.i("T",url);
            Document doc = null;
            try {
                doc = Jsoup.connect(url)
                        .userAgent(userAgnt).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Element table = doc.select("div.Table").first();
            Elements veggiesList = table.select("div.Row");
            vegNPrice = new String[veggiesList.size()];
//            try {
            int i=-1;
            for (Element veg : veggiesList) {
                Elements vegData = veg.select("div.Cell");
//                    JSONObject temp = new JSONObject();
//                    temp.put("VegName", vegData.eq(0).text());
//                    temp.put("MarketPrice", vegData.eq(2).text().replaceAll("[^a-zA-Z0-9\\s]", ""));
//                    String[] temp1 = vegData.eq(4).text().replaceAll("[^a-zA-Z0-9\\s]", "").split(" ");
//                    String temp2 = temp1[1] + " - " + temp1[3];
//                    temp.put("MallPrice", temp2);
//                    veggiesListJ.put(temp);
                vegNPrice[++i] = vegData.eq(0).text() + " - ₹" + vegData.eq(2).text().replaceAll("[^a-zA-Z0-9\\s]", "");
            }
//                veggiesData.put("Vegetables", veggiesListJ);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
            return vegNPrice[1];
        }

        @Override
        protected void onPostExecute(String result) {
            mProgressDialog.dismiss();
            if(vegNPrice != null){
                adapter = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,vegNPrice);
                listVeg.setAdapter(adapter);
            }
//            try {
//                Log.d("checkJSON", vegNPrice[0]);
//                Log.d("checkJSON", veggiesData.getJSONArray("Vegetables").toString());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
        }
    }
}