package com.example.jdot;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<DressModelClass> adapterArrayList;
    Context context;
    int Quantity = 1; // Initialize Quantity to 1

    CustomAdapter(Context context, ArrayList<DressModelClass> adapterArrayList) {
        this.context = context;
        this.adapterArrayList = adapterArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dress_lists, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DressModelClass dress = adapterArrayList.get(position);
        long prices = dress.price;

        // Set initial values from the dress model
        holder.itemName.setText(dress.getQualtyName());
        holder.itemPrice.setText(String.valueOf(dress.getPrice())); // Convert long to string
        holder.itemImage.setImageResource(dress.getImg());

        // Handle increasing the quantity and updating the total price
        holder.plusBtn.setOnClickListener(view -> {
            Quantity++;

            long totalPrice = prices * Quantity;

            holder.itemQuantity.setText(String.valueOf(Quantity)); // Set quantity as string
            holder.itemPrice.setText(String.valueOf(totalPrice));  // Set total price as string
        });

        // Handle decreasing the quantity and updating the total price
        holder.minusBtn.setOnClickListener(view -> {
            if (Quantity > 1) {
                Quantity--;
            }
            long totalPrice = prices * Quantity;

            holder.itemQuantity.setText(String.valueOf(Quantity)); // Set quantity as string
            holder.itemPrice.setText(String.valueOf(totalPrice));  // Set total price as string
        });

        // Handle purchase button click
        /*    holder.purchase.setOnClickListener(view -> {
            String names = dress.qualtyName;
            long price = dress.price;
            int img = dress.img;
            int flag = 0;
            Toast.makeText(context, "Heilo", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, Purchase_Item.class);
            intent.putExtra("itemName", names);
            intent.putExtra("itemPrice", price);
            intent.putExtra("itemImg", img);
            intent.putExtra("flag",flag);
            context.startActivity(intent);
        });*/
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String names = dress.qualtyName;
                long price = dress.price;
                int img = dress.img;

                Toast.makeText(context, "Heilo", Toast.LENGTH_SHORT).show();
                Intent secondIntent = new Intent(context, AddToCarts.class);
                secondIntent.putExtra("itemName", names);
                secondIntent.putExtra("itemPrice", price);
                secondIntent.putExtra("itemImg", img);
                context.startActivity(secondIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return adapterArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImage;
        Button plusBtn, minusBtn, addToCart, purchase;
        TextView itemName, itemPrice, itemQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.item_img);
            itemName = itemView.findViewById(R.id.item_name_text);
            itemPrice = itemView.findViewById(R.id.priceIdInteger);
            itemQuantity = itemView.findViewById(R.id.item_quantity);
            plusBtn = itemView.findViewById(R.id.plusBtn);
            minusBtn = itemView.findViewById(R.id.minusBtn); // Correct assignment here
            addToCart = itemView.findViewById(R.id.addToCartBtn);
//            purchase = itemView.findViewById(R.id.buyBtn);
        }
    }
}

