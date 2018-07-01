package com.om.omplayer.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.om.omplayer.R;
import com.om.omplayer.adapter.TrackAdapter;
import com.om.omplayer.pojo.DataBean;
import com.om.omplayer.pojo.Track;
import com.om.omplayer.utils.Util;

import java.util.List;

/**
 * Created by ${Shahbaz} on 01-06-2018
 */
public class TrendingSongsFragment extends Fragment {

    public static final String KEY_DATA = "key:data-bean";

    private TrackAdapter mAdapter;
    private DataBean mBean;

    public static TrendingSongsFragment newInstance(DataBean bean) {
        TrendingSongsFragment fragment = new TrendingSongsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_DATA, bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setData(List<Track> tracks) {
        mBean.getTrack().clear();
        mBean.getTrack().addAll(tracks);
        mAdapter.notifyDataSetChanged();
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
        return inflater.inflate(R.layout.fragment_trending_songs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdapter = new TrackAdapter(TrackAdapter.TYPE_TRENDING_SONGS, mBean.getTrack());
        RecyclerView recyclerView = view.findViewById(R.id.rv_trending);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(Util.getColumnCount(getActivity()), StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }
}
