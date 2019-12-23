package com.vaadin.menu.dao;

import com.vaadin.menu.domain.Yazar;
import com.vaadin.menu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class YazarDao {

    public Yazar saveYazar(Yazar yazar) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            yazar = (Yazar) session.merge(yazar);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return yazar;
    }

    public List<Yazar> findAllYazar() {
        List<Yazar> yazarList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select yazar From Yazar yazar";
            Query query = session.createQuery(hql);
            yazarList = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return yazarList;
    }

    public void deleteYazar(Yazar yazar) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            String hql = "delete from Yazar yazar where yazar.id=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", yazar.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
