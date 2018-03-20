package com.example.shivendra.hackaraj.Fragments;


import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.shivendra.hackaraj.Login.Login;
import com.example.shivendra.hackaraj.R;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class FifthFragment extends Fragment {

     private ToggleButton change;
     Locale myLocale;
     String url = "https://bot.dialogflow.com/a875122e-e0ca-4d7f-8539-d2cd43dd2f18";


    public FifthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fifth, container, false);

        change = view.findViewById(R.id.button_lang);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
            //}

            if(change.getTextOff() == "English"){
                setLocale("en");
                change.setChecked(false);
            }else {
                setLocale("hi");
                change.setChecked(true);
            }

            }
        });

        return view;


    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}
