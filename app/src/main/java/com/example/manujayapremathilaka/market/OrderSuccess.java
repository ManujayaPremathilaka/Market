package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OrderSuccess extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
    }

    public void orderClick7(View view)
    {
        Intent orderClick7 = new Intent(this,TakeOrder.class);
        startActivity(orderClick7);
    }
}
