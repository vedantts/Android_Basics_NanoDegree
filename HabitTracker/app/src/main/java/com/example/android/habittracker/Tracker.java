package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by win 8.1 on 06-03-2017.
 */

public class Tracker {
    public static final class Habit implements BaseColumns {

        public static final String ID = "id";
        public static final String TABLE_NAME = "Habit";
        public static final String COLUMN_NAME = "Name";
        public static final String COLUMN_HABIT = "habit";
        public static final String COLUMN_FREQUENCY = "Frequency";
    }
}

