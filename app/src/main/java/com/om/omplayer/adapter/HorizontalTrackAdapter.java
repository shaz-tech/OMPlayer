package com.om.omplayer.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.om.omplayer.R;
import com.om.omplayer.pojo.Horizon;

import java.util.List;


/**
 * Created by ${Shahbaz} on 01-06-2018
 */
public class HorizontalTrackAdapter extends RecyclerView.Adapter<HorizontalTrackAdapter.RootVH> {

    private List<Horizon> mHorizons;

    public HorizontalTrackAdapter(List<Horizon> horizons) {
        this.mHorizons = horizons;
    }

    @NonNull
    @Override
    public RootVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_horizontal_track, parent, false);
        return new RootVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RootVH rootVH, int i) {
        TrackAdapter adapter = new TrackAdapter(TrackAdapter.TYPE_NEW_SONGS, mHorizons.get(i).getTracks());
        rootVH.rvHorizontal.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return mHorizons.size();
    }

    class RootVH extends RecyclerView.ViewHolder {

        private RecyclerView rvHorizontal;

        RootVH(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        private void bindViews(View itemView) {
            rvHorizontal = itemView.findViewById(R.id.rv_horizontal_track);
            rvHorizontal.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
