package com.example.apps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    ArrayList<RecipeAfterSearch> result;

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
                callApi(Integer.parseInt(editText2.getText().toString()),editText.getText().toString());


            }
        });

    }

    public void callApi(int numberOfsearches, String... ingredient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonToObject jsonPostApi = retrofit.create(JsonToObject.class);

        result = new ArrayList<>();

        Call<List<Recipe>> call = jsonPostApi.getRecipes("b5a9c3d3771345ac8e3f0e24e1a5fa90", ingredient,numberOfsearches,false,null,true);
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (!response.isSuccessful()) {
                    //textView.setText("Code: " + response.code());
                    return;
                }

                List<Recipe> receitas = response.body();
                int count=0;
                for (Recipe r : receitas) {
                    count++;
                    String rp = "Receita - "+count+"\n";
                    rp += "ID: " + r.getId() +"\n";
                    rp += "Image: " + r.getImage() + "\n";
                    rp += "tipo: " + r.getImageType() +"\n";
                    rp += "Likes: " + r.getLikes() + "\n";
                    rp += "mari: " + r.getMissedIngredientCount() +"\n";
                    rp += "\n";

                    for(int i = 0;i!=r.getMissedIngredientCount();i++) {
                        int j = i+1;
                        rp += "INGREDIENTE - " + j + "                     \n";
                        rp += "ID - " + r.getMissedIngredients()[i].getId() + "\n";
                        rp += "Amount - " + r.getMissedIngredients()[i].getAmount()+ "\n";
                        rp += "Unit - " + r.getMissedIngredients()[i].getUnit()+ "\n";
                        rp += "UnitLong - " + r.getMissedIngredients()[i].getUnitLong()+ "\n";
                        rp += "UnitShort - " + r.getMissedIngredients()[i].getUnitShort()+ "\n";
                        rp += "Aisle - " + r.getMissedIngredients()[i].getAisle()+ "\n";
                        rp += "Name - " + r.getMissedIngredients()[i].getName()+ "\n";
                        rp += "original - " + r.getMissedIngredients()[i].getOriginal()+ "\n";
                        rp += "originalString - " + r.getMissedIngredients()[i].getOriginal()+ "\n";
                        rp += "Imagem - " + r.getMissedIngredients()[i].getImage()+ "\n";
                        rp += "Meta - " + r.getMissedIngredients()[i].getMeta().length+ "\n";
                        rp += "Meta Information - " + r.getMissedIngredients()[i].getMetaInformation().length+ "\n";
                        rp += "\n";
                        rp += "\n";

                    }
                    result.add(new RecipeAfterSearch(r.getId(),r.getTitle(),r.getImage(),r.getLikes()));
                }
                Intent intent = new Intent(getApplicationContext(), SearchResult.class);
                intent.putParcelableArrayListExtra("result",result);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

            }
        });
    }
}
