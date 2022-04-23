package com.smlz.yisounews.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("feedback_info")
@ApiModel(value = "FeedbackInfo对象", description = "")
public class FeedbackInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "feedback_id", type = IdType.AUTO)
    private Integer feedbackId;

    private Integer userId;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH-mm-ss", timezone = "GMT+8")
    private LocalDate feedbackTime;

    private String feedbackContent;

    private String feedbackStatus;

    private String feedbackRepo;

    public Integer getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Integer feedbackId) {
        this.feedbackId = feedbackId;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public LocalDate getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(LocalDate feedbackTime) {
        this.feedbackTime = feedbackTime;
    }
    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }
    public String getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(String feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }
    public String getFeedbackRepo() {
        return feedbackRepo;
    }

    public void setFeedbackRepo(String feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    @Override
    public String toString() {
        return "FeedbackInfo{" +
            "feedbackId=" + feedbackId +
            ", userId=" + userId +
            ", feedbackTime=" + feedbackTime +
            ", feedbackContent=" + feedbackContent +
            ", feedbackStatus=" + feedbackStatus +
            ", feedbackRepo=" + feedbackRepo +
        "}";
    }

    public FeedbackInfo(Integer feedbackId, Integer userId, LocalDate feedbackTime, String feedbackContent, String feedbackStatus, String feedbackRepo) {
        this.feedbackId = feedbackId;
        this.userId = userId;
        this.feedbackTime = feedbackTime;
        this.feedbackContent = feedbackContent;
        this.feedbackStatus = feedbackStatus;
        this.feedbackRepo = feedbackRepo;
    }
}
