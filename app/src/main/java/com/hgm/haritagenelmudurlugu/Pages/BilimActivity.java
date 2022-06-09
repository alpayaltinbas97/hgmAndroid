package com.hgm.haritagenelmudurlugu.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hgm.haritagenelmudurlugu.Adaptor.BilimAdaptor;
import com.hgm.haritagenelmudurlugu.Model.BilimModel;
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

public class BilimActivity extends AppCompatActivity {
    TextView text, errorText;
    private ArrayList<BilimModel> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private BilimAdaptor itemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilim);

      //  errorText = (TextView) findViewById(R.id.textView);

        Task orderData = new Task();
        orderData.execute();
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.recycler_view);
        itemAdaptor = new BilimAdaptor(items);
        recyclerView.setAdapter(itemAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public class Task extends AsyncTask<Void, Void, BilimSonuc>{


        @Override
        protected BilimSonuc doInBackground (Void...voids){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.harita.gov.tr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService5 apiService5 = retrofit.create(ApiService5.class);


            try {
                return apiService5.listScience().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute (BilimSonuc bilimSonuc) {
            viewSettings();
            itemAdaptor.notifyDataSetChanged();
            if (bilimSonuc != null) {
                for (BilimModel article : bilimSonuc.getScience()) {
                    Log.i("value1", article.getUnvan());
                    Log.i("value2", article.getAd());
                    Log.i("year", article.getKurum());
                    Log.i("issue", article.getBransi());
                    items.add(new BilimModel(article.getUnvan(),article.getAd(),article.getKurum(),article.getBransi()));
                }
            }
            else
                Log.i("makale", "null");
        }
    }
}