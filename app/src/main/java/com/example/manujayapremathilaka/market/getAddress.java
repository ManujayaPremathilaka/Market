package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class getAddress extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);
    }

    public void ReloadGetOrder(View view){
        Intent getOrder = new Intent(getAddress.this, getOrder.class);
        startActivity(getOrder);
    }
}
