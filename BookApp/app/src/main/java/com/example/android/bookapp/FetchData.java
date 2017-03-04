package com.example.android.bookapp;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by win 8.1 on 05-03-2017.
 */

public class FetchData extends AsyncTask<String, Void, ArrayList<Result>> {

    private final String LOG = FetchData.class.getSimpleName();

    public ArrayList<Result> getFetchDataFromJson(String fetch) throws JSONException {


        JSONObject fetchData = new JSONObject(fetch);
        JSONArray item = fetchData.getJSONArray("items");
        ArrayList<Result> result = new ArrayList<Result>();

        for (int i = 0; i < item.length(); i++) {
/**JSON Declaration*/
            JSONObject book = item.getJSONObject(i);
            JSONObject vol = book.getJSONObject("volumeInfo");
            String title = vol.getString("title");
            String author = "N/A";
            String publisher = vol.optString("publisher");
            String page=vol.optString("pageCount");
            String rating=vol.optString("averageRating");
            if (publisher.equals("")) {
                publisher = "N/A";
            }
            JSONArray authors = vol.optJSONArray("authors");
            if (authors != null) {
                authors = vol.getJSONArray("authors");
                author = authors.getString(0);
            }
            result.add(new Result(title, author, publisher,page,rating));
        }
        return result;
    }

    @Override
    protected ArrayList<Result> doInBackground(String... params) {

        String fetch = null;
        if (params.length == 0) {
            return null;
        }

        HttpURLConnection url = null;
        BufferedReader reader = null;
        try {
            // Construct the URL
            final String api =
                    "https://www.googleapis.com/books/v1/volumes?";
            final String query = "q";
            final String key = "key";

            Uri builtUri = Uri.parse(api).buildUpon()
                    .appendQueryParameter(query, params[0])
                    .appendQueryParameter(key, "AIzaSyCavsdJ5NkHiiLDDyuPwYA-LMSaB3usAS4")
                    .build();

            URL url1 = new URL(builtUri.toString());

            // open the connection
            url = (HttpURLConnection) url1.openConnection();
            url.setRequestMethod("GET");
            url.connect();

            // Read the input stream
            InputStream inputStream = url.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to display

                return null;

            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream Empty
                return null;
            }
            fetch = buffer.toString();
        } catch (IOException e) {
            Log.e(LOG, "Error ", e);
            return null;
        } finally {
            if (url!= null) {
                url.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG, "Error closing stream", e);
                }
            }
        }
        try {
            return getFetchDataFromJson(fetch);
        } catch (JSONException e) {
            Log.e(LOG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;

    }
}
