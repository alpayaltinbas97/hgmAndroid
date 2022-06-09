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

import com.hgm.haritagenelmudurlugu.Adaptor.OzelSayiAdaptor;
import com.hgm.haritagenelmudurlugu.Model.MakaleModel;
import com.hgm.haritagenelmudurlugu.Model.OzelSayiModel;
import com.hgm.haritagenelmudurlugu.R;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import io.grpc.internal.Stream;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OzelSayiActivity extends AppCompatActivity {
    TextView text, errorText;
    private ArrayList<OzelSayiModel> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private OzelSayiAdaptor itemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_ozel_sayilar);

        //  errorText = (TextView) findViewById(R.id.textView);

        OzelSayiActivity.Task orderData = new OzelSayiActivity.Task();
        orderData.execute();
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.rvOzel);

        itemAdaptor = new OzelSayiAdaptor(items, new OzelSayiAdaptor.ItemClickListener() {
            @Override
            public void onItemClick(OzelSayiModel items) {
                showToast(items.getValue2());
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
            String value= "https://www.harita.gov.tr/uploads/files/mapmagazinespecialissues/" + name+"-"+id+".pdf";
            intent.putExtra("",value);
            startActivity(intent);
            //Intent methodu sitenin adresini ve json verilerinden çektiðimiz dosya adýný StreamPDFActivity classýna göndermemize olanak tanýyor.
        }

    public class Task extends AsyncTask<Void, Void, OzelSayiSonuc>{


        @Override
        protected OzelSayiSonuc doInBackground (Void...voids){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://www.harita.gov.tr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService2 apiService2 = retrofit.create(ApiService2.class);


            try {
                return apiService2.listSpecial().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute (OzelSayiSonuc ozelSayiSonuc) {
            viewSettings();
            itemAdaptor.notifyDataSetChanged();
            if (ozelSayiSonuc != null) {
                for (OzelSayiModel article : ozelSayiSonuc.getSpecials()) {
                    Log.i("value2", article.getValue2());
                    Log.i("value1", article.getValue1());
                    Log.i("name", article.getName());
                    Log.i("id", article.getId());
                    items.add(new OzelSayiModel(article.getValue2(),article.getValue1(),article.getName(),article.getId()));
                }
            }
            else
                Log.i("makale", "null");
        }
    }
}
