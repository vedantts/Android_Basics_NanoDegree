package com.example.android.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TrackerDbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    static final String NAME = "Tracker.db";

    public TrackerDbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + Tracker.Habit.TABLE_NAME + " (" +
                Tracker.Habit.ID + " INTEGER PRIMARY KEY," +
                Tracker.Habit.COLUMN_NAME+ " TEXT UNIQUE NOT NULL, " +
                Tracker.Habit.COLUMN_HABIT + " TEXT NOT NULL, "  +
                Tracker.Habit.COLUMN_FREQUENCY +"INTEGER NOT NULL,"+
                " );";
        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tracker.Habit.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    // Insert data in the table
    public boolean insertData(int id, String name, String location, String habit, int frquency) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tracker.Habit.ID, id);
        contentValues.put(Tracker.Habit.COLUMN_NAME, name);
        contentValues.put(Tracker.Habit.COLUMN_HABIT, habit);
        contentValues.put(Tracker.Habit.COLUMN_FREQUENCY,frquency);
        db.insert(Tracker.Habit.TABLE_NAME, null, contentValues);
        db.close();
        return true;
    }
    // Get data from the table
    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * from " + Tracker.Habit.TABLE_NAME +
                " WHERE name=" + name, null);
        db.close();
        return result;
    }
    // Delete all table entries
    public int deleteAllEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Tracker.Habit.TABLE_NAME, null, null);
    }
    // Delete the database
    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(Tracker.Habit.TABLE_NAME);
    }
    // Update data in the table
    public boolean updateData(int id, String name, String location, String habit, int frequnecy) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Tracker.Habit.ID, id);
        contentValues.put(Tracker.Habit.COLUMN_NAME, name);
        contentValues.put(Tracker.Habit.COLUMN_HABIT, habit);
        contentValues.put(Tracker.Habit.COLUMN_FREQUENCY, frequnecy);
        db.update(Tracker.Habit.TABLE_NAME, contentValues, " id = ? ",
                new String[]{Integer.toString(id)});
        db.close();
        return true;
    }



}
