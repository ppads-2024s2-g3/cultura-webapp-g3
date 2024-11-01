package com.project.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int nota; // Nota de 0 a 10
    private String comentario;

    @ManyToOne
    private Geek geek;

    @ManyToOne
    private ItemCultural itemCultural; // Supondo que você tenha uma classe ItemCultural

    // Construtores, getters e setters
    public Avaliacao() {}

    public Avaliacao(int nota, String comentario, Geek geek, ItemCultural itemCultural) {
        this.nota = nota;
        this.comentario = comentario;
        this.geek = geek;
        this.itemCultural = itemCultural;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Geek getGeek() {
        return geek;
    }

    public void setGeek(Geek geek) {
        this.geek = geek;
    }

    public ItemCultural getItemCultural() {
        return itemCultural;
    }

    public void setItemCultural(ItemCultural itemCultural) {
        this.itemCultural = itemCultural;
    }
}
