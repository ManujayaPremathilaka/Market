package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class deliverLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_login);
    }

    public void GetOrder(View view){
        Intent getOrder = new Intent(deliverLogin.this, getOrder.class);
        startActivity(getOrder);
    }
}
