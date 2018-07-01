package com.om.omplayer.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.om.omplayer.R;
import com.om.omplayer.frag.TrendingSongsFragment;
import com.om.omplayer.frag.NewSongsFragment;
import com.om.omplayer.utils.CircularViewPagerHandler;
import com.om.omplayer.adapter.SliderAdapter;
import com.om.omplayer.pojo.DataBean;
import com.om.omplayer.pojo.Track;

import java.util.Timer;
import java.util.TimerTask;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class MainActivity extends AppCompatActivity {

    private final int BANNER_DELAY = 3 * 1000;

    private ViewPager mViewPager;
    private SliderAdapter mSliderAdapter;

    private Timer mSliderTimer;

    private NewSongsFragment mNewSongsFragment;
    private TrendingSongsFragment mTrendingSongsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSliderAdapter = new SliderAdapter(getSupportFragmentManager());
        mSliderTimer = new Timer();

        mViewPager = findViewById(R.id.viewPager);
        TabLayout pagerIndicator = findViewById(R.id.indicator);
        mViewPager.setAdapter(mSliderAdapter);
        mViewPager.addOnPageChangeListener(new CircularViewPagerHandler(mViewPager));
        pagerIndicator.setupWithViewPager(mViewPager, true);

        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getBanners().observe(this, new Observer<DataBean>() {
            @Override
            public void onChanged(@Nullable DataBean bean) {
                assert bean != null;
                mSliderAdapter.clearTrack();
                for (Track track : bean.getTrack()) {
                    mSliderAdapter.addTrack(track);
                }
                reloadBanner();
            }
        });
        viewModel.getNewSongs().observe(this, new Observer<DataBean>() {
            @Override
            public void onChanged(@Nullable DataBean bean) {
                assert bean != null;
                if (mNewSongsFragment != null)
                    mNewSongsFragment.setData(bean.getTrack());
            }
        });
        viewModel.getTrendingSongs().observe(this, new Observer<DataBean>() {
            @Override
            public void onChanged(@Nullable DataBean bean) {
                assert bean != null;
                if (mTrendingSongsFragment != null)
                    mTrendingSongsFragment.setData(bean.getTrack());
            }
        });
        viewModel.loadAll(this);

        bindFrags();
    }

    private void bindFrags() {
        mNewSongsFragment = NewSongsFragment.newInstance(new DataBean());
        mTrendingSongsFragment = TrendingSongsFragment.newInstance(new DataBean());
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.disallowAddToBackStack();
        transaction.replace(R.id.fragment_list, mNewSongsFragment, "new_songs");
        transaction.replace(R.id.fragment_grid, mTrendingSongsFragment, "trending");
        transaction.commit();
    }

    @Override
    protected void onPause() {
        mSliderTimer.cancel();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startAutoSlider(true);
    }

    public class SliderTimer extends TimerTask {
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int currentItemCount = mViewPager.getCurrentItem();
                    if (currentItemCount < mSliderAdapter.getCount() - 1) {
                        mViewPager.setCurrentItem(currentItemCount + 1);
                    } else {
                        mViewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void reloadBanner() {
        mSliderAdapter.notifyDataSetChanged();
        startAutoSlider(false);
    }

    private void startAutoSlider(boolean restart) {
        long period = mSliderAdapter.getCount() * 1000;
        if (period > 1000) {
            if (restart) mSliderTimer = new Timer();
            mSliderTimer.scheduleAtFixedRate(new SliderTimer(), BANNER_DELAY, period);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    /*@Override
    public void loadList(List<Track> tracks) {

    }

    @Override
    public void loadBannerGrid(List<Track> tracks) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showInfoMessage(String message) {

    }

    @Override
    public void showErrorMessage(String message) {

    }*/
}
