package iss.workshop.dlimagethreadws;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String URL="https://p4.wallpaperbetter.com/wallpaper/291/663/679/stones-background-stones-spa-wallpaper-preview.jpg";
        startDownloadingImage(URL);
    }

    protected void startDownloadingImage(String imageURL) {

        String destFilename= UUID.randomUUID().toString()+
                imageURL.lastIndexOf(".")+1;

        File dir=getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File destFile=new File(dir,destFilename);

        //Creating a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                ImageDownloader imgDL=new ImageDownloader();
                if(imgDL.downloadingImage(imageURL,destFile)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bitmap= BitmapFactory.decodeFile(destFile.getAbsolutePath());
                            ImageView imageView=findViewById(R.id.imageView);
                            imageView.setImageBitmap(bitmap);
                        }
                    });
                }
            }
        }).start();
    }
}