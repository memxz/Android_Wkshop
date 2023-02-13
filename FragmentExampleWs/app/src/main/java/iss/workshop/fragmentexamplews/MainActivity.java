package iss.workshop.fragmentexamplews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements
        ListFragment.IListFragment {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void viewDetail(int itemId) {
        startDetailActivity(itemId);
    }

    void startDetailActivity(int newItemId) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemId", newItemId);
        startActivity(intent);
    }
}