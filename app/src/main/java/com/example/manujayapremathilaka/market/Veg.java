package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Veg extends AppCompatActivity {
    EditText a1,b1,c1,d1,a2,b2,c2,d2;
    Button view1,view2;
    DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg);

        a1 = (EditText) findViewById(R.id.id);
        b1 = (EditText)findViewById(R.id.name);
        c1 = (EditText)findViewById(R.id.price);
        d1 = (EditText)findViewById(R.id.qty);
        view1 = (Button)findViewById(R.id.View1);

        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Iteam").child("-LoALPZfPEnCHZzWUtvp");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String id = dataSnapshot.child("id").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String price = dataSnapshot.child("price").getValue().toString();
                        String qty = dataSnapshot.child("qty").getValue().toString();

                        a1.setText(id);
                        b1.setText(name);
                        c1.setText(price);
                        d1.setText(qty);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        a2 = (EditText)findViewById(R.id.id2);
        b2 = (EditText)findViewById(R.id.name2);
        c2 = (EditText)findViewById(R.id.price2);
        d2 = (EditText)findViewById(R.id.qty2);
        view2 = (Button)findViewById(R.id.View2);

    }
    public void InsertIeam(View view){
        Intent newiteam = new Intent(Veg.this, InsertItem.class);
        startActivity(newiteam);
    }
}
