package iss.workshop.progressbar;

import static java.lang.Thread.sleep;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ProgressBar pgsBar;
    private int i = 0;
    private TextView txtView;
    private Handler hdlr = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgsBar = (ProgressBar) findViewById(R.id.pBar);
        txtView = (TextView) findViewById(R.id.tView);
        Button btn = (Button)findViewById(R.id.btnShow);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = pgsBar.getProgress();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (i < 100) {
                            i += 1;
                            // Update the progress bar and display the current value in text view
                            hdlr.post(new Runnable() {
                                public void run() {
                                    pgsBar.setProgress(i);
                                    txtView.setText(i+"/"+pgsBar.getMax());
                                }
                            });
                            try {
                                // Sleep for 100 milliseconds to show the progress slowly.
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

            }
        });
    }
}

