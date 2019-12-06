package com.example.anjoslogin.Modelo;

import java.io.Serializable;

public class Anjo implements Serializable {
    private static final long serialVersioUID = 1L;
    private String _id;
    private String nome;
    private String cpf;
    private String celular;
    private String token;


    public Anjo() {
    }
    @Override
    public String toString() {
        return nome;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
