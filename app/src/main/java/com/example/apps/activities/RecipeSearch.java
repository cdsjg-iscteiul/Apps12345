package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.apps.R;
import com.example.apps.api.JsonToObject;
import com.example.apps.api.Recipe;
import com.example.apps.api.RecipeAfterSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeSearch extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    Button button;
    TextView t;
    ArrayList<RecipeAfterSearch> result = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_search);

        editText = findViewById(R.id.ListOfProducts);
        editText2 = findViewById(R.id.numberOfSearch);
        button = findViewById(R.id.StartSearch);
        t = findViewById(R.id.recipeText);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WaitForRetrofit().execute();
            }
        });

    }

    public void callApi(int numberOfsearches, String... ingredient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonToObject jsonPostApi = retrofit.create(JsonToObject.class);


        Call<List<Recipe>> call = jsonPostApi.getRecipes("b5a9c3d3771345ac8e3f0e24e1a5fa90", ingredient,numberOfsearches,false,null,true);

        try {
            List<Recipe> receitas = call.execute().body();
            for (Recipe r : receitas) {
                Log.e("asd", "ADICIO");
                result.add(new RecipeAfterSearch(r.getId(), r.getTitle(), r.getImage(), r.getLikes()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<RecipeAfterSearch> getResult() {
        return result;
    }

    private class WaitForRetrofit extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... params) {
            callApi(Integer.parseInt(editText2.getText().toString()),editText.getText().toString());
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            Intent intent = new Intent(getApplicationContext(), SearchResult.class);
            intent.putParcelableArrayListExtra("result",getResult());
            startActivity(intent);
        }
    }

}
