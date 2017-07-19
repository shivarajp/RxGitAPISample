package com.shivaraj.githubapi.models;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorItemModel {

    @SerializedName("author_id")
    @Expose
    private Integer authorId;
    @SerializedName("author_name")
    @Expose
    private String authorName;
    @SerializedName("author_pic")
    @Expose
    private String authorPic;
    @SerializedName("messages")
    @Expose
    private List<String> messages = null;
    @SerializedName("date")
    @Expose
    private List<String> date = null;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPic() {
        return authorPic;
    }

    public void setAuthorPic(String authorPic) {
        this.authorPic = authorPic;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

}
