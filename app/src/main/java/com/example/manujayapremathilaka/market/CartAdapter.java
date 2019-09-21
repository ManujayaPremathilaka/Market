package com.example.manujayapremathilaka.market;

import android.app.Activity;
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
    Items updatedItem;
    DatabaseReference databaseReference;
    private final String CART = "Cart";
    private final String SAVE = "SAVE";
    private final String EDIT = "EDIT";
    private String NIC;
    private int buttonPress = 0;

    public CartAdapter(Context c, ArrayList<Items> p, String NIC) {
        context = c;
        items = p;
        this.NIC = NIC;
    }


    /**
     * Creates a view holder when there are no existing view holders
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cart_card_view,parent,false));
    }

    /**
     * Binds the data set with the view holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemNo.setText(items.get(position).getID());
        holder.price.setText(items.get(position).getPrice());
        holder.quantity.setText(items.get(position).getQuantity());
        Picasso.get().load(items.get(position).getItemPic()).into(holder.itemPic);

        holder.onClick(position);
    }

    /**
     * Returns the size of the array list
     * @return
     */
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
        {   //edit the quantity in cart table
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (buttonPress == 0){
                        quantity.setEnabled(true);
                        buttonPress++;
                        btnEdit.setText(SAVE);
                    }
                    else{
                        quantity.setEnabled(false);
                        buttonPress = 0;
                        btnEdit.setText(EDIT);

                        updatedItem = new Items();

                        updatedItem.setID(items.get(position).getID());
                        updatedItem.setName(itemNo.getText().toString());
                        updatedItem.setPrice(price.getText().toString());
                        updatedItem.setQuantity(quantity.getText().toString());
                        updatedItem.setItemPic(items.get(position).getItemPic());

                        databaseReference = FirebaseDatabase.getInstance().getReference().child(CART);
                        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.hasChild(NIC)){
                                    databaseReference = FirebaseDatabase.getInstance().getReference().child(CART).child(NIC).child(updatedItem.getID());
                                    databaseReference.setValue(updatedItem);

                                    Intent intent = new Intent(context, Cart.class);
                                    intent.putExtra("NIC", NIC);
                                    context.startActivity(intent);
                                    ((Activity)context).finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }
                }
            });

            //remove the particular item from the cart table
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    databaseReference = FirebaseDatabase.getInstance().getReference().child(CART).child(NIC);
                    databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(itemNo.getText().toString())){
                                databaseReference = FirebaseDatabase.getInstance().getReference().child(CART).child(NIC).child(itemNo.getText().toString());
                                databaseReference.removeValue();

                                Intent intent = new Intent(context, Cart.class);
                                intent.putExtra("NIC", NIC);
                                context.startActivity(intent);
                                ((Activity)context).finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }

            });

        }
    }
}
