package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TakeOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_order);
    }

    public void orderClick1(View view)
    {
        Intent orderClick1 = new Intent(this,FillOrder.class);
        startActivity(orderClick1);
    }

    public void orderClick2(View view)
    {
        Intent orderClick2 = new Intent(this,FillOrder.class);
        startActivity(orderClick2);
    }

    public void orderClick3(View view)
    {
        Intent orderClick3 = new Intent(this,FillOrder.class);
        startActivity(orderClick3);
    }

    public void orderClick4(View view)
    {
        Intent orderClick4 = new Intent(this,FillOrder.class);
        startActivity(orderClick4);
    }

    public void orderClick5(View view)
    {
        Intent orderClick5 = new Intent(this,FillOrder.class);
        startActivity(orderClick5);
    }
}
