package com.smlz.yisounews.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.smlz.yisounews.entity.ArticleInfo;
import com.smlz.yisounews.entity.ArticleInfoBase64;
import com.smlz.yisounews.entity.HttpResult;
import com.smlz.yisounews.service.IArticleInfoService;
import com.smlz.yisounews.util.FileUtil;
import com.smlz.yisounews.util.HttpResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author smlz
 * @since 2022-04-17
 */
@Controller
@RequestMapping("/yisounews/article")
public class ArticleCtrl {
    private static final Logger LOG = LoggerFactory.getLogger(ArticleCtrl.class);

    private IArticleInfoService articleInfoService;

    @Autowired
    public void setArticleInfoService(IArticleInfoService articleInfoService) {
        this.articleInfoService = articleInfoService;
    }

    @RequestMapping(value = "insertarticle", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult insertArticle(@RequestParam(value = "articleCover",required = false) MultipartFile file,
                                    @RequestParam(value = "userId") Integer userId,
                                    @RequestParam(value = "articleTitle") String articleTitle,
                                    @RequestParam(value = "articleContent",required = false) String articleContent) {
        if (userId != null) {
            LOG.info("article insert:文章插入  ---name = " + articleTitle);
            boolean ifInserted;
            QueryWrapper<ArticleInfo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("article_title", articleTitle);
            ifInserted = articleInfoService.count(queryWrapper) != 0;
            if (ifInserted)
                return HttpResultUtil.failure("文章已存在");
            ifInserted = articleInfoService.save(new ArticleInfo(null, userId, articleTitle,
                    file.isEmpty()?null: FileUtil.multipartFile2bytes(file),
                    LocalDate.now(), articleContent));
            LOG.info("article insert:结果" + ifInserted);
            if (ifInserted)
                return HttpResultUtil.success();
            else
                return HttpResultUtil.failure("文章上传失败");
        } else
            return HttpResultUtil.failure("文章上传失败");
    }


    @RequestMapping(value = "getarticlelist", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult getArticleList() {
        List<ArticleInfo> articles;
        articles = articleInfoService.list();
        List<ArticleInfoBase64> articleInfoBase64s=new ArrayList<>();
        for(ArticleInfo articleInfo :articles){
            articleInfoBase64s.add(ArticleInfoBase64.toArticleInfoBase64(articleInfo));
        }
        LOG.info("get article list:获取文章列表数量  " + articleInfoBase64s.size());
        if (articleInfoBase64s != null) {
            String json= JSON.toJSONString(articleInfoBase64s);
//            LOG.info("get article list:获取文章列表  json is "+json);
            return HttpResultUtil.success(json);
        } else return HttpResultUtil.failure("文章列表获取失败");
    }


    @RequestMapping(value = "getarticledetail", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult getArticleDetail(@RequestParam(value = "articleId") Integer articleId) {
        if (articleId != null) {
            ArticleInfo article;
            article = articleInfoService.getById(articleId);
            ArticleInfoBase64 articleInfoBase64=ArticleInfoBase64.toArticleInfoBase64(article);
            LOG.info("get article detail:获取文章详情 ---article_Id = " + articleId);
            String json=JSON.toJSONString(articleInfoBase64);
            LOG.info("get article detail:获取文章详情 ---article_Id = " + json);
            if (articleInfoBase64 != null) {
                return HttpResultUtil.success(json);
            } else return HttpResultUtil.failure("文章详情获取失败");
        } else return HttpResultUtil.failure("文章详情获取失败");
    }

    @RequestMapping(value = "deletearticle", method = RequestMethod.POST)
    @ResponseBody
    public HttpResult deleteArticle(@RequestParam(value = "articleId") Integer articleId,
                                    @RequestParam(value = "userId") Integer userId) {
        if (articleId != null && userId != null) {
            ArticleInfo article = articleInfoService.getById(articleId);
            if (!Objects.equals(article.getUserId(), userId))
                return HttpResultUtil.failure("非本人文章无法删除");
            boolean ifDeleted;
            ifDeleted = articleInfoService.removeById(articleId);
            LOG.info("delete article: 删除文章 --- articleId = " + articleId + " ; userId = " + userId);
            if (ifDeleted) {
                return HttpResultUtil.success(ifDeleted);
            } else return HttpResultUtil.failure("文章删除失败");
        } else return HttpResultUtil.failure("文章删除失败");
    }
}
