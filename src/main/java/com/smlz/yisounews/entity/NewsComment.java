package com.smlz.yisounews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
@TableName("news_comment")
@ApiModel(value = "NewsComment对象", description = "")
public class NewsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    private Integer userId;

    private Integer newsId;

    private String content;

    private LocalDate commentTime;

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
    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public LocalDate getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(LocalDate commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public String toString() {
        return "NewsComment{" +
            "commentId=" + commentId +
            ", userId=" + userId +
            ", newsId=" + newsId +
            ", content=" + content +
            ", commentTime=" + commentTime +
        "}";
    }

    public NewsComment(Integer commentId, Integer userId, Integer newsId, String content, LocalDate commentTime) {
        this.commentId = commentId;
        this.userId = userId;
        this.newsId = newsId;
        this.content = content;
        this.commentTime = commentTime;
    }

    public NewsComment() {
    }
}
