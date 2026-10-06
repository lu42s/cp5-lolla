package br.fiap.cp1.lollapalooza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "palco")
public class Palco{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name= "nome_palco")
    private String nome;

    @Column(name = "headliner")
    private String headliner;

    @Column(name = "capacidade")
    private Integer capacidade;

    @Column(name = "localizacao")
    private String localizacao;

    @Column(name = "genero_musical")
    private String generoMusical;

    public Palco() {
    }

    public Palco(Long id, String nome, String headliner,Integer capacidade, String localizacao, String generoMusical) {
        this.id = id;
        this.nome = nome;
        this.headliner = headliner;
        this.capacidade = capacidade;
        this.localizacao = localizacao;
        this.generoMusical = generoMusical;
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

    public String getHeadliner() {
        return headliner;
    }

    public void setHeadliner(String headliner) {
        this.headliner = headliner;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }
}
