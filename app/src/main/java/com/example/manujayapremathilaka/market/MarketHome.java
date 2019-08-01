package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MarketHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_home);
    }

    public void pushRegisterButton(View view){
        Intent register = new Intent(MarketHome.this, CustomerRegistration.class);
        startActivity(register);
    }

    public void pushLoginButton(View view){
        Intent login = new Intent(MarketHome.this,ItemMenu.class);
        startActivity(login);
    }
}
