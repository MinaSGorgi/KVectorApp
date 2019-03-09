package com.example.KVectorApp.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String strUrl = "http://kvectorfoundation.com/k19/magazine";

    public Magazine[] getMagazines() throws IOException, JSONException {
        Magazine[] magazines;

        URL url = new URL(strUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String jsonStr = scanner.hasNext() ? scanner.next() : "";

            JSONArray json = new JSONArray(jsonStr);
            magazines = new Magazine[json.length()];
            for(int i=0; i<json.length(); ++i) {
                JSONObject magazine = json.getJSONObject(i);
                magazines[i] = new Magazine(magazine.getString("title"), magazine.getString("url"));
            }


        } finally{
            urlConnection.disconnect();
        }

        return magazines;
    }

    public String getMagazine(URL url) throws IOException, JSONException {
        String magazine;

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
            String jsonStr = scanner.hasNext() ? scanner.next() : "";
            JSONObject json = new JSONObject(jsonStr);
            String html = json.getJSONObject("data").getString("body");
            magazine = android.text.Html.fromHtml(html).toString();

        } finally{
            urlConnection.disconnect();
        }

        return magazine;
    }

}
