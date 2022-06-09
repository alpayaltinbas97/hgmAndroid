package com.hgm.haritagenelmudurlugu.Adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hgm.haritagenelmudurlugu.Model.BilimModel;
import com.hgm.haritagenelmudurlugu.Model.YonetimModel;
import com.hgm.haritagenelmudurlugu.R;

import java.util.ArrayList;

public class YonetimAdaptor extends RecyclerView.Adapter<YonetimAdaptor.MyViewHolder> {
    private ArrayList<YonetimModel> items;

    public YonetimAdaptor(ArrayList<YonetimModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public YonetimAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yonetim_layout, parent, false);
        return new YonetimAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YonetimAdaptor.MyViewHolder holder, int position) {
        holder.gorev.setText(items.get(position).getGorev());
        holder.rutbe.setText(items.get(position).getRutbe());
        holder.ad.setText(items.get(position).getAd());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView gorev,rutbe, ad;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            gorev = itemView.findViewById(R.id.yntmGorev);
            rutbe = itemView.findViewById(R.id.yntmRutbe);
            ad = itemView.findViewById(R.id.yntmAd);

        }
    }
}
