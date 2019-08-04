package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class OrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
    }

    public void onHomeButtonPush(View view){
        Intent home = new Intent(OrderList.this,ItemMenu.class);
        startActivity(home);
    }

    public void onCartButtonPushed(View view){
        Intent cart = new Intent(OrderList.this, Cart.class);
        startActivity(cart);
    }
}
