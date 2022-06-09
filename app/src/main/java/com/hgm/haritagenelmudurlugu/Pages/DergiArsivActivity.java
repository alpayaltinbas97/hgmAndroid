package com.hgm.haritagenelmudurlugu.Pages;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hgm.haritagenelmudurlugu.Adaptor.DergiArsivAdaptor;
import com.hgm.haritagenelmudurlugu.Model.DergiArsivModel;
import com.hgm.haritagenelmudurlugu.Model.MakaleModel;
import com.hgm.haritagenelmudurlugu.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DergiArsivActivity extends AppCompatActivity {
    TextView errorText;
    private ArrayList<DergiArsivModel> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private DergiArsivAdaptor itemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dergi_arsiv);

        //  errorText = (TextView) findViewById(R.id.textView);

        DergiArsivActivity.Task orderData = new DergiArsivActivity.Task();
        orderData.execute();
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.rvDergi);

        itemAdaptor = new DergiArsivAdaptor(items, new DergiArsivAdaptor.ItemClickListener() {
            @Override
            public void onItemClick(DergiArsivModel items) {
                showToast(items.getValue1());
                intentS(items.getName(),items.getId());
            }
        });
        recyclerView.setAdapter(itemAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private void intentS(String name,String id){
        Intent intent=new Intent(this,StreamPDFActivity.class);
        String value= "https://www.harita.gov.tr/uploads/files/mapmagazines/" + name +"-"+id+".pdf";
        intent.putExtra("",value);
        startActivity(intent);
        //Intent methodu sitenin adresini ve json verilerinden çektiðimiz dosya adýný StreamPDFActivity classýna göndermemize olanak tanýyor.
    }

    public class Task extends AsyncTask<Void, Void, DergiArsivSonuc>{


        @Override
        protected DergiArsivSonuc doInBackground (Void...voids){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.harita.gov.tr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService3 apiService3 = retrofit.create(ApiService3.class);


            try {
                return apiService3.listArchive().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute (DergiArsivSonuc dergiArsivSonuc) {
            viewSettings();
            itemAdaptor.notifyDataSetChanged();
            if (dergiArsivSonuc != null) {
                for (DergiArsivModel article : dergiArsivSonuc.getArchives()) {
                    Log.i("value1", article.getValue1());
                    Log.i("name", article.getName());
                    Log.i("value2", article.getValue2());
                    Log.i("id", article.getId());
                    items.add(new DergiArsivModel(article.getValue1(),article.getName(),article.getValue2(),article.getId()));
                }
            }
            else
                Log.i("makale", "null");
        }
    }
}
