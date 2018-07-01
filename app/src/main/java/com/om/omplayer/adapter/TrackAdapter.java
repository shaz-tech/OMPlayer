package com.om.omplayer.adapter;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.om.omplayer.R;
import com.om.omplayer.pojo.Track;

import java.util.List;

/**
 * Created by ${Shahbaz} on 01-06-2018
 */
public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.RootVH> {

    public static final int TYPE_NEW_SONGS = 1, TYPE_TRENDING_SONGS = 2;

    private List<Track> mTracks;
    private int mTrackType;

    public TrackAdapter(int trackType, List<Track> tracks) {
        this.mTrackType = trackType;
        this.mTracks = tracks;
    }

    @NonNull
    @Override
    public RootVH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        RootVH rootVH;
        if (mTrackType == TYPE_NEW_SONGS) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_songs, parent, false);
            rootVH = new NewSongsVH(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_trending_songs, parent, false);
            rootVH = new TrendingSongsVH(view);
        }
        return rootVH;
    }

    @Override
    public int getItemViewType(int position) {
        return mTrackType;
    }

    @Override
    public void onBindViewHolder(@NonNull RootVH rootVH, int i) {
        Track track = mTracks.get(i);
        rootVH.track.setText(track.getTrackName());
        rootVH.artist.setText(track.getArtistName());
        ColorDrawable imgDrawable = new ColorDrawable(Color.parseColor(track.getImgColorHexcode()));
        if (mTrackType == TYPE_NEW_SONGS) {
            NewSongsVH vh = (NewSongsVH) rootVH;
            vh.left.setImageDrawable(imgDrawable);
            vh.right.setImageDrawable(imgDrawable);
        } else {
            TrendingSongsVH vh = (TrendingSongsVH) rootVH;
            vh.top.setImageDrawable(imgDrawable);
        }
    }

    @Override
    public int getItemCount() {
        return mTracks.size();
    }

    class RootVH extends RecyclerView.ViewHolder {

        private AppCompatTextView track, artist;

        public RootVH(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        private void bindViews(View itemView) {
            track = itemView.findViewById(R.id.tv_track_name);
            artist = itemView.findViewById(R.id.tv_artist_name);
        }
    }

    public class NewSongsVH extends RootVH {

        private AppCompatImageView left, right;

        NewSongsVH(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        private void bindViews(View itemView) {
            left = itemView.findViewById(R.id.iv_left);
            right = itemView.findViewById(R.id.iv_right);
        }
    }

    public class TrendingSongsVH extends RootVH {

        private AppCompatImageView top;

        TrendingSongsVH(@NonNull View itemView) {
            super(itemView);
            bindViews(itemView);
        }

        private void bindViews(View itemView) {
            top = itemView.findViewById(R.id.iv_top);
        }
    }
}
