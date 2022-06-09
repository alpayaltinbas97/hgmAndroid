package com.hgm.haritagenelmudurlugu.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hgm.haritagenelmudurlugu.Model.BilimModel;
import com.hgm.haritagenelmudurlugu.R;

import java.util.ArrayList;

public class BilimAdaptor extends RecyclerView.Adapter<BilimAdaptor.MyViewHolder> {
    private ArrayList<BilimModel> items;

    public BilimAdaptor(ArrayList<BilimModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_bilim_kurul, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.unvan.setText(items.get(position).getUnvan());
        holder.ad.setText(items.get(position).getAd());
        holder.kurum.setText(items.get(position).getKurum());
        holder.brans.setText(items.get(position).getBransi());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView unvan, ad, kurum, brans;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            unvan = itemView.findViewById(R.id.blmUnvan);
            ad = itemView.findViewById(R.id.blmAd);
            kurum = itemView.findViewById(R.id.blmKurum);
            brans = itemView.findViewById(R.id.blmBrans);
        }
    }
}
