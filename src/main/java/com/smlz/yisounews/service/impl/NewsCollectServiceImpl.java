package com.smlz.yisounews.service.impl;

import com.smlz.yisounews.entity.NewsCollect;
import com.smlz.yisounews.entity.NewsInfo;
import com.smlz.yisounews.mapper.NewsCollectMapper;
import com.smlz.yisounews.service.INewsCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
@Service
public class NewsCollectServiceImpl extends ServiceImpl<NewsCollectMapper, NewsCollect> implements INewsCollectService {
    @Resource
    NewsCollectMapper newsCollectMapper;

    @Override
    public List<NewsInfo> getCollection(Integer userId) {
        return newsCollectMapper.getCollection(userId);
    }
}
