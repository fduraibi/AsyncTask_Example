package net.fadvisor.asynctask_example;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resetValues();
    }

    public void startSync(View view) {
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);

        textView1.setText("بدأ");
        for (int i=1 ; i<51 ; i++) {
            textView2.setText(String.valueOf(i));
            progressBar1.setProgress(i);
            SystemClock.sleep(100);
        }
        textView1.setText("خطوه 2");
    }

    public void startAsync(View view) {
        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        final TextView textView4 = (TextView) findViewById(R.id.textView4);
        final ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        textView3.setText("بدأ");
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void...nothing) {
                for (int i=1 ; i<51 ; i++) {
                    publishProgress(i);
                    SystemClock.sleep(100);
                }
                return null;
            }
            protected void onProgressUpdate(Integer... value) {
                textView4.setText(value[0].toString());
                progressBar2.setProgress(value[0]);
            };
        }.execute();

        textView3.setText("خطوه 2");
    }

    public void startClear(View view) {
        resetValues();
    }

    private void resetValues() {
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);
        ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progressBar1);
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);

        textView1.setText("العمل في الواجهة");
        textView2.setText("0");
        textView3.setText("العمل في الخلفية");
        textView4.setText("0");
        progressBar1.setProgress(0);
        progressBar2.setProgress(0);
    }
}
