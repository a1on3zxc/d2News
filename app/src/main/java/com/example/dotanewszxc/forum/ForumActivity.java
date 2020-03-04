package com.example.dotanewszxc.forum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.dotanewszxc.MainActivity;
import com.example.dotanewszxc.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class ForumActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ForumAdapter adapter;
    private ArrayList<ForumItem> forumItems = new ArrayList<>();
    ImageView imageToolbarForum;
    private ProgressBar progressBarForum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        recyclerView = findViewById(R.id.recyclerView_forum);
        imageToolbarForum = findViewById(R.id.image_toolbar_forum);
        progressBarForum = findViewById(R.id.progressBarForum);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ForumAdapter(forumItems, this);
        recyclerView.setAdapter(adapter);

        imageToolbarForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.image_toolbar_forum:
                        Intent intent = new Intent(ForumActivity.this, MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }

            }
        });

        Forum comments = new Forum();
        comments.execute();
    }

    public class Forum extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBarForum.setVisibility(View.VISIBLE);
            progressBarForum.setAnimation(AnimationUtils.loadAnimation(ForumActivity.this, android.R.anim.fade_in));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBarForum.setVisibility(View.GONE);
            progressBarForum.setAnimation(AnimationUtils.loadAnimation(ForumActivity.this, android.R.anim.fade_out));
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                String url = "https://dota2.ru";
                Document doc = Jsoup.connect(url).get();

                Elements els = doc.select("section.activities-block ");

                int size = els.size();
                for (int i = 0; i < 20; i++) {

                    String titleTheme = els.select("div.text")
                            .select("div.text")
                            .eq(i)
                            .text();

                    String countComments = els.select("div.comments")
                            .select("div.comments")
                            .eq(i)
                            .text();

                    String commentUrl = els.select("article.item")
                            .select("a")
                            .eq(i)
                            .attr("href");

                    forumItems.add(new ForumItem(titleTheme, countComments, commentUrl));
                    Log.d("comment", "url:" + commentUrl);
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
