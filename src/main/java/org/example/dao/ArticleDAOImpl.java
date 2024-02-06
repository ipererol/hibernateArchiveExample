package org.example.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.example.model.Article;
import org.example.model.Author;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ArticleDAOImpl implements ArticleDAO{

    @Override
    public void createArticle(Article article) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(article);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public Article getArticle(Long articleId) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Article.class, articleId);
        } catch (NoResultException error) {
            System.err.println(error.getMessage());
            return null;
        }
    }

    @Override
    public Article getArticleByTitleAndAuthor(String articleTitle, Author author) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Article> query = builder.createQuery(Article.class);
            Root<Article> articleTable = query.from(Article.class);
            Join<Article, Author> authorTable = articleTable.join("author");
            query.where(builder.and(builder.equal(authorTable, author), builder.equal(articleTable.get("title"), articleTitle)));
            return session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Article> getArticlesByName(String articleName) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Article> query = builder.createQuery(Article.class);
            Root<Article> articleTable = query.from(Article.class);
            query.where(builder.like(articleTable.get("name"), "%" + articleName + "%"));
            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Article> getArticles() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Article> query = builder.createQuery(Article.class);
            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateArticle(Article article) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(article);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void removeArticle(Article article) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(article);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }
}
