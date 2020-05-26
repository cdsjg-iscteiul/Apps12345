package com.example.apps.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apps.R;
import com.example.apps.items.toBuyProduct;

import java.util.ArrayList;

public class CardConstructer extends RecyclerView.Adapter<CardConstructer.cardView> {

    private ArrayList<toBuyProduct> list;

    public static class cardView extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView amount;
        public TextView type;
        public transient CheckBox checkBox;


        public cardView(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.shoplist_product_name);
            amount = itemView.findViewById(R.id.shoplist_product_amount);
            type = itemView.findViewById(R.id.shoplist_product_type);
            checkBox = itemView.findViewById(R.id.shoplist_checkbox);
        }
    }
    public CardConstructer(ArrayList<toBuyProduct> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public cardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.shopping_list_product,parent,false);
        cardView card = new cardView(v);
        return card;
    }


    @Override
    public void onBindViewHolder(@NonNull cardView card, int position) {
        toBuyProduct p = list.get(position);
        p.setCheckBox(card.checkBox);
        card.name.setText(p.getName());
        card.amount.setText(""+p.getAmout());
        card.type.setText(TypeOfProduct.toString(p.getType()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
