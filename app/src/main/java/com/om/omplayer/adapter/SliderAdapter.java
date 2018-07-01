package com.om.omplayer.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.om.omplayer.frag.BannerFragment;
import com.om.omplayer.pojo.Track;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class SliderAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> list;

    public SliderAdapter(FragmentManager fm) {
        super(fm);
        list = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void removeTrack(Fragment fragment) {
        list.remove(fragment);
    }

    public void addTrack(Fragment fragment) {
        list.add(fragment);
    }

    public void addTrack(Track track) {
        Fragment fragment = BannerFragment.newInstance(track);
        addTrack(fragment);
    }

    public void clearTrack() {
        list.clear();
    }

}
