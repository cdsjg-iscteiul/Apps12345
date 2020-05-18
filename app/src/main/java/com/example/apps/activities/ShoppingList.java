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
import com.example.apps.utility.CardConstructer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    private ArrayList<Product> shoppingList;
    private RecyclerView recyclerview;
    private CardConstructer adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton temporario; //PARA ELEMINAR ERA SO PARA TESTE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getApplicationContext(), AddProduct.class),1);
            }
        });

        temporario = findViewById(R.id.floatingActionButton2);
        temporario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;
                for(Product p:shoppingList){
                    if(p.isChecked())
                        count++;

                }
                Log.e("OLAAAAAAAAAAAAAA",""+count);
            }
        });

        shoppingList = new ArrayList<>();
        recyclerview = findViewById(R.id.recyclerView2);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CardConstructer(shoppingList);


        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);




    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data) {
        shoppingList.add(shoppingList.size(), (Product) data.getSerializableExtra("ProductAdded"));
        adapter.notifyItemInserted(shoppingList.size());
        super.onActivityResult(requestCode, resultCode, data);

    }

}






