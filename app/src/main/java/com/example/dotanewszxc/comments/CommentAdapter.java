package com.example.dotanewszxc.comments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dotanewszxc.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<CommentItem> commentItems;
    private Context context;

    public CommentAdapter(ArrayList<CommentItem> commentItems, Context context) {
        this.commentItems = commentItems;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {

        CommentItem commentItem = commentItems.get(position);
        holder.textViewUserName.setText(commentItem.getUserName());
        holder.textViewUserDataReg.setText("Регистарция: " + commentItem.getUserDataReg());
        holder.textViewUserMessage.setText("Сообщения: " + commentItem.getUserMessage());
        holder.textViewUserRating.setText("Рейтинг: " + commentItem.getUserRating());
        holder.textViewUserRealMessage.setText(commentItem.getUserRealMessage());
        holder.textViewCommentTime.setText(commentItem.getCommentTime());
        holder.textViewCommentNumber.setText(commentItem.getCommentNumber());
        holder.textViewCountLike.setText(commentItem.getCountLike());
        holder.textViewCountDislike.setText(commentItem.getCountDislike());
        if (commentItems.isEmpty()) {
            holder.imageViewUserPhoto.setImageResource(R.drawable.pudge0);
        } else
            Picasso.get().load("https://dota2.ru/" + commentItem.getImgUserPhoto()).into(holder.imageViewUserPhoto);

    }

    @Override
    public int getItemCount() {
        return commentItems.size();
    }

    public class ViewHolderOne extends RecyclerView.ViewHolder {

        TextView nameTheme;
        public ViewHolderOne(@NonNull View itemView) {
            super(itemView);

            nameTheme = itemView.findViewById(R.id.name_theme);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewUserPhoto;
        TextView textViewUserName;
        TextView textViewUserDataReg;
        TextView textViewUserMessage;
        TextView textViewUserRating;
        TextView textViewUserRealMessage;
        TextView textViewCommentTime;
        TextView textViewCommentNumber;
        TextView textViewCountLike;
        TextView textViewCountDislike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewUserPhoto = itemView.findViewById(R.id.user_photo);
            textViewUserName = itemView.findViewById(R.id.user_name);
            textViewUserDataReg = itemView.findViewById(R.id.user_date_reg);
            textViewUserMessage = itemView.findViewById(R.id.user_message);
            textViewUserRating = itemView.findViewById(R.id.user_rating);
            textViewUserRealMessage = itemView.findViewById(R.id.user_real_message);
            textViewCommentTime = itemView.findViewById(R.id.comment_time);
            textViewCommentNumber = itemView.findViewById(R.id.comment_number);
            textViewCountLike = itemView.findViewById(R.id.count_like);
            textViewCountDislike = itemView.findViewById(R.id.count_dislike);
        }

    }
}
