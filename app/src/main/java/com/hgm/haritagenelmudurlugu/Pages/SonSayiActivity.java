package com.hgm.haritagenelmudurlugu.Pages;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;
import com.hgm.haritagenelmudurlugu.R;

public class SonSayiActivity  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        Bundle bundle = getIntent().getExtras();
        Log.d("DEÐERRRRR...",bundle.getString(""));
        pdfView.fromAsset(bundle.getString(""))
                .enableSwipe(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .enableAnnotationRendering(false)
                .enableAntialiasing(true)
                .spacing(0)
                .swipeHorizontal(false)
                .password(null)
                .load();

    }
}