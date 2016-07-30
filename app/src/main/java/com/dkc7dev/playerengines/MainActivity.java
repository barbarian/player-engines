package com.dkc7dev.playerengines;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.dkc7dev.playerengines.adapter.PlayerItem;
import com.dkc7dev.playerengines.adapter.PlayersAdapter;
import com.dkc7dev.playerengines.exoplayer.PlayerActivity;
import com.dkc7dev.playerengines.libvlc.VideoActivity;
import com.dkc7dev.playerengines.mediaplayer.MediaPlayerActivity;
import com.dkc7dev.playerengines.videoView.MediaActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PlayersAdapter.ItemListener {


    private PlayersAdapter mAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chooser);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.players_list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new PlayersAdapter(createItems(), this);
        recyclerView.setAdapter(mAdapter);

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.setListener(null);
    }

    public List<PlayerItem> createItems() {
        ArrayList<PlayerItem> items = new ArrayList<>();
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "VideoView Player",PlayerItem.VIDEO_PLAYER));
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "Android Media Player",PlayerItem.MEDIA_PLAYER));
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "Exo Player",PlayerItem.EXO_PLAYER));
        //items.add(new PlayerItem(R.drawable.ic_link_24dp, "Vitamio Player",PlayerItem.VITAMIO_PLAYER));
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "LibVLC Player",PlayerItem.VLC_PLAYER));
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "Other player",PlayerItem.OTHER_PLAYER));
        return items;
    }
    public static final String CONTENT_TYPE_EXTRA = "content_type";
    private static final String CONTENT_EXT_EXTRA = "type";
    @Override
    public void onItemClick(PlayerItem item) {
        Uri contentUri = getIntent().getData();
        if(contentUri==null) return;

        if("acestream".equalsIgnoreCase(contentUri.getScheme())){
            contentUri = Uri.parse("http://127.0.0.1:6878/ace/manifest.m3u8?id="+contentUri.getHost()+"&transcode_audio=1&transcode_mp3=1");
        }

        int contentType = getIntent().getIntExtra(CONTENT_TYPE_EXTRA,
                PlayerActivity.inferContentType(contentUri, getIntent().getStringExtra(CONTENT_EXT_EXTRA)));


        Intent intent = new Intent(Intent.ACTION_VIEW);
        switch (item.getType()){

            case PlayerItem.VIDEO_PLAYER:
                intent= new Intent(this,MediaActivity.class);
                break;
            case PlayerItem.MEDIA_PLAYER:
                intent= new Intent(this,MediaPlayerActivity.class);
                break;
            case PlayerItem.EXO_PLAYER:
                intent= new Intent(this,PlayerActivity.class);
                break;
            case PlayerItem.VLC_PLAYER:
                intent= new Intent(this,VideoActivity.class);
                break;

        }

        intent.setData(contentUri);
        intent.putExtra(CONTENT_TYPE_EXTRA,contentType);

        startActivity(intent);
    }
}
