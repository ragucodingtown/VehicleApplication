package com.example.codingtown.vehiclepgm.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.codingtown.vehiclepgm.R;
import com.example.codingtown.vehiclepgm.adapters.ListAdapter;
import com.example.codingtown.vehiclepgm.database.DatabaseHandlers;
import com.example.codingtown.vehiclepgm.model.TradeLoadData;

import java.util.ArrayList;

/**
 * Created by CodingTown on 21-12-2016.
 */
public class TradeLoadDataViewActivity extends AppCompatActivity
{
    ListView lv_tradeload;
    DatabaseHandlers tradeloaddb;
    ListAdapter adapter;
    ArrayList<TradeLoadData> tradeloadlist;

    ImageView imv_nextactivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tradeloaddatas_listpage);

        lv_tradeload=(ListView)findViewById(R.id.tradeload_lv);

        tradeloaddb=new DatabaseHandlers(getApplicationContext());

        tradeloadlist=tradeloaddb.getAllDetails();

        adapter = new ListAdapter(TradeLoadDataViewActivity.this,tradeloadlist);
        lv_tradeload.setAdapter(adapter);

        imv_nextactivity=(ImageView)findViewById(R.id.nextactivity_imv);

        imv_nextactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa=new Intent(TradeLoadDataViewActivity.this,TradeActivity.class);
                startActivity(aa);
                TradeLoadDataViewActivity.this.finish();

            }
        });
    }
}
