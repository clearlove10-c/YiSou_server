package com.smlz.yisounews.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smlz.yisounews.entity.FeedbackInfo;
import com.smlz.yisounews.entity.HttpResult;
import com.smlz.yisounews.service.IFeedbackInfoService;
import com.smlz.yisounews.util.HttpResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/yisounews/admin")
public class AdminCtrl {

    private IFeedbackInfoService feedbackInfoService;

    @Autowired
    public void setFeedbackInfoService(IFeedbackInfoService feedbackInfoService) {
        this.feedbackInfoService = feedbackInfoService;
    }

    private static final Logger LOG = LoggerFactory.getLogger(AdminCtrl.class);

    @RequestMapping(value = "getfeedback", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult getFeedback(@RequestParam(value = "adminId") String adminId) {
        List<FeedbackInfo> userFeedbacks;
        userFeedbacks = feedbackInfoService.list();
        LOG.info("get feedback:获取用户反馈列表 ---adminId = " + adminId);
        String json = JSON.toJSONString(userFeedbacks);
        if (userFeedbacks != null) {
            LOG.info("用户反馈列表返回成功");
            return HttpResultUtil.success(json);

        } else {
            LOG.info("用户反馈列表返回失败");
            return HttpResultUtil.failure("报错无用户反馈");
        }
    }

    @RequestMapping(value = "updatefeedback", method = RequestMethod.POST)
    @ResponseBody
    public void updateFeedback(@RequestParam(value = "feedbackId") Integer feedbackId,
                               @RequestParam(value = "feedbackRepo") String feedbackRepo,
                               @RequestParam(value = "feedbackStatus") String feedbackStatus) {
        if (feedbackId != null && feedbackRepo != null && feedbackStatus != null) {
            FeedbackInfo feedbackInfo = feedbackInfoService.getById(feedbackId);
            QueryWrapper<FeedbackInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("feedback_id", feedbackId);
            feedbackInfoService.update(new FeedbackInfo(feedbackId, feedbackInfo.getUserId(), feedbackInfo.getFeedbackTime(),
                    feedbackInfo.getFeedbackContent(), feedbackStatus, feedbackRepo), queryWrapper);
            return;
        }
    }
}
