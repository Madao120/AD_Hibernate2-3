
import model.Adestrador;
import model.Pokemon;
import model.Pokedex;

import services.AdestradorService;
import services.PokemonService;
import services.PokedexService;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        AdestradorService as = new AdestradorService();
        PokemonService ps = new PokemonService();
        PokedexService pds = new PokedexService();

        //Creamos unha lista pra Misty
        List<Pokemon> pokemonsMisty = new ArrayList<>();
        Pokemon p1 = new Pokemon();
        p1.setNome("Starmie");
        Pokemon p2 = new Pokemon();
        p2.setNome("Psyduck");
        Pokemon p3 = new Pokemon();
        p3.setNome("Goldeen");
        Pokemon p4 = new Pokemon();
        p4.setNome("Staryu");

        pokemonsMisty.add(p1);
        pokemonsMisty.add(p2);
        pokemonsMisty.add(p3);
        pokemonsMisty.add(p4);


        //Creamos adestradores

        System.out.println("-------------ADESTRADORES-------------");
        Adestrador ash = new Adestrador("Ash", null);
        Adestrador misty = new Adestrador("Misty", pokemonsMisty);

        as.crearAdestrador(ash);
        as.crearAdestrador(misty);

        //Creamos as entradas da pokedex

        System.out.println("-------------POKEDEX-------------");
        Pokedex pikachu = new Pokedex("Pikachu", 6.0, "Pokemon electrico");
        Pokedex squirtle = new Pokedex("Squirtle", 8.0, "Pokemon acuático");
        pds.crearPokedex(pikachu);
        pds.crearPokedex(squirtle);


        // Creamos pokemons para as entradas da pokedex collendo datos de db

        System.out.println("-------------POKEMONS-------------");

        Adestrador ashDB = as.lerAdestradorPorNome("Ash");
        Pokedex entradaPikachu = pds.lerPokedexPorNome("Pikachu");
        Pokemon pika = new Pokemon();
        pika.setNome("Pikachu");
        ps.crearPokemon(pika, ashDB.getId(), entradaPikachu.getId());
        Pokemon escuero = new Pokemon();
        escuero.setNome("Squirtle");
        escuero.setAdestrador(misty);
        escuero.setPokedex(squirtle);
        ps.crearPokemonConTodo(escuero);


        //Listas
        System.out.println("-------------LISTADOS-------------");


        System.out.println("-------------ADESTRADORES-------------");

        List<Adestrador> adestradorList = as.listarAdestradores();

        if (adestradorList != null) {
            for (Adestrador a : adestradorList) {
                System.out.println("ID: " + a.getId());
                System.out.println("Nome: " + a.getNome());
                System.out.println("--------------------");
            }
        }


        System.out.println("-------------POKEDEX-------------");

        List<Pokedex> pokedexList = pds.listarPokedex();

        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Peso: " + p.getPeso());
                System.out.println("Misc: " + p.getMisc());
                System.out.println("--------------------");
            }
        }


        System.out.println("-------------POKEMONS-------------");

        List<Pokemon> pokemonList = ps.listarPokemons();

        if (pokemonList != null) {
            for (Pokemon p : pokemonList) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());

                if (p.getAdestrador() != null) {
                    System.out.println("Adestrador: "
                            + p.getAdestrador().getNome());
                }

                if (p.getPokedex() != null) {
                    System.out.println("Entrada Pokedex: "
                            + p.getPokedex().getNome());
                }

                System.out.println("--------------------");
            }
        }


        //LECTURAS

        System.out.println("-------------POKEMON-------------");

        Pokemon pokemon = ps.lerPokemon(1L);

        if (pokemon != null) {
            System.out.println("Pokemon atopado: " + pokemon.getNome());
        }


        //UPDATE

        System.out.println("-------------PIKACHU EVOLUCIONA-------------");

        ps.actualizarPokemon(
                pika.getId(),
                "Raichu",
                pika.getAdestrador().getId(),
                pika.getPokedex().getId()
        );


        System.out.println("-------------POKEDEX POR ID (Non ten sentido pero vale de exemplo)-------------");

        Pokedex raichu = new Pokedex("Raichu", 30.0, "Evolucion de Pikachu");

        pds.actualizarPokedex(
                1L,
                raichu
        );


        System.out.println("-------------Listo Adestradores-------------");

        adestradorList = as.listarAdestradores();

        if (adestradorList != null) {
            for (Adestrador a : adestradorList) {
                System.out.println("ID: " + a.getId());
                System.out.println("Nome: " + a.getNome());
                System.out.println("--------------------");
                System.out.println("Pokemon fav: " + a.getPokemons().get(0).getNome());

            }
        }


        System.out.println("-------------Listo Pokedex-------------");

        pokedexList = pds.listarPokedex();

        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());
                System.out.println("Peso: " + p.getPeso());
                System.out.println("Misc: " + p.getMisc());
                System.out.println("--------------------");
            }
        }


        System.out.println("-------------Listo Pokemon-------------");

        pokemonList = ps.listarPokemons();

        if (pokemonList != null) {
            for (Pokemon p : pokemonList) {
                System.out.println("ID: " + p.getId());
                System.out.println("Nome: " + p.getNome());

                if (p.getAdestrador() != null) {
                    System.out.println("Adestrador: "
                            + p.getAdestrador().getNome());
                }

                if (p.getPokedex() != null) {
                    System.out.println("Entrada Pokedex: "
                            + p.getPokedex().getNome());
                }

                System.out.println("--------------------");
            }
        }


        System.out.println("-------------Elimino Pokedex E TEÑO ERROS-------------");

        if (pokedexList != null) {
            for (Pokedex p : pokedexList) {
                System.out.println("ELIMINO: " + p.getNome());
                pds.eliminarPokedex(p.getId());
            }

            System.out.println("-------------Elimino Adestradores E TEÑO ERROS-------------");
            if (adestradorList != null) {
                for (Adestrador a : adestradorList) {
                    System.out.println("ELIMINO: " + a.getNome());
                    as.eliminarAdestrador(a.getId());
                }
            }
            System.out.println("-------------Elimino Pokemons (LOGO)-------------");
            if (pokemonList != null) {
                for (Pokemon p : pokemonList) {
                    System.out.println("ELIMINO: " + p.getNome());
                    ps.eliminarPokemon(p.getId());
                }
            }

            //  EXECUTADOS AQUI NON DAN ERRO
            System.out.println("-------------Elimino Pokedex -------------");
            pokedexList = pds.listarPokedex();
            if (pokedexList != null) {
                for (Pokedex p : pokedexList) {
                    System.out.println("ELIMINO: " + p.getNome());
                    pds.eliminarPokedex(p.getId());
                }
            }
            adestradorList = as.listarAdestradores();
            System.out.println("-------------Elimino Adestradores-------------");
            if (adestradorList != null) {
                for (Adestrador a : adestradorList) {
                    System.out.println("ELIMINO: " + a.getNome());
                    as.eliminarAdestrador(a.getId());
                }
            }

        }
    }
}

