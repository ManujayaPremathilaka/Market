package com.example.manujayapremathilaka.market;

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

public class profile extends AppCompatActivity {
TextView license_no,nic_no,tv_name1,tv_email1;
EditText con_number;
Button btnEdit,btnDelete,btnDeleteAccount;
DatabaseReference reff;
DiliverMember diliverMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        license_no=(TextView)findViewById(R.id.license_no);
        nic_no=(TextView)findViewById(R.id.nic_no);
        con_number=(EditText)findViewById(R.id.con_number);
        tv_name1=(TextView)findViewById(R.id.tv_name1);
        tv_email1=(TextView)findViewById(R.id.tv_email1);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        btnDeleteAccount =(Button)findViewById(R.id.btnDeleteAccount);
        diliverMember = new DiliverMember();

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff= FirebaseDatabase.getInstance().getReference().child("DiliverMember").child("1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String name =dataSnapshot.child("name").getValue().toString();
                        String email =dataSnapshot.child("email").getValue().toString();
                        String nic =dataSnapshot.child("nic").getValue().toString();
                        String phone =dataSnapshot.child("phone").getValue().toString();
                        String vehicalno =dataSnapshot.child("vehicalno").getValue().toString();

                        tv_name1.setText(name);
                        tv_email1.setText(email);
                        nic_no.setText(nic);
                        con_number.setText(phone);
                        license_no.setText(vehicalno);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff =FirebaseDatabase.getInstance().getReference().child("DiliverMember");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            try{
                                diliverMember.setPhone(con_number.getText().toString().trim());

                                reff = FirebaseDatabase.getInstance().getReference().child("DiliverMember").child("1");
                                reff.setValue("1");

                                Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid Contact Number",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No sourse to Update",Toast.LENGTH_SHORT).show();
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff = FirebaseDatabase.getInstance().getReference().child("DiliverMember");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            reff = FirebaseDatabase.getInstance().getReference().child("DiliverMember").child("1");
                            reff.removeValue();

                            Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"No Source to delete",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


}
