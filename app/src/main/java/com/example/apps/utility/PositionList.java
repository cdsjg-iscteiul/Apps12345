package com.example.apps.utility;

import android.os.Parcel;
import android.os.Parcelable;

public class PositionList implements Parcelable {
    int realPosition;
    String name;

    public PositionList(int realPosition, String name) {
        this.realPosition = realPosition;
        this.name = name;
    }

    protected PositionList(Parcel in) {
        realPosition = in.readInt();
        name = in.readString();
    }

    public static final Creator<PositionList> CREATOR = new Creator<PositionList>() {
        @Override
        public PositionList createFromParcel(Parcel in) {
            return new PositionList(in);
        }

        @Override
        public PositionList[] newArray(int size) {
            return new PositionList[size];
        }
    };

    public int getRealPosition() {
        return realPosition;
    }

    public void setRealPosition(int realPosition) {
        this.realPosition = realPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(realPosition);
        dest.writeString(name);
    }
}