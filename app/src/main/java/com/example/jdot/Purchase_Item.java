package com.example.jdot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;

public class Purchase_Item extends AppCompatActivity {

    RecyclerView purchaseRecycler;
    Date date = new Date(); // Current date and time
    static ArrayList<PurchasedItems> purchasedItemsArr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_item);

        // Set up edge-to-edge insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        long price = intent.getLongExtra("itemPrice", 0);
        int imgResource = intent.getIntExtra("itemImg", 0);
        int flag = intent.getIntExtra("flag", -1);
        int removeItem = intent.getIntExtra("removeItem", -1);

        // Add item if necessary
        if (itemName != null && price > 0) {
            purchasedItemsArr.add(new PurchasedItems(imgResource, itemName, price, date.getTime()));
        }

        purchaseRecycler = findViewById(R.id.purchasedItemRecycler);
        purchaseRecycler.setLayoutManager(new LinearLayoutManager(this));

        PuchaseAdapter purchaseAdapter = new PuchaseAdapter(Purchase_Item.this, purchasedItemsArr);
        purchaseRecycler.setAdapter(purchaseAdapter);
        purchaseRecycler.scrollToPosition(purchasedItemsArr.size() - 1);

        // Handle remove logic
        if (removeItem == 1 && !purchasedItemsArr.isEmpty()) {
            purchasedItemsArr.remove(purchasedItemsArr.size() - 1);
            purchaseAdapter.notifyDataSetChanged();
        }
    }
}
