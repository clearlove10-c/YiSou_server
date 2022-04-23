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
@TableName("news_like")
@ApiModel(value = "NewsLike对象", description = "")
public class NewsLike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "like_id", type = IdType.AUTO)
    private Integer likeId;

    private Integer userId;

    private Integer newsId;

    public Integer getLikeId() {
        return likeId;
    }

    public void setLikeId(Integer likeId) {
        this.likeId = likeId;
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

    @Override
    public String toString() {
        return "NewsLike{" +
            "likeId=" + likeId +
            ", userId=" + userId +
            ", newsId=" + newsId +
        "}";
    }

    public NewsLike(Integer likeId, Integer userId, Integer newsId) {
        this.likeId = likeId;
        this.userId = userId;
        this.newsId = newsId;
    }
}
