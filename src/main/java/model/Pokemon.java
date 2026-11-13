package model;

import javax.persistence.*;

@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "adestrador",nullable = true) //<----Indico que a clave foránea está aquí definida pero non vou facer un service para esta entidade
    private Adestrador adestrador;

    @ManyToOne
    @JoinColumn(name="pokedexentry")
    private Pokedex pokedex;

    public Adestrador getAdestrador() {
        return adestrador;
    }

    public void setAdestrador(Adestrador adestrador) {
        this.adestrador = adestrador;
    }

    public Pokemon(String nome, Adestrador adestrador, Pokedex pokedex) {
        this.nome = nome;
        this.adestrador = adestrador;
        this.pokedex = pokedex;
    }

    public Pokemon(Long id, String nome, Adestrador adestrador, Pokedex pokedex) {
        this.id = id;
        this.nome = nome;
        this.adestrador = adestrador;
        this.pokedex = pokedex;
    }

    public Pokemon() {
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

    public Pokedex getPokedex() {
        return pokedex;
    }

    public void setPokedex(Pokedex pokedex) {
        this.pokedex = pokedex;
    }
}
