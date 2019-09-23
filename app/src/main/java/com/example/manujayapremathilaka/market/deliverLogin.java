package com.example.manujayapremathilaka.market;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.manujayapremathilaka.market.com.market.model.RegisteredCustomer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class deliverLogin extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deliver_login);


        userName = findViewById(R.id.et_name);
        password = findViewById(R.id.et_name6);
        btn_register = (Button)findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(((userName.getText().toString()).equalsIgnoreCase("admin")) && ((password.getText().toString()).equalsIgnoreCase("admin"))){
                    Intent adminLogin = new Intent(deliverLogin.this, EmployeeHome.class);
                    startActivity(adminLogin);

                }
                else{
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    Query query = databaseReference.child("DiliverMember").orderByChild("name").equalTo(userName.getText().toString());
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot d: dataSnapshot.getChildren()){
                                DiliverMember diliverMember = d.getValue(DiliverMember.class);
                                if (diliverMember.getPassowrd().equals(password.getText().toString())) {
                                    Intent login = new Intent(deliverLogin.this, getOrder.class);
                                    startActivity(login);
                                    Toast.makeText(getApplicationContext(),"Login Successfully",Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                                    Intent login = new Intent(deliverLogin.this, deliverLogin.class);
                                    startActivity(login);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });



                }
            }
        });
    }

//    public void GetOrder(View view){
//        Intent getOrder = new Intent(deliverLogin.this, getOrder.class);
//        startActivity(getOrder);
//    }






}
