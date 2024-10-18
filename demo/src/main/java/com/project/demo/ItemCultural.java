package com.project.demo;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class ItemCultural {

    @Id @GeneratedValue
    private long id;

    private String nome;
    private int anoLancamento;
    private String genero;

    public ItemCultural() {
        super();
    }

    public ItemCultural(String nome, int anoLancamento, String genero) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.genero = genero;
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
}
