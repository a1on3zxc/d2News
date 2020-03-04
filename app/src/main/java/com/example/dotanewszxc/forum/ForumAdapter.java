package com.example.dotanewszxc.forum;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotanewszxc.R;
import com.example.dotanewszxc.comments.CommentActivity;

import java.util.ArrayList;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ViewHolder> {

    private ArrayList<ForumItem> forumItems;
    private Context context;


    public ForumAdapter(ArrayList<ForumItem> forumItems, Context context) {
        this.forumItems = forumItems;
        this.context = context;

    }

    @NonNull
    @Override
    public ForumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumAdapter.ViewHolder holder, int position) {

        ForumItem forumItem = forumItems.get(position);
        holder.titleTheme.setText(forumItem.getdText());
        holder.countComments.setText(forumItem.getdComments());


    }

    @Override
    public int getItemCount() {
        return forumItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTheme;
        TextView countComments;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTheme = itemView.findViewById(R.id.title_theme);
            countComments = itemView.findViewById(R.id.count_comments);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            ForumItem forumItem = forumItems.get(position);
            Intent intent = new Intent(context, CommentActivity.class);
            intent.putExtra("nameTheme", forumItem.getdText());
            intent.putExtra("commentUrl", forumItem.getCommentUrl());
            context.startActivity(intent);
        }
    }
}
