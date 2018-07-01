package com.om.omplayer.frag;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.om.omplayer.R;
import com.om.omplayer.pojo.Track;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class BannerFragment extends Fragment {

    public static final String KEY_TRACK = "key:data-track";

    public static BannerFragment newInstance(Track track) {
        BannerFragment fragment = new BannerFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_TRACK, track);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.banner_slider, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RelativeLayout layout = view.findViewById(R.id.relativeLayout);
        AppCompatTextView tvTrackName = view.findViewById(R.id.tv_track_name);
        AppCompatTextView tvArtistName = view.findViewById(R.id.tv_artist_name);

        Bundle bundle = getArguments();
        if (bundle != null) {
            Track track = bundle.getParcelable(KEY_TRACK);
            if (track != null) {
                layout.setBackgroundColor(Color.parseColor(track.getImgColorHexcode()));
                tvTrackName.setText(track.getTrackName());
                tvArtistName.setText(track.getArtistName());
            }
        }
    }
}
