package services;

import config.HibernateConfig;
import model.Pokedex;
import org.hibernate.Session;

import java.util.ArrayList;

public class PokedexService {

    public static void createPokedex(Pokedex pokedex) {
        // Lógica para crear unha nova entrada na Pokedex
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(pokedex);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao crear a entrada na Pokedex: " + e.getMessage());
        }
    }

    public static ArrayList<Pokedex> readPokedex() {
        // Lógica para ler unha entrada da Pokedex
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            return (ArrayList<Pokedex>) session.createQuery("from Pokedex", Pokedex.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao ler a entrada da Pokedex: " + e.getMessage());
            return null;
        }
    }

    public static void updatePokedex(Pokedex pokedex) {
        // Lógica para actualizar unha entrada da Pokedex
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(pokedex);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao actualizar a entrada da Pokedex: " + e.getMessage());
        }
    }

    public static void deletePokedex(Long id) {
        // Lógica para eliminar unha entrada da Pokedex
        try(Session session = HibernateConfig.getSessionFactory().openSession()) {
            session.beginTransaction();
            Pokedex pokedex = session.get(Pokedex.class, id);
            if (pokedex != null) {
                session.delete(pokedex);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Erro ao eliminar a entrada da Pokedex: " + e.getMessage());
        }
    }
}
