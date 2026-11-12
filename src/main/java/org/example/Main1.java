package org.example;

import model.Adestrador;
import model.Pokedex;
import model.Pokemon;
import services.AdestradorService;
import services.PokedexService;
import services.PokemonServices;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main1 {
    public static void main(String[] args) {

        // 1. Crear los adestradores que se asociarán a los Pokémon
        AdestradorService.createAdestrador(
                new Adestrador("Madao120", LocalDate.of(1990, 5, 15))
        );
        AdestradorService.createAdestrador(
                new Adestrador("Warroemo", LocalDate.of(1995, 8, 20))
        );

        ArrayList<Adestrador> adestradores = AdestradorService.readAdestrador();

        // 2. Insertar 10 entradas en la Pokédex
        PokedexService.createPokedex(
                new Pokedex("Pikachu", 6.5, "Pokémon rata eléctrica")
        );
        PokedexService.createPokedex(new Pokedex("Swampert", 240.5, "Pokémon ajolote de tierra"));
        PokedexService.createPokedex(new Pokedex("Garchomp", 95.0, "Pokémon tiburón dragón"));
        PokedexService.createPokedex(new Pokedex("Gardevoir", 48.4, "Pokémon hada psíquico"));
        PokedexService.createPokedex(new Pokedex("Aggron", 500.0, "Pokémon acero roca"));
        PokedexService.createPokedex(new Pokedex("Togekiss", 38.0, "Pokémon hada volador"));
        PokedexService.createPokedex(new Pokedex("Gyarados", 235.0, "Pokémon serpiente voladora"));
        PokedexService.createPokedex(new Pokedex("Salamence", 102.6, "Pokémon dragón volador"));
        PokedexService.createPokedex(new Pokedex("Metagross", 550.0, "Pokémon acero psíquico"));
        PokedexService.createPokedex(new Pokedex("Tyranitar", 202.8, "Pokémon roca siniestro"));

        // 3. Crear los Pokémon usando objetos Adestrador, no sus identificadores
        ArrayList<Pokedex> entradasPokedex = PokedexService.readPokedex();
        LocalDate nacementoPokemon = LocalDate.of(2020, 1, 1);

        for (int i = 0; i < entradasPokedex.size(); i++) {
            Adestrador adestrador = adestradores.get(i % adestradores.size());
            Pokemon pokemon = new Pokemon(
                    entradasPokedex.get(i).getNome(),
                    nacementoPokemon,
                    entradasPokedex.get(i).getId(),
                    adestrador
            );
            PokemonServices.crearPokemon(pokemon);
        }

        // 4. Listar los Pokémon de la Pokédex
        System.out.println("===== POKÉMON INSERTADOS =====");
        ArrayList<Pokedex> pokemons = PokedexService.readPokedex();
        for (Pokedex pokemon : pokemons) {
            System.out.println(pokemon);
        }

        // 5. Modificar dos entradas de la Pokédex
        Pokedex primerPokemon = pokemons.get(0);
        primerPokemon.setNome("Raichu");
        primerPokemon.setPeso(30.0);
        primerPokemon.setMisc("Pokémon rata eléctrica evolucionado");

        Pokedex segundoPokemon = pokemons.get(1);
        segundoPokemon.setNome("Mega-Swampert");
        segundoPokemon.setPeso(102.0);
        segundoPokemon.setMisc("Pokémon agua tierra evolucionado");

        PokedexService.updatePokedex(primerPokemon);
        PokedexService.updatePokedex(segundoPokemon);

        // 6. Volver a listar los Pokémon
        System.out.println("\n===== POKÉMON MODIFICADOS =====");
        pokemons = PokedexService.readPokedex();
        for (Pokedex pokemon : pokemons) {
            System.out.println(pokemon);
        }

        // 7. Eliminar los Pokémon relacionados antes que sus adestradores
        ArrayList<Pokemon> listaPokemon = PokemonServices.listarPokes();
        for (Pokemon pokemon : listaPokemon) {
            PokemonServices.eliminarPokemon(pokemon.getId());
        }

        // 8. Eliminar todas las entradas de la Pokédex
        for (Pokedex pokemon : pokemons) {
            PokedexService.deletePokedex(pokemon.getId());
        }

        for (Adestrador adestrador : adestradores) {
            AdestradorService.deleteAdestrador(adestrador.getId());
        }

        // 9. Comprobar que la Pokédex está vacía
        System.out.println("\n===== POKÉDEX DESPUÉS DE ELIMINAR =====");
        pokemons = PokedexService.readPokedex();
        if (pokemons == null || pokemons.isEmpty()) {
            System.out.println("La Pokédex está vacía.");
        }
        else {
            for (Pokedex pokemon : pokemons) {
                System.out.println(pokemon);
            }
        }
    }
}
