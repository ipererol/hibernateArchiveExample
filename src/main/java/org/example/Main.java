package org.example;

import org.example.controller.ArticleController;
import org.example.controller.AuthorController;
import org.example.model.Article;
import org.example.model.Author;

public class Main {
    public static void main(String[] args) {
        AuthorController authorController = new AuthorController();
        ArticleController articleController = new ArticleController();
        Author author = authorController.createAuthor("John Doe");
        String[] tags = new String[]{"tag1", "tag2"};
        Article article1 = authorController.addArticle(author, "Article 1", "¡Ph’nglui mglw’nafh Cthulhu R’lyeh wgah’nagl fhtagn!", tags);
        articleController.publishArticle(article1);
        String[] tags2 = new String[]{"tag3", "tag2"};
        authorController.addArticle(author, "Article 2", "Android sheep with electric dream", tags2);
        for(Article article : authorController.getArticles(author)) {
            System.out.println(article);
        }
    }
}