package com.dkc7dev.playerengines;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.dkc7dev.playerengines.adapter.PlayerItem;
import com.dkc7dev.playerengines.adapter.PlayersAdapter;

import java.util.ArrayList;
import java.util.List;


public class PlayerChooserFragment extends BottomSheetDialogFragment
        implements PlayersAdapter.ItemListener {

    private BottomSheetBehavior mBehavior;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.sheet, null);

        view.findViewById(R.id.fakeShadow).setVisibility(View.GONE);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.players_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        PlayersAdapter itemAdapter = new PlayersAdapter(createItems(), this);
        recyclerView.setAdapter(itemAdapter);

        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public List<PlayerItem> createItems() {
        ArrayList<PlayerItem> items = new ArrayList<>();
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "Exo Player",PlayerItem.EXO_PLAYER));
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "LibVLC Player",PlayerItem.VLC_PLAYER));
        items.add(new PlayerItem(R.drawable.ic_link_24dp, "Other player",PlayerItem.OTHER_PLAYER));
        return items;
    }

    @Override
    public void onItemClick(PlayerItem item) {
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}