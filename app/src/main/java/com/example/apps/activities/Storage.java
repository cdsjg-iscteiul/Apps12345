package com.example.apps.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

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
                startActivityForResult(new Intent(getApplicationContext(), AddProductMaria.class),1);
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
    }
    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data) {
        storageList.add(storageList.size(), (alreadyBoughtProduct) data.getSerializableExtra("ProductAdded"));
        adapter.notifyItemInserted(storageList.size());
        super.onActivityResult(requestCode, resultCode, data);

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
                resultIntent.putParcelableArrayListExtra("RESULTSstorage", storageList);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
