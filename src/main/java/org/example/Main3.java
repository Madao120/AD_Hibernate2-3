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

public class Main3 {
    public static void main(String[] args) {
        System.out.println("Creando 12 entradas en Pokedex");
        PokedexService.createPokedex(new Pokedex("Swampert", 240.5, "Pokémon ajolote de tierra"));
        PokedexService.createPokedex(new Pokedex("Garchomp", 95.0, "Pokémon tiburón dragón"));
        PokedexService.createPokedex(new Pokedex("Gardevoir", 48.4, "Pokémon hada psíquico"));
        PokedexService.createPokedex(new Pokedex("Aggron", 500.0, "Pokémon acero roca"));
        PokedexService.createPokedex(new Pokedex("Togekiss", 38.0, "Pokémon hada volador"));
        PokedexService.createPokedex(new Pokedex("Gyarados", 235.0, "Pokémon serpiente voladora"));
        PokedexService.createPokedex(new Pokedex("Salamence", 102.6, "Pokémon dragón volador"));
        PokedexService.createPokedex(new Pokedex("Metagross", 550.0, "Pokémon acero psíquico"));
        PokedexService.createPokedex(new Pokedex("Tyranitar", 202.8, "Pokémon roca siniestro"));
        PokedexService.createPokedex(new Pokedex("Lucario", 54.0, "Pokémon lucha acero"));
        PokedexService.createPokedex(new Pokedex("Infernape", 55.0, "Pokémon fuego lucha"));
        PokedexService.createPokedex(new Pokedex("Blaziken", 62.0, "Pokémon fuego lucha"));
        PokedexService.createPokedex(new Pokedex("Sceptile", 52.2, "Pokémon planta dragón"));

        System.out.println("Creando 2 entrenadores");
        AdestradorService.createAdestrador(new Adestrador("Madao120", LocalDate.of(1990, 5, 15)));
        AdestradorService.createAdestrador(new Adestrador("Warroemo", LocalDate.of(1995, 8, 20)));

        System.out.println("Creando las listas de Pokedex y Adestradores");
        ArrayList<Pokedex> pokedexList = PokedexService.readPokedex();
        ArrayList<Adestrador> adestradorList = AdestradorService.readAdestrador();

        for (int i = 0; i < pokedexList.size(); i++) {
            if (i % 2 == 0) {
                PokemonServices.crearPokemon(new Pokemon(
                        pokedexList.get(i).getNome(),
                        LocalDate.of(2020, 1, 1),
                        pokedexList.get(i).getId(),
                        adestradorList.get(0)
                ));
            } else {
                PokemonServices.crearPokemon(new Pokemon(
                        pokedexList.get(i).getNome(),
                        LocalDate.of(2020, 1, 1),
                        pokedexList.get(i).getId(),
                        adestradorList.get(1)
                ));
            }
        }

        System.out.println("Pokemons Creados\nImprimiendo entidades:\n");
        System.out.println("===== POKEDEX =====");
        for (Pokedex p : pokedexList) {
            System.out.println(p);
        }
        System.out.println("\n===== ENTRENADORES =====");
        for (Adestrador a : adestradorList) {
            System.out.println(a);
        }
        System.out.println("\n===== POKEMONS =====");
        ArrayList<Pokemon> pokemonList = PokemonServices.listarPokes();
        for (Pokemon p : pokemonList) {
            System.out.println(p);
        }

        System.out.println("Serializar 2 entradas de Pokedex");
        ArrayList<Pokedex> pokedexToSerialize = new ArrayList<>();
        pokedexToSerialize.add(pokedexList.get(0));
        pokedexToSerialize.add(pokedexList.get(1));

        String rutaSerialPokedex = "C:\\Users\\DIEGO CURRÁS\\Desktop\\DAM2\\Acceso_Datos\\Trimestre_1\\AD_HIbernate2-3\\src\\main\\java\\pokedex.ser";
        FileService.serializarPokedex(rutaSerialPokedex, pokedexToSerialize);

        System.out.println("Transformar Adestradores a XML");
        String rutaXML = "C:\\Users\\DIEGO CURRÁS\\Desktop\\DAM2\\Acceso_Datos\\Trimestre_1\\AD_HIbernate2-3\\src\\main\\java\\adestradores.xml";
        FileService.transformarXMLAdestradores(adestradorList, rutaXML);

        System.out.println("Modificando las 2 entradas de Pokedex y Adestradores");
        pokedexToSerialize.get(0).setNome("Swampert-Mega");
        pokedexToSerialize.get(1).setNome("Garchomp-Z");
        PokedexService.updatePokedex(pokedexToSerialize.get(0));
        PokedexService.updatePokedex(pokedexToSerialize.get(1));

        adestradorList.get(0).setNome("Deiko120");
        adestradorList.get(1).setNome("Fos2511");
        AdestradorService.updateAdestrador(adestradorList.get(0));
        AdestradorService.updateAdestrador(adestradorList.get(1));

        System.out.println("Modificando 4 pokemon");
        pokemonList.get(0).setNome("Swampert-Mega");
        pokemonList.get(1).setNome("Garchomp-Z");
        pokemonList.get(2).setNome("Gardevoir-Mega");
        pokemonList.get(3).setNome("Aggron-Mega");
        PokemonServices.actualizarPokemon(pokemonList.get(0));
        PokemonServices.actualizarPokemon(pokemonList.get(1));
        PokemonServices.actualizarPokemon(pokemonList.get(2));
        PokemonServices.actualizarPokemon(pokemonList.get(3));

        System.out.println("Listar TODO");
        ArrayList<Pokedex> pokedexTodo = PokedexService.readPokedex();
        ArrayList<Adestrador> adestradorTodo = AdestradorService.readAdestrador();
        ArrayList<Pokemon> pokemonTodo = PokemonServices.listarPokes();

        for (Pokedex dex :pokedexTodo) {
            System.out.println(dex);
        }
        for (Adestrador a:adestradorTodo) {
            System.out.println(a);
        }
        for (Pokemon pokemon : pokemonTodo) {
            System.out.println(pokemon);
        }

        System.out.println("Desserializar 2 entradas de Pokedex para que quede como antes de modificar");
        System.out.println("Modificar las 2 entradas serializadas de pokedex");
        ArrayList<Pokedex> pokedexDesSerial = FileService.desSerializarPokedex(rutaSerialPokedex);

        PokedexService.updatePokedex(pokedexDesSerial.get(0));
        PokedexService.updatePokedex(pokedexDesSerial.get(1));

        System.out.println("Importar XML de Adestradores para que quede como antes de modificar");
        ArrayList<Adestrador> adestradoresDesSerial = FileService.desSerializarXMLAdestradores(rutaXML);
        for (Adestrador adestrador : adestradoresDesSerial) {
            AdestradorService.updateAdestrador(adestrador);
        }

        System.out.println("Listar TODO");
        ArrayList<Pokedex> pokedexFinal = PokedexService.readPokedex();
        ArrayList<Adestrador> adestradorFinal = AdestradorService.readAdestrador();
        ArrayList<Pokemon> pokemonFinal = PokemonServices.listarPokes();

        for (Pokedex dex :pokedexFinal) {
            System.out.println(dex);
        }
        for (Adestrador a:adestradorFinal) {
            System.out.println(a);
        }
        for (Pokemon pokemon : pokemonFinal) {
            System.out.println(pokemon);
        }

        System.out.println("ELIMINAR TODO");
        for (Pokemon pokemon : pokemonFinal) {
            PokemonServices.eliminarPokemon(pokemon.getId());
        }

        for (Pokedex pokemon : pokedexFinal) {
            PokedexService.deletePokedex(pokemon.getId());
        }

        for (Adestrador adestrador : adestradorFinal) {
            AdestradorService.deleteAdestrador(adestrador.getId());
        }
    }
}
