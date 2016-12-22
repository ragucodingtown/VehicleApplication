package com.example.codingtown.vehiclepgm.activites;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.codingtown.vehiclepgm.R;
import com.example.codingtown.vehiclepgm.autocompletetextviews.CustomAutoCompleteView;
import com.example.codingtown.vehiclepgm.autocompletetextviews.MyObject;
import com.example.codingtown.vehiclepgm.database.DatabaseHandlers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TradeActivity extends AppCompatActivity implements TextWatcher {
    DatabaseHandlers databaseHandlers;
    private int mYear, mMonth, mDay;

    CustomAutoCompleteView actv_coconuttype;

    Toolbar mtool;
    AlertDialog alert;

    int noofcoconut_i, wastagecoconut_i, totalcoconut_i;
    String noofcocnut_s, wastagecoconut_s, totalcoconut_s;

    EditText et_billno, et_farmername, et_coconutype, et_noofcoconut, et_wastagecoconut, et_tradecoconut;
    TextInputLayout txl_farmername, txl_coconuttype, txl_noofcoconut, txl_wastagecoconut, txl_tradecoconut;

    ImageView imv_addtype;
    LinearLayout lnl_piece, lnl_tradecoconut;
    RadioGroup rgb_pieceweight;
    RadioButton rbtn_piece, rbtn_weight;
    ArrayAdapter<String> myAdapter;
    String[] item = new String[]{"Please search..."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trade_page);

        mtool = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtool);
        String dates = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        getSupportActionBar().setTitle(dates);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mtool.setNavigationIcon(R.mipmap.back);

        mtool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cc = new Intent(TradeActivity.this, TradeLoadDataViewActivity.class);
                startActivity(cc);
                TradeActivity.this.finish();

            }
        });

        initialize();

    }

    private void initialize() {
        databaseHandlers = new DatabaseHandlers(getApplicationContext());

        actv_coconuttype = (CustomAutoCompleteView) findViewById(R.id.coconuttype_et);

        et_billno = (EditText) findViewById(R.id.billno_et);
        et_farmername = (EditText) findViewById(R.id.farmername_et);
        et_coconutype = (EditText) findViewById(R.id.coconuttype_et);
        et_noofcoconut = (EditText) findViewById(R.id.noofcoconut_et);
        et_wastagecoconut = (EditText) findViewById(R.id.wastagecoconut_et);
        et_tradecoconut = (EditText) findViewById(R.id.tradecoconut_et);

        txl_farmername = (TextInputLayout) findViewById(R.id.farmername_txl);
        txl_coconuttype = (TextInputLayout) findViewById(R.id.coconuttype_txl);
        txl_noofcoconut = (TextInputLayout) findViewById(R.id.noofcoconut_txl);
        txl_wastagecoconut = (TextInputLayout) findViewById(R.id.wastagecoconut_txl);
        txl_tradecoconut = (TextInputLayout) findViewById(R.id.tradecoconut_txl);

        imv_addtype = (ImageView) findViewById(R.id.addtype_imv);


        lnl_piece = (LinearLayout) findViewById(R.id.piece_lnl);
        lnl_tradecoconut = (LinearLayout) findViewById(R.id.tradecoconut_lnl);

        rgb_pieceweight = (RadioGroup) findViewById(R.id.pieceweight_rgb);
        rbtn_piece = (RadioButton) findViewById(R.id.piece_rbtn);
        rbtn_weight = (RadioButton) findViewById(R.id.weight_rbtn);
        rbtn_piece.setChecked(true);
        txl_tradecoconut.setHint(getResources().getString(R.string.piece));

        et_noofcoconut.addTextChangedListener(this);
        et_wastagecoconut.addTextChangedListener(this);

        actv_coconuttype.addTextChangedListener(new CustomAutoCompleteTextChangedListener(this));

        // set our adapter
        myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
        actv_coconuttype.setAdapter(myAdapter);

        imv_addtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //coconuttypeadd();

                final LayoutInflater layoutInflater = LayoutInflater.from(TradeActivity.this);
                final View promptView = layoutInflater.inflate(R.layout.addcoconuttype, null);
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(TradeActivity.this);
                alertDialogBuilder.setView(promptView);
                alertDialogBuilder.setCancelable(true);

                final TextInputLayout txl_addcoconuttype = (TextInputLayout) promptView.findViewById(R.id.addcoconuttype_txl);
                final EditText et_addcoconuttype = (EditText) promptView.findViewById(R.id.addcoconuttype_et);
                final Button btn_save = (Button) promptView.findViewById(R.id.addsave_btn);
                alert = alertDialogBuilder.create();
                alert.show();

                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (et_addcoconuttype.length() > 0) {
                            String coconuttype = et_addcoconuttype.getText().toString();
                            databaseHandlers.insertCoconut(coconuttype);
                            alert.dismiss();
                            Log.e("Inseted", "Datainserted");
                        } else if (et_addcoconuttype.length() == 0) {
                            txl_addcoconuttype.setError(getResources().getString(R.string.addcoconuttype));
                        }
                    }
                });
            }
        });


        rgb_pieceweight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int position = rgb_pieceweight.getCheckedRadioButtonId();
                switch (position) {

                    case R.id.piece_rbtn:
                        lnl_piece.setVisibility(View.VISIBLE);
                        lnl_tradecoconut.setVisibility(View.VISIBLE);
                        txl_tradecoconut.setHint(getResources().getString(R.string.piece));
                        et_tradecoconut.setText(" ");
                        break;

                    case R.id.weight_rbtn:
                        lnl_piece.setVisibility(View.GONE);
                        lnl_tradecoconut.setVisibility(View.VISIBLE);
                        txl_tradecoconut.setHint(getResources().getString(R.string.weight));
                        et_tradecoconut.setText(" ");
                        break;
                }

            }
        });


    }

    private void coconuttypeadd() {

    }

    //coconuttype databasestuff
    public String[] getItemsFromDb(String searchTerm) {

        // add items on the array dynamically
        List<MyObject> products = databaseHandlers.read(searchTerm);
        int rowCount = products.size();

        String[] item = new String[rowCount];
        int x = 0;

        for (MyObject record : products) {

            item[x] = record.objectName;
            x++;
        }

        return item;
    }

    //coconuttype autocompletelistener
    private class CustomAutoCompleteTextChangedListener implements TextWatcher {
        public static final String TAG = "CustomAutoCompleteTextChangedListener.java";
        Context context;

        public CustomAutoCompleteTextChangedListener(Context context) {
            this.context = context;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence userInput, int start, int before, int count) {

            // if you want to see in the logcat what the user types
            // Log.e(TAG, "User input: " + userInput);

            TradeActivity mainActivity = ((TradeActivity) context);

            // query the database based on the user input
            mainActivity.item = mainActivity.getItemsFromDb(userInput.toString());

            // update the adapater
            mainActivity.myAdapter.notifyDataSetChanged();
            mainActivity.myAdapter = new ArrayAdapter<String>(mainActivity, android.R.layout.simple_dropdown_item_1line, mainActivity.item);
            mainActivity.actv_coconuttype.setAdapter(mainActivity.myAdapter);

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private void totalTrade() {
        noofcocnut_s = et_noofcoconut.getText().toString();
        wastagecoconut_s = et_wastagecoconut.getText().toString();

        try {
            noofcoconut_i = Integer.parseInt(noofcocnut_s);
            wastagecoconut_i = Integer.parseInt(wastagecoconut_s);
            totalcoconut_i = noofcoconut_i - wastagecoconut_i;

        } catch (NumberFormatException e) {
        }
        totalcoconut_s = String.valueOf(totalcoconut_i);
        et_tradecoconut.setText(totalcoconut_s);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.headbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_date:
                dateMethod();
                break;

            case R.id.action_save:

                if (rbtn_piece.isChecked()) {
                    if (et_billno.length() == 0) {
                        et_billno.setError(getResources().getString(R.string.billno));
                    } else if (et_farmername.length() == 0) {
                        txl_farmername.setError(getResources().getString(R.string.namefarmer));
                    } else if (et_coconutype.length() == 0) {
                        txl_coconuttype.setError(getResources().getString(R.string.typecoconut));
                    } else if (et_noofcoconut.length() == 0) {
                        txl_noofcoconut.setError(getResources().getString(R.string.nofcoconut));
                    } else if ((et_billno.length() > 0) && (et_farmername.length() > 0) &&
                            (et_coconutype.length() > 0) && (et_noofcoconut.length() > 0)) {
                        goToNextPage();

                    }
                } else if (rbtn_weight.isChecked()) {
                    if (et_billno.length() == 0) {
                        et_billno.setError(getResources().getString(R.string.billno));
                    } else if (et_farmername.length() == 0) {
                        txl_farmername.setError(getResources().getString(R.string.namefarmer));
                    } else if (et_coconutype.length() == 0) {
                        txl_coconuttype.setError(getResources().getString(R.string.typecoconut));
                    } else if (et_tradecoconut.length() == 0) {
                        txl_coconuttype.setError(getResources().getString(R.string.weightcoconut));
                    } else if ((et_billno.length() > 0) && (et_farmername.length() > 0) &&
                            (et_coconutype.length() > 0) && (et_tradecoconut.length() > 0)) {
                        goToNextPage();
                    }

                }

                break;
        }
        return true;
    }

    public void dateMethod() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mtool.setTitle(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        //    .setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void goToNextPage()//  this datas pass to another pages
    {
        Intent a = new Intent(TradeActivity.this, LoadActivity.class);
        a.putExtra("farmername", et_farmername.getText().toString());
        a.putExtra("billno", et_billno.getText().toString());
        a.putExtra("coconuttype", et_coconutype.getText().toString());
        a.putExtra("noofcoconut", et_noofcoconut.getText().toString());
        a.putExtra("tradecoconut", et_tradecoconut.getText().toString());
        a.putExtra("dates", getSupportActionBar().getTitle());

        startActivity(a);
        TradeActivity.this.finish();


    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        totalTrade();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}