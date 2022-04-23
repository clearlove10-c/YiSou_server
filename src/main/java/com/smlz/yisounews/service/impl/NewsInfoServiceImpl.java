package com.smlz.yisounews.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smlz.yisounews.entity.NewsInfo;
import com.smlz.yisounews.mapper.NewsInfoMapper;
import com.smlz.yisounews.service.INewsInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
@Service
public class NewsInfoServiceImpl extends ServiceImpl<NewsInfoMapper, NewsInfo> implements INewsInfoService {

    private INewsInfoService newsInfoService;
    @Autowired
    public void setNewsInfoService(INewsInfoService newsInfoService) {
        this.newsInfoService = newsInfoService;
    }

    @Override
    public Integer getNewsIdByNewsTitle(String newsTitle) {
        QueryWrapper<NewsInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("news_title",newsTitle);

        NewsInfo news=newsInfoService.getOne(queryWrapper);
        if(news==null)
            return -1;
        else
            return news.getNewsId();
    }
}
