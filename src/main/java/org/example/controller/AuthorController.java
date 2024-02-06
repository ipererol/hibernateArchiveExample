package org.example.controller;

import org.example.dao.AuthorDAO;
import org.example.dao.AuthorDAOImpl;
import org.example.model.Article;
import org.example.model.Author;
import org.example.model.Tag;

import java.util.List;

public class AuthorController {
    private final AuthorDAO authorDAO;

    public AuthorController(){
        this.authorDAO = new AuthorDAOImpl();
    }

    public Author createAuthor(String authorName) {
        Author author = new Author(authorName);
        authorDAO.createAuthor(author);
        return author;
    }

    public Author createAuthor(String authorName, String description) {
        Author author = new Author(authorName, description);
        authorDAO.createAuthor(author);
        return author;
    }
    public Article addArticle(Author author, String articleTitle, String articleContent, String[] tags) {
        Article article = new Article(articleTitle, articleContent);
        ArticleController ac = new ArticleController();
        article.setAuthor(author);
        ac.addArticleTags(article, tags);
        author.getArticles().add(article);
        authorDAO.updateAuthor(author);
        return article;
    }

    public List<Article> getArticles(Author author) {
        return authorDAO.getAuthorArticles(author);
    }
    
}
