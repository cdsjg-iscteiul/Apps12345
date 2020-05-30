package com.example.apps.utility;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apps.R;
import com.example.apps.activities.SearchResult;
import com.example.apps.api.JsonToObject;
import com.example.apps.api.Recipe;
import com.example.apps.api.RecipeAfterSearch;
import com.example.apps.api.RecipeInfo;
import com.example.apps.items.item;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterRecipes extends RecyclerView.Adapter<AdapterRecipes.ExampleViewHolder>{
    private ArrayList<RecipeAfterSearch> mLista;
    private AdapterRecipes.OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int id) throws InterruptedException;
    }

    public void setOnItemClickListener(AdapterRecipes.OnItemClickListener listener){
        mListener = listener;
    }

    public  static class ExampleViewHolder extends  RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mText1;
        public TextView mtext2;
        public int id;
        private String url;

        public void setUrl(String url) {
            this.url = url;
        }

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.recipe_image);
            this.mText1 = itemView.findViewById(R.id.recipe_title);
            this.mtext2 = itemView.findViewById(R.id.recipe_likes);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mListener.onItemClick(id);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });


        }
    }

    public  AdapterRecipes(ArrayList<RecipeAfterSearch> lista){
        mLista = lista;
    }


    @NonNull
    @Override
    public AdapterRecipes.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card,parent,false);
        AdapterRecipes.ExampleViewHolder evh = new AdapterRecipes.ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecipes.ExampleViewHolder holder, int position) {

        RecipeAfterSearch current = mLista.get(position);
        Log.e("asdasdasd",current.getUrl());
//        Log.e("-------------",holder.mImageView.getDrawable().toString()+"");
       // Picasso.get().load(current.getUrl()).into(holder.mImageView);
        holder.mImageView.setImageResource(R.drawable.ic_restaurant);
        holder.id = current.getId();
        holder.mText1.setText(current.getName());
        holder.mtext2.setText("Likes: " + current.getLikes());
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }






    }



