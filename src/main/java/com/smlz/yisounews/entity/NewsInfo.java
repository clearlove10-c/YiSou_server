package com.smlz.yisounews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("news_info")
@ApiModel(value = "NewsInfo对象", description = "")
public class NewsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "news_id", type = IdType.AUTO)
    private Integer newsId;

    private String newsUrl;

    private String newsTitle;

    private String newsType;

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }
    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }
    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
            "newsId=" + newsId +
            ", newsUrl=" + newsUrl +
            ", newsTitle=" + newsTitle +
            ", newsType=" + newsType +
        "}";
    }

    public NewsInfo(Integer newsId, String newsUrl, String newsTitle, String newsType) {
        this.newsId = newsId;
        this.newsUrl = newsUrl;
        this.newsTitle = newsTitle;
        this.newsType = newsType;
    }
}
