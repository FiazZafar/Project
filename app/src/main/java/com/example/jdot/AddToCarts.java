package com.example.jdot;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class AddToCarts extends AppCompatActivity {

    Toolbar toolbar;
    static ArrayList<AddToCartsModel> addToCartsModelsArr = new ArrayList<AddToCartsModel>();
    RecyclerView addToCartRecycler;
    Button buyBtn;
    Date date = new Date();
    static long totalPrice;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_to_carts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        long itemPrice = intent.getLongExtra("itemPrice",0);
        int itemImage = intent.getIntExtra("itemImg",0);

//        toolbar = findViewById(R.id.myToolBar);
        buyBtn = findViewById(R.id.addToCartBuyBtn);
        addToCartRecycler = findViewById(R.id.addTocartRecycler);

        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("JDot");
            getSupportActionBar().setSubtitle("Add To Cart");
        }

        addToCartRecycler.setLayoutManager(new LinearLayoutManager(this));

        addToCartsModelsArr.add(new AddToCartsModel(itemName,itemImage,itemPrice,1));


        AddToCartsAdapter addToCartsAdapter = new AddToCartsAdapter(AddToCarts.this,addToCartsModelsArr);

        addToCartRecycler.setAdapter(addToCartsAdapter);




        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalPrice=0;
                for (int i = 0; i < addToCartsModelsArr.size(); i++) {
                    long currentItemPrice = addToCartsModelsArr.get(i).itemPrice;
                    int currentItemQuantity = addToCartsModelsArr.get(i).quantity;
//                    int currentItemQuantity = addToCartsModelsArr.get(i).quantity;
                    totalPrice += currentItemPrice * currentItemQuantity;
                    ;
                }


                AlertDialog.Builder dialogue = new AlertDialog.Builder(AddToCarts.this);
                dialogue.setTitle("Total Amount");
                dialogue.setCancelable(false);

                dialogue.setMessage("Total amount is : " + totalPrice);
                dialogue.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AddToCarts.this, "Order confirmed.", Toast.LENGTH_SHORT).show();
                        totalPrice=0;
//                        Intent intent = new Intent(AddToCarts.this, MainActivity.class);
//                        startActivity(intent);
                        for (int j = 0; j < addToCartsModelsArr.size(); j++) {
                            PurchasedItems purchasedItems = new PurchasedItems(addToCartsModelsArr.get(j).getImageResource(),addToCartsModelsArr.get(j).getItemName(), addToCartsModelsArr.get(j).getItemPrice(),date.getTime());
                        }
                        addToCartsModelsArr.clear();
                        addToCartRecycler.scrollToPosition(addToCartsModelsArr.size());
                    }
                });
                dialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        totalPrice = 0;
                    }
                });
               AlertDialog alertDialog = dialogue.create();
               alertDialog.show();

            }
        });
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        new MenuInflater(this).inflate(R.menu.toolbar_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
*/
   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.addtocart){
            Toast.makeText(this, "Yor are already here ", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.home_menu) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else if (itemId == R.id.Purchased) {
            int removeItem = 1;
            Intent intent = new Intent(this, Purchase_Item.class);
            intent.putExtra("removeItem",-1);
            startActivity(intent);
            Toast.makeText(this, "BackPressed", Toast.LENGTH_SHORT).show();

        }else {
            Intent intent = new Intent(this, Purchase_Item.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    */

}
