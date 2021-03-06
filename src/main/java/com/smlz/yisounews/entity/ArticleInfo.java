package com.smlz.yisounews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;
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
@TableName("article_info")
@ApiModel(value = "ArticleInfo对象", description = "")
public class ArticleInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "article_id", type = IdType.AUTO)
    private Integer articleId;

    private Integer userId;

    private String articleTitle;

    private byte[] articleCover;

    private LocalDate articleTime;

    private String articleContent;

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
    public byte[] getArticleCover() {
        return articleCover;
    }

    public void setArticleCover(byte[] articleCover) {
        this.articleCover = articleCover;
    }
    public LocalDate getArticleTime() {
        return articleTime;
    }

    public void setArticleTime(LocalDate articleTime) {
        this.articleTime = articleTime;
    }
    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    @Override
    public String toString() {
        return "ArticleInfo{" +
            "articleId=" + articleId +
            ", userId=" + userId +
            ", articleTitle=" + articleTitle +
            ", articleCover=" + articleCover +
            ", articleTime=" + articleTime +
            ", articleContent=" + articleContent +
        "}";
    }

    public ArticleInfo(Integer articleId, Integer userId, String articleTitle, byte[] articleCover, LocalDate articleTime, String articleContent) {
        this.articleId = articleId;
        this.userId = userId;
        this.articleTitle = articleTitle;
        this.articleCover = articleCover;
        this.articleTime = articleTime;
        this.articleContent = articleContent;
    }

    public ArticleInfo() {
    }
}
