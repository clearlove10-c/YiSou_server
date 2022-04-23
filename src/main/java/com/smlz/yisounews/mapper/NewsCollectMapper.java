package com.smlz.yisounews.mapper;

import com.smlz.yisounews.entity.NewsCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.smlz.yisounews.entity.NewsInfo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
public interface NewsCollectMapper extends BaseMapper<NewsCollect> {
    public List<NewsInfo> getCollection(Integer userId);
}
