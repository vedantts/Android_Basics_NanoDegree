package com.example.android.newsapp;

import java.util.Date;

/**
 * Created by win 8.1 on 05-03-2017.
 */

public class News {
    private String mtitle;
    private String mdescription;
    private String mcategory;
    private Date mpublishedDate;
    private String mAuthor;
    private String mlink;
    public News(String title, String description, String category, String author, String lonk, Date publishedDate) {
        mtitle = title;
        mdescription = description;
        mcategory = category;
        mAuthor=author;
        mlink=lonk;
        mpublishedDate = publishedDate;
    }

    // Getters
    public String getTitle() {
        return mtitle;
    }

    public String getDescription() {
        return mdescription;
    }

    public String getCategory() {
        return mcategory;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getMlink() {
        return mlink;
    }

    public Date getPublishedDate() {
        return mpublishedDate;
    }



}
