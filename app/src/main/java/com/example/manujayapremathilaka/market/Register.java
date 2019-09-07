package com.example.manujayapremathilaka.market;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    EditText et_name,et_email,et_contactno,et_nic,et_vehicalno,et_passowrd,et_repassword;
    Button btn_register;
    DatabaseReference reff;
    DiliverMember diliverMember;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_name = (EditText)findViewById(R.id.et_name);
        et_email =(EditText)findViewById(R.id.et_email);
        et_contactno =(EditText)findViewById(R.id.et_contactno);
        et_nic =(EditText)findViewById(R.id.et_nic);
        et_vehicalno=(EditText)findViewById(R.id.et_vehicalno);
        et_passowrd=(EditText)findViewById(R.id.et_passowrd);
        et_repassword=(EditText)findViewById(R.id.et_repassword);
        btn_register =(Button)findViewById(R.id.btn_register);
        diliverMember = new DiliverMember();
        reff = FirebaseDatabase.getInstance().getReference().child("DiliverMember");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid =(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diliverMember.setName(et_name.getText().toString().trim());
                diliverMember.setEmail(et_email.getText().toString().trim());
                diliverMember.setPhone(et_contactno.getText().toString().trim());
                diliverMember.setNic(et_nic.getText().toString().trim());
                diliverMember.setVehicalno(et_vehicalno.getText().toString().trim());
                diliverMember.setPassowrd(et_passowrd.getText().toString().trim());
                diliverMember.setRepassword(et_repassword.getText().toString().trim());
                reff.child(String.valueOf(maxid+1)).setValue(diliverMember);

                Toast.makeText(Register.this,"Registered Succesfully",Toast.LENGTH_LONG).show();
            }
        });


    }
}
