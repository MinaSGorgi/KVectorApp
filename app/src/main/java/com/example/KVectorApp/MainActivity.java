package com.example.KVectorApp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.KVectorApp.Utils.Magazine;
import com.example.KVectorApp.Utils.MagazinesAdapter;
import com.example.KVectorApp.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private NetworkUtils networkUtils = new NetworkUtils();
    private MagazinesAdapter magazinesAdapter;

    private ListView listView;

    private class MagazinesRequest extends AsyncTask<Void, Void, Magazine[]> {

        @Override
        protected Magazine[] doInBackground(Void... voids) {
            try {
                return networkUtils.getMagazines();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new Magazine[]{};
        }

        @Override
        protected void onPostExecute(Magazine[] magazines) {
            super.onPostExecute(magazines);
            magazinesAdapter.update(magazines);
        }
    }

    private class MagazineRequest extends AsyncTask<URL, Void, String> {
        @Override
        protected String doInBackground(URL... urls) {
            try {
                return networkUtils.getMagazine(urls[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return "ERROR";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent intent = new Intent(MainActivity.this, MagazineActivity.class);
            intent.putExtra("body", s);
            startActivity(intent);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        magazinesAdapter = new MagazinesAdapter(this,
                android.R.layout.simple_list_item_1, new ArrayList<Magazine>());
        listView = findViewById(R.id.lv_magazines);
        listView.setAdapter(magazinesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                new MagazineRequest().execute(magazinesAdapter.getItem(position).getUrl());
            }
        });

        new MagazinesRequest().execute();
    }



}
