package com.smlz.yisounews.controller;

import com.smlz.yisounews.entity.UserInfo;
import com.smlz.yisounews.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author smlz
 * @since 2022-04-15
 */
@Controller
@RequestMapping("/yisounews/userinfo")
public class UserInfoController {
    @Autowired
    private IUserInfoService userService;
    @GetMapping("/getUser")
    public UserInfo getUser(){
        return userService.getById(1);
    }
}
