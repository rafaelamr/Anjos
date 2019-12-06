package com.example.anjoslogin.Modelo;

import java.io.Serializable;

public class Doacao implements Serializable {
    private static final long serialVersioUID = 1L;
    private String _id;
    private Familia familia;
    private Anjo anjo;
    private String doacao;

    public Doacao (){

    }

    @Override
    public String toString() {
        return "O anjo " + anjo+ " efetuou uma doação:" + '\n' +
//                "Família: " + familia + '\n' +
                "Doação:" + doacao;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Familia getFamilia() {
        return familia;
    }

    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public Anjo getAnjo() {
        return anjo;
    }

    public void setAnjo(Anjo anjo) {
        this.anjo = anjo;
    }

    public String getDoacao() {
        return doacao;
    }

    public void setDoacao(String doacao) {
        this.doacao = doacao;
    }
}
