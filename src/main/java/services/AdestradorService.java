package services;

import config.HibernateConfig;
import model.Adestrador;
import org.hibernate.Session;

import java.util.ArrayList;

public class AdestradorService {

    public static void createAdestrador(Adestrador adestrador) {
        // Lógica para crear un novo adestrador
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(adestrador);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao crear o adestrador: " + e.getMessage());
        }
    }

    public static ArrayList<Adestrador> readAdestrador() {
        // Lógica para ler un adestrador
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return (ArrayList<Adestrador>) session.createQuery("from Adestrador", Adestrador.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao ler o adestrador: " + e.getMessage());
            return null;
        }
    }

    public static void updateAdestrador(Adestrador adestrador) {
        // Lógica para actualizar un adestrador
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(adestrador);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualizar o adestrador: " + e.getMessage());
        }
    }

    public static void deleteAdestrador(Long id) {
        // Lógica para eliminar un adestrador
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Adestrador adestrador = session.get(Adestrador.class, id);
            if (adestrador != null) {
                session.delete(adestrador);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao eliminar o adestrador: " + e.getMessage());
        }
    }
}
