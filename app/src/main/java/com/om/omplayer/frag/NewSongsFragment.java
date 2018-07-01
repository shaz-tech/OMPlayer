package com.om.omplayer.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.om.omplayer.R;
import com.om.omplayer.adapter.HorizontalTrackAdapter;
import com.om.omplayer.pojo.DataBean;
import com.om.omplayer.pojo.Horizon;
import com.om.omplayer.pojo.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Shahbaz} on 01-06-2018
 */
public class NewSongsFragment extends Fragment {

    public static final String KEY_DATA = "key:data-bean";
    private final int SHOW_THRESHOLD = 4;

    private HorizontalTrackAdapter mAdapter;
    private List<Horizon> mHorizons;
    private DataBean mBean;

    public static NewSongsFragment newInstance(DataBean bean) {
        NewSongsFragment fragment = new NewSongsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DATA, bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setData(List<Track> tracks) {
        mHorizons.clear();
        mBean.getTrack().clear();
        mBean.getTrack().addAll(tracks);
        loadTracks();
        mAdapter.notifyDataSetChanged();
    }

    private void loadTracks() {
        if (mHorizons == null)
            mHorizons = new ArrayList<>();
        if (mBean == null)
            return;
        List<Track> tracks = mBean.getTrack();
        Horizon horizon = new Horizon();
        int counter = 0;
        while (counter < mBean.getTrack().size()) {
            Track track = tracks.get(counter++);
            if (counter % (SHOW_THRESHOLD + 1) == 0) {
                mHorizons.add(horizon);
                horizon = new Horizon();
            }
            horizon.addTracks(track);
        }
        if (horizon.getTracks().size() > 0)
            mHorizons.add(horizon);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey(KEY_DATA))
            mBean = bundle.getParcelable(KEY_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_songs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadTracks();
        mAdapter = new HorizontalTrackAdapter(mHorizons);
        RecyclerView recyclerView = view.findViewById(R.id.rv_new_songs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mAdapter);
    }
}
