package com.om.omplayer.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${Shahbaz} on 01-06-2018
 */
public class Horizon {
    private List<Track> tracks;

    public Horizon() {
        this.tracks = new ArrayList<>();
    }

    public Horizon(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void addTracks(Track track) {
        this.tracks.add(track);
    }
}
