package com.example.manujayapremathilaka.market;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Cart extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<Items> list;
    CartAdapter cartAdapter;
    Button btnPlaceOrder;
    ArrayList<Items> orderList;
    ArrayList<Items> orderCount;
    private final String CART = "Cart";
    private final String ORDER = "Order";
    private final String EMPTY_CART_MESSAGE = "CART IS EMPTY";
    private final String TOTAL = "TOTAL = ";
    private final String ORDER_STATUS = "In Order";
    String NIC;
    int total = 0;
    ImageButton imageButton;
    TextView txtTotal, txtMarketCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        NIC = getIntent().getStringExtra("NIC");
        imageButton = findViewById(R.id.imgBtnCartCart);
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder);
        txtTotal = findViewById(R.id.txtTotal);
        txtMarketCart = findViewById(R.id.txtMarketCart);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, Cart.class);
                intent.putExtra("NIC", NIC);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.cartRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseReference = FirebaseDatabase.getInstance().getReference().child(CART).child(NIC);
        list = new ArrayList<Items>();

        /**
         * getting values from the database (cart table) and display
         */
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    Items item = ds.getValue(Items.class);
                    list.add(item);
                }

                cartAdapter = new CartAdapter(Cart.this, list, NIC);
                recyclerView.setAdapter(cartAdapter);
                if(list.isEmpty()){
                    txtTotal.setText(EMPTY_CART_MESSAGE);
                }
                else {
                    //calculating the total
                    for (int i = 0; i < list.size(); i++){
                        total = total + (Integer.parseInt(list.get(i).getPrice()) * Integer.parseInt(list.get(i).getQuantity()));
                    }
                    txtTotal.setText(TOTAL + total);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error in retrieving", Toast.LENGTH_SHORT).show();
            }
        });

        //remove the items from the cart and insert into Order table
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                orderList = new ArrayList<>();
                orderCount = new ArrayList<>();

                databaseReference = FirebaseDatabase.getInstance().getReference().child(CART).child(NIC);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds: dataSnapshot.getChildren()){
                            Items list = ds.getValue(Items.class);
                            list.setOrderStatus(ORDER_STATUS);
                            orderList.add(list);
                        }


                        for(int i = 0; i < orderList.size(); i++){
                            databaseReference = FirebaseDatabase.getInstance().getReference().child(ORDER).child(NIC).child(orderList.get(i).getID());
                            databaseReference.setValue(orderList.get(i));
                        }

                        databaseReference = FirebaseDatabase.getInstance().getReference().child(CART).child(NIC);
                        databaseReference.removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent intent = new Intent(Cart.this, ItemMenu.class);
                intent.putExtra("NIC", NIC);
                startActivity(intent);
                finish();
            }
        });

        //redirect to items menu
        txtMarketCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, ItemMenu.class);
                intent.putExtra("NIC", NIC);
                startActivity(intent);
                finish();
            }
        });
    }
}
