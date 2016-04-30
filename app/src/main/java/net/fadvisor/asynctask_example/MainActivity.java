package net.fadvisor.asynctask_example;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSync(View view) {
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);

        textView1.setText("بدأ");
        for (int i=0 ; i<10 ; i++) {
            textView2.setText(String.valueOf(i));
            SystemClock.sleep(200);
        }
        textView1.setText("انتهى");
    }

    public void startAsync(View view) {
        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        final TextView textView4 = (TextView) findViewById(R.id.textView4);

        textView3.setText("بدأ");
        new AsyncTask<Void, Integer, Void>() {
            @Override
            protected Void doInBackground(Void...nothing) {
                for (int i=0 ; i<10 ; i++) {
                    publishProgress(i);
                    SystemClock.sleep(200);
                }
                return null;
            }
            protected void onProgressUpdate(Integer... value) {
                textView4.setText(value[0].toString());
            };
        }.execute();

        textView3.setText("انتهى");
    }

    public void startClear(View view) {
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        TextView textView4 = (TextView) findViewById(R.id.textView4);

        textView1.setText("");
        textView2.setText("0");
        textView3.setText("");
        textView4.setText("0");
    }
}
