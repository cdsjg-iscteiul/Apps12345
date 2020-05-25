package com.example.apps.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apps.R;
import com.example.apps.items.alreadyBoughtProduct;

import java.util.ArrayList;

public class Adap extends RecyclerView.Adapter<Adap.ExampleViewHolder> {

    private ArrayList<alreadyBoughtProduct> mListaStorage;

    public  static class ExampleViewHolder extends  RecyclerView.ViewHolder{

        public TextView mName;
        public TextView mText1;
        public TextView mtext2;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_of_the_product);
            mText1 = itemView.findViewById(R.id.textView4);
            mtext2 = itemView.findViewById(R.id.textView5);

        }
    }

    public  Adap(ArrayList<alreadyBoughtProduct> lista){
        mListaStorage = lista;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_layout,parent,false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        alreadyBoughtProduct p = mListaStorage.get(position);

        holder.mName.setText(p.getName());
        holder.mText1.setText(""+p.getAmount());
        holder.mtext2.setText(p.getDate());
    }

    @Override
    public int getItemCount() {
        return mListaStorage.size();
    }
}
