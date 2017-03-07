package com.example.android.inventoryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final DbHelper dataB = new DbHelper(this);


        final ListView listView = (ListView) findViewById(R.id.list_1);
        listView.setEmptyView(findViewById(R.id.empty_text));
        ArrayList<String> list = dataB.getAllData();
        Adapt arrayAdapter = new Adapt(MainActivity.this, list);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent infoIntent = new Intent(MainActivity.this, Details.class);
                String itemSelected = ((TextView) view.findViewById(R.id.text_1)).getText().toString();
                infoIntent.putExtra("listItem", itemSelected);
                startActivity(infoIntent);
            }
        });

        Button add = (Button) findViewById(R.id.btn_add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this, AddProduct.class);
                startActivity(addIntent);
            }
        });

        // Refresh List
        Button refresh = (Button) findViewById(R.id.btn_refresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ListView listView1 = (ListView) findViewById(R.id.list_1);
                listView1.setEmptyView(findViewById(R.id.empty_text));
                ArrayList<String> list = dataB.getAllData();
                Adapt arrayAdapter = new Adapt(MainActivity.this, list);
                listView1.setAdapter(arrayAdapter);
                listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent infoIntent = new Intent(MainActivity.this, Details.class);
                        String itemSelected = ((TextView) view.findViewById(R.id.text_1)).getText().toString();
                        infoIntent.putExtra("listItem", itemSelected);
                        startActivity(infoIntent);
                    }
                });
            }
        });

        // Delete Everything
        Button delete = (Button) findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataB.deleteEntries();
                Toast.makeText(MainActivity.this, "Data Deleted Please Refresh the List", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
