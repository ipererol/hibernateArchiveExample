package org.example.dao;

import org.example.model.Article;
import org.example.model.Author;

import java.util.List;

public interface ArticleDAO {

    void createArticle(Article article);
    Article getArticle(Long articleId);
    Article getArticleByTitleAndAuthor(String articleTitle, Author author);
    List<Article> getArticlesByName(String articleName);
    List<Article> getArticles();
    void updateArticle(Article article);
    void removeArticle(Article article);
}
