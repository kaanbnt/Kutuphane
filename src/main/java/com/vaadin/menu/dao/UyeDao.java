package com.vaadin.menu.dao;

import com.vaadin.menu.domain.Uye;
import com.vaadin.menu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class UyeDao {
    public Uye saveUye(Uye uye) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            uye = (Uye) session.merge(uye);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return uye;
    }
    public List<Uye> findAllUye() {
        List<Uye> uyeList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select uye From Uye uye";
            Query query = session.createQuery(hql);
            uyeList = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return uyeList;
    }

    public void deleteUye(Uye uye) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            String hql = "delete from Uye uye where uye.id=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", uye.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
