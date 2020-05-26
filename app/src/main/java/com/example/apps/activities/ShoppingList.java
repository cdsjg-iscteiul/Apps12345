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
import com.example.apps.utility.Dialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingList extends AppCompatActivity implements Dialog.InterfaceListener {

    private ArrayList<toBuyProduct> shoppingList;
    private ArrayList<Product> shoppingList;
    private ArrayList<String> listsNames;
    private RecyclerView recyclerview;
    private CardConstructer adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton temporario; //PARA ELEMINAR ERA SO PARA TESTE
    private ArrayList<Integer>  listOfSelected;


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
                listOfSelected = new ArrayList<>();
                for(int i=0;i!=shoppingList.size();i++){
                    if(shoppingList.get(i).isChecked()){
                        listOfSelected.add(i);
                    }
                }
                Dialog d = new Dialog();
                Bundle b = new Bundle();

                b.putStringArrayList("list",listsNames);
                d.setArguments(b);
                d.show(getSupportFragmentManager(),"ola");
            }
        });

        Intent intent = getIntent();

        shoppingList =  intent.getParcelableArrayListExtra("BUNDLE");

        listsNames = new ArrayList<>();

        recyclerview = findViewById(R.id.recyclerView2);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new CardConstructer(shoppingList);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);

        fillListOfLists();

    }

    private void fillListOfLists() {
        ArrayList<String> aux = getIntent().getStringArrayListExtra("sendlist");
        for(String s:aux){
            listsNames.add(s);
        }
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
    public void sendToList(int i) {
        //Intent intent = new Intent()
        //setResult();
    }

    @Override
    public void removeAndUpdate() {
        int counter = 0;
        Iterator<Product> i = shoppingList.iterator();
        while (i.hasNext()) {
            Product s = i.next(); // must be called before you can call i.remove()
            if(s.isChecked()) {
                s.setCheck(false);
                i.remove();
               adapter.notifyItemRemoved(counter);
               counter--;
            }
            counter++;
        }

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






