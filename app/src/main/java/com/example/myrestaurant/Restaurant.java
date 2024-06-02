package com.example.myrestaurant;

public class Restaurant {

    String nombre;
    String tipo_via, calle, numero, cp;
    CategoriaRestaurant categoriaRestaurant;
    String horario;

    String tlfno;

    public Restaurant(String nombre, String tipo_via, String calle, String numero, String cp, CategoriaRestaurant categoriaRestaurant, String horario, String tlfno) {
        this.nombre = nombre;
        this.tipo_via = tipo_via;
        this.calle = calle;
        this.numero = numero;
        this.cp = cp;
        this.categoriaRestaurant = categoriaRestaurant;
        this.horario = horario;
        this.tlfno = tlfno;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_via() {
        return tipo_via;
    }

    public void setTipo_via(String tipo_via) {
        this.tipo_via = tipo_via;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public CategoriaRestaurant getCategoriaRestaurant() {
        return categoriaRestaurant;
    }

    public void setCategoriaRestaurant(CategoriaRestaurant categoriaRestaurant) {
        this.categoriaRestaurant = categoriaRestaurant;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTlfno() {
        return tlfno;
    }

    public void setTlfno(String tlfno) {
        this.tlfno = tlfno;
    }
}
