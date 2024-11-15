package com.project.demo;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "item_type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Livro.class, name = "LIVRO"),
        @JsonSubTypes.Type(value = Filme.class, name = "FILME"),
        @JsonSubTypes.Type(value = Serie.class, name = "SERIE") // Corrigido para Série
})
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
