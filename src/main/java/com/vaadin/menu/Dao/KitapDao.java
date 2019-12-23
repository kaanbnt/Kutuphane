package com.vaadin.menu.Dao;

import com.vaadin.menu.domain.Kitap;
import com.vaadin.menu.domain.Uye;
import com.vaadin.menu.domain.Yazar;
import com.vaadin.menu.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class KitapDao {

    public Kitap saveKitap(Kitap kitap) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            kitap = (Kitap) session.merge(kitap);
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kitap;
    }

    public List<Kitap> findAllKitap() {
        List<Kitap> kitapList = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            String hql = "Select kitap From Kitap kitap";
            Query query = session.createQuery(hql);
            kitapList = query.list();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return kitapList;
    }

    public void deleteKitap(Kitap kitap) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try (Session session = sessionFactory.openSession();) {
            session.getTransaction().begin();
            String hql = "delete from Kitap kitap where kitap.id=:id";
            Query query = session.createQuery(hql);
            query.setLong("id", kitap.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
