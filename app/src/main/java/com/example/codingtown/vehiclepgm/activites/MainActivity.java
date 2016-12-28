package com.example.codingtown.vehiclepgm.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.codingtown.vehiclepgm.R;

/**
 * Created by CodingTown on 27-12-2016.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);  //10000 elay of 10 seconds
                } catch (Exception e) {

                } finally {

                    Intent i = new Intent(MainActivity.this, TradeLoadDataViewActivity.class);
                    startActivity(i);
                    MainActivity.this.finish();
                }
            }
        };
        welcomeThread.start();

    }
}
