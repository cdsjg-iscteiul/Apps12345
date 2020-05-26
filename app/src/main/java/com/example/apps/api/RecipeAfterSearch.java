package com.example.apps.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class RecipeAfterSearch implements Parcelable {
    int id;
    String name;
    String url;
    int likes;

    public RecipeAfterSearch(int id, String name, String url, int likes) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.likes = likes;
    }

    protected RecipeAfterSearch(Parcel in) {
        id = in.readInt();
        name = in.readString();
        url = in.readString();
        likes = in.readInt();
    }

    public static final Creator<RecipeAfterSearch> CREATOR = new Creator<RecipeAfterSearch>() {
        @Override
        public RecipeAfterSearch createFromParcel(Parcel in) {
            return new RecipeAfterSearch(in);
        }

        @Override
        public RecipeAfterSearch[] newArray(int size) {
            return new RecipeAfterSearch[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(url);
        dest.writeInt(likes);
    }
}
