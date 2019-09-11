package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Fruit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
    }
    public void InsertIeamm(View view) {
        Intent newiteam = new Intent(Fruit.this, InsertItem.class);
        startActivity(newiteam);

    }
}
