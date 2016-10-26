package br.com.android.saniel.cadastrodeusuario.Entidades;

import java.io.Serializable;

/**
 * Created by saniel on 03/10/16.
 */
public class Contatos implements Serializable { //Serializable - permitem alteração com numeros binários.
    private Long id;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private double nota;

    public String toString(){
        return getId() + " - " + getNome();
    }


    //Métodos gets e sets

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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
