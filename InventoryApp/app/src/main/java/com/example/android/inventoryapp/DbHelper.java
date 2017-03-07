package com.example.android.inventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by win 8.1 on 07-03-2017.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    static final String NAME = "inventory.db";

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_HABIT_TABLE = "CREATE TABLE " + Contract.InventoryEntry.TABLE_NAME + " (" +
                Contract.InventoryEntry.COLUMN_ID + " INTEGER PRIMARY KEY," +
                Contract.InventoryEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, " +
                Contract.InventoryEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, " +
                Contract.InventoryEntry.COLUMN_PRICE + " INTEGER NOT NULL," +
                Contract.InventoryEntry.COLUMN_IMAGE + " BLOB NOT NULL)";
        sqLiteDatabase.execSQL(SQL_CREATE_HABIT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Contract.InventoryEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    // Insert data in the table
    public boolean insertData(String productName, int quantity, int price,byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.InventoryEntry.COLUMN_PRODUCT_NAME, productName);
        contentValues.put(Contract.InventoryEntry.COLUMN_QUANTITY, quantity);
        contentValues.put(Contract.InventoryEntry.COLUMN_PRICE, price);
        contentValues.put(Contract.InventoryEntry.COLUMN_IMAGE, image);
        db.insert(Contract.InventoryEntry.TABLE_NAME, null, contentValues);
        return true;
    }

    // Get data from the table
    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * from " + Contract.InventoryEntry.TABLE_NAME +
                " WHERE name=\"" + name + "\"", null);
        return res;
    }
    // Delete all entries
    public int deleteEntries() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Contract.InventoryEntry.TABLE_NAME, null, null);
    }

    // Delete one table entry
    public boolean deleteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Contract.InventoryEntry.TABLE_NAME, "name=?", new String[]{name}) > 0;
    }

    // Update data in the table
    public void updateData(String name, int quantity, int change) {
        SQLiteDatabase db = this.getWritableDatabase();
        String strSQL = "UPDATE " + Contract.InventoryEntry.TABLE_NAME + " SET quantity = "
                + (quantity + change) + " WHERE name = \"" + name + "\"";
        db.execSQL(strSQL);
    }

    public ArrayList<String> getAllData() {
        ArrayList<String> productList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor getList = db.rawQuery("SELECT * FROM " + Contract.InventoryEntry.TABLE_NAME, null);
        getList.moveToFirst();
        while (getList.isAfterLast() == false) {
            String productName = getList.getString(getList.getColumnIndex(Contract.InventoryEntry.COLUMN_PRODUCT_NAME));
            int quantity = getList.getInt(getList.getColumnIndex(Contract.InventoryEntry.COLUMN_QUANTITY));
            int price = getList.getInt(getList.getColumnIndex(Contract.InventoryEntry.COLUMN_PRICE));
            productList.add(productName + "\n" + "Quantity: " + quantity + "\n" + "Price: $" + price);
            getList.moveToNext();
        }
        return productList;
    }
}

