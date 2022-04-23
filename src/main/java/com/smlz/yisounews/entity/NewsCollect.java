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
@TableName("news_collect")
@ApiModel(value = "NewsCollect对象", description = "")
public class NewsCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "collect_id", type = IdType.AUTO)
    private Integer collectId;

    private Integer userId;

    private Integer newsId;

    public Integer getCollectId() {
        return collectId;
    }

    public void setCollectId(Integer collectId) {
        this.collectId = collectId;
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
        return "NewsCollect{" +
            "collectId=" + collectId +
            ", userId=" + userId +
            ", newsId=" + newsId +
        "}";
    }

    public NewsCollect(Integer collectId, Integer userId, Integer newsId) {
        this.collectId = collectId;
        this.userId = userId;
        this.newsId = newsId;
    }
}
