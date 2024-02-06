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

public class AuthorDAOImpl implements AuthorDAO{

    @Override
    public void createAuthor(Author author) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(author);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public Author getAuthor(Long id) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Author.class, id);
        } catch (NoResultException error) {
            System.err.println(error.getMessage());
            return null;
        }
    }

    @Override
    public List<Author> getAuthors() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> query = builder.createQuery(Author.class);
            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Author> getAuthorsByName(String authorName) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Author> query = builder.createQuery(Author.class);
            Root<Author> authorTable = query.from(Author.class);
            query.where(builder.like(authorTable.get("name"), "%" + authorName + "%"));
            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<Article> getAuthorArticles(Author author) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Article> query = builder.createQuery(Article.class);
            Root<Article> articleTable = query.from(Article.class);
            Join<Author, Article> authorTable = articleTable.join("author");
            query.where(builder.equal(authorTable, author));
            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateAuthor(Author author) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(author);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void removeAuthor(Author author) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(author);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }
}
