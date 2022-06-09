package com.hgm.haritagenelmudurlugu.Pages;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import android.app.SearchManager;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.github.barteksc.pdfviewer.PDFView;
import com.hgm.haritagenelmudurlugu.Adaptor.MakaleAdaptor;
import com.hgm.haritagenelmudurlugu.Model.MakaleModel;
import com.hgm.haritagenelmudurlugu.R;
import com.mysql.jdbc.log.NullLogger;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.CharBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import io.grpc.Context;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import static com.hgm.haritagenelmudurlugu.R.id.checked;
import static com.hgm.haritagenelmudurlugu.R.id.clear_text;
import static com.hgm.haritagenelmudurlugu.R.id.forever;
import static com.hgm.haritagenelmudurlugu.R.id.pdfView;
import static com.hgm.haritagenelmudurlugu.R.id.radio;
import static com.hgm.haritagenelmudurlugu.R.id.radioGroup;
import static com.hgm.haritagenelmudurlugu.R.id.radio_makale;
import static com.hgm.haritagenelmudurlugu.R.id.radio_sayi;
import static com.hgm.haritagenelmudurlugu.R.id.radio_yazar;
import static com.hgm.haritagenelmudurlugu.R.id.radio_yil;
import static com.hgm.haritagenelmudurlugu.R.id.search_close_btn;
import static com.hgm.haritagenelmudurlugu.R.id.start;
import static com.hgm.haritagenelmudurlugu.R.id.txtDergiNo;
import static com.hgm.haritagenelmudurlugu.R.id.txtMakaleAdi;

public class MakaleActivity extends AppCompatActivity implements OnQueryTextListener {
    TextView text, errorText;
    private ArrayList<MakaleModel> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private MakaleAdaptor itemAdaptor;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.makale_activty_layout);

        //  errorText = (TextView) findViewById(R.id.textView);

        MakaleActivity.Task orderData = new MakaleActivity.Task();
        orderData.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_ara);
        SearchView searchView =
                (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(this);
        searchView.setIconifiedByDefault(true);
        searchView.setQueryRefinementEnabled(false);
        getSupportActionBar().setTitle("Makaleler");
        searchView.setQueryHint(Html.fromHtml("<font color = #ffffff>" + "Filtreye Göre Makale Ara..." + "</font>"));
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_mag_icon);

        searchIcon.setColorFilter(getResources().getColor(R.color.design_default_color_on_primary),
                android.graphics.PorterDuff.Mode.SRC_IN);

        ImageView closeIcon = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        closeIcon.setImageDrawable(getResources().getDrawable(R.drawable.close_icon));

        return true;
    }

    private void viewSettings() {
        recyclerView = findViewById(R.id.rvMakale);
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        itemAdaptor = new MakaleAdaptor(items, new MakaleAdaptor.ItemClickListener() {
            @Override
            public void onItemClick(MakaleModel items) {
                showToast(items.getValue1());

                intentS(items.getName(), items.getId());
            }
        });
        recyclerView.setAdapter(itemAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void intentS(String name, String id) {
        Intent intent = new Intent(this, StreamPDFActivity.class);
        String value = "https://www.harita.gov.tr/uploads/files/articles/" + name + "-" + id + ".pdf";
        intent.putExtra("", value);
        startActivity(intent);
        //Intent methodu sitenin adresini ve json verilerinden çektiðimiz dosya adýný StreamPDFActivity classýna göndermemize olanak tanýyor.
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case radio_sayi:
                if (checked)
                    break;
            case radio_yil:
                if (checked)
                    break;
            case radio_makale:
                if (checked)
                    break;
            case radio_yazar:
                if (checked)
                    break;
        }
    }

    @Override
    public boolean onQueryTextChange(String s) {
        ArrayList<MakaleModel> newItems = new ArrayList<>();
        for (MakaleModel item : items) {
            RadioButton rb1;
            RadioButton rb2;
            RadioButton rb3;
            RadioButton rb4;

            rb1 = (RadioButton) findViewById(radio_sayi);
            rb2 = (RadioButton) findViewById(radio_yil);
            rb3 = (RadioButton) findViewById(radio_makale);
            rb4 = (RadioButton) findViewById(radio_yazar);


            if (rb1.isChecked() && item.getIssue().equals(s) || item.getValue2().contains(s)) {
                newItems.add(item);
                continue;
            }
            else if (rb2.isChecked() && item.getYear().toLowerCase().contains(s.toLowerCase())) {
                newItems.add(item);
                continue;
            } else if (rb3.isChecked() && item.getValue1().toLowerCase().contains(s.toLowerCase())) {
                newItems.add(item);
                continue;
            } else if (rb4.isChecked() && item.getValue2().toLowerCase().contains(s.toLowerCase())) {
                newItems.add(item);
                continue;
            }

        }
        itemAdaptor.setItems(newItems);
        itemAdaptor.notifyDataSetChanged();
        return true;
    }

    public class Task extends AsyncTask<Void, Void, MakaleSonuc>{


        @Override
        protected MakaleSonuc doInBackground (Void...voids){
            Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.harita.gov.tr/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiService apiService = retrofit.create(ApiService.class);


            try {
                return apiService.listArticles().execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute (MakaleSonuc makaleSonuc) {
            viewSettings();
            itemAdaptor.notifyDataSetChanged();
            if (makaleSonuc != null) {
                for (MakaleModel article : makaleSonuc.getLanguages()) {
                            Log.i("value1", article.getValue1());
                            Log.i("value2", article.getValue2());
                            Log.i("year", article.getYear());
                            Log.i("issue", article.getIssue());
                            Log.i("name", article.getName());
                            Log.i("id", article.getId());
                            items.add(new MakaleModel(article.getValue1(),article.getYear(),article.getIssue(),article.getName(),article.getId(),article.getValue2()));
                }
            }
            else
                Log.i("makale", "null");
        }
    }
}
