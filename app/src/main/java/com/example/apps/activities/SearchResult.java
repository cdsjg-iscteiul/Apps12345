package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.apps.R;
import com.example.apps.api.JsonRecipe;
import com.example.apps.api.JsonToObject;
import com.example.apps.api.Recipe;
import com.example.apps.api.RecipeAfterSearch;
import com.example.apps.api.RecipeInfo;
import com.example.apps.utility.AdapterRecipes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchResult extends AppCompatActivity {

    private ArrayList<RecipeAfterSearch> ListOfRecipes;
    private RecyclerView recyclerview;
    private AdapterRecipes adapter1;
    private RecyclerView.LayoutManager layoutManager;
    private String url;

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

        adapter1.setOnItemClickListener(new AdapterRecipes.OnItemClickListener() {
            @Override
            public void onItemClick(final int id) throws InterruptedException {
                new WaitForRetrofit(id).execute();
            }
        });

        ArrayList<RecipeAfterSearch> aux = getIntent().getParcelableArrayListExtra("result");
        Log.e("list", ListOfRecipes.toString());
        for (RecipeAfterSearch r : aux) {
            ListOfRecipes.add(r);
            adapter1.notifyItemInserted(ListOfRecipes.size());
        }

    }

    public String callApi(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonRecipe jsonPostApi = retrofit.create(JsonRecipe.class);


        final String[] url = new String[1];

        Call<RecipeInfo> call = jsonPostApi.getRecipeInfo(id,"e49b2232910c4c9087f7e59c8caea66b", true);

        Log.e("asdasd",call.request().url().toString());
        try {
            RecipeInfo recipe = call.execute().body();
                if(recipe.getSpoonacularSourceUrl()!=null)
                    return recipe.getSpoonacularSourceUrl();
                else
                    return recipe.getSourceUrl();


        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private class WaitForRetrofit extends AsyncTask<Void, Void, Void>
    {
        int id;
        public WaitForRetrofit(int id){
            this.id=id;
        }
        @Override
        protected Void doInBackground(Void... params) {
            setUrl(callApi(id));
            Log.e("SAIO","SAIO DO BACK");
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            Log.e("ENTRO","DEPOIS");
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(intent);
        }
    }

}
