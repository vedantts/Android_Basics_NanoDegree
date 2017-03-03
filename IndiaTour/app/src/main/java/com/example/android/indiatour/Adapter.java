package com.example.android.indiatour;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends ArrayAdapter<Detail> {

    private int mColorResourceId;

    /**
     * Create a new {@link Adapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param content is the list of {@link Detail}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    public Adapter(Context context, ArrayList<Detail> content, int colorResourceId) {
        super(context, 0, content);
        mColorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Detail currentItem = getItem(position);

        TextView text = (TextView) listItemView.findViewById(R.id.text_view);

        text.setText(currentItem.getText());

        TextView review = (TextView) listItemView.findViewById(R.id.review);

        review.setText(currentItem.getReview());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        if (currentItem.hasimage()) {
            // If an image is available, display the provided image based on the resource ID
            imageView.setImageResource(currentItem.getimageid());
            // Make sure the view is visible
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageView.setVisibility(View.GONE);
        }

        // Set the theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listItemView;
    }
}
