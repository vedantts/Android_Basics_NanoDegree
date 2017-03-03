package com.example.android.indiatour;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;



public class RestaurantFragment extends Fragment {
    public RestaurantFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Detail> detail = new ArrayList<>();
        detail.add(new Detail(R.string.res_one, R.string.res_review_one,
                R.drawable.restaurant));
        detail.add(new Detail(R.string.res_two, R.string.res_review_two,
                R.drawable.restaurant));
        detail.add(new Detail(R.string.res_three, R.string.res_review_three,
                R.drawable.restaurant));
        detail.add(new Detail(R.string.res_four, R.string.res_review_four,
                R.drawable.restaurant));
        detail.add(new Detail(R.string.res_five, R.string.res_review_five,
                R.drawable.restaurant));
        detail.add(new Detail(R.string.res_six, R.string.res_review_six,
                R.drawable.restaurant));
        detail.add(new Detail(R.string.res_seven, R.string.res_review_seven,
                R.drawable.restaurant));
        detail.add(new Detail(R.string.res_eight, R.string.res_review_eight,
                R.drawable.restaurant));

        Adapter adapter = new Adapter(getActivity(), detail, R.color.category_restaurants);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;
    }
}