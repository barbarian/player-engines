package com.dkc7dev.playerengines.videoView;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.VideoView;

import com.dkc7dev.playerengines.R;


public class MediaActivity extends AppCompatActivity implements  MediaPlayer.OnPreparedListener , MediaPlayer.OnCompletionListener,
        WafleMediaController.MediaPlayerControl,
        View.OnTouchListener
{
    private final String TAG = MediaActivity.class.getSimpleName();

    private VideoView vvContainer;
    //private MediaController mcPlayer;
    //private ConstantAnchorMediaController mcPlayer;
    private WafleMediaController mcPlayer;
    private FrameLayout rlMediaControllerContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "onCreate");


        setContentView(R.layout.activity_media);

        vvContainer = (VideoView) findViewById(R.id.vvContainer);
        rlMediaControllerContainer = (FrameLayout) findViewById(R.id.rlMediaControllerContainer);

        vvContainer.setOnTouchListener(this);

        //mcPlayer = new MediaController(this, false);
        //mcPlayer = new ConstantAnchorMediaController(this, rlMediaControllerContainer);
        mcPlayer = new WafleMediaController(this);
        //mcPlayer.setMediaPlayer(vvContainer);
        //mcPlayer.setAnchorView(vvContainer);

        /*
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.BOTTOM;
        mcPlayer.setLayoutParams(lp);

        ((ViewGroup) mcPlayer.getParent()).removeView(mcPlayer);
        ((FrameLayout) findViewById(R.id.rlMediaControllerContainer)).addView(mcPlayer);
        */

        vvContainer.setOnPreparedListener(this);
        vvContainer.setOnCompletionListener(this);
        vvContainer.setVideoURI(getIntent().getData());

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.v(TAG, "onPrepared");
        mcPlayer.setMediaPlayer(this);
        mp.start();
        mcPlayer.setAnchorView(rlMediaControllerContainer);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        Log.v(TAG, "onCompletion");
    }

    @Override
    public void start() {
        vvContainer.start();
    }

    @Override
    public void pause() {
        vvContainer.pause();
    }

    @Override
    public int getDuration() {
        return vvContainer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return vvContainer.getCurrentPosition();
    }

    @Override
    public void seekTo(int pos) {
        vvContainer.seekTo(pos);
    }

    @Override
    public boolean isPlaying() {
        return vvContainer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    public void toggleFullScreen() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        mcPlayer.show();
        return false;
    }
}