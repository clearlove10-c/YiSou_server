package com.smlz.yisounews.service;

import com.smlz.yisounews.entity.NewsComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.smlz.yisounews.entity.NewsCommentResponse;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
public interface INewsCommentService extends IService<NewsComment> {
    List<NewsCommentResponse> getComments(Integer newsId);
}
