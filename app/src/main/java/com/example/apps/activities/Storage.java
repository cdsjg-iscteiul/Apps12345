package com.example.apps.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.apps.R;
import com.example.apps.items.Product;
import com.example.apps.utility.Adap;
import com.example.apps.utility.CardConstructer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Storage extends AppCompatActivity {

    private ArrayList<Product> storageList;
    private RecyclerView recyclerview;
    private Adap adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton temporario; //PARA ELIMINAR ERA SO PARA TESTE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        floatingActionButton = findViewById(R.id.floatingActionButtonM);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), AddProductMaria.class),1);
            }
        });

        storageList = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerViewM);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adap(storageList);


        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data) {
        storageList.add(storageList.size(), (Product) data.getSerializableExtra("ProductAdded"));
        adapter.notifyItemInserted(storageList.size());
        super.onActivityResult(requestCode, resultCode, data);

    }
}
