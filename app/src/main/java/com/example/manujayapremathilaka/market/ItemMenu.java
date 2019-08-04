package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ItemMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu);
    }

    public void onAddtoCartButtonPush(View view){
        Intent addToCart = new Intent(ItemMenu.this, Cart.class);
        startActivity(addToCart);
    }

    public void onHomeButtonPush(View view){
        Intent home = new Intent(ItemMenu.this,ItemMenu.class);
        startActivity(home);
    }

    public void onCartButtonPushed(View view){
        Intent cart = new Intent(ItemMenu.this, Cart.class);
        startActivity(cart);
    }
}
