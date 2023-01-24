package iss.workshop.mymusicservicews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener {

    private final ArrayList<Song> songs=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSongList();
        initListView();
        initStopRequest();
        initService();
    }

    protected void initSongList(){

        songs.add(new Song("Creative Minds",
                "Inspiring music featuring guitars.",
                "canon"));

        songs.add(new Song("Memories",
                "Music composition featuring piano and drums,",
                "scandinavia"));

        songs.add(new Song("Acoustic breeze",
                "Acoustic music with a soft and mellow mood",
                "johann_canon"));

        songs.add(new Song("Buddy",
                "Music with a playful and happy mood",
                "scandi"));

        songs.add(new Song("Dreams",
                "Music with bensound",
                "dreams"));
    }

    protected  void initListView(){

        ListView listView=findViewById(R.id.listView);
        if(listView!=null){
            listView.setAdapter(new MyAdapter(this,songs));
            listView.setOnItemClickListener(this);

        }
    }

    protected  void initStopRequest(){
        Button stopBtn=findViewById(R.id.stopBtn);
        if(stopBtn!=null){
            stopBtn.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    Intent intent=new Intent(
                            MainActivity.this,MyMusicService.class);
                    intent.setAction("stop");
                    startService(intent);
                }
            });
        }
    }

    protected void initService(){
        Intent intent =new Intent(this,MyMusicService.class);
        intent.setAction("init_songs");
        intent.putExtra("songs",songs);

        startService(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> av,View v,int pos, long id){
        Intent intent=new Intent(this,MyMusicService.class);
        intent.setAction("play");
        intent.putExtra("song_idx",pos);
        startService(intent);
    }
}