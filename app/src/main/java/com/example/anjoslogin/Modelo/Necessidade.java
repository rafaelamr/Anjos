package com.example.anjoslogin.Modelo;

import java.io.Serializable;

public class Necessidade implements Serializable {
    private static final long serialVersioUID = 1L;
    private String _id;
    private String necessidade;
    public Necessidade(){
    }

    @Override
    public String toString() {  return  necessidade;}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNecessidade() {
        return necessidade;
    }

    public void setNecessidade(String necessidade) {
        this.necessidade = necessidade;
    }
}
