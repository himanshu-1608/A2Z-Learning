package com.himanshu.a2zlearning;

public class UploadImage {
    private String mName;
    private String mImageUrl;
    public UploadImage() {
        //Empty Constructor Needed
    }
    public UploadImage(String name,String imageUrl) {
        mName = name;
        mImageUrl = imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
