package com.example.anjoslogin.Modelo;

public class Bairro {
    private String _id;
    private String bairro;

    public Bairro(){
    }


    @Override
    public String toString() {  return  bairro;}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
}
