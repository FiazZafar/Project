package com.example.jdot;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    SearchView searchView;
    ArrayList<DressModelClass> dressArrayList = new ArrayList<DressModelClass>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.list_Item_recycler);

        dressArrayList.add(new DressModelClass(R.drawable.cotton,"Cotton",500));
        dressArrayList.add(new DressModelClass(R.drawable.silk_item, "Silk", 700));
        dressArrayList.add(new DressModelClass(R.drawable.linen_item, "Linen", 600));
        dressArrayList.add(new DressModelClass(R.drawable.wool_item, "Wool", 800));
        dressArrayList.add(new DressModelClass(R.drawable.dinem, "Denim", 450));
        dressArrayList.add(new DressModelClass(R.drawable.polyster_item, "Polyester", 550));
        dressArrayList.add(new DressModelClass(R.drawable.cotton, "Rayon", 650));
        dressArrayList.add(new DressModelClass(R.drawable.chiffon_item, "Chiffon", 750));
        dressArrayList.add(new DressModelClass(R.drawable.velvet_item, "Velvet", 900));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CustomAdapter customAdapter = new CustomAdapter(MainActivity.this,dressArrayList);
        recyclerView.setAdapter(customAdapter);

    }
}