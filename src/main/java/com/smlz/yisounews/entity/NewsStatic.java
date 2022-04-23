package com.smlz.yisounews.entity;


import java.io.Serializable;

public class NewsStatic implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer newsId;

    private String newsTitle;

    private Integer newsLikeNum;

    private Integer newsCollectNum;

    public NewsStatic(Integer newsId, String newsTitle, Integer newsLikeNum, Integer newsCollectNum) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsLikeNum = newsLikeNum;
        this.newsCollectNum = newsCollectNum;
    }

    public NewsStatic() {
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public Integer getNewsLikeNum() {
        return newsLikeNum;
    }

    public void setNewsLikeNum(Integer newsLikeNum) {
        this.newsLikeNum = newsLikeNum;
    }

    public Integer getNewsCollectNum() {
        return newsCollectNum;
    }

    public void setNewsCollectNum(Integer newsCollectNum) {
        this.newsCollectNum = newsCollectNum;
    }

    @Override
    public String toString() {
        return "NewsStatic{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsLikeNum=" + newsLikeNum +
                ", newsCollectNum=" + newsCollectNum +
                '}';
    }
}
