package com.example.manujayapremathilaka.market;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    Context context;
    ArrayList<Items> items;
    DatabaseReference databaseReference;
    String NIC;
    int buttonPress = 0;

    public CartAdapter(Context c, ArrayList<Items> p, String NIC) {
        context = c;
        items = p;
        this.NIC = NIC;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_card_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemNo.setText(items.get(position).getItemNo());
        holder.price.setText(items.get(position).getPrice());
        holder.quantity.setText(items.get(position).getQuantity());
        Picasso.get().load(items.get(position).getItemPic()).into(holder.itemPic);

        holder.onClick(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView itemNo,price, quantity;
        ImageView itemPic;
        Button btnEdit, btnRemove;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemNo = (TextView) itemView.findViewById(R.id.itemNoCart);
            price = (TextView) itemView.findViewById(R.id.priceCart);
            quantity = (TextView)  itemView.findViewById(R.id.quantityCart);
            quantity.setEnabled(false);
            itemPic = (ImageView) itemView.findViewById(R.id.itemPicCart);

            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
        }
        public void onClick(final int position)
        {
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttonPress == 0){
                        quantity.setEnabled(true);
                        buttonPress++;
                        btnEdit.setText("SAVE");
                    }
                    else{
                        quantity.setEnabled(false);
                        buttonPress = 0;
                        btnEdit.setText("EDIT");

                        final Items updatedItem = new Items();

                        updatedItem.setItemNo(itemNo.getText().toString());
                        updatedItem.setPrice(price.getText().toString());
                        updatedItem.setQuantity(quantity.getText().toString());
                        updatedItem.setItemPic(items.get(position).getItemPic());

                        databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart");


                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(NIC)){
                                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Cart").child(NIC).child(updatedItem.getItemNo());
                                    databaseReference.setValue(updatedItem);

                                    Intent intent = new Intent(context, Cart.class);
                                    intent.putExtra("NIC", NIC);
                                    context.startActivity(intent);
                                   // context.startActivity(new Intent(context, Cart.class));
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
    }
}
