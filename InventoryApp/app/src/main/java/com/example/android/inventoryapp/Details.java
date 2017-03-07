package com.example.android.inventoryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final DbHelper db = new DbHelper(this);
        Intent getList = getIntent();
        String productName = getList.getExtras().getString("listItem");
        int pos = productName.indexOf("\nQuantity");
        final String subProductName = productName.substring(0, pos);

        final Cursor cur = db.getData(subProductName);

        if (cur.moveToFirst()) {
            // Set Product Name
            TextView tName = (TextView) findViewById(R.id.text_name);
            tName.setText(subProductName);
            // Set Price
            int price = cur.getInt(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_PRICE));
            TextView tPrice = (TextView) findViewById(R.id.text_price);
            tPrice.setText("$" + price);
            // Set Quantity
            int quantity = cur.getInt(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_QUANTITY));
            TextView tQuantity = (TextView) findViewById(R.id.text_quantity);
            tQuantity.setText("" + quantity);
        }
        // Decrease quantity by 1
        Button Track = (Button) findViewById(R.id.track);
        Track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cur.moveToFirst()) {
                    int quantity = cur.getInt(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_QUANTITY));
                    if (quantity > 0) {
                        db.updateData(subProductName, quantity, -1);
                        quantity = cur.getInt(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_QUANTITY));
                        TextView tQuantity = (TextView) findViewById(R.id.text_quantity);
                        tQuantity.setText("" + quantity);
                        Toast.makeText(Details.this, "Refresh the app", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Details.this, "It's empty! Order Now!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Increase quantity by 1
        Button Receive = (Button) findViewById(R.id.receive);
        Receive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cur.moveToFirst()) {
                    int quantity = cur.getInt(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_QUANTITY));
                    db.updateData(subProductName, quantity, 1);
                    quantity = cur.getInt(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_QUANTITY));
                    TextView tQuantity = (TextView) findViewById(R.id.text_quantity);
                    tQuantity.setText("" + quantity);
                    Toast.makeText(Details.this, "Refresh the app", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Refresh List When you click on refresh the app will go to main activity and show the incremented
        //or decremented value of quantity it will refresh the app as whole
        Button refresh = (Button) findViewById(R.id.refresh_data);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(Details.this, MainActivity.class);
                startActivity(mainIntent);

            }
        });
        // Order Now
        Button order = (Button) findViewById(R.id.order);
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String productName = "";
                if (cur.moveToFirst()) {
                    productName = cur.getString(cur.getColumnIndex(Contract.InventoryEntry.COLUMN_PRODUCT_NAME));
                }
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_TEXT, "Got An Order for " + productName);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        // delete row
        Button delete = (Button) findViewById(R.id.delete_data);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if (db.deleteData(subProductName)) {
                                    Intent returnHome = new Intent(Details.this, MainActivity.class);
                                    startActivity(returnHome);
                                    Toast.makeText(Details.this, "Deleted!", Toast.LENGTH_SHORT).show();
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder ab = new AlertDialog.Builder(Details.this);
                ab.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

    }
}

