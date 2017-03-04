package com.example.android.bookapp;

/**
 * Created by win 8.1 on 05-03-2017.
 */

public class Result {

    private String mAuthour;
    private String mTitle;
    private String mPublisher;
    private String mPagecount;
    private String mRating;

    public Result(String author, String title, String publisher,String desc,String Rating) {
        mAuthour=author;
        mTitle=title;
        mPublisher=publisher;
        mPagecount=desc;
        mRating=Rating;
    }


    /**Getter methods*/
    public String getmAuthour() {
        return mAuthour;
    }
    public String getmPublisher() {
        return mPublisher;
    }
    public String getmTitle() {
        return mTitle;
    }

    public String getmRating() {
        return mRating;
    }

    public String getPagecount() {
        return mPagecount;
    }
}