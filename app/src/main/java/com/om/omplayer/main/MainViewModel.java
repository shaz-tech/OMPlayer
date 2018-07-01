package com.om.omplayer.main;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.om.omplayer.pojo.DataBean;
import com.om.omplayer.utils.Util;

import java.lang.ref.WeakReference;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class MainViewModel extends ViewModel {

    private MutableLiveData<DataBean> mBannerBean;
    private MutableLiveData<DataBean> mNewSongsBean;
    private MutableLiveData<DataBean> mTrendingSongsBean;

    private final int REQ_BANNER = 1;
    private final int REQ_NEW_SONGS = 2;
    private final int REQ_TRENDING_SONGS = 3;

    public LiveData<DataBean> getBanners() {
        initBanner();
        return mBannerBean;
    }

    public LiveData<DataBean> getNewSongs() {
        initNewSongs();
        return mNewSongsBean;
    }

    public LiveData<DataBean> getTrendingSongs() {
        initTrendingSongs();
        return mTrendingSongsBean;
    }

    private void initBanner() {
        if (mBannerBean == null) {
            mBannerBean = new MutableLiveData<>();
            mBannerBean.setValue(new DataBean());
        }
    }

    private void initNewSongs() {
        if (mNewSongsBean == null) {
            mNewSongsBean = new MutableLiveData<>();
            mNewSongsBean.setValue(new DataBean());
        }
    }

    private void initTrendingSongs() {
        if (mTrendingSongsBean == null) {
            mTrendingSongsBean = new MutableLiveData<>();
            mTrendingSongsBean.setValue(new DataBean());
        }
    }

    public void loadAll(@NonNull Activity activity) {
        loadBanners(activity);
        loadNewSongs(activity);
        loadTrendingSongs(activity);
    }

    public void loadBanners(@NonNull Activity activity) {
        initBanner();
        RequestLoader loader = new RequestLoader(activity, REQ_BANNER);
        loader.execute();
    }

    public void loadNewSongs(@NonNull Activity activity) {
        initNewSongs();
        RequestLoader loader = new RequestLoader(activity, REQ_NEW_SONGS);
        loader.execute();
    }

    public void loadTrendingSongs(@NonNull Activity activity) {
        initTrendingSongs();
        RequestLoader loader = new RequestLoader(activity, REQ_TRENDING_SONGS);
        loader.execute();
    }

    private class RequestLoader extends AsyncTask<Void, Void, DataBean> {

        private WeakReference<Activity> weakActivity;
        private int requestType;

        RequestLoader(Activity activity, int requestType) {
            this.weakActivity = new WeakReference<>(activity);
            this.requestType = requestType;
        }

        @Override
        protected DataBean doInBackground(Void[] objects) {
            switch (requestType) {
                case REQ_BANNER:
                    return Util.loadBannerJSONFromAsset();
                case REQ_NEW_SONGS:
                    return Util.loadNewSongsJSONFromAsset();
                case REQ_TRENDING_SONGS:
                    return Util.loadTrendingSongsJSONFromAsset();
                default:
                    return null;
            }
        }

        @Override
        protected void onPostExecute(DataBean data) {
            super.onPostExecute(data);
            if (weakActivity.get() != null && !weakActivity.get().isFinishing()) {
                switch (requestType) {
                    case REQ_BANNER:
                        mBannerBean.postValue(data);
                        break;
                    case REQ_NEW_SONGS:
                        mNewSongsBean.postValue(data);
                        break;
                    case REQ_TRENDING_SONGS:
                        mTrendingSongsBean.postValue(data);
                        break;
                }
            }
        }
    }
}
