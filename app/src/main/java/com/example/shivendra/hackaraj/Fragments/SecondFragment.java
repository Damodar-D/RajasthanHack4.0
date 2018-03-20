package com.example.shivendra.hackaraj.Fragments;


import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shivendra.hackaraj.R;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.Context.VIBRATOR_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
    Spinner spin;
    EditText dat1;
    EditText dat2;
    EditText dat3;
    EditText dat4;
    Button ok;
    TextView disp;
    Data data;
    Vibrator vibrator;
    DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    String dispVal;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        spin = view.findViewById(R.id.eai_sp);
        dat1 = view.findViewById(R.id.temp);
        dat2 = view.findViewById(R.id.m1);
        dat3 = view.findViewById(R.id.m2);
        dat4 = view.findViewById(R.id.m3);
        disp = view.findViewById(R.id.eai_tv);
        ok = view.findViewById(R.id.eai_but);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spin.getSelectedItem().equals("Manual Entry")){
                    int dat1_ = Integer.parseInt(dat1.getText().toString());
                    int dat2_ = Integer.parseInt(dat2.getText().toString());
                    int dat3_ = Integer.parseInt(dat3.getText().toString());
                    int dat4_ = Integer.parseInt(dat4.getText().toString());

                    data = new Data(1,dat1_, dat2_, dat3_, dat4_);
                    root.child("eAI").setValue(data);

                    if(dispVal!=null){
                        disp.setText(getString(R.string.energy_val)+":\n" + dispVal);
                    }

                }else {

                }
            }
        });

        root.child("eAIUpdates").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue().toString() != null){
                    dispVal = dataSnapshot.getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

class Data{
    public int Activate;
    public int Temp;
    public int Month1;
    public int Month2;
    public int Month3;

    public Data(int activate, int temp, int month1, int month2, int month3) {
        Activate = activate;
        Temp = temp;
        Month1 = month1;
        Month2 = month2;
        Month3 = month3;
    }

    public Data() {
    }
}
