package com.example.dotanewszxc;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.dotanewszxc.forum.ForumActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ParseAdapter adapter;
    private ArrayList<ParseItem> parseItems = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView dataTimeTextView;
    private String dataTimeString;
    private Toolbar toolbar;
//    private Spinner spinner;
    ImageView imageToolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        recyclerView = findViewById(R.id.recyclerView);
        dataTimeTextView = findViewById(R.id.data_time_text_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imageToolbar = findViewById(R.id.image_toolbar);
//        spinner = (Spinner) findViewById(R.id.spinner);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ParseAdapter(parseItems, this);
        recyclerView.setAdapter(adapter);
        imageToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.image_toolbar:
                        Intent intent = new Intent(MainActivity.this, ForumActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

//                   Toast.makeText(MainActivity.this, "ForumActivity", Toast.LENGTH_SHORT).show();
                }

            }
        });

        setSupportActionBar(toolbar);

        Content content = new Content();
        content.execute();

    }

    //Search

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_item, menu);

        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setQueryHint("Search...");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                newText = newText.toLowerCase();
                ArrayList<ParseItem> newList = new ArrayList<>();
                for (ParseItem parseItem: parseItems) {
                    String title = parseItem.getTitle().toLowerCase();
                    if (title.contains(newText)) {
                        newList.add(parseItem);
                    }
                }
                adapter.setFilter(newList);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;

    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            progressBar.setAnimation(AnimationUtils.loadAnimation(MainActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String urlDota = "https://dota2.ru/news/"; // MB here https://dota2.ru

            try {
                Document doc = Jsoup.connect(urlDota).get();

                Elements els = doc.select("div.pagination");

                String lastPage = els.select("div.pagination_next")
                        .select("a")
                        .attr("href"); //  /news/?page=911
                int count = Integer.parseInt(lastPage.replace(lastPage, "4"));

                for (int i = 1; i <= count; i++) {
                    String url = "https://dota2.ru/news/?page=" + i;
                    itemNews(url);

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private void itemNews (String url) {

            try {
//              String url = "https://dota2.ru/news/"; //mb delete "/"
                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("div#news-categories-blocks.i-news"); // mb article.item
                Elements dataTwo = doc.select("div.comment-rating");

                for (int i = 0; i <= 13; i++) {
                    String imgUrl = data.select("div.content")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String title = data.select("div.inline")
                            .select("h3")
                            .eq(i)
                            .text();

                    String detailUrl = data.select("article.item")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    dataTimeString = data.select("div.info")
                            .select("span.date")
                            .eq(i)
                            .text();

                    String star = data.select("div.comment-rating")
                            .select("div.comment-rating")
                            .eq(i)
                            .text();

                    parseItems.add(new ParseItem(imgUrl, title, detailUrl, dataTimeString, star));
                    Log.d("items", "img: " + imgUrl + "title: " + title);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
