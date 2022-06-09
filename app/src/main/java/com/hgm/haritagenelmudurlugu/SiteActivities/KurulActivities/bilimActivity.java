package com.hgm.haritagenelmudurlugu.SiteActivities.KurulActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hgm.haritagenelmudurlugu.R;
import com.hgm.haritagenelmudurlugu.SiteActivities.OzelSayilarActivity;
import com.hgm.haritagenelmudurlugu.SiteActivities.Utility.BilimAdapter;
import com.hgm.haritagenelmudurlugu.SiteActivities.Utility.BilimModel;
import com.hgm.haritagenelmudurlugu.pdf.ItemClickListener;
import com.hgm.haritagenelmudurlugu.pdf.PDFActivity2;
import com.hgm.haritagenelmudurlugu.pdf.PDFModel2;

import java.util.ArrayList;
import java.util.List;

public class bilimActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    public static List<BilimModel> bilimModelList;
    DatabaseReference database;
    private BottomSheetDialog bottomSheetDialog;
    BilimModel bilimModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bilim);
        database = FirebaseDatabase.getInstance().getReference("bilimKurul");
        recycler_view = findViewById(R.id.recycler_view);

        bilimModelList = new ArrayList<>();
        recycler_view.setLayoutManager(new LinearLayoutManager(this));


        BilimAdapter bilimAdapter = new BilimAdapter(this, bilimModelList);
        recycler_view.setAdapter(bilimAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    BilimModel bilimModel = dataSnapshot.getValue(BilimModel.class);
                    bilimModelList.add(bilimModel);

                }
                bilimAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


   /*
        List<BilimModel> modelList=new ArrayList<>();
        modelList.add(new BilimModel("Dr.Tu�g.","Osman ALP osman alp osman ","Kara Kuvvetleri","Jeodezi"));
        modelList.add(new BilimModel("Prof.Dr.","Bahad�r AKTU� ","Ankara �niversitesi","Jeodezi"));
        modelList.add(new BilimModel("Prof.Dr.","Ali Melih BA�ARANER ","Y�ld�z Teknik �niversitesi","Kartografya-CBS"));
        modelList.add(new BilimModel("Prof.Dr.","Ahmet Tu�rul BA�OKUR ","Ankara �niversitesi","Jeofizik"));
        modelList.add(new BilimModel("general","osmanpa�a osman alp pa�a ","HGM","Jeodezi"));
        modelList.add(new BilimModel("general","osman ","HGM","Jeodezi ba�kanl���"));
        modelList.add(new BilimModel("general","osman ","HGM","Jeodezi"));
*/

}