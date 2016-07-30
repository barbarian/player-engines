package com.dkc7dev.playerengines.videoView;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by ezepeda on 28-08-15.
 */
public class ConstantAnchorMediaController extends WafleMediaController
{
    private FrameLayout anchorView;


    public ConstantAnchorMediaController(Context context, FrameLayout anchorView)
    {
        super(context);
        this.anchorView = anchorView;
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld)
    {
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) anchorView.getLayoutParams();
        lp.setMargins(0, 0, 0, yNew);

        anchorView.setLayoutParams(lp);
        anchorView.requestLayout();
    }
}