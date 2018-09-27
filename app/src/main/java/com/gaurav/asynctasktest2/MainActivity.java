package com.gaurav.asynctasktest2;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn;
    ProgressBar progressB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.button);
        progressB = (ProgressBar) findViewById(R.id.progress);
        progressB.setMax(17);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Task().execute();
            }
        });
    }
    public class Task extends AsyncTask<Void, Integer, Void>{

        @Override
        protected void onPreExecute() {
            TextView tx = (TextView) findViewById(R.id.textV);
            tx.setText("started");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i = 0; i < 10; i++){
                try {
                    Thread.sleep(1000);
                    publishProgress(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            TextView tx = (TextView) findViewById(R.id.textV);
            tx.setText("Executed");
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressB.setProgress(values[0]);
            super.onProgressUpdate(values);
        }
    }
}
