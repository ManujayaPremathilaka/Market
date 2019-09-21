package com.example.manujayapremathilaka.market;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InsertItem extends AppCompatActivity {
    EditText IeamID,IteamName,QTY,price;
    Button Add;
    DatabaseReference db;
    Iteam ITM;
    long maxid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item);

      //edittext
                IeamID = (EditText) findViewById(R.id.et_IteamId);
        IteamName = (EditText) findViewById(R.id.et_Name);
        QTY = (EditText) findViewById(R.id.et_QTY);
        price = (EditText) findViewById(R.id.et_price);
        //button
        Add = (Button) findViewById(R.id.Addnew);
        ITM = new Iteam();
        db = FirebaseDatabase.getInstance().getReference().child("Item");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    try{


                        if(TextUtils.isEmpty(IeamID.getText().toString()))
                            Toast.makeText(getApplicationContext(),"Please Enter a ID",Toast.LENGTH_SHORT).show();
                        else if(TextUtils.isEmpty(IteamName.getText().toString()))
                            Toast.makeText(getApplicationContext(),"Please Enter a Name",Toast.LENGTH_SHORT).show();
                        else if(TextUtils.isEmpty(QTY.getText().toString()))
                            Toast.makeText(getApplicationContext(),"Please Enter Quantity",Toast.LENGTH_SHORT).show();
                        else if(TextUtils.isEmpty(price.getText().toString()))
                            Toast.makeText(getApplicationContext(),"Please Enter price",Toast.LENGTH_SHORT).show();
                        else {
                                       ITM.setID(IeamID.getText().toString().trim());
                                       ITM.setName(IteamName.getText().toString().trim());
                                       ITM.setQTY(QTY.getText().toString().trim());
                                       ITM.setPrice(price.getText().toString().trim());


                                       db.child(String.valueOf(maxid + 1)).setValue(ITM);
                                       Toast.makeText(InsertItem.this, "successfull Adding", Toast.LENGTH_LONG).show();


                        }
                    }
                    catch (NumberFormatException e){
                        Toast.makeText(getApplicationContext(),"Invalid Contact number",Toast.LENGTH_SHORT).show();
                    }


            }
        });
    }
}
