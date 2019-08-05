package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class getOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order);
    }

    public void AdminHomeLog(View view){
        Intent loadprofile = new Intent(getOrder.this, profile.class);
        startActivity(loadprofile);
    }
}
