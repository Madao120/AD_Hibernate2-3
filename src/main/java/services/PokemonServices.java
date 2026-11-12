package services;

import config.HibernateConfig;
import model.Pokemon;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class PokemonServices {

    public static void crearPokemon(Pokemon pokemon) {
        // Lógica para crear un Pokémon
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            Transaction transaction = session.beginTransaction();
            session.save(pokemon);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao crear o Pokémon: " + e.getMessage());
        }
    }

    public static ArrayList<Pokemon> listarPokes(){
        try (Session session = HibernateConfig.getSessionFactory().openSession()){
            return (ArrayList<Pokemon>) session.createQuery("from Pokemon", Pokemon.class).getResultList();
        } catch (Exception e) {
            System.out.println("Erro ao listar o Pokémon: " + e.getMessage());
            return null;
        }
    }

    public static void actualizarPokemon(Pokemon pokemon) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            Pokemon pokemonExistente = session.get(Pokemon.class, pokemon.getId());

            if (pokemonExistente != null) {
                session.merge(pokemon);
                transaction.commit();
            } else {
                System.out.println("Pokémon non encontrado para actualizar.");
                transaction.rollback();
            }

        } catch (Exception e) {
            System.out.println("Erro ao actualizar o Pokémon: " + e.getMessage());
        }
    }

    public static void eliminarPokemon(Long id) {
        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Pokemon pokemon = session.get(Pokemon.class, id);
            if (pokemon != null) {
                session.delete(pokemon);
            } else {
                System.out.println("Pokémon non encontrado para eliminar.");
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Erro ao eliminar o Pokémon: " + e.getMessage());
        }
    }



}
