package com.om.omplayer.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ${Shahbaz} on 30-05-2018
 */
public class Track implements Parcelable {

    @SerializedName("id")
    private String id;
    @SerializedName("artist_name")
    private String artistName;
    @SerializedName("track_name")
    private String trackName;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("img_color_hexcode")
    private String imgColorHexcode;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImgColorHexcode() {
        return imgColorHexcode;
    }

    public void setImgColorHexcode(String imgColorHexcode) {
        this.imgColorHexcode = imgColorHexcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Track(Parcel in) {
        id = in.readString();
        artistName = in.readString();
        trackName = in.readString();
        releaseDate = in.readString();
        imgColorHexcode = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(artistName);
        dest.writeString(trackName);
        dest.writeString(releaseDate);
        dest.writeString(imgColorHexcode);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Track> CREATOR = new Parcelable.Creator<Track>() {
        @Override
        public Track createFromParcel(Parcel in) {
            return new Track(in);
        }

        @Override
        public Track[] newArray(int size) {
            return new Track[size];
        }
    };
}