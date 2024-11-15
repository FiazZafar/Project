package com.example.jdot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PuchaseAdapter extends RecyclerView.Adapter<PuchaseAdapter.ViewHolder> {

    Context context;
    ArrayList<PurchasedItems> purchasedItemsArrayList;
    public PuchaseAdapter(Context context, ArrayList<PurchasedItems> purchasedItemsArrayList){
        this.context=context;
        this.purchasedItemsArrayList=purchasedItemsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recent_purchased_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchasedItems currentItem = purchasedItemsArrayList.get(position);
           holder.price.setText(String.valueOf(currentItem.getPrice()));
           holder.name.setText(currentItem.getItemName());
           holder.date.setText(currentItem.getFormattedDate());
           holder.img.setImageResource(currentItem.getImgResource());
       }

    @Override
    public int getItemCount() {
        return purchasedItemsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name,price,date;
        public ViewHolder(View itemView){
            super(itemView);

            img = itemView.findViewById(R.id.purchased_img);
            name = itemView.findViewById(R.id.purchased_item_name);
            price = itemView.findViewById(R.id.purchased_item_price);
            date = itemView.findViewById(R.id.purchased_item_date);

        }
    }
}
