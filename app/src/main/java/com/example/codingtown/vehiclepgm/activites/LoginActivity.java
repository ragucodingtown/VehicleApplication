package com.example.codingtown.vehiclepgm.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.codingtown.vehiclepgm.R;
import com.example.codingtown.vehiclepgm.sharedprefrence.LoginSession;

/**
 * Created by CodingTown on 13-12-2016.
 */
public class LoginActivity extends AppCompatActivity {

    TextInputLayout txl_mobileno;
    EditText et_mobileno;
    Button btn_login;
    LoginSession loginSession;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
     loginSession=new LoginSession(getApplicationContext());
        txl_mobileno=(TextInputLayout)findViewById(R.id.mobileno_txl);
        et_mobileno=(EditText)findViewById(R.id.mobileno_et);
        btn_login=(Button)findViewById(R.id.login_btn);

               btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobno=et_mobileno.getText().toString();
                if (et_mobileno.getText().toString().equals("9865380903"))
                {
                    Toast.makeText(getApplicationContext(), "login Sucess", Toast.LENGTH_LONG).show();
                    loginSession.createLoginSession(et_mobileno.getText().toString());
                    Intent ii=new Intent(LoginActivity.this,TradeLoadDataViewActivity.class);
                    startActivity(ii);
                    LoginActivity.this.finish();
                }

              /*  else if ((et_mobileno.length() == 10)&&(mobno.startsWith("7")||mobno.startsWith("8")||mobno.startsWith("9")))
                {
                    Intent ii=new Intent(LoginActivity.this,TradeLoadDataViewActivity.class);
                    startActivity(ii);
                    LoginActivity.this.finish();
                }*/

                else if (et_mobileno.length() ==0)
                {
                    txl_mobileno.setError(getResources().getString(R.string.mobileno));
                }

                else if ((mobno.startsWith("7")||mobno.startsWith("8")||mobno.startsWith("9")))
                {
                    txl_mobileno.setError(getResources().getString(R.string.tendigit));
                }

                else if ((et_mobileno.length()== 10)&&(mobno.startsWith("6")||mobno.startsWith("5")||mobno.startsWith("4")||
                        mobno.startsWith("3") ||mobno.startsWith("2")||mobno.startsWith("1")||mobno.startsWith("0")))
                {
                     txl_mobileno.setError(getResources().getString(R.string.staartno));
                }
                else if (mobno.startsWith("6")||mobno.startsWith("5")||mobno.startsWith("4")||mobno.startsWith("3")
                        ||mobno.startsWith("2")||mobno.startsWith("1")||mobno.startsWith("0"))
                {
                    txl_mobileno.setError(getResources().getString(R.string.startanddigit));
                }

            }
        });


    }
    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
