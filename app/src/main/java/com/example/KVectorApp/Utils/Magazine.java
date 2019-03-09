package com.example.KVectorApp.Utils;

import java.net.MalformedURLException;
import java.net.URL;

public class Magazine {

    private final String title;
    private final URL url;

    public Magazine(String title, String url) throws MalformedURLException {
        this.title = title;
        this.url = new URL(url);
    }


    public String getTitle() {
        return title;
    }

    public URL getUrl() {
        return url;
    }
}
