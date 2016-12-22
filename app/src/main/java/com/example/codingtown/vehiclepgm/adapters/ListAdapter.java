package com.example.codingtown.vehiclepgm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.codingtown.vehiclepgm.R;
import com.example.codingtown.vehiclepgm.model.TradeLoadData;

import java.util.ArrayList;

/**
 * Created by CodingTown on 21-12-2016.
 */
public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<TradeLoadData> tradeLoadDatas;

    public ListAdapter(Context context, ArrayList<TradeLoadData> list) {

        this.context = context;
        tradeLoadDatas = list;

    }

    @Override
    public int getCount() {
        return tradeLoadDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return tradeLoadDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        TradeLoadData tradeload = tradeLoadDatas.get(position);

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.datas_page, null);

        }


        final TextView date = (TextView) convertView.findViewById(R.id.date_tv);
        final TextView billno = (TextView) convertView.findViewById(R.id.billno_tv);
        final TextView farmername = (TextView) convertView.findViewById(R.id.farmername_tv);
        final TextView coconuttype = (TextView) convertView.findViewById(R.id.coconuttype_tv);
        final TextView  tradecoconut= (TextView) convertView.findViewById(R.id.tradecoconut_tv);
        final TextView fromlocation = (TextView) convertView.findViewById(R.id.fromlocation_tv);
        final TextView tolocation = (TextView) convertView.findViewById(R.id.tolocation_tv);
        final TextView vehicleno = (TextView) convertView.findViewById(R.id.vehicleno_tv);
        final TextView drivername = (TextView) convertView.findViewById(R.id.drivername_tv);

        date.setText(tradeload.getDate());
        billno.setText("B:"+tradeload.getBillno());
        farmername.setText(tradeload.getFarmername());
        coconuttype.setText(tradeload.getCoconuttype());
        tradecoconut.setText(tradeload.getTradecoconut());
        fromlocation.setText(tradeload.getFromlocation());
        tolocation.setText(tradeload.getTolocation());
        vehicleno.setText(tradeload.getVehicleno());
        drivername.setText(tradeload.getDrivername());

        return convertView;

    }
}
