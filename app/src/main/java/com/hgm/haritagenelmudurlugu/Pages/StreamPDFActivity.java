package com.hgm.haritagenelmudurlugu.Pages;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.scroll.ScrollHandle;
import com.hgm.haritagenelmudurlugu.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import io.perfmark.Tag;

public class StreamPDFActivity extends AppCompatActivity {

    // creating a variable
    // for PDF view.
    PDFView pdfView;
    Integer pageNumber = 0;
    String pdfFileName;
    /* bu activity, makaleactivity veya özelsayiactivity gibi pdf görüntülememizi saðlayan classlardan intent alarak, url baðlantýsý ile pdf stream etmemize olanak saðlýyor.
    stream etme iþi telefonun memory kýsmýnda gerçekleþiyor, retrivePDFfromUrl classý bize istediðimiz pdf dosyasýný linkine baðlanarak açýyor.*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view);
        // initializing our pdf view.
        Bundle bundle = getIntent().getExtras();
        Log.d("DEÐERRRRR...",bundle.getString(""));
        pdfView = findViewById(R.id.pdfView);
        pdfView.setVisibility(View.INVISIBLE);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        new RetrivePDFfromUrl().execute(bundle.getString(""));
    }

    // create an async task class for loading pdf file from URL.
    class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }


            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
             pdfView.fromStream(inputStream).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);
                    progressBar.setVisibility(View.INVISIBLE);
                    pdfView.setVisibility(View.VISIBLE);
                }
            }).scrollHandle(new DefaultScrollHandle(getBaseContext())).load();
            }
        }
        }