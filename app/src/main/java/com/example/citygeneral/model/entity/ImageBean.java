package com.example.citygeneral.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhangzl
 * 描述:
 * 日期: 2017/3/6 0006.
 */

public class ImageBean implements Parcelable {
    private String beforeImagePath;
    private String afterImagePath;
    private boolean isSelected;

    public String getBeforeImagePath() {
        return beforeImagePath;
    }

    public void setBeforeImagePath(String beforeImagePath) {
        this.beforeImagePath = beforeImagePath;
    }

    public String getAfterImagePath() {
        return afterImagePath;
    }

    public void setAfterImagePath(String afterImagePath) {
        this.afterImagePath = afterImagePath;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.beforeImagePath);
        dest.writeString(this.afterImagePath);
        dest.writeByte(this.isSelected ? (byte) 1 : (byte) 0);
    }

    public ImageBean() {
    }

    protected ImageBean(Parcel in) {
        this.beforeImagePath = in.readString();
        this.afterImagePath = in.readString();
        this.isSelected = in.readByte() != 0;
    }

    public static final Creator<ImageBean> CREATOR = new Creator<ImageBean>() {
        @Override
        public ImageBean createFromParcel(Parcel source) {
            return new ImageBean(source);
        }

        @Override
        public ImageBean[] newArray(int size) {
            return new ImageBean[size];
        }
    };
}
