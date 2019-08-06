package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class FillOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_order);
    }

    public void orderClick6(View view)
    {
        Intent orderClick6 = new Intent(this,OrderSuccess.class);
        startActivity(orderClick6);
    }
}
