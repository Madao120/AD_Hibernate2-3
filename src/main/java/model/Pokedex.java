package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pokedex")
public class Pokedex implements Serializable {

    public Pokedex(){};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "peso", precision = 10, scale = 2)
    private Double peso;

    @Column(name = "misc", columnDefinition = "TEXT")
    private String misc;

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

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public Pokedex(Long id, String nome, Double peso, String misc) {
        this.id = id;
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    public Pokedex(String nome, Double peso, String misc) {
        this.nome = nome;
        this.peso = peso;
        this.misc = misc;
    }

    @Override
    public String toString() {
        return "Pokedex{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", peso=" + peso +
                ", misc='" + misc + '\'' +
                '}';
    }
}