package com.example.dotanewszxc.forum;

public class ForumItem {

    private String dText;
    private String dComments;
    private String commentUrl;

    public ForumItem(String dText, String dComments, String commentUrl) {
        this.dText = dText;
        this.dComments = dComments;
        this.commentUrl = commentUrl;
    }

    public String getdText() {
        return dText;
    }

    public void setdText(String dText) {
        this.dText = dText;
    }

    public String getdComments() {
        return dComments;
    }

    public void setdComments(String dComments) {
        this.dComments = dComments;
    }

    public String getCommentUrl() {
        return commentUrl;
    }

    public void setCommentUrl(String dText) {
        this.commentUrl = commentUrl;
    }
}

