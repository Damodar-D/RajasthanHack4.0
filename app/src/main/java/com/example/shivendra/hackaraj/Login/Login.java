package com.example.shivendra.hackaraj.Login;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.shivendra.hackaraj.ContentPage;
import com.example.shivendra.hackaraj.R;

import java.util.Locale;


public class Login extends AppCompatActivity {

    private Button login;
    private EditText id;
    private EditText pass;
    private TextView signup;

    Locale myLocale;

    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private RadioButton yesRadio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();
        setContentView(R.layout.login_page);

        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.login_layout);
        final AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        login = findViewById(R.id.login_button);
        id = findViewById(R.id.loginID);
        pass = findViewById(R.id.password);
        signup = findViewById(R.id.signup_hl);

        radioGroup = findViewById(R.id.radio_group);
        yesRadio = findViewById(R.id.radio_eng);

        //yesRadio.setChecked(true);

        signup.setClickable(true);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://sso.rajasthan.gov.in/register'>"+ getString(R.string.signup)+"</a>";
        signup.setText(Html.fromHtml(text));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ContentPage.class);
                startActivity(i);
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = findViewById(checkedId);
                switch (radioButton.getId()){
                    case R.id.radio_eng:{
                        setLocale("en");
                        break;
                    }
                    case R.id.radio_hindi:{
                        setLocale("hi");
                        break;
                    }
                }
            }
        });

    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Login.class);
        refresh.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(refresh);
    }

}
