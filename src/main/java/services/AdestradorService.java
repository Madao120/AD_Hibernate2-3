package services;

import config.HibernateConfig;
import model.Adestrador;
import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class AdestradorService {

    public void crearAdestrador(Adestrador adestrador) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(adestrador);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crear o Adestrador: " + e.getMessage());
        }
    }

    public Adestrador lerAdestrador(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Adestrador.class, id);
        } catch (Exception e) {
            System.out.println("Erro ao ler o Adestrador: " + e.getMessage());
            return null;
        }
    }

    public void actualizarAdestrador(Long id, Adestrador datosUpdate) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador Adestrador = session.get(Adestrador.class, id);
            if (Adestrador != null) {
                Adestrador.setNome(datosUpdate.getNome());
                Adestrador.setPokemons(datosUpdate.getPokemons());
                session.update(Adestrador);
                transaction.commit();
            } else {
                System.out.println("Non se atopou o Adestrador co id " + id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao actualizar o Adestrador: " + e.getMessage());
        }
    }

    public void eliminarAdestrador(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Adestrador Adestrador = session.get(Adestrador.class, id);
            if (Adestrador != null) {
                session.delete(Adestrador);
                transaction.commit();
            } else {
                System.out.println("Non se atopou o Adestrador co id " + id);
            }
        } catch (Exception e) {
            System.out.println("Erro ao eliminar o Adestrador: " + e.getMessage());
        }
    }
    public Adestrador lerAdestradorPorNome(String nome) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

           return  session.createQuery(
                            "FROM Adestrador WHERE nome = :nome",
                            Adestrador.class
                    )
                    .setParameter("nome", nome)
                    .uniqueResult();

        } catch (Exception e) {
            System.out.println("Erro ao ler o Adestrador polo nome: " + e.getMessage());
            return null;
        }
    }

    public List<Adestrador> listarAdestradores() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            List<Adestrador>adestradorList = session.createQuery("FROM Adestrador").list();
            return adestradorList;
        } catch (Exception e) {
            System.out.println("Erro ao listar Adestradors: " + e.getMessage());
            return null;
        }
    }
}
