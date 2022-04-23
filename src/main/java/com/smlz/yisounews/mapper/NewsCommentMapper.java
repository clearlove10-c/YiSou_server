package com.smlz.yisounews.mapper;

import com.smlz.yisounews.entity.NewsComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smlz.yisounews.entity.NewsCommentResponse;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
public interface NewsCommentMapper extends BaseMapper<NewsComment> {
    public List<NewsCommentResponse> getComments(Integer newsId);
}
