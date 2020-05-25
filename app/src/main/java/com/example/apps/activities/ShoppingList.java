package com.example.apps.activities;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.apps.R;
import com.example.apps.items.toBuyProduct;
import com.example.apps.utility.CardConstructer;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShoppingList extends AppCompatActivity {

    private ArrayList<toBuyProduct> shoppingList;
    private RecyclerView recyclerview;
    private CardConstructer adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton temporario; //PARA ELEMINAR ERA SO PARA TESTE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);

        getSupportActionBar().setTitle("Check Shopping List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ShoppingList.this, AddProduct.class),45);
            }
        });

        temporario = findViewById(R.id.floatingActionButton2);
        temporario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=0;
                for(toBuyProduct p:shoppingList){
                    if(p.isChecked())
                        count++;

                }
                Log.e("OLAAAAAAAAAAAAAA",""+count);
            }
        });

        Intent intent = getIntent();

        shoppingList =  intent.getParcelableArrayListExtra("BUNDLE");

        recyclerview = findViewById(R.id.recyclerView2);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CardConstructer(shoppingList);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);

    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==45) {
            shoppingList.add(shoppingList.size(), (toBuyProduct) data.getParcelableExtra("ProductAdded"));
            adapter.notifyItemInserted(shoppingList.size());
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.teste, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Save:
                Intent resultIntent = new Intent();
                resultIntent.putParcelableArrayListExtra("RESULTS", shoppingList);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}






