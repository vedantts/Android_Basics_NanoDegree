package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by win 8.1 on 05-03-2017.
 */

public class Adapt extends ArrayAdapter<News> {

    private final static String DATE_FORMAT = "MM/dd/yyyy ";
    public Adapt(Context context, ArrayList<News> news) {
        super(context, 0, news);
    }
    static class ViewHolder {
        TextView tvTitle;
        TextView tvDescription;
        TextView tvCategory;
        TextView tvAuthor;
        TextView tvPublishedDate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        News news = getItem(position);
        ViewHolder view;

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
            view = new ViewHolder();
            view.tvTitle = (TextView) convertView.findViewById(R.id.title);
            view.tvDescription = (TextView) convertView.findViewById(R.id.description);
            view.tvCategory = (TextView) convertView.findViewById(R.id.category);
            view.tvAuthor = (TextView) convertView.findViewById(R.id.author);
            view.tvPublishedDate = (TextView) convertView.findViewById(R.id.published_date);
            // Associate the holder with the view for later lookup
            convertView.setTag(view);
        } else {
            // view already exists, get the holder instance from the view
            view = (ViewHolder) convertView.getTag();
        }


        view.tvTitle.setText(news.getTitle());
        view.tvDescription.setText(news.getDescription());
        view.tvCategory.setText(news.getCategory());
        view.tvAuthor.setText(news.getmAuthor());

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        if (news.getPublishedDate() != null) {
            view.tvPublishedDate.setText(formatter.format(news.getPublishedDate()));
        } else {
            view.tvPublishedDate.setText(R.string.no_date);
        }

        return convertView;
    }



}
