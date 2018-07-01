package com.om.omplayer.garbage;

import com.om.omplayer.pojo.Track;

import java.util.List;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public interface MainInteractor {
    interface OnCompleteListener{
        void onSuccess(List<Track> tracks);
        void onFailed(String reason);
    }
    void loadBannerData(OnCompleteListener listener);
    void loadListData(OnCompleteListener listener);
    void loadGridData(OnCompleteListener listener);
}
