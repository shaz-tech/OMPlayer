package com.om.omplayer.garbage;

import com.om.omplayer.pojo.Track;

import java.util.List;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view, MainInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void loadBanner() {
        interactor.loadBannerData(new MainInteractor.OnCompleteListener() {
            @Override
            public void onSuccess(List<Track> tracks) {
                view.loadBanner(tracks);
            }

            @Override
            public void onFailed(String reason) {
                view.showErrorMessage(reason);
            }
        });
    }

    @Override
    public void loadList() {

    }

    @Override
    public void loadGrid() {

    }
}
