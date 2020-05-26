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
import com.example.apps.utility.Dialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Iterator;

public class ShoppingList extends AppCompatActivity implements Dialog.InterfaceListener {

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

        shoppingList = new ArrayList<>();
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
        shoppingList.add(shoppingList.size(), (Product) data.getSerializableExtra("ProductAdded"));
        adapter.notifyItemInserted(shoppingList.size());
        super.onActivityResult(requestCode, resultCode, data);

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


}






