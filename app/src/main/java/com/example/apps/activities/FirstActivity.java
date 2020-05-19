package com.example.apps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.apps.utility.Adapter;
import com.example.apps.items.BoughtProduct;
import com.example.apps.items.ProductsToBuy;
import com.example.apps.R;
import com.example.apps.items.item;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private ArrayList<item> items;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        createList();
        buildRecyclerView();


    }

    public void createList(){
        items = new ArrayList<>();
    }

    public void buildRecyclerView(){
        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(items);
        mRecycler.setLayoutManager(mManager);
        mRecycler.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutopo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.test:
                startActivity(new Intent(getApplicationContext(), ShoppingList.class));
                return true;

            case R.id.testMaria:
                startActivityForResult(new Intent(getApplicationContext(), Storage.class),1);
                return true;

            case R.id.addCart:
                items.add(items.size(), new item(R.drawable.ic_shopping, "Lista de Compras ",null,new ArrayList<ProductsToBuy>()));
                mAdapter.notifyItemInserted(items.size());
                return true;

            case R.id.addCabinet:
                items.add(items.size(), new item(R.drawable.ic_office_material, "Guardar Produtos",new ArrayList<BoughtProduct>(),null));
                mAdapter.notifyItemInserted(items.size());
                return true;


            case R.id.item2:
                fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                break;




            default:
                return super.onOptionsItemSelected(item);
        }
        return  true;
    }
}

