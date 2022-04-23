package com.smlz.yisounews.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.smlz.yisounews.entity.*;
import com.smlz.yisounews.service.IFeedbackInfoService;
import com.smlz.yisounews.service.IUserInfoService;
import com.smlz.yisounews.util.FileUtil;
import com.smlz.yisounews.util.HttpResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
@Controller
@RequestMapping("/yisounews/user")
public class UserCtrl {
    private static final Logger LOG = LoggerFactory.getLogger(UserCtrl.class);

    private IUserInfoService userInfoService;

    private IFeedbackInfoService feedbackInfoService;

    @Autowired
    public void setUserInfoService(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @Autowired
    public void setFeedbackInfoService(IFeedbackInfoService feedbackInfoService) {
        this.feedbackInfoService = feedbackInfoService;
    }


    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult registerUser(@RequestParam(value = "userName") String userName,
                                   @RequestParam(value = "userPwd") String userPwd) {
        if (userName != null && userPwd != null) {
            LOG.info("user register:用户注册  ---name = " + userName + " ; pwd = " + userPwd);
            boolean ifRegistered;
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", userName);
            ifRegistered = userInfoService.count(queryWrapper) != 0;
            if (ifRegistered)
                return HttpResultUtil.failure("用户名已存在");
            ifRegistered = userInfoService.save(new UserInfo(null, userName, userPwd, null, null, null, null));
            LOG.info("user register:结果" + ifRegistered);
            if (ifRegistered)
                return HttpResultUtil.success();
            else
                return HttpResultUtil.failure("创建失败");
        } else
            return HttpResultUtil.failure("创建失败");
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult login(@RequestParam(value = "userName") String userName,
                            @RequestParam(value = "userPwd") String userPwd) {
        if (userName != null && userPwd != null) {
            LOG.info("user login:用户登录  ---name = " + userName + " ; userpwd = " + userPwd);
            UserInfo user;
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", userName);
            queryWrapper.eq("user_pwd", userPwd);
            user = userInfoService.getOne(queryWrapper);
            LOG.info("user login:结果" + (user != null));
            if (user != null) {
                UserInfoBase64 userInfoBase64 = UserInfoBase64.toUserInfoBase64(user);
                LOG.info(JSON.toJSONString(userInfoBase64));
                //必须先将user实体封装为JSON字符串，不然前端接收到的httpResult.getData()类型为com.google.gson.internal.LinkedTreeMap，非常之恶心
                return HttpResultUtil.success(JSON.toJSONString(userInfoBase64));
            } else
                return HttpResultUtil.failure("用户名或密码错误");
        }
        return HttpResultUtil.failure("新闻标题为空");
    }

    @RequestMapping(value = "userinfoedit", method = RequestMethod.POST)
    public void userInfoEdit(@RequestParam(value = "userPic", required = false) MultipartFile file,
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "userSex", required = false) String userSex,
                             @RequestParam(value = "userBirthday", required = false) String userBirthday,
                             @RequestParam(value = "userSignature", required = false) String userSignature) {
        if (userName != null) {
            LOG.info("userinfo edit:修改用户信息 ---" + userName);
            QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_name", userName);

            if (file.isEmpty())
                LOG.info("userinfo edit:修改用户信息 ---empty file get\n");
            else
                LOG.info("userinfo edit:修改用户信息 ---file get\n");

            UserInfo oldUser = userInfoService.getOne(queryWrapper);
            oldUser.setUserSex(userSex);
            oldUser.setUserBirthday(LocalDate.parse(userBirthday, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            oldUser.setUserSignature(userSignature);
            if (!file.isEmpty())
                oldUser.setUserPic(FileUtil.multipartFile2bytes(file));

            boolean ifSuccess;
            ifSuccess = userInfoService.update(oldUser, queryWrapper);
            LOG.info("userinfo edit:修改用户信息 ---user_id = " + oldUser.getUserId() +
                    " ; user_sex = " + oldUser.getUserSex() +
                    " ; user_birthday = " + oldUser.getUserBirthday() +
                    " ; user_signature = " + oldUser.getUserSignature() +
                    " 结果 " + ifSuccess);
        } else
            LOG.info("userinfo edit:修改用户信息 ---userinfo==null");
    }

    @RequestMapping(value = "insertrepo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void commentOnNews(@RequestBody FeedbackInfo feedbackInfo) {
        if (feedbackInfo != null) {
            LOG.info("feedback init:用户反馈  ---content = " + feedbackInfo.getFeedbackContent());
            feedbackInfo.setFeedbackStatus("未处理");
            feedbackInfo.setFeedbackTime(LocalDate.now());
            boolean ifSuccess = false;
            ifSuccess = feedbackInfoService.save(feedbackInfo);
            LOG.info("feedback init:结果" + ifSuccess);
        }
    }

}
