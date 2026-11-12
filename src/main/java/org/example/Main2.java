package org.example;

import model.Adestrador;
import model.Pokedex;
import model.Pokemon;
import services.AdestradorService;
import services.FileService;
import services.PokedexService;
import services.PokemonServices;

import java.time.LocalDate;
import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args) {

        // ==========================================
        // Insertar 10 Pokémon en la tabla Pokedex
        // ==========================================

        PokedexService.createPokedex(new Pokedex("Pikachu", 6.5, "Pokémon rata eléctrica"));
        PokedexService.createPokedex(new Pokedex("Swampert", 240.5, "Pokémon ajolote de tierra"));
        PokedexService.createPokedex(new Pokedex("Garchomp", 95.0, "Pokémon tiburón dragón"));
        PokedexService.createPokedex(new Pokedex("Gardevoir", 48.4, "Pokémon hada psíquico"));
        PokedexService.createPokedex(new Pokedex("Aggron", 500.0, "Pokémon acero roca"));
        PokedexService.createPokedex(new Pokedex("Togekiss", 38.0, "Pokémon hada volador"));
        PokedexService.createPokedex(new Pokedex("Gyarados", 235.0, "Pokémon serpiente voladora"));
        PokedexService.createPokedex(new Pokedex("Salamence", 102.6, "Pokémon dragón volador"));
        PokedexService.createPokedex(new Pokedex("Metagross", 550.0, "Pokémon acero psíquico"));
        PokedexService.createPokedex(new Pokedex("Tyranitar", 202.8, "Pokémon roca siniestro"));

        // ==========================================
        // Insertar 2 adestradores
        // ==========================================

        AdestradorService.createAdestrador(new Adestrador("Madao120", LocalDate.of(1990, 5, 15)));

        AdestradorService.createAdestrador(new Adestrador("Warroemo", LocalDate.of(1995, 8, 20)));

        // ==========================================
        // Crear Pokémon asociados a los adestradores
        // ==========================================
        ArrayList<Adestrador> listaAdestradores =
                AdestradorService.readAdestrador();
        ArrayList<Pokedex> entradasPokedex = PokedexService.readPokedex();
        ArrayList<Pokemon> listaPokemon = new ArrayList<>();
        LocalDate nacementoPokemon = LocalDate.of(2020, 1, 1);

        for (int i = 0; i < entradasPokedex.size(); i++) {
            Pokemon pokemon = new Pokemon(
                    entradasPokedex.get(i).getNome(),
                    nacementoPokemon,
                    entradasPokedex.get(i).getId(),
                    listaAdestradores.get(i % listaAdestradores.size()) // Para que los pares sean de un entrenador y ls impares de otro
            );
            PokemonServices.crearPokemon(pokemon);
        }
        listaPokemon = PokemonServices.listarPokes();

        // ==========================================
        // Listar Pokémon de la Pokédex
        // ==========================================

        System.out.println("===== POKÉMON EN LA POKÉDEX =====");

        ArrayList<Pokedex> listaPokedex =
                PokedexService.readPokedex();

        for (Pokedex pokemon : listaPokedex) {
            System.out.println(pokemon);
        }

        // ==========================================
        // Listar adestradores
        // ==========================================

        System.out.println("\n===== ADESTRADORES =====");

        listaAdestradores = AdestradorService.readAdestrador();

        for (Adestrador adestrador : listaAdestradores) {
            System.out.println(adestrador);
        }

        // ==========================================
        // Modificar un Pokémon pasando el objeto completo
        // ==========================================
        Pokemon pokemonActualizado = listaPokemon.get(0);
        pokemonActualizado.setNome("Raichu");
        pokemonActualizado.setNacemento(LocalDate.of(2020, 2, 1));
        PokemonServices.actualizarPokemon(pokemonActualizado);

        // ==========================================
        // Modificar dos adestradores
        // ==========================================

        Adestrador adestrador1 = listaAdestradores.get(0);
        adestrador1.setNome("Deiko120");
        adestrador1.setNacemento(LocalDate.of(1987, 5, 22));

        Adestrador adestrador2 = listaAdestradores.get(1);
        adestrador2.setNome("Fos");
        adestrador2.setNacemento(LocalDate.of(1990, 6, 15));

        AdestradorService.updateAdestrador(adestrador1);
        AdestradorService.updateAdestrador(adestrador2);

        // ==========================================
        // Modificar dos entradas da Pokédex
        // ==========================================

        Pokedex pokemon1 = listaPokedex.get(0);
        pokemon1.setNome("Raichu");
        pokemon1.setPeso(30.0);
        pokemon1.setMisc(
                "Pokémon rata eléctrica evolucionado"
        );

        Pokedex pokemon2 = listaPokedex.get(1);
        pokemon2.setNome("Mega-Swampert");
        pokemon2.setPeso(102.0);
        pokemon2.setMisc(
                "Pokémon agua tierra evolucionado"
        );

        PokedexService.updatePokedex(pokemon1);
        PokedexService.updatePokedex(pokemon2);

        // ==========================================
        // Volver a listar los Pokémon
        // ==========================================

        System.out.println("\n===== POKÉMON DESPUÉS DE MODIFICAR =====");

        listaPokedex = PokedexService.readPokedex();

        for (Pokedex pokemon : listaPokedex) {
            System.out.println(pokemon);
        }

        // ==========================================
        // Volver a listar los adestradores
        // ==========================================

        System.out.println("\n===== ADESTRADORES DESPUÉS DE MODIFICAR =====");

        listaAdestradores = AdestradorService.readAdestrador();

        for (Adestrador adestrador : listaAdestradores) {
            System.out.println(adestrador);
        }

        // ==========================================
        // Generar los archivos XML
        // ==========================================

        FileService.transformarXMLPokedex(
                listaPokedex,
                "pokedex.xml"
        );

        FileService.transformarXMLAdestradores(
                listaAdestradores,
                "adestradores.xml"
        );

        System.out.println("\nArchivos XML creados correctamente.");

        // ==========================================
        // Eliminar todos los datos de las tablas
        // ==========================================

        for (Pokemon pokemon : listaPokemon) {
            PokemonServices.eliminarPokemon(pokemon.getId());
        }

        for (Pokedex pokemon : listaPokedex) {
            PokedexService.deletePokedex(
                    pokemon.getId()
            );
        }

        for (Adestrador adestrador : listaAdestradores) {
            AdestradorService.deleteAdestrador(
                    adestrador.getId()
            );
        }

        System.out.println(
                "Todos los datos han sido eliminados."
        );
    }
}