package com.example.shivendra.hackaraj.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

/**
 * Created by Shivendra on 15/03/18.
 */

public class Login extends AppCompatActivity {

    private Button login;
    private EditText id;
    private EditText pass;
    private TextView signup;

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

        login = findViewById(R.id.login_button);
        id = findViewById(R.id.loginID);
        pass = findViewById(R.id.password);
        signup = findViewById(R.id.signup_hl);

        radioGroup = findViewById(R.id.radio_group);
        yesRadio = findViewById(R.id.radio_eng);

        yesRadio.setChecked(true);

        signup.setClickable(true);
        signup.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "<a href='https://sso.rajasthan.gov.in/register'> Sign Up </a>";
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
                        //TODO: Setup language support
                        break;
                    }
                    case R.id.radio_hindi:{
                        //TODO: Setup language support
                        break;
                    }
                }
            }
        });

    }
}
