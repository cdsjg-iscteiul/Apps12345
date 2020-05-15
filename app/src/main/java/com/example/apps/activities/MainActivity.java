package com.example.apps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.apps.Adapter;
import com.example.apps.BoughtProduct;
import com.example.apps.ProductsToBuy;
import com.example.apps.R;
import com.example.apps.item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private ArrayList<item> items;

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
            case R.id.addCart:
                items.add(items.size(), new item(R.drawable.ic_shopping, "Lista de Compras ",null,new ArrayList<ProductsToBuy>()));
                mAdapter.notifyItemInserted(items.size());
                return true;

            case R.id.addCabinet:
                items.add(items.size(), new item(R.drawable.ic_office_material, "Guardar Produtos",new ArrayList<BoughtProduct>(),null));
                mAdapter.notifyItemInserted(items.size());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

