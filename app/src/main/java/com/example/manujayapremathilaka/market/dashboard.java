package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void LaodVegPAge(View view){
        Intent vegpage = new Intent(dashboard.this, Veg.class);
        startActivity(vegpage);
    }
    public void LaodfruitPAge(View view){
        Intent frpage = new Intent(dashboard.this, Fruit.class);
        startActivity(frpage);
    }
    public void LaodfishmeatPAge(View view){
        Intent fishrpage = new Intent(dashboard.this, FishMeat.class);
        startActivity(fishrpage);
    }
}
