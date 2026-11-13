package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "adestrador")
public class Adestrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false, length = 255)
    private String nome;
    @OneToMany(mappedBy = "adestrador",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Pokemon> pokemons; //<------SETTER COMPLEXO PARA CONSEGUIR A ASOCIACIÓN DOS POKEMON

    public Adestrador(Long id, String nome, List<Pokemon> pokemons) {
        this.id = id;
        this.nome = nome;
        this.pokemons = pokemons;
    }

    public Adestrador(String nome, List<Pokemon> pokemons) {
        this.nome = nome;
        this.pokemons = pokemons;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;

        if (pokemons != null) {
            for (Pokemon pokemon : pokemons) {
                pokemon.setAdestrador(this);
            }
        }
    }

    public Adestrador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}