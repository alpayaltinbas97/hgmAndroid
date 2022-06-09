package com.hgm.haritagenelmudurlugu.Adaptor;

import android.app.AlertDialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hgm.haritagenelmudurlugu.Model.MakaleModel;
import com.hgm.haritagenelmudurlugu.Pages.PdfViewActivity;
import com.hgm.haritagenelmudurlugu.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  class MakaleAdaptor extends RecyclerView.Adapter<MakaleAdaptor.MyViewHolder> implements Filterable {
    public void setItems(ArrayList<MakaleModel> items) {
        this.items = items;
    }

    private ArrayList<MakaleModel> items;
    private ArrayList<MakaleModel> itemsAll;
    private ItemClickListener mItemClickListener;
    public String text;

    public MakaleAdaptor(ArrayList<MakaleModel> items, ItemClickListener itemClickListener) {
        this.items = items;
        this.itemsAll=itemsAll;
        this.mItemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public MakaleAdaptor.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.makale_sorgulama_card_tasarim, parent, false);
        return new MakaleAdaptor.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MakaleAdaptor.MyViewHolder holder, int position) {
        holder.value1.setText(items.get(position).getValue1());
        holder.year.setText(items.get(position).getYear());
        holder.issue.setText(items.get(position).getIssue());
        // holder.link.setText(items.get(position).getLink());
        holder.itemView.setOnClickListener(view -> {
            mItemClickListener.onItemClick(items.get(position));
        });

    }



    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MakaleModel> filteredList=new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filteredList.addAll(itemsAll);
            }
            else {
                for (MakaleModel item:itemsAll){
                    if (item.toString().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(item);
                    }
                }
            }
            FilterResults filterResults= new FilterResults();
            filterResults.values=filteredList;
            return  filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            items.clear();
            items.addAll((Collection<? extends MakaleModel>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public interface ItemClickListener{
        void onItemClick(MakaleModel items);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView value1, year, issue;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            // itemView.setOnClickListener((View.OnClickListener) this);
            itemView.setOnClickListener(this);
            value1 = itemView.findViewById(R.id.txtMakaleAdi);
            year = itemView.findViewById(R.id.txtSayi);
            issue = itemView.findViewById(R.id.txtDergiNo);

        }

        @Override
        public void onClick(View v) {
            // v.getContext().startActivity(new Intent(v.getContext(), PdfViewActivity.class));

            Log.d("value",text);
            Intent intent=new Intent(v.getContext(),PdfViewActivity.class);
            intent.putExtra("",text);
            v.getContext().startActivity(intent);
        }

    }
}
