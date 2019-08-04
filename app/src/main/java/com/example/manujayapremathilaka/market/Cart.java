package com.example.manujayapremathilaka.market;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class Cart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

    }

    public void onHomeButtonPush(View view){
        Intent home = new Intent(Cart.this,ItemMenu.class);
        startActivity(home);
    }

    public void onCartButtonPushed(View view){
        Intent cart = new Intent(Cart.this, Cart.class);
        startActivity(cart);
    }

    public void onOrderListButtonPush(View view){
        Context context = getApplicationContext();
        CharSequence text = "Order Request Sent";
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.BOTTOM,0,0);
        toast.show();

        Intent orderList = new Intent(Cart.this, OrderList.class);
        startActivity(orderList);
    }


}
