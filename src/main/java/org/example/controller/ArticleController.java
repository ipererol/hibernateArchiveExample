package org.example.controller;

import org.example.dao.ArticleDAO;
import org.example.dao.ArticleDAOImpl;
import org.example.model.Article;
import org.example.model.ArticleStatus;
import org.example.model.Tag;

public class ArticleController {
    private final ArticleDAO articleDAO;

    public ArticleController() {
        this.articleDAO = new ArticleDAOImpl();
    }

    public void addArticleTags(Article article, String[] tags) {
        TagController tg = new TagController();
        for(String tagName : tags) {
            Tag tag = tg.getTag(tagName);
            if(tag!=null)
                article.getTags().add(tag);
            else{
                article.getTags().add(tg.createTag(tagName));
            }
        }
    }

    public void publishArticle(Article article){
        article.setStatus(ArticleStatus.PUBLISH);
        articleDAO.updateArticle(article);
    }
}
