package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.apps.R;
import com.example.apps.api.RecipeAfterSearch;
import com.example.apps.utility.AdapterRecipes;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {

    private ArrayList<RecipeAfterSearch> ListOfRecipes;
    private RecyclerView recyclerview;
    private AdapterRecipes adapter1;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        ListOfRecipes = new ArrayList<>();

        recyclerview = findViewById(R.id.recyclerView_search_result);
        recyclerview.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        this.adapter1 = new AdapterRecipes(ListOfRecipes);

        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(this.adapter1);

        ArrayList<RecipeAfterSearch> aux = getIntent().getParcelableArrayListExtra("result");
        Log.e("list",ListOfRecipes.toString());
        for(RecipeAfterSearch r:aux){
            ListOfRecipes.add(r);
            adapter1.notifyItemInserted(ListOfRecipes.size());
        }
    }


}
