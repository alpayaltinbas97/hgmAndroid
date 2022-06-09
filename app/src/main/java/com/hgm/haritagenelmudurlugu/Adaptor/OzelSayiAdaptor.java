package com.hgm.haritagenelmudurlugu.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hgm.haritagenelmudurlugu.Model.OzelSayiModel;
import com.hgm.haritagenelmudurlugu.R;

import java.util.ArrayList;

public class OzelSayiAdaptor extends RecyclerView.Adapter<OzelSayiAdaptor.MyViewHolder> {
    private ArrayList<OzelSayiModel> items;
    private OzelSayiAdaptor.ItemClickListener mItemClickListener;

    public OzelSayiAdaptor(ArrayList<OzelSayiModel> items, ItemClickListener itemClickListener) {
        this.items = items;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public OzelSayiAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_item_ozel, parent, false);
        return new OzelSayiAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OzelSayiAdaptor.MyViewHolder holder, int position) {

        holder.baslik.setText(items.get(position).getValue2());
     //   holder.pdf.setText(items.get(position).getPdf());
        holder.sayi.setText(items.get(position).getValue1());
       holder.itemView.setOnClickListener(view -> {
            mItemClickListener.onItemClick(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemClickListener {
        void onItemClick(OzelSayiModel items);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView baslik,pdf,sayi;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            baslik = itemView.findViewById(R.id.baslik);
           // pdf = itemView.findViewById(R.id.textView4);
            sayi = itemView.findViewById(R.id.ustbaslik);
        }
    }
}
