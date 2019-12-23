package com.vaadin.menu.dao;

import com.vaadin.menu.domain.Emanet;
import com.vaadin.menu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EmanetDao {
    public Emanet saveEmanet(Emanet emanet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            emanet = (Emanet) session.merge(emanet);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return emanet;
    }
    public List<Emanet> findAllEmanet() {
        List<Emanet> emanetList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select emanet From Emanet emanet";
            Query query = session.createQuery(hql);
            emanetList= query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return emanetList;
    }

    public void deleteEmanet(Emanet emanet) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            String hql = "delete from Emanet emanet where emanet.id=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", emanet.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
