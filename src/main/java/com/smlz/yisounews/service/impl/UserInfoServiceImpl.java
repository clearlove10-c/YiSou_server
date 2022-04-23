package com.smlz.yisounews.service.impl;

import com.smlz.yisounews.entity.UserInfo;
import com.smlz.yisounews.mapper.UserInfoMapper;
import com.smlz.yisounews.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author smlz
 * @since 2022-04-15
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
