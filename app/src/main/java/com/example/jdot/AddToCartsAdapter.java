package com.example.jdot;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddToCartsAdapter extends RecyclerView.Adapter<AddToCartsAdapter.ViewHolder> {

    Context context;
    ArrayList<AddToCartsModel> addToCartsModelArrayList;
    int currentQuantity=1;
    long currentPrice ;
    int count = 0;
    AddToCartsAdapter(Context context, ArrayList<AddToCartsModel> arrayList){
        this.context = context;
        this.addToCartsModelArrayList = arrayList;
    }
    @NonNull
    @Override
    public AddToCartsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_to_cart_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddToCartsAdapter.ViewHolder holder, int position) {
        AddToCartsModel currentPosition = addToCartsModelArrayList.get(position);
        long prices = currentPosition.itemPrice;


        holder.itemPrice.setText("Price: " + currentPosition.itemPrice);
        holder.itemName.setText(currentPosition.itemName);
        holder.itemImage.setImageResource(currentPosition.imageResource);

         holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
             @Override
             public boolean onLongClick(View view) {

                 AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                 alertDialog.setTitle("Delete Item");
                 alertDialog.setMessage("Are you want to delete this item?");
                 alertDialog.setCancelable(false);
                 alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                        addToCartsModelArrayList.remove(currentPosition);
                        notifyItemRemoved(position);
                     }
                 });
                 alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {

                     }
                 });
                 AlertDialog dialog = alertDialog.create();
                 dialog.show();

                 return false;
             }
         });

        holder.plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuantity++;
                holder.itemQuantity.setText(String.valueOf(currentQuantity));

                currentPrice = prices * currentQuantity;
                holder.itemPrice.setText("Price: " + currentPrice);
            }
        });
        holder.minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
             if (currentQuantity>1){
                 currentQuantity--;
             }else {
                 Toast.makeText(context, "No", Toast.LENGTH_SHORT).show();
             }
                 holder.itemQuantity.setText(String.valueOf(currentQuantity));
                currentPrice = prices * currentQuantity;
                holder.itemPrice.setText("Price: " + currentPrice);

            }
        });
        currentPosition.setQuantity(currentQuantity);


    }


    @Override
    public int getItemCount() {
        return addToCartsModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView itemName,itemPrice,itemQuantity;
        ImageView itemImage;
        Button plusBtn,minusBtn;
        public ViewHolder (View itemView){
            super(itemView);

            itemImage = itemView.findViewById(R.id.addtoCartImg);
            itemName = itemView.findViewById(R.id.itemNameAddTocart);
            itemPrice = itemView.findViewById(R.id.itemPriceAddtocart);
            plusBtn = itemView.findViewById(R.id.plusBtnAddToCart);
            minusBtn = itemView.findViewById(R.id.minusBtnAddToCart);
            itemQuantity = itemView.findViewById(R.id.item_quantityAddtoCart);
        }
    }
}
