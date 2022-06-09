package com.hgm.haritagenelmudurlugu.Pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.hgm.haritagenelmudurlugu.R;

import java.io.File;


/*PDFView k�t�phanesini kullanarak sonsayi.pdf'i g�r�nt�l�yoruz.
k�t�phanede bulununan fromAsset komutu ile asset klas�r�n�n i�erisindeki dosya ismi ile e�le�en pdf'i (�rn. SonSayi.pdf) g�r�nt�leyebiliyoruz. */
public class PdfViewActivity  extends AppCompatActivity {
    @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        Bundle bundle = getIntent().getExtras();
        Log.d("DE�ERRRRR...",bundle.getString(""));
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
