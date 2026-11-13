
package services;

import config.HibernateConfig;
import model.Pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PokedexService {

    public void crearPokedex(Pokedex p) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(p);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao crear a pokedex da Pokedex: " + e.getMessage());
        }
    }

    public Pokedex lerPokedex(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Pokedex.class, id);

        } catch (Exception e) {
            System.out.println("Erro ao ler a pokedex da Pokedex: " + e.getMessage());
            return null;
        }
    }


    public void actualizarPokedex(Long id, Pokedex pokedexUpdate) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pokedex pokedex = session.get(Pokedex.class, id);

            if (pokedex != null) {

                pokedex.setNome(pokedexUpdate.getNome());
                pokedex.setPeso(pokedexUpdate.getPeso());
                pokedex.setMisc(pokedexUpdate.getMisc());

                session.update(pokedex);

                transaction.commit();

            } else {
                System.out.println("Non se atopou a pokedex co id " + id);
                transaction.rollback();
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao actualizar a pokedex da Pokedex: " + e.getMessage());
        }
    }

    public void eliminarPokedex(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pokedex pokedex = session.get(Pokedex.class, id);

            if (pokedex != null) {
                session.delete(pokedex);
                transaction.commit();

            } else {
                System.out.println("Non se atopou a pokedex co id " + id);
                transaction.rollback();
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao eliminar a pokedex da Pokedex: " + e.getMessage());
        }
    }

    public Pokedex lerPokedexPorNome(String nome) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            return  session.createQuery(
                            "FROM Pokedex WHERE nome = :nome",
                            Pokedex.class
                    )
                    .setParameter("nome", nome)
                    .uniqueResult();

        } catch (Exception e) {
            System.out.println("Erro ao ler o Adestrador polo nome: " + e.getMessage());
            return null;
        }
    }


    public List<Pokedex> listarPokedex() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

            List<Pokedex> pokedexList = session.createQuery(
                    "FROM Pokedex", Pokedex.class
            ).list();

            return pokedexList;

        } catch (Exception e) {
            System.out.println("Erro ao listar pokedexs da Pokedex: " + e.getMessage());
            return null;
        }
    }
}
