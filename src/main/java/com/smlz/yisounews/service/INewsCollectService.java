package com.smlz.yisounews.service;

import com.smlz.yisounews.entity.NewsCollect;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smlz.yisounews.entity.NewsInfo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
public interface INewsCollectService extends IService<NewsCollect> {
    public List<NewsInfo> getCollection(Integer userId);
}
