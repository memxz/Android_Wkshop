package iss.workshop.downloadmultiimage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnDownload;
    String url="https://stocksnap.io";
    List<String> urlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            urlList=GetWeb.getImagePathList(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageView = findViewById(R.id.imageView);
        btnDownload = findViewById(R.id.btnDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Picasso.with(MainActivity.this)
                        .load("https://cdn.stocksnap.io/img-thumbs/960w/boat-lake_USNUCINPEP.jpg")
                        .into(imageView);
            }
        });
    }
}