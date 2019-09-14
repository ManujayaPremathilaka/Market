package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class getAddress extends AppCompatActivity {

    TextView et_name,et_name2,et_name3,et_name4;
    Button btn_showde;
    DatabaseReference reff;
    DiliverMember diliverMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_address);

        et_name = (TextView)findViewById(R.id.et_name) ;
        et_name2 = (TextView)findViewById(R.id.et_name2) ;
        et_name3 = (TextView)findViewById(R.id.et_name3) ;
        et_name4 = (TextView)findViewById(R.id.et_name4) ;
        btn_showde = (Button) findViewById(R.id.btn_showde) ;
        diliverMember = new DiliverMember();

        btn_showde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff= FirebaseDatabase.getInstance().getReference().child("RegisteredCustomers").child("971380616V");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name =dataSnapshot.child("firstName").getValue().toString();
                        String address =dataSnapshot.child("address").getValue().toString();
                        String email =dataSnapshot.child("email").getValue().toString();
                        String phone =dataSnapshot.child("mobileNumber").getValue().toString();

                        et_name.setText(name);
                        et_name4.setText(email);
                        et_name2.setText(address);
                        et_name3.setText(phone);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

    public void ReloadGetOrder(View view){
        Intent getOrder = new Intent(getAddress.this, getOrder.class);
        startActivity(getOrder);
    }
}
