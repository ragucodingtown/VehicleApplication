package com.example.codingtown.vehiclepgm.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codingtown.vehiclepgm.R;
import com.example.codingtown.vehiclepgm.database.DatabaseHandlers;

/**
 * Created by CodingTown on 13-12-2016.
 */
public class LoadActivity extends AppCompatActivity {

    DatabaseHandlers dbload;

    TextInputLayout txl_fromlocation, txl_tolocation, txl_vehicleno, txl_drivername,txl_mdmobileno;
    EditText et_farmername, et_fromlocation, et_tolocation, et_vehicleno, et_drivername,et_mdmobileno;

    Toolbar mtool;

    String fname, bno,ctype,noofc,cweight,tradecoco,date, time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_page);

        dbload=new DatabaseHandlers(getApplicationContext());

         txl_fromlocation= (TextInputLayout) findViewById(R.id.fromlocation_txl);
         txl_tolocation= (TextInputLayout) findViewById(R.id.tolocation_txl);
         txl_vehicleno = (TextInputLayout) findViewById(R.id.vehicleno_txl);
         txl_drivername= (TextInputLayout) findViewById(R.id.drivername_txl);
        txl_mdmobileno=(TextInputLayout)findViewById(R.id.mdmobileno_txl);

         et_fromlocation= (EditText) findViewById(R.id.fromlocation_et);
         et_tolocation = (EditText) findViewById(R.id.tolocation_et);
         et_vehicleno= (EditText) findViewById(R.id.vehicleno_et);
         et_drivername= (EditText) findViewById(R.id.drivername_et);
        et_mdmobileno=(EditText)findViewById(R.id.mdmobileno_et);


        mtool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtool);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mtool.setNavigationIcon(R.mipmap.back);

        mtool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(LoadActivity.this, TradeActivity.class);
                startActivity(in);
                LoadActivity.this.finish();

            }
        });

        Intent a = getIntent();
        fname = a.getStringExtra("farmername");
        bno = a.getStringExtra("billno");
        ctype=a.getStringExtra("coconuttype");
        //noofc=a.getStringExtra("noofcoconut");
       // cweight=a.getStringExtra("coconutweight");
        tradecoco=a.getStringExtra("tradecoconut");
        date=a.getStringExtra("dates");
        time=a.getStringExtra("time");

        intitalize();
    }

    private void intitalize() {
        et_farmername= (EditText) findViewById(R.id.farmername_et);
        et_farmername.setText(fname);
        et_farmername.setEnabled(false);

        getSupportActionBar().setTitle("Bill No:" + bno);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.transaction_header, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:

                if ((et_fromlocation.length() > 0) && (et_tolocation.length() > 0) &&
                        (et_vehicleno.length() > 0) && (et_drivername.length() > 0)&&(et_mdmobileno.length()>0))
                {
                    String fromlocation=et_fromlocation.getText().toString();
                    String tolocation=et_tolocation.getText().toString();
                    String vehicleno=et_vehicleno.getText().toString();
                    String drivername=et_drivername.getText().toString();

                    dbload.insertTrade(time,date,bno,fname,ctype,tradecoco,fromlocation,tolocation,vehicleno,drivername);
                    Log.e("Inseted", "Datainserted");

                    sendSmsMethod();

                    Intent d=new Intent(LoadActivity.this,TradeLoadDataViewActivity.class);
                    startActivity(d);
                    LoadActivity.this.finish();

                } else if (et_fromlocation.length() == 0) {
                    txl_fromlocation.setError(getResources().getString(R.string.flocation));
                } else if (et_tolocation.length() == 0) {
                    txl_tolocation.setError(getResources().getString(R.string.tlocation));
                } else if (et_vehicleno.length() == 0) {
                    txl_vehicleno.setError(getResources().getString(R.string.vno));
                } else if (et_drivername.length() == 0)
                {
                    txl_drivername.setError(getResources().getString(R.string.dname));
                }
                else if (et_mdmobileno.length()==0)
                {
                    txl_mdmobileno.setError(getResources().getString(R.string.mdmobile));
                }

        }
        return true;
    }

    private void sendSmsMethod() {

       String s="  ";
        String sy="---->";
        String t="\n";
        String f="F :";

        String dat=date;
        String ti=time;
        String bill=bno;
        String farmername=fname;
        String cotype=ctype;
        String tradco=tradecoco;


        String fromlocation=et_fromlocation.getText().toString();
        String tolocation=et_tolocation.getText().toString();
        String phoneNo=et_mdmobileno.getText().toString();
        String drivername=et_drivername.getText().toString();
        String vehicleno=et_vehicleno.getText().toString();
        String sms=dat+s+ti+t+bill+s+farmername+t+cotype+s+tradco+t+drivername+t+vehicleno+t+fromlocation+sy+tolocation;


        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, sms, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "SMS faild, please try again later!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
