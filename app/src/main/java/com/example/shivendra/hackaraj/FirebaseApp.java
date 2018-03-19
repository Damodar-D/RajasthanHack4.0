package com.example.shivendra.hackaraj;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Subham on 17-03-2018.
 */

public class FirebaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
