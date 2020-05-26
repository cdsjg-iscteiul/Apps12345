package com.example.apps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.apps.items.alreadyBoughtProduct;
import com.example.apps.items.toBuyProduct;
import com.example.apps.utility.Adapter;
import com.example.apps.R;
import com.example.apps.items.item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private ArrayList<item> items;
    private int positionTest;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadData();
        buildRecyclerView();


    }

    public void buildRecyclerView(){
        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(items);
        mRecycler.setLayoutManager(mManager);
        mRecycler.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                items.remove(position);
                mAdapter.notifyItemRemoved(position);
                saveData();
            }

            @Override
            public void onItemClick(int position) {
                Log.e("TOU ONDE?","-----------> TOU NO ON CLICK" );
                Log.e("QUE TEM A LISTA?","-----------------------> " + items.get(position).getArrayComprar()  );
                if(items.get(position).getArrayComprar()!= null) {
                    Log.e("TOU ONDE?","-----------> TOU NO ON CLICK DO CARRINHO" );
                    Intent intent = new Intent(FirstActivity.this, ShoppingList.class);
                    intent.putParcelableArrayListExtra("BUNDLE",items.get(position).getArrayComprar());
                    positionTest = position;
                    startActivityForResult(intent,3);

                }

                if(items.get(position).getArrayComprados()!= null) {
                    Log.e("TOU ONDE?","-----------> TOU NO ON CLICK DA PASTA" );
                    Intent intent = new Intent(FirstActivity.this, Storage.class);
                    intent.putParcelableArrayListExtra("ARRAYCOMPRADOS",items.get(position).getArrayComprados());
                    positionTest = position;
                    startActivityForResult(intent,3);

                }



            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutopo, menu);
        return true;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String text = data.getStringExtra("ListName");
                items.add(items.size(), new item(R.drawable.ic_shopping,text,null ,new ArrayList<toBuyProduct>()));
                mAdapter.notifyItemInserted(items.size());
                saveData();
            }
        }

        if(requestCode == 2){
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra("ListName");
                items.add(items.size(), new item(R.drawable.ic_office_material,text,new ArrayList<alreadyBoughtProduct>() ,null));
                mAdapter.notifyItemInserted(items.size());
                saveData();
            }
        }


        if(requestCode == 3){
            if (resultCode == RESULT_OK) {
                ArrayList<toBuyProduct> lista = data.getParcelableArrayListExtra("RESULTS");
                Log.e("RECEBI ESTA MERDA", lista.toString());
                items.get(positionTest).setArray2(lista);
                saveData();
                mAdapter.notifyDataSetChanged();
            }
        }

        if(requestCode ==4){
            if (resultCode == RESULT_OK) {
                ArrayList<alreadyBoughtProduct> lista = data.getParcelableArrayListExtra("RESULTSstorage");
                Log.e("RECEBI ESTA MERDA", lista.toString());
                items.get(positionTest).setArray1(lista);
                saveData();
                mAdapter.notifyDataSetChanged();
            }
        }



    }


    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(items);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<item>>() {}.getType();
        items = gson.fromJson(json, type);
        if (items == null) {
            items = new ArrayList<>();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addCart:
                Intent intent = new Intent(FirstActivity.this, AddList.class);
                startActivityForResult(intent,1);
                return true;

            case R.id.addCabinet:
                startActivityForResult(new Intent(getApplicationContext(), AddList.class),2);
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

