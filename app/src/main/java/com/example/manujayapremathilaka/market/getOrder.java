package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manujayapremathilaka.market.com.market.model.RegisteredCustomer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class getOrder extends AppCompatActivity {

    EditText orderID;

    Button btnOrder;
    DatabaseReference reference;

//    DiliverMember diliverMember = new DiliverMember();
    DiliverMember diliverMember;
    RegisteredCustomer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_order);

        orderID = findViewById(R.id.et_name);

        btnOrder = findViewById(R.id.btn_register);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                reference = FirebaseDatabase.getInstance().getReference().child("RegisteredCustomers").child(orderID.getText().toString());

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        customer = dataSnapshot.getValue(RegisteredCustomer.class);
                        if(dataSnapshot.hasChildren()){

                            Intent loadViewAddress = new Intent(getOrder.this, getAddress.class);
                            loadViewAddress.putExtra("NIC", customer.getNIC());
                            startActivity(loadViewAddress);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void LoadProfile(View view){
        Intent loadprofile = new Intent(getOrder.this, profile.class);
        startActivity(loadprofile);
    }





    public void LoadViewAddress(View view){

        Intent loadViewAddress = new Intent(getOrder.this, getAddress.class);
        loadViewAddress.putExtra("NIC", diliverMember.getNic());
        startActivity(loadViewAddress);
    }
}
