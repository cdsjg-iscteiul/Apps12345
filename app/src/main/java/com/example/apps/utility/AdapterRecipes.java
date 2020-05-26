package com.example.apps.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.example.apps.api.RecipeAfterSearch;
import com.example.apps.items.item;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class AdapterRecipes extends RecyclerView.Adapter<AdapterRecipes.ExampleViewHolder>{
    private ArrayList<RecipeAfterSearch> mLista;
    private AdapterRecipes.OnItemClickListener mListener;

    public interface  OnItemClickListener{
        void onItemClick(int position);
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(AdapterRecipes.OnItemClickListener listener){
        mListener = listener;
    }

    public  static class ExampleViewHolder extends  RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mText1;
        public TextView mtext2;
        public int id;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mImageView = itemView.findViewById(R.id.recipe_image);
            this.mText1 = itemView.findViewById(R.id.recipe_title);
            this.mtext2 = itemView.findViewById(R.id.recipe_likes);


        }
    }

    public  AdapterRecipes(ArrayList<RecipeAfterSearch> lista){
        mLista = lista;
    }

    @NonNull
    @Override
    public AdapterRecipes.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card,parent,false);
        AdapterRecipes.ExampleViewHolder evh = new AdapterRecipes.ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecipes.ExampleViewHolder holder, int position) {

        RecipeAfterSearch current = mLista.get(position);
        //holder.mImageView.setImageBitmap(Bitmap.createBitmap(doInBackground(current.getUrl()),60,60));
        holder.mText1.setText(current.getName());
        holder.mtext2.setText("Likes: " + current.getLikes());
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }

    public Bitmap doInBackground(String url) {
        Bitmap bmp = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            bmp = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;
    }

    }



