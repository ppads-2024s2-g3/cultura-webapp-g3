package com.project.demo;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;



@Entity
@EqualsAndHashCode(callSuper = true)
@DiscriminatorValue("desenhoAnimado")
public class DesenhoAnimado extends ItemCultural {

    @Id @GeneratedValue
    private long id;

    private String nome;
    private String criador;
    private int anoLancamento;
    private String genero;
    private int numeroTemporadas;
    private int numeroEpisodios;

    public DesenhoAnimado() {
        super();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCriador() {
        return criador;
    }

    public void setCriador(String criador) {
        this.criador = criador;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getNumeroTemporadas() {
        return numeroTemporadas;
    }

    public void setNumeroTemporadas(int numeroTemporadas) {
        this.numeroTemporadas = numeroTemporadas;
    }

    public int getNumeroEpisodios() {
        return numeroEpisodios;
    }

    public void setNumeroEpisodios(int numeroEpisodios) {
        this.numeroEpisodios = numeroEpisodios;
    }
}
