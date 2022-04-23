package com.smlz.yisounews.service.impl;

import com.smlz.yisounews.entity.NewsComment;
import com.smlz.yisounews.entity.NewsCommentResponse;
import com.smlz.yisounews.mapper.NewsCommentMapper;
import com.smlz.yisounews.service.INewsCommentService;
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
public class NewsCommentServiceImpl extends ServiceImpl<NewsCommentMapper, NewsComment> implements INewsCommentService {

    @Resource
    NewsCommentMapper newsCommentMapper;

    @Override
    public List<NewsCommentResponse> getComments(Integer newsId) {
        return newsCommentMapper.getComments(newsId);
    }
}
