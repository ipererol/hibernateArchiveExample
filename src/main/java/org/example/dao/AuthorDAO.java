package org.example.dao;

import org.example.model.Article;
import org.example.model.Author;
import org.example.model.Tag;

import java.util.List;

public interface AuthorDAO {
    void createAuthor(Author author);
    Author getAuthor(Long id);
    List<Author> getAuthors();
    List<Author> getAuthorsByName(String authorName);
    List<Article> getAuthorArticles(Author author);
    void updateAuthor(Author author);
    void removeAuthor(Author author);
}
