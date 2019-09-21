package com.example.manujayapremathilaka.market;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    Context context;
    ArrayList<Items> items;
    String NIC;
    DatabaseReference databaseReference;
    private final String CART = "Cart";
    private final String DEFAULT_QTY = "1";
    private final String ADDED_TO_CART_MESSAGE = "Added to Cart";

    public MyAdapter(Context c , ArrayList<Items> p, String NIC)
    {
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
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    /**
     * Binds the data set with the view holder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.itemDescription.setText("250g packet of "+ items.get(position).getName());
        holder.itemNo.setText(items.get(position).getID());
        holder.price.setText(items.get(position).getPrice());
        Picasso.get().load(items.get(position).getItemPic()).into(holder.itemPic);

        holder.btn.setVisibility(View.VISIBLE);
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
        TextView itemNo,price, itemDescription;
        ImageView itemPic;
        Button btn;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemNo = (TextView) itemView.findViewById(R.id.itemNo);
            itemDescription = (TextView) itemView.findViewById(R.id.itemDescriptionItemMenu);
            price = (TextView) itemView.findViewById(R.id.price);
            itemPic = (ImageView) itemView.findViewById(R.id.itemPic);
            btn = (Button) itemView.findViewById(R.id.buy);
        }
        public void onClick(final int position)
        {
            //item will be added to the cart table
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Items item = new Items();
                    item.setName(items.get(position).getName());
                    item.setID(items.get(position).getID());
                    item.setPrice(items.get(position).getPrice());
                    item.setItemPic(items.get(position).getItemPic());
                    item.setQuantity(DEFAULT_QTY);

                    databaseReference = FirebaseDatabase.getInstance().getReference().child(CART).child(NIC);
                    databaseReference.child(item.getID()).setValue(item);
                    Toast.makeText(context, ADDED_TO_CART_MESSAGE, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
