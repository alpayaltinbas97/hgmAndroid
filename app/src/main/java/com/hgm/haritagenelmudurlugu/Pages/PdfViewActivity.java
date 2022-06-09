package com.hgm.haritagenelmudurlugu.Pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.hgm.haritagenelmudurlugu.R;

import java.io.File;


/*PDFView kütüphanesini kullanarak sonsayi.pdf'i görüntülüyoruz.
kütüphanede bulununan fromAsset komutu ile asset klasörünün içerisindeki dosya ismi ile eþleþen pdf'i (örn. SonSayi.pdf) görüntüleyebiliyoruz. */
public class PdfViewActivity  extends AppCompatActivity {
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
