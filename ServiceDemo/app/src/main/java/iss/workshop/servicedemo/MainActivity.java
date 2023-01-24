package iss.workshop.servicedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        demoService();

    }

    protected void demoService(){
        Intent intent =new Intent(this, MyService.class);

        intent.setAction("download");
        intent.putExtra("name","rain");
        //startService(intent);

        intent.setAction("play_music");
        startService(intent);
    }
}