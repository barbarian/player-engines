package com.dkc7dev.playerengines.adapter;

import android.support.annotation.DrawableRes;

public class PlayerItem {

    public static final int VIDEO_PLAYER = 100;
    public static final int MEDIA_PLAYER = 101;
    public static final int EXO_PLAYER = 102;
    public static final int VLC_PLAYER = 103;
    public static final int VITAMIO_PLAYER = 104;

    public static final int OTHER_PLAYER = 200;
    private  int mType;
    private int mDrawableRes;

    private String mTitle;

    public PlayerItem(@DrawableRes int drawable, String title, int type) {
        mDrawableRes = drawable;
        mTitle = title;
        mType = type;
    }

    public int getDrawableResource() {
        return mDrawableRes;
    }

    public String getTitle() {
        return mTitle;
    }

    public int getType() {
        return mType;
    }
}
