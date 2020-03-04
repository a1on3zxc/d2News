package com.example.dotanewszxc.comments;

public class CommentItem {
    private String imgUserPhoto;
    private String userName;
    private String userDataReg;
    private String userMessage;
    private String userRating;
    private String userRealMessage;
    private String commentTime;
    private String commentNumber;
    private String countLike;
    private String countDislike;
    private String nameTheme;

    public CommentItem(String imgUserPhoto, String userName, String userDataReg, String userMessage,
                       String userRating, String userRealMessage, String commentTime, String commentNumber,
                       String countLike, String countDislike, String nameTheme) {

        this.imgUserPhoto = imgUserPhoto;
        this.userName = userName;
        this.userDataReg = userDataReg;
        this.userMessage = userMessage;
        this.userRating = userRating;
        this.userRealMessage = userRealMessage;
        this.commentTime = commentTime;
        this.commentNumber = commentNumber;
        this.countLike = countLike;
        this.countDislike = countDislike;
        this.nameTheme = nameTheme;
    }

    public String getImgUserPhoto() {
        return imgUserPhoto;
    }

    public void setImgUserPhoto(String imgUserPhoto) {
        this.imgUserPhoto = imgUserPhoto;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserDataReg() {
        return userDataReg;
    }

    public void setUserDataReg(String userDataReg) {
        this.userDataReg = userDataReg;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    public String getUserRealMessage() {
        return userRealMessage;
    }

    public void setUserRealMessage(String userRealMessage) {
        this.userRealMessage = userRealMessage;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(String commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getCountLike() {
        return countLike;
    }

    public void setCountLike(String countLike) {
        this.countLike = countLike;
    }

    public String getCountDislike() {
        return countDislike;
    }

    public void setCountDislike(String countDislike) {
        this.countDislike = countDislike;
    }

    public String getNameTheme() {
        return nameTheme;
    }

    public void setNameTheme(String nameTheme) {
        this.nameTheme = nameTheme;
    }
}
