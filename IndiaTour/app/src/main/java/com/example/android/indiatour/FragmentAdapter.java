package com.example.android.indiatour;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentAdapter.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentAdapter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentAdapter extends FragmentPagerAdapter {
    /** Context of the app */
    private Context mContext;

    /**
     * Create a new {@link FragmentAdapter} object.
     *
     * @param context is the context of the app
     * @param fm is the fragment manager that will keep each fragment's state in the adapter
     *           across swipes.
     */
    public FragmentAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PlacesFragment();
        } else if (position == 1) {
            return new EventFragment();
        } else if (position == 2) {
            return new RestaurantFragment();
        } else {
            return new ReviewFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_places);
        } else if (position == 1) {
            return mContext.getString(R.string.category_event);
        } else if (position == 2) {
            return "Food";
        } else {
            return mContext.getString(R.string.category_reviews);
        }
    }
}
