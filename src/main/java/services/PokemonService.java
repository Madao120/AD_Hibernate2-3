
package services;

import config.HibernateConfig;
import model.Pokemon;
import model.Adestrador;
import model.Pokedex;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PokemonService {

    // CREATE
    public void crearPokemon(Pokemon novoPokemon, Long idAdestrador, Long idPokedex) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Adestrador adestrador = session.get(Adestrador.class, idAdestrador);
            Pokedex pokedex = session.get(Pokedex.class, idPokedex);

            if (adestrador == null || pokedex == null) {
                System.out.println("Non existe o adestrador ou a entrada da Pokedex.");
                transaction.rollback();
                return;
            }

            novoPokemon.setAdestrador(adestrador);
            novoPokemon.setPokedex(pokedex);

            session.save(novoPokemon);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao crear o Pokemon: " + e.getMessage());
        }
    }
    public void crearPokemonConTodo(Pokemon novoPokemon) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.save(novoPokemon);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao crear o Pokemon: " + e.getMessage());
        }
    }

    public Pokemon lerPokemon(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            return session.get(Pokemon.class, id);

        } catch (Exception e) {
            System.out.println("Erro ao ler o Pokemon: " + e.getMessage());
            return null;
        }
    }

    // UPDATE
    public void actualizarPokemon(Long id, String novoNome,
                                  Long idAdestrador, Long idPokedex) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pokemon pokemon = session.get(Pokemon.class, id);

            if (pokemon != null) {

                Adestrador adestrador = session.get(Adestrador.class, idAdestrador);
                Pokedex pokedex = session.get(Pokedex.class, idPokedex);

                if (adestrador == null || pokedex == null) {
                    System.out.println("Non existe o adestrador ou a entrada da Pokedex.");
                    transaction.rollback();
                    return;
                }

                pokemon.setNome(novoNome);
                pokemon.setAdestrador(adestrador);
                pokemon.setPokedex(pokedex);

                session.update(pokemon);

                transaction.commit();

            } else {
                System.out.println("Non se atopou o Pokemon co id " + id);
                transaction.rollback();
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao actualizar o Pokemon: " + e.getMessage());
        }
    }

    // DELETE
    public void eliminarPokemon(Long id) {
        Transaction transaction = null;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Pokemon pokemon = session.get(Pokemon.class, id);

            if (pokemon != null) {
                session.delete(pokemon);
                transaction.commit();

            } else {
                System.out.println("Non se atopou o Pokemon co id " + id);
                transaction.rollback();
            }

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.out.println("Erro ao eliminar o Pokemon: " + e.getMessage());
        }
    }

    public List<Pokemon> listarPokemons() {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {

    /*        List<Pokemon> pokemonList = session.createQuery(
                    "SELECT p FROM Pokemon p " +
                            "LEFT JOIN FETCH p.adestrador " +
                            "LEFT JOIN FETCH p.pokedex",
                    Pokemon.class
            ).list();
*/
            List<Pokemon> pokemonList = session.createQuery(
                    "FROM Pokemon", Pokemon.class).list();

            return pokemonList;

        } catch (Exception e) {
            System.out.println("Erro ao listar Pokemons: " + e.getMessage());
            return null;
        }
    }
}
