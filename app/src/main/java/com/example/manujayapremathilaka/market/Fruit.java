package com.example.manujayapremathilaka.market;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.database.DatabaseReference;

public class Fruit extends AppCompatActivity {
    public class Veg extends AppCompatActivity {
        EditText a1, b1, c1, d1, a2, b2, c2, d2;
        Button view1, view2, Up1, Up2;
        DatabaseReference db;
        Iteam ITMM;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fruit);

            a1 = (EditText) findViewById(R.id.id);
            b1 = (EditText) findViewById(R.id.name);
            c1 = (EditText) findViewById(R.id.price);
            d1 = (EditText) findViewById(R.id.qty);
            view1 = (Button) findViewById(R.id.View1);
            a2 = (EditText) findViewById(R.id.id2);
            b2 = (EditText) findViewById(R.id.name2);
            c2 = (EditText) findViewById(R.id.price2);
            d2 = (EditText) findViewById(R.id.qty2);
            view2 = (Button) findViewById(R.id.View2);
            Up1 = (Button) findViewById(R.id.update1);
            Up2 = (Button) findViewById(R.id.Update2);


        }
    }
}





