package com.example.apps.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apps.R;
import com.example.apps.items.item;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ExampleViewHolder> {

    private ArrayList<item> mLista;

    public  static class ExampleViewHolder extends  RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mText1;
        public TextView mtext2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mText1 = itemView.findViewById(R.id.text1);
            mtext2 = itemView.findViewById(R.id.text2);

        }
    }

    public  Adapter(ArrayList<item> lista){
        mLista = lista;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.examplecar,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        item current = mLista.get(position);

        holder.mImageView.setImageResource(current.getmImageResource());
        holder.mText1.setText(current.getmText1());
        holder.mtext2.setText("Número de produtos na lista: " + current.getmText2());
    }

    @Override
    public int getItemCount() {
        return mLista.size();
    }
}
