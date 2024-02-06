package org.example.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.model.Author;
import org.example.model.Tag;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl implements TagDAO {

    @Override
    public void createTag(Tag tag) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(tag);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public Tag getTag(Long tagId) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Tag.class, tagId);
        } catch (NoResultException error) {
            System.err.println(error.getMessage());
            return null;
        }    }

    @Override
    public Tag getTagByName(String tagName) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
            Root<Tag> tagTable = query.from(Tag.class);
            query.where(builder.equal(tagTable.get("name"), tagName));
            return session.createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Tag> getTags() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
            return session.createQuery(query).getResultList();
        } catch (NoResultException exception) {
            System.err.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void updateTag(Tag tag) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(tag);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public void removeTag(Tag tag) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(tag);
            transaction.commit();
        } catch (HibernateError exception) {
            if(transaction!=null) {
                transaction.rollback();
            }
            System.err.println(exception.getMessage());
        }
    }
}
