package iss.workshop.mymusicservicews;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class MyMusicService extends Service {
    public MyMusicService() {
    }

    private static final String TITLE="My Music Service";
    private static final String CHANNEL_ID="MyMusicService_Channel";
    private static final int FOREGD_NOTIFY_ID=1;
    private MediaPlayer player=null;
    private int currSongidx=0;
    private ArrayList<Song> songs=new ArrayList<>();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        startInForeground();
    }
    protected void startInForeground(){
        createNotificationChannel();

        Notification notification=createNotification(TITLE,"");
        startForeground(FOREGD_NOTIFY_ID,notification);
    }

    protected void createNotificationChannel(){
        NotificationManager notiMgr=getSystemService(NotificationManager.class);

        NotificationChannel serviceChannel=new NotificationChannel(CHANNEL_ID,
                CHANNEL_ID,NotificationManager.IMPORTANCE_DEFAULT);
        serviceChannel.setSound(null,null);
        notiMgr.createNotificationChannel((serviceChannel));
    }

    protected Notification createNotification(String title,String text){
        Intent intent=new Intent(this,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);

        return new NotificationCompat.Builder(this,CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(pendingIntent)
                .build();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        String action =intent.getAction();

        if(action!=null){
            if(action.equalsIgnoreCase("play")){
                currSongidx=intent.getIntExtra("sing_idx",0);

                if(player!=null){
                    stopSong();
                }
                playSong();
            }
            else if(action.equalsIgnoreCase("stop")){
                stopSong();
            }
            else if(action.equalsIgnoreCase("init_songs")){
                songs=(ArrayList<Song>)intent.getSerializableExtra("songs");
            }
        }

        return super.onStartCommand(intent,flags,startId);
    }

    protected void playSong(){
        if(player!=null){
            stopSong();
        }
        Song song=songs.get(currSongidx);
        int resId=getResources().getIdentifier(song.getFname(),
                "raw",getPackageName());

        player= MediaPlayer.create(this,resId);
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                currSongidx=(currSongidx+1)%songs.size();
                playSong();
            }
        });
        player.start();
        onPlay();
    }

    protected void stopSong(){
        if(player!=null){
            player.stop();
            player.release();
            player=null;
        }
        onStop();
    }

    protected void onPlay(){
        Song song=songs.get(currSongidx);

        NotificationManager notifMgr=getSystemService(NotificationManager.class);
        Notification notification=createNotification(TITLE,"Playing \""+song.getTitle()+"\"");
        notifMgr.notify(FOREGD_NOTIFY_ID,notification);

    }

    protected void onStop(){
        NotificationManager notifMgr=getSystemService((NotificationManager.class));
        Notification notification=createNotification(TITLE,"");
        notifMgr.notify(FOREGD_NOTIFY_ID,notification);
    }
}