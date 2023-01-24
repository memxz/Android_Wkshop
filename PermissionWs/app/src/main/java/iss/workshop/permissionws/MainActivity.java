package iss.workshop.permissionws;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private Button requestAudioBtn,receiveSMSBtn;
    private final int REQ_RECORD_AUDIO=1;
    private final int REQ_REC_SMS=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestAudioBtn=findViewById(R.id.reqAudioBtn);
        if(requestAudioBtn!=null){
            requestAudioBtn.setOnClickListener(this);
        }

        receiveSMSBtn=findViewById(R.id.recSMSBtn);
        if(receiveSMSBtn!=null){
            receiveSMSBtn.setOnClickListener(this);
        }
    }

    public boolean requestPermission(String permission, int requestCode){
        if(checkSelfPermission(permission)==PackageManager.PERMISSION_GRANTED)
            return true;
        else {
            ActivityCompat.requestPermissions(this, new String[]{permission},requestCode);
            return false;
        }
    }

    public void onClick(View v){
        if(v==requestAudioBtn){
            if(requestPermission(Manifest.permission.RECORD_AUDIO,REQ_RECORD_AUDIO)){
                Toast.makeText(this,getString(R.string.permission_record_audio_already_granted),
                        Toast.LENGTH_SHORT).show();
            }
        }
        if(v==receiveSMSBtn){
            if(requestPermission(Manifest.permission.RECORD_AUDIO,REQ_REC_SMS)){
                Toast.makeText(this,getString(R.string.permission_receive_sms_already_granted),
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull  int[] grantResult){
        super.onRequestPermissionsResult(requestCode,permissions,grantResult);

        String msg="";

        switch(requestCode){
            case REQ_RECORD_AUDIO:
                msg=grantResult[0]==PackageManager.PERMISSION_GRANTED?
                        getString(R.string.record_audio_granted):getString(R.string.record_audio_denied);
                break;

            case REQ_REC_SMS:
                msg=grantResult[1]==PackageManager.PERMISSION_GRANTED?
                        getString(R.string.receive_sms_granted):getString(R.string.receive_sms_denied);
                break;
        }

        if(!msg.isEmpty()){

            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }


    }


}