package com.e.weddingprogram.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PhotoCard implements Parcelable {
    public final static String TYPE_PREWEDDING="prewedding";
    public final static String TYPE_ENGAGEMENT="engagement";
    public final static String TYPE_TRADITIONAL="traditional";
    public final static String TYPE_INTRODUCTION="introduction";
    public final static String TYPE_RECEPTION="reception";
    public final static String TYPE_BRIDAL="bridal";
    String title;
    String type;
    int image_id;

    public PhotoCard(String title, String type, int image_id) {
        this.title = title;
        this.type = type;
        this.image_id = image_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeInt(this.image_id);
    }

    protected PhotoCard(Parcel in) {
        this.title = in.readString();
        this.type = in.readString();
        this.image_id = in.readInt();
    }

    public static final Parcelable.Creator<PhotoCard> CREATOR = new Parcelable.Creator<PhotoCard>() {
        @Override
        public PhotoCard createFromParcel(Parcel source) {
            return new PhotoCard(source);
        }

        @Override
        public PhotoCard[] newArray(int size) {
            return new PhotoCard[size];
        }
    };
}
