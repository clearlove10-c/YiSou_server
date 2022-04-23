package com.smlz.yisounews.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smlz.yisounews.entity.*;
import com.smlz.yisounews.service.INewsCollectService;
import com.smlz.yisounews.service.INewsCommentService;
import com.smlz.yisounews.service.INewsInfoService;
import com.smlz.yisounews.service.INewsLikeService;
import com.smlz.yisounews.util.HttpResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
@Controller
@RequestMapping("/yisounews/news")
public class NewsCtrl {
    private static final Logger LOG = LoggerFactory.getLogger(NewsCtrl.class);

    private INewsInfoService newsInfoService;
    private INewsLikeService newsLikeService;
    private INewsCollectService newsCollectService;
    private INewsCommentService newsCommentService;

    @Autowired
    public void setNewsInfoService(INewsInfoService newsInfoService) {
        this.newsInfoService = newsInfoService;
    }

    @Autowired
    public void setNewsLikeService(INewsLikeService newsLikeService) {
        this.newsLikeService = newsLikeService;
    }

    @Autowired
    public void setNewsCollectService(INewsCollectService newsCollectService) {
        this.newsCollectService = newsCollectService;
    }

    @Autowired
    public void setNewsCommentService(INewsCommentService newsCommentService) {
        this.newsCommentService = newsCommentService;
    }

    @RequestMapping(value = "initnewsinfo", method = RequestMethod.POST)
    @ResponseBody
    public void initNewsInfo(@RequestParam(value = "newsUrl") String newsUrl,
                             @RequestParam(value = "newsTitle") String newsTitle,
                             @RequestParam(value = "newsType") String newsType) {
        if (newsUrl != null && newsTitle != null) {
            LOG.info("\nnews init:新闻初始化  ---url = " + newsUrl + " ; title = " + newsTitle + " ; type = " + newsType);
            QueryWrapper<NewsInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.like("news_title", newsTitle);
            if(newsInfoService.count(queryWrapper)!=0)
                return;
            boolean ifSuccess;
            NewsInfo news = new NewsInfo(null, newsUrl, newsTitle, newsType);
            ifSuccess = newsInfoService.save(news);
            LOG.info("news init:结果\n" + ifSuccess);
        }
    }

    @RequestMapping(value = "likenews", method = RequestMethod.POST)
    @ResponseBody
    public void likeNews(@RequestParam(value = "userId") Integer userId,
                         @RequestParam(value = "newsTitle") String newsTitle,
                         @RequestParam(value = "flag") Boolean flag) {
        if (newsTitle != null && flag != null) {
            LOG.info("news like:新闻点赞  ---title = " + newsTitle + " ; flag = " + flag);
            boolean ifSuccess = false;
            Integer newsId = newsInfoService.getNewsIdByNewsTitle(newsTitle);
            if (flag) {
                if (newsId != null && newsId != -1)
                    ifSuccess = newsLikeService.save(new NewsLike(null, userId, newsId));
            } else {
                if (newsId != null && newsId != -1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("user_id", userId);
                    map.put("news_id", newsId);
                    ifSuccess = newsLikeService.removeByMap(map);
                }
            }
            LOG.info("news like:结果" + ifSuccess);
        }
    }

