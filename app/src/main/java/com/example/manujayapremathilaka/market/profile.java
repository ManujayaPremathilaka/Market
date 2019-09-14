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
    EditText con_number,password_diliver,re_repassword_diliver;
    Button btnShow,btnEdit,btnRem;
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
        btnShow=(Button)findViewById(R.id.btnShow);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnRem =(Button)findViewById(R.id.btnRem);
        password_diliver =(EditText)findViewById(R.id.password_diliver);
        re_repassword_diliver=(EditText)findViewById(R.id.re_repassword_diliver);
        diliverMember = new DiliverMember();

        btnShow.setOnClickListener(new View.OnClickListener() {
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
                        String passowrd = dataSnapshot.child("passowrd").getValue().toString();
                        String repassword = dataSnapshot.child("repassword").getValue().toString();

                        tv_name1.setText(name);
                        tv_email1.setText(email);
                        nic_no.setText(nic);
                        con_number.setText(phone);
                        license_no.setText(vehicalno);
                        password_diliver.setText(passowrd);
                        re_repassword_diliver.setText(repassword);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reff =FirebaseDatabase.getInstance().getReference().child("DiliverMember");
                reff.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            try{
                                diliverMember.setPhone(con_number.getText().toString().trim());
                                diliverMember.setName(tv_name1.getText().toString().trim());
                                diliverMember.setEmail(tv_email1.getText().toString().trim());
                                diliverMember.setNic(nic_no.getText().toString().trim());
                                diliverMember.setVehicalno(license_no.getText().toString().trim());
                                diliverMember.setPassowrd(password_diliver.getText().toString().trim());
                                diliverMember.setRepassword(re_repassword_diliver.getText().toString().trim());


                                reff = FirebaseDatabase.getInstance().getReference().child("DiliverMember").child("1");
                                reff.setValue(diliverMember);

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

        btnRem.setOnClickListener(new View.OnClickListener() {
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
