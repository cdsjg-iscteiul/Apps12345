package com.example.apps.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.apps.R;
import com.example.apps.items.alreadyBoughtProduct;
import com.example.apps.utility.Adap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Storage extends AppCompatActivity {

    private ArrayList<alreadyBoughtProduct> storageList;
    private RecyclerView recyclerview;
    private Adap adapter;
    private RecyclerView.LayoutManager layoutManager;
    private FloatingActionButton floatingActionButton;
    private FloatingActionButton temporario; //PARA ELIMINAR ERA SO PARA TESTE


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        getSupportActionBar().setTitle("Check Product List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionButtonM);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(getApplicationContext(), AddProductToTheList.class).putExtra("NotificationNumber",storageList.size()),50);
            }
        });

        Intent intent = getIntent();
        storageList =  intent.getParcelableArrayListExtra("ARRAYCOMPRADOS");

        recyclerview = findViewById(R.id.recyclerViewM);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new Adap(storageList);


        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(adapter);

        if(getIntent().getParcelableArrayListExtra("listaTransferida")!=null){
            ArrayList<alreadyBoughtProduct> aux = getIntent().getParcelableArrayListExtra("listaTransferida");
            for(alreadyBoughtProduct p:aux){
                storageList.add(p);
                adapter.notifyItemInserted(storageList.size());
            }
        }
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==50 && resultCode==RESULT_OK) {
            storageList.add(storageList.size(), (alreadyBoughtProduct) data.getParcelableExtra("ProductAdded"));
            adapter.notifyItemInserted(storageList.size());
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

                if(storageList.isEmpty()){
                    Toast.makeText(this, "NOTHING TO SAVE", Toast.LENGTH_SHORT).show();
                }else {
                    Intent resultIntent = new Intent();
                    resultIntent.putParcelableArrayListExtra("RESULTSstorage", storageList);
                    setResult(Activity.RESULT_OK, resultIntent);
                    Log.e("O QUE TYOU A MANDAR ------------>      ", storageList.toString());
                    finish();
                    return true;
                }


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
