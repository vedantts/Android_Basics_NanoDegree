package com.example.android.indiatour;

/**
 * Created by win 8.1 on 03-03-2017.
 */

public class Detail {
    private int mText;
    private int mReview;
    private int mImageResourceId=NO_IMAGE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED=-1;

    public Detail(int name, int review) {
        mText = name;
        mReview = review;
    }

    public Detail(int name, int review, int imageResourceId) {
        mText = name;
        mReview = review;
        mImageResourceId = imageResourceId;
    }

    public int getText() {
        return mText;
    }
    public int getReview() {
        return mReview;
    }
    public int getimageid() {
        return mImageResourceId;
    }
    public boolean hasimage(){
        return mImageResourceId !=NO_IMAGE_PROVIDED;
    }
}
