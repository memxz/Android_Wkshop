package iss.workshop.linearlayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1=findViewById(R.id.button1);
        button1.setOnClickListener(this);

        Button btn3=findViewById(R.id.button3);
        if (btn3 != null) {
            btn3.setOnClickListener(this);

        }


    }

    @Override
    public void onClick(View V){
        int id=V.getId();
        if(id==R.id.button1){
            Toast msg = Toast.makeText(this,
                    "Hello from ISS!", Toast.LENGTH_LONG);
            msg.show();
        }
        if(id==R.id.button3){
            String title = "save";
            String msg = "Confirm to save";
            AlertDialog.Builder dlg = new AlertDialog.Builder(this)
                    .setTitle(title)
                    .setMessage(msg)
                    .setPositiveButton(android.R.string.yes,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dlg, int which) {
                                    // your action here
                                    Toast.makeText(getApplicationContext(),"you choose Yes action for alertbox",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                    .setNegativeButton(android.R.string.no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // your action here
                                    Toast.makeText(getApplicationContext(),"you choose no action for alertbox",
                                            Toast.LENGTH_SHORT).show();
                                }
                            })
                    .setIcon(android.R.drawable.ic_dialog_alert);
            dlg.show();
        }
    }


}