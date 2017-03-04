package com.example.android.bookapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button search = (Button) findViewById(R.id.search);
        final int internet = 1;
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = cm.getActiveNetworkInfo();


        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, new String[]{Manifest.permission.INTERNET},
                    internet);
        } else {
            if (networkInfo != null && networkInfo.isConnected()) {


                search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ConnectivityManager cm =
                                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

                        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                        if (networkInfo != null && networkInfo.isConnected()) {
                            EditText search1 = (EditText) findViewById(R.id.search_text);
                            String searchString = search1.getText().toString();
                            FetchData fetch = new FetchData();
                            AsyncTask<String, Void, ArrayList<Result>> getData = fetch.execute(searchString);
                            ListView listView = null;
                            TextView emptyTextView = null;
                            try {
                                if (getData.get() == null) {
                                    Toast.makeText(MainActivity.this, "Your search doesn't have any result, please" +
                                            "enter another search", Toast.LENGTH_SHORT).show();
                                } else {
                                    listView = (ListView) findViewById(R.id.list);
                                    emptyTextView = (TextView) findViewById(R.id.empty_text_view);
                                    listView.setEmptyView(findViewById(R.id.empty_text_view));
                                    String[] authors = new String[getData.get().size()];
                                    for (int i = 0; i < getData.get().size(); i++) {
                                        authors[i] = "Name: " + String.valueOf(getData.get().get(i).getmTitle()) + "\n" +
                                                "Author: " + String.valueOf(getData.get().get(i).getmAuthour()) + "\n" +
                                                "Pages: " + String.valueOf(getData.get().get(i).getPagecount()) + "\n" +
                                                "Publisher: " + String.valueOf(getData.get().get(i).getmPublisher() + "\n" +
                                                "Rating: " + String.valueOf(getData.get().get(i).getmRating()));
                                    }
                                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                                            MainActivity.this,
                                            R.layout.list_item,
                                            R.id.text_1,
                                            authors);

                                    if (arrayAdapter != null && !arrayAdapter.isEmpty())
                                        listView.setAdapter(arrayAdapter);
                                    else
                                        emptyTextView.setText("BOOKS NOT FOUND!!");


                                }


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, " Please, check internet connection.", Toast.LENGTH_SHORT).show();

                        }
                    }



                });
            } else {
                TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_text_view);
                // Update empty state with no connection error message
                mEmptyStateTextView.setText(R.string.no_internet_connection);
            }

        }
    }
}
