package com.example.manujayapremathilaka.market;

import android.content.Intent;
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

public class Veg extends AppCompatActivity {
    EditText a1,b1,c1,d1,a2,b2,c2,d2;
    Button view1,view2,Up1,Up2,De1,De2;
    DatabaseReference db;
    Iteam ITMM;
    String itemPic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg);
        a1 = (EditText) findViewById(R.id.id);
        b1 = (EditText)findViewById(R.id.name);
        c1 = (EditText)findViewById(R.id.price);
        d1 = (EditText)findViewById(R.id.qty);
        view1 = (Button)findViewById(R.id.View1);
        a2 = (EditText)findViewById(R.id.id2);
        b2 = (EditText)findViewById(R.id.name2);
        c2 = (EditText)findViewById(R.id.price2);
        d2 = (EditText)findViewById(R.id.qty2);
        view2 = (Button)findViewById(R.id.View2);
        Up1 = (Button)findViewById(R.id.update1);
        Up2 = (Button)findViewById(R.id.Update2);
        De1 = (Button)findViewById(R.id.Delete1);
        De2 = (Button)findViewById(R.id.delete2);
        ITMM = new Iteam();
        //retrivw of 1st iteam-----------------------------------------------------------------------------------------------------------------------
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Item").child("1");
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

        //retrivw of 2nd iteam----------------------------------------------------------------------------------------------------------------------------

        view2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Item").child("2");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String id = dataSnapshot.child("id").getValue().toString();
                        String name = dataSnapshot.child("name").getValue().toString();
                        String price = dataSnapshot.child("price").getValue().toString();
                        String qty = dataSnapshot.child("qty").getValue().toString();

                        a2.setText(id);
                        b2.setText(name);
                        c2.setText(price);
                        d2.setText(qty);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
//update 1st iteam-------------------------------------------------------------------------------------------------------------------------------
        Up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db =FirebaseDatabase.getInstance().getReference().child("Item");
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            try{

                                ITMM.setID(a1.getText().toString().trim());
                                ITMM.setName(b1.getText().toString().trim());
                                ITMM.setPrice( c1.getText().toString().trim());
                                ITMM.setQTY(d1.getText().toString().trim());


                                db = FirebaseDatabase.getInstance().getReference().child("Item").child("1");
                                db.setValue(ITMM);

                                Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid ",Toast.LENGTH_SHORT).show();
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
        //update 2nd iteam------------------------------------------------------------------------------------------------------------------------------
        Up2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db =FirebaseDatabase.getInstance().getReference().child("Item");
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("2")){
                            try{

                                ITMM.setID(a2.getText().toString().trim());
                                ITMM.setName(b2.getText().toString().trim());
                                ITMM.setPrice(c2.getText().toString().trim());
                                ITMM.setQTY(d2.getText().toString().trim());


                                db = FirebaseDatabase.getInstance().getReference().child("Item").child("2");
                                db.setValue(ITMM);

                                Toast.makeText(getApplicationContext(),"Updated Successfully",Toast.LENGTH_SHORT).show();
                            }
                            catch (NumberFormatException e){
                                Toast.makeText(getApplicationContext(),"Invalid ",Toast.LENGTH_SHORT).show();
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

        //delete 2nd iteam-------------------------------------------------------------------------------------------------------------------------------------------

        De2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Item");
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("2")){
                            db = FirebaseDatabase.getInstance().getReference().child("Item").child("2");
                            db.removeValue();

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
        //delete 1st iteam
        De1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db = FirebaseDatabase.getInstance().getReference().child("Item");
                db.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.hasChild("1")){
                            db = FirebaseDatabase.getInstance().getReference().child("Item").child("1");
                            db.removeValue();

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
    public void InsertIeam(View view){
        Intent newiteam = new Intent(Veg.this, InsertItem.class);
        startActivity(newiteam);
    }
}
