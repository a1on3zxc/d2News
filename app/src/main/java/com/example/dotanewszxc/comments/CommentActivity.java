package com.example.dotanewszxc.comments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dotanewszxc.forum.ForumActivity;
import com.example.dotanewszxc.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private LinearLayoutManager manager;
    private ArrayList<CommentItem> commentItems = new ArrayList<>();
    private ImageView imageViewUserPhoto;
    private TextView textViewUserName, textViewUserDataReg, textViewUserMessage, textViewUserRating, textViewUserRealMessage,
            textViewCommentTime, textViewCommentNumber, textViewCountLike, textViewCountDislike, nameThemeTextView;
    private String userName, userDataReg, userMessage, userRating, userRealMessage, commentTime,
            commentNumber, countLike, countDislike;
    private String userPhoto;
    private String nameTheme;
    private ImageView back;
    Boolean isScrolling = false;
    int currentItems, totalItems, scrollOutItems;


    private ArrayList<CommentItem> nameItems = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        recyclerView = findViewById(R.id.recyclerView_comments);

        imageViewUserPhoto = findViewById(R.id.user_photo);
        textViewUserName = findViewById(R.id.user_name);
        textViewUserDataReg = findViewById(R.id.user_date_reg);
        textViewUserMessage = findViewById(R.id.user_message);
        textViewUserRating = findViewById(R.id.user_rating);
        textViewUserRealMessage = findViewById(R.id.user_real_message);
        textViewCommentTime = findViewById(R.id.comment_time);
        textViewCommentNumber = findViewById(R.id.comment_number);
        textViewCountLike = findViewById(R.id.count_like);
        textViewCountDislike = findViewById(R.id.count_dislike);
        nameThemeTextView = findViewById(R.id.name_theme);
        back = findViewById(R.id.back);
        nameThemeTextView.setText(getIntent().getStringExtra("nameTheme"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.back:
                        Intent intent = new Intent(CommentActivity.this, ForumActivity.class);
                        startActivity(intent);
                }

            }
        });

        manager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(manager);
        adapter = new CommentAdapter(commentItems, this);
        recyclerView.setAdapter(adapter);


        Comments comments = new Comments();
        comments.execute();
    }

    public class Comments extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            String baseUrlPage = "https://dota2.ru";
            String commentUrlPage = getIntent().getStringExtra("commentUrl");

            String urlPage = baseUrlPage + commentUrlPage;
            try {
                Document docPage = Jsoup.connect(urlPage).get();

                Elements nextPage = docPage.select("ul.pagination.pull-left");

                int nextPageSize = nextPage.size();

                for (int i = 0; i < 3; i++) {

                    String nextPageCount = nextPage.select("li")
                            .select("a")
                            .eq(i)
                            .attr("href");


                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                String baseUrl = "https://dota2.ru";
                String commentUrl = getIntent().getStringExtra("commentUrl");

                String url = baseUrl + commentUrl;

                Document doc = Jsoup.connect(url).get();

                Elements data = doc.select("ul.message-list");
                Elements data2 = doc.select("li.message staff         ");
                int sizee = data.size();

                for (int i = 0; i <= 19; i++) {
                    userPhoto = data.select("div.avatarHolder")
                            .select("img")
                            .eq(i)
                            .attr("src");

                    String userName = data.select("h3.userText")
                            .select("span")
                            .eq(i)
                            .text();

                    String userDataReg = data.select("dl.pairsJustified.self-registered")
                            .select("dd")
                            .eq(i)
                            .text();

                    String userMessage = data.select("dl.pairsJustified.uposts-count.self-posts")
                            .select("dd")
                            .eq(i)
                            .text();

                    String userRating = data.select("dl.pairsJustified.self-likes")
                            .select("dd")
                            .eq(i)
                            .text();

                    String userRealMessage = data.select("blockquote.messageText.baseHtml")
                            .eq(i)
                            .select("p")
                            .text();

                    String commentTime = data.select("span.post-send-time")
                            .select("time")
                            .eq(i)
                            .text();

                    String commentNumber = data.select("span.share-post-container")
                            .select("a")
                            .eq(i)
                            .text();

                    String countLike = data.select("li.rates.js-smile-rates.smile-972.rate-like-bottom-color")
                            .select("span")
                            .eq(i)
                            .text();

                    String countDislike = data.select("li.rates.js-smile-rates.smile-973.rate-dislike-bottom-color")
                            .select("span")
                            .eq(i)
                            .text();

                    Elements name = doc.select("div.page-title");

                    nameTheme = name.select("div.page-title > h1")
                            .select("span")
                            .text();


                    commentItems.add(new CommentItem(userPhoto, userName, userDataReg, userMessage, userRating, userRealMessage,
                            commentTime, commentNumber, countLike, countDislike, nameTheme));
                    Log.d("items", "nameTheme: " + nameTheme);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
