package com.hgm.haritagenelmudurlugu.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hgm.haritagenelmudurlugu.Model.DergiArsivModel;
import com.hgm.haritagenelmudurlugu.R;

import java.util.ArrayList;

public class DergiArsivAdaptor extends RecyclerView.Adapter<DergiArsivAdaptor.MyViewHolder> {
    private ArrayList<DergiArsivModel> items;
    private DergiArsivAdaptor.ItemClickListener mItemClickListener;

    public DergiArsivAdaptor( ArrayList<DergiArsivModel> items, ItemClickListener itemClickListener) {
        this.items = items;
        this.mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DergiArsivAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pdf_item_ozel, parent, false);
        return new DergiArsivAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DergiArsivAdaptor.MyViewHolder holder, int position) {
        holder.baslik.setText(items.get(position).getValue1());
       // holder.pdf.setText(items.get(position).getPdf());
        holder.yil.setText(items.get(position).getValue2());
        holder.itemView.setOnClickListener(view -> {
            mItemClickListener.onItemClick(items.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface ItemClickListener {
        void onItemClick(DergiArsivModel items);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView baslik,pdf,yil;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            baslik = itemView.findViewById(R.id.ustbaslik);
           // pdf = itemView.findViewById(R.id.cardViewArsiv);
            yil = itemView.findViewById(R.id.baslik);
        }
    }
}
