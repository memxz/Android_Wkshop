package iss.workshop.permissiondangerous;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final int REQ_PERMISSION=1;
    //private final int REQ_CAMERA=2;

    String[] permissions = { Manifest.permission.RECORD_AUDIO,Manifest.permission.CAMERA  };
    //String[] camera_permissions = { Manifest.permission.CAMERA };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                permissions, REQ_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                    @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions,grantResults);
        if (requestCode == REQ_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                int i=0;
                // record audio
            }
            if(grantResults[1]== getPackageManager().PERMISSION_GRANTED){

            }
        }
    }
}