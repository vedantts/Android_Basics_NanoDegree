package com.example.android.indiatour;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;



public class EventFragment extends Fragment {
    public EventFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Detail> detail = new ArrayList<>();
        detail.add(new Detail(R.string.event_one, R.string.event_review_one));
        detail.add(new Detail(R.string.event_two, R.string.event_review_two));
        detail.add(new Detail(R.string.event_three, R.string.event_review_three));
        detail.add(new Detail(R.string.event_four, R.string.event_review_four));
        detail.add(new Detail(R.string.event_five, R.string.event_review_five));
        detail.add(new Detail(R.string.event_six, R.string.event_review_six));
        detail.add(new Detail(R.string.event_seven, R.string.event_review_seven));
        detail.add(new Detail(R.string.event_eight, R.string.event_review_eight));

        Adapter adapter = new Adapter(getActivity(), detail, R.color.category_events);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);

        return rootView;
    }
}