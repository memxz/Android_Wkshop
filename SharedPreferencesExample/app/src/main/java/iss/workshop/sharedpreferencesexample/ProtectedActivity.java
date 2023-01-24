package iss.workshop.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ProtectedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected);
    }
}