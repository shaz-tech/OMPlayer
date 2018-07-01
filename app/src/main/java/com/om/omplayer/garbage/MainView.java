package com.om.omplayer.garbage;

import com.om.omplayer.pojo.Track;

import java.util.List;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public interface MainView {
    void loadBanner(List<Track> tracks);
    void loadList(List<Track> tracks);
    void loadBannerGrid(List<Track> tracks);
    void showLoading();
    void hideLoading();
    void showInfoMessage(String message);
    void showErrorMessage(String message);
}
