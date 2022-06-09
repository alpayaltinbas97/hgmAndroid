package com.hgm.haritagenelmudurlugu.Pages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hgm.haritagenelmudurlugu.Adaptor.YonetimAdaptor;
import com.hgm.haritagenelmudurlugu.Model.MakaleModel;
import com.hgm.haritagenelmudurlugu.Model.YonetimModel;
import com.hgm.haritagenelmudurlugu.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class YonetimActivity extends AppCompatActivity {
    TextView text, errorText;
    private ArrayList<YonetimModel> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private YonetimAdaptor itemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yonetim_recycler);

        //  errorText = (TextView) findViewById(R.id.textView);

        YonetimActivity.Task orderData = new YonetimActivity.Task();
        orderData.execute();
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.yonetimRv);
        itemAdaptor = new YonetimAdaptor(items);
        recyclerView.setAdapter(itemAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public class Task extends AsyncTask<Void, Void, YonetimSonuc>{


        @Override
        protected YonetimSonuc doInBackground (Void...voids){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.harita.gov.tr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService4 apiService4 = retrofit.create(ApiService4.class);


            try {
                return apiService4.listManagement().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute (YonetimSonuc yonetimSonuc) {
            viewSettings();
            itemAdaptor.notifyDataSetChanged();
            if (yonetimSonuc != null) {
                for (YonetimModel article : yonetimSonuc.getManagement()) {
                    Log.i("gorev", article.getGorev());
                    Log.i("rutbe", article.getRutbe());
                    Log.i("ad", article.getAd());
                    items.add(new YonetimModel(article.getGorev(),article.getRutbe(),article.getAd()));
                }
            }
            else
                Log.i("makale", "null");
        }
    }
}
