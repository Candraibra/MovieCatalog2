package com.candraibra.moviecatalog2;

import android.os.Parcel;
import android.os.Parcelable;

public class Tvshow implements Parcelable {
    int getPoster() {
        return poster;
    }

    void setPoster(int poster) {
        this.poster = poster;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    private int poster;
    private String title;
    private String description;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.description);
    }

    Tvshow() {
    }

    private Tvshow(Parcel in) {
        this.poster = in.readInt();
        this.title = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Tvshow> CREATOR = new Parcelable.Creator<Tvshow>() {
        @Override
        public Tvshow createFromParcel(Parcel source) {
            return new Tvshow(source);
        }

        @Override
        public Tvshow[] newArray(int size) {
            return new Tvshow[size];
        }
    };
}
