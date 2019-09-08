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
Button btnEdit,btnDelete;
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
                reff= FirebaseDatabase.getInstance().getReference().child("DiliverMember");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                               diliverMember.setPhone(con_number.getText().toString().trim());

                               reff = FirebaseDatabase.getInstance().getReference().child("DiliverMember").child("1");
                               reff.child("1").setValue(diliverMember);

                               Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                            }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }


}
