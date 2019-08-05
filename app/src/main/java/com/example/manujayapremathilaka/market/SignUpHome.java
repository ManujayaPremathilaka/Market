package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignUpHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_home);
    }

    public void Loadregister(View view) {
        Intent register = new Intent(SignUpHome.this, Register.class);
        startActivity(register);
    }

    public void LoardLogin(View view){
        Intent loardlogin = new Intent(SignUpHome.this, deliverLogin.class);
        startActivity(loardlogin);
    }
}