    @RequestMapping(value = "checklike", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult checkLike(@RequestParam(value = "userId") Integer userId,
                                @RequestParam(value = "newsTitle") String newsTitle) {
        if (newsTitle != null) {
            LOG.info("\nnews like:新闻点赞检测  ---title = " + newsTitle + " ; userId = " + userId);
            boolean ifLike;
            Integer newsId = newsInfoService.getNewsIdByNewsTitle(newsTitle);
            QueryWrapper<NewsLike> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("news_id", newsId);
            ifLike = newsLikeService.count(queryWrapper) != 0;
            LOG.info("news like:结果" + ifLike);
            if (ifLike)
                return HttpResultUtil.success();
            else
                return HttpResultUtil.failure("报错未点赞");
        }
        return HttpResultUtil.failure("新闻标题为空");
    }

    @RequestMapping(value = "collectnews", method = RequestMethod.POST)
    @ResponseBody
    public void collectNews(@RequestParam(value = "userId") Integer userId,
                            @RequestParam(value = "newsTitle") String newsTitle,
                            @RequestParam(value = "flag") Boolean flag) {
        if (newsTitle != null && flag != null) {
            LOG.info("news collect:新闻收藏  ---title = " + newsTitle + " ; flag = " + flag);
            boolean ifSuccess = false;
            Integer newsId = newsInfoService.getNewsIdByNewsTitle(newsTitle);
            if (flag) {
                if (newsId != null && newsId != -1)
                    ifSuccess = newsCollectService.save(new NewsCollect(null, userId, newsId));
            } else {
                if (newsId != -1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("user_id", userId);
                    map.put("news_id", newsId);
                    ifSuccess = newsCollectService.removeByMap(map);
                }
            }
            LOG.info("news collect:结果" + ifSuccess);
        }
    }

    @RequestMapping(value = "checkcollect", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult checkCollect(@RequestParam(value = "userId") Integer userId,
                                   @RequestParam(value = "newsTitle") String newsTitle) {
        if (newsTitle != null) {
            LOG.info("news collect:新闻收藏检测  ---title = " + newsTitle + " ; userId = " + userId);
            boolean ifCollect;
            Integer newsId = newsInfoService.getNewsIdByNewsTitle(newsTitle);
            QueryWrapper<NewsCollect> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("user_id", userId);
            queryWrapper.eq("news_id", newsId);
            ifCollect = newsCollectService.count(queryWrapper) != 0;
            LOG.info("news collect:结果" + ifCollect);
            if (ifCollect)
                return HttpResultUtil.success();
            else
                return HttpResultUtil.failure("报错未收藏");
        }
        return HttpResultUtil.failure("新闻标题为空");
    }

//    @RequestMapping(value = "getcollection", method = RequestMethod.POST)
//    @ResponseBody
//    public HttpResult getCollection(@RequestParam(value = "userId") Integer userId) {
//        if (userId != null) {
//            LOG.info("news collect:新闻收藏获取  ---userId = " + userId);
//            List<NewsCollect> collection;
//            QueryWrapper<NewsCollect> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("user_id", userId);
//            collection = newsCollectService.list(queryWrapper);
//            LOG.info("news collect:结果 " + collection.size());
//            if (collection != null)
//                return HttpResultUtil.success(collection);
//            else
//                return HttpResultUtil.failure("收藏为空");
//        }
//        return HttpResultUtil.failure("userId为空");
//    }
    @RequestMapping(value = "getcollection", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult getCollection(@RequestParam(value = "userId") Integer userId) {
        if (userId != null) {
            LOG.info("news collect:新闻收藏获取  ---userId = " + userId);
            List<NewsInfo> collection=null;
            collection=newsCollectService.getCollection(userId);
            LOG.info("news collect:结果 " + collection.size());
            String json= JSON.toJSONString(collection);
            if (collection != null)
                return HttpResultUtil.success(json);
            else
                return HttpResultUtil.failure("收藏为空");
        }
        return HttpResultUtil.failure("userId为空");
    }


    //接受JSON对象用RequestBody
    @RequestMapping(value = "commentnews", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void commentOnNews(@RequestBody NewsCommentResponse newsCommentResponse) {
        if (newsCommentResponse != null) {
            LOG.info("news comment:新闻评论  ---content = " + newsCommentResponse.getContent());
            Integer newsId = newsInfoService.getNewsIdByNewsTitle(newsCommentResponse.getNewsTitle());
            boolean ifSuccess;
            ifSuccess = newsCommentService.save(new NewsComment(null,
                    newsCommentResponse.getUserId(),
                    newsId,
                    newsCommentResponse.getContent(),
                    LocalDate.now()));
            LOG.info("news comment:结果" + ifSuccess);
        }
    }

//    @RequestMapping(value = "getcomment", method = RequestMethod.POST)
//    @ResponseBody
//    public HttpResult getComment(@RequestParam(value = "newsTitle") String newsTitle) {
//        Integer newsId = newsInfoService.getNewsIdByNewsTitle(newsTitle);
//        if (newsId != null) {
//            LOG.info("\nnews collect:新闻收藏获取  ---newsId = " + newsId);
//            List<NewsComment> collection;
//            QueryWrapper<NewsComment> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("news_id", newsId);
//            collection = newsCommentService.list(queryWrapper);
//            LOG.info("news collect:结果" + collection.size());
//
//            List<NewsCommentResponse> responses=new ArrayList<>();
//            for(NewsComment comment:collection)
//                ;
//
//            if (collection != null)
//                return HttpResultUtil.success(collection);
//            else
//                return HttpResultUtil.failure("收藏为空");
//        }
//        return HttpResultUtil.failure("userId为空");
//    }

    @RequestMapping(value = "getcomment", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult getComment(@RequestParam(value = "newsTitle") String newsTitle) {
        Integer newsId = newsInfoService.getNewsIdByNewsTitle(newsTitle);
        if (newsId != null) {
            LOG.info("\nnews collect:新闻收藏获取  ---newsId = " + newsId);
            List<NewsCommentResponse> collection;
            collection=newsCommentService.getComments(newsId);
            LOG.info("news collect:结果" + collection.size());
            String json=JSON.toJSONString(collection);

            if (collection != null)
                return HttpResultUtil.success(json);
            else
                return HttpResultUtil.failure("收藏为空");
        }
        return HttpResultUtil.failure("userId为空");
    }
}
