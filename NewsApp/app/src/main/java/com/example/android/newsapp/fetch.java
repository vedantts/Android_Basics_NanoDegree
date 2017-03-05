package com.example.android.newsapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by win 8.1 on 05-03-2017.
 */

public class fetch {
    public static final String LOG = fetch.class.getSimpleName();

    public static ArrayList<News> News(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);
        String json = null;
        try {
            json = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG, "Error", e);
        }
        ArrayList<News> news = extractNews(json);
        return news;
    }

    private static URL createUrl(String Url) {
        URL url = null;
        try {
            url = new URL(Url);
        } catch (MalformedURLException e) {
            Log.e(LOG, "Error with URL ", e);
        }
        return url;
    }
    /**Make the request*/
    private static String makeHttpRequest(URL url) throws IOException {
        String json = null;

        if (url == null) {
            return null;
        }
        HttpURLConnection url1 = null;
        InputStream inputStream = null;
        try {
            url1 = (HttpURLConnection) url.openConnection();
            url1.setRequestMethod("GET");
            url1.connect();
            if (url1.getResponseCode() == 200) {
                inputStream = url1.getInputStream();
                json = read(inputStream);
            } else {
                Log.e(LOG, "Error response code: " + url1.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG, "Problem retrieving the news JSON results.", e);
        } finally {
            if (url1 != null) {
                url1.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return json;
    }

    private static String read(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader input = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(input);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<News> extractNews(String JSONnews) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(JSONnews)) {
            return null;
        }

        ArrayList<News> news = new ArrayList<>();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss");

        String title = null;
        String description = null;
        String category = null;
        String author = null;
        String link = null;
        Date publishedOn = null;

        try {
            JSONObject Object = new JSONObject(JSONnews);
            JSONObject response = Object.getJSONObject("response");
            JSONArray result = response.optJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jsonObject = result.getJSONObject(i);
                JSONObject fields = jsonObject.getJSONObject("fields");

                title = jsonObject.getString("webTitle").toString();
                description = fields.getString("trailText").toString();
                author = jsonObject.getString("type").toString();
                category = jsonObject.getString("sectionName").toString();
                link = jsonObject.getString("webUrl").toString();
                try {
                    publishedOn = formatter.parse(jsonObject.getString("webPublicationDate").toString());
                } catch (java.text.ParseException e) {
                    publishedOn = null;
                }
                news.add(new News(title, description, category, author, link, publishedOn));
            }

            return news;
        } catch (JSONException e) {
            Log.e(LOG, "Problem parsing the earthquake JSON results", e);
            return null;
        }
    }
}
