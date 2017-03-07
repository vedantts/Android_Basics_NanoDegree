package com.example.android.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by win 8.1 on 07-03-2017.
 */

public class Adapt extends ArrayAdapter<String> {
    public Adapt(Context context, ArrayList<String> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final String currentItem = getItem(position);

        TextView setItem = (TextView) listItemView.findViewById(R.id.text_1);
        setItem.setText(currentItem);
        Button btn = (Button) listItemView.findViewById(R.id.track_item);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DbHelper db = new DbHelper(getContext());
                int pos = currentItem.indexOf("\nQuantity");
                final String subProductName = currentItem.substring(0, pos);
                final Cursor cur = db.getData(subProductName);
                if (cur.moveToFirst()) {
                    int quantity = cur.getInt(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_QUANTITY));
                    if (quantity > 0) {
                        db.updateData(subProductName, quantity, -1);
                        Toast.makeText(getContext(), "Refresh!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "It's empty! Order Now!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return listItemView;
    }
}
