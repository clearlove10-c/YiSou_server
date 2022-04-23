package com.smlz.yisounews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NewsCommentRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    private Integer userId;

    private String newsTitle;

    private String content;


    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsId(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "NewsComment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", newsTitle=" + newsTitle +
                ", content=" + content +
                "}";
    }
}
