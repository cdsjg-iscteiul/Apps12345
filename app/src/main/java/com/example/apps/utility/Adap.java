package com.example.apps.utility;

import android.util.Log;
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
    private Adap.OnItemClickListener mListener;

    public  static class ExampleViewHolder extends  RecyclerView.ViewHolder{

        public TextView mName;
        public TextView mText1;
        public TextView mText2;
        public TextView mtext3;
        public int id;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener mListener) {
            super(itemView);
            mName = itemView.findViewById(R.id.name_of_the_product);
            mText1 = itemView.findViewById(R.id.textView4);
            mText2 = itemView.findViewById(R.id.textView6);
            mtext3 = itemView.findViewById(R.id.textView5);

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

    public  Adap(ArrayList<alreadyBoughtProduct> lista){
        mListaStorage = lista;
    }

    @NonNull
    @Override
    public Adap.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_view_layout,parent,false);

        Adap.ExampleViewHolder evh = new ExampleViewHolder(v,mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull Adap.ExampleViewHolder holder, int position) {

        alreadyBoughtProduct p = mListaStorage.get(position);

        holder.id=position;
        holder.mText1.setText(""+p.getAmount());
        holder.mText2.setText(TypeOfProduct.toString(p.getType()));
        holder.mtext3.setText(p.getDate());
        holder.mName.setText(p.getName());

    }

    @Override
    public int getItemCount() {
        return mListaStorage.size();
    }

    public interface  OnItemClickListener{
        void onItemClick(int id) throws InterruptedException;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
         mListener = listener;
    }

}
