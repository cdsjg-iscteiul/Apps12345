package com.example.apps.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.apps.api.JsonToObject;
import com.example.apps.api.Recipe;
import com.example.apps.utility.Adapter;
import com.example.apps.items.BoughtProduct;
import com.example.apps.items.ProductsToBuy;
import com.example.apps.R;
import com.example.apps.items.item;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private Adapter mAdapter;
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

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                items.remove(position);
                mAdapter.notifyItemRemoved(position);
            }

            @Override
            public void onItemClick(int position) {

                if(items.get(position).getArray1()!= null) {
                    Intent intent = new Intent(getApplicationContext(), ShoppingList.class);
                    ArrayList<String> listaux = new ArrayList<>();
                    for(item i:items) {
                        if(i.getmImageResource()==R.drawable.ic_office_material)
                        listaux.add(i.getmText1());
                    }
                    intent.putStringArrayListExtra("sendlist",listaux);
                    startActivityForResult(intent,20);
                }

                if(items.get(position).getArray2()!= null) {
                    startActivity(new Intent(getApplicationContext(), Storage.class));
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
                Toast.makeText(FirstActivity.this, text, Toast.LENGTH_SHORT).show();
                items.add(items.size(), new item(R.drawable.ic_shopping,text,null ,new ArrayList<ProductsToBuy>()));
                mAdapter.notifyItemInserted(items.size());
            }
        }

        if(requestCode == 2){
            if (resultCode == RESULT_OK) {
                String text = data.getStringExtra("ListName");
                Toast.makeText(FirstActivity.this, text, Toast.LENGTH_SHORT).show();
                items.add(items.size(), new item(R.drawable.ic_office_material,text,new ArrayList<BoughtProduct>() ,null));
                mAdapter.notifyItemInserted(items.size());
            }
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
            case R.id.item3:
                startActivity(new Intent(getApplicationContext(), RecipeSearch.class));

            default:
                return super.onOptionsItemSelected(item);
        }
        return  true;
    }



}

