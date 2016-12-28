package com.example.codingtown.vehiclepgm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.codingtown.vehiclepgm.autocompletetextviews.MyObject;
import com.example.codingtown.vehiclepgm.model.TradeLoadData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CodingTown on 16-12-2016.
 */
public class DatabaseHandlers extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "container";
    private static final String TABLE_ADDCOCONUT = "addcoconut";
    private static final String TABLE_TRADE = "trade";

    private static final String _addcoconuttype = "addcoconuttype";

    private static final String _time = "time";
    private static final String _date = "date";
    private static final String _billno = "billno";
    private static final String _farmername = "farmername";
    private static final String _coconuttype = "coconuttype";
    private static final String _tradecoconut = "tradecoconut";
    private static final String _fromlocation = "fromlocation";
    private static final String _tolocation = "tolocation";
    private static final String _vehicleno = "vehicleno";
    private static final String _drivername = "drivername";


    public DatabaseHandlers(Context context)
    {
        super(context,DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_ADDCOCONT_TABLE = "CREATE TABLE " + TABLE_ADDCOCONUT + "("
                + _addcoconuttype + " TEXT" + ");";
        db.execSQL(CREATE_ADDCOCONT_TABLE);

        String  CREATE_TRADE_TABLE = "CREATE TABLE "+ TABLE_TRADE + "("
                + _date + " TEXT,"
                + _time + " TEXT,"
                + _billno + " TEXT,"
                + _farmername + " TEXT,"
                + _coconuttype + " TEXT,"
                + _tradecoconut + " TEXT,"
                + _fromlocation + " TEXT,"
                + _tolocation + " TEXT ,"
                + _vehicleno + " TEXT,"
                + _drivername + " TEXT" + ");";
       db.execSQL(CREATE_TRADE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDCOCONUT);
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_TRADE);
        onCreate(db);
    }


    public void insertCoconut(String coconuttype)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(_addcoconuttype,coconuttype);
        db.insert(TABLE_ADDCOCONUT, null, values);
        db.close();
    }


    public void insertTrade(String time,String date,String billno,String farmername,String coconuttype,
                            String tradecoconut,String fromlocation,
                            String tolocation,String vehicleno,String drivername)
    {
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(_time,time);
        values.put(_date,date);
        values.put(_billno,billno);
        values.put(_farmername,farmername);
        values.put(_coconuttype,coconuttype);
        values.put(_tradecoconut,tradecoconut);
        values.put(_fromlocation,fromlocation);
        values.put(_tolocation,tolocation);
        values.put(_vehicleno,vehicleno);
        values.put(_drivername,drivername);

        db1.insert(TABLE_TRADE, null, values);
        db1.close();
    }

    //getalldetails
    public ArrayList<TradeLoadData> getAllDetails()
    {
        ArrayList<TradeLoadData> tldata = new ArrayList<TradeLoadData>();
        String selectquery = "SELECT * FROM " + TABLE_TRADE + "";// ORDER BY date COLLATE NOCASE";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);
        if (cursor.moveToFirst())
        {
            do {
               TradeLoadData tradeLoadData = new TradeLoadData();

                tradeLoadData.setTime(cursor.getString(0));
                tradeLoadData.setDate(cursor.getString(1));
                tradeLoadData.setBillno(cursor.getString(2));
                tradeLoadData.setFarmername(cursor.getString(3));
                tradeLoadData.setCoconuttype(cursor.getString(4));
                tradeLoadData.setTradecoconut(cursor.getString(5));
                tradeLoadData.setFromlocation(cursor.getString(6));
                tradeLoadData.setTolocation(cursor.getString(7));
                tradeLoadData.setVehicleno(cursor.getString(8));
                tradeLoadData.setDrivername(cursor.getString(9));


                tldata.add(tradeLoadData);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return tldata;
    }

    //addcoconuttype getting from database and show to autocompletetext view
    public List<MyObject> read(String searchTerm) {

        List<MyObject> recordsList = new ArrayList<MyObject>();

        // select query
        String sql = "";
        sql += "SELECT * FROM " + TABLE_ADDCOCONUT;
        sql += " WHERE " + _addcoconuttype + " LIKE '%" + searchTerm + "%'";
        sql += " ORDER BY " + _addcoconuttype + " DESC";
        sql += " LIMIT 0,5";

        SQLiteDatabase db = this.getWritableDatabase();

        // execute the query
        Cursor cursor = db.rawQuery(sql, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                // int productId = Integer.parseInt(cursor.getString(cursor.getColumnIndex(fieldProductId)));
                String objectName = cursor.getString(cursor.getColumnIndex(_addcoconuttype));
                MyObject myObject = new MyObject(objectName);

                // add to list
                recordsList.add(myObject);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        // return the list of records
        return recordsList;
    }


}
