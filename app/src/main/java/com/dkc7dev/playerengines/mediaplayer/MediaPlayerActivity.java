package com.dkc7dev.playerengines.mediaplayer;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.dkc7dev.playerengines.R;

import java.io.IOException;

public class MediaPlayerActivity extends AppCompatActivity implements
        MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener,
        SurfaceHolder.Callback {
    private MediaPlayer sampleMediaPlayer;


    // SurfaceView and SurfaceHolder for MediaPlayer video surface
    private SurfaceView videoSurfaceView;
    private SurfaceHolder surfaceHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_activity);

        sampleMediaPlayer = new MediaPlayer();
        sampleMediaPlayer.setOnCompletionListener(this);
        sampleMediaPlayer.setOnPreparedListener(this);

        // Set MediaPlayer video playback surface
        videoSurfaceView = (SurfaceView)findViewById(R.id.surfaceView);
        surfaceHolder = videoSurfaceView.getHolder();
        // MediaPlayer.setDisplay() will be called in :surfaceCreated() callback
        surfaceHolder.addCallback(this);
    }

    @Override
    protected  void onResume() {

        sampleMediaPlayer.reset();
        try {
            sampleMediaPlayer.setDataSource(getIntent().getDataString());
        } catch (IOException e) {
            Log.e("MediaPlayerActivity","start playback",e);
        }
        sampleMediaPlayer.prepareAsync();

        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }

    protected void releasePlayer() {

        if (sampleMediaPlayer != null) {
            sampleMediaPlayer.setDisplay(null);
            sampleMediaPlayer.stop();
            sampleMediaPlayer.release();
            sampleMediaPlayer = null;
        }
    }

    // Implementation of MediaPlayer Listener callbacks
    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //MediaPlayer.TrackInfo info[] = mp.getTrackInfo();

        sampleMediaPlayer.start();
    }

    //  Implementation of SurfaceHolder callbacks
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        sampleMediaPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}