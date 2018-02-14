/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

public class HashObjeto {

    private int indice;
    private String nombre;
    private Double coordX;
    private Double coordY;
    private int capacidad;
    private int capacidadRemanenete;

    enum TipoPunto {
        CIUDAD, TAMBO, CENTRO_PASTEURIZADO
    };
    private TipoPunto tipoOb;

    public String getNombre() {
        return nombre;
    }

    public int getCapacidadRemanenete() {
        return capacidadRemanenete;
    }

    public void setCapacidadRemanenete(int capacidadRemanenete) {
        this.capacidadRemanenete = capacidadRemanenete;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public Double getCoordX() {
        return coordX;
    }

    public void setCoordX(Double coordX) {
        this.coordX = coordX;
    }

    public Double getCoordY() {
        return coordY;
    }

    public void setCoordY(Double coordY) {
        this.coordY = coordY;
    }

    public TipoPunto getTipOb() {
        return tipoOb;
    }

    public void setTipoOb(TipoPunto tipo) {
        this.tipoOb = tipo;
    }

    public HashObjeto(String nom,Double coordX, Double coordY,int cap) {
        this.nombre = nom;
        this.coordX = coordX;
        this.coordY = coordY;
        this.capacidad = cap;
    }

    public HashObjeto() {
    }

    public HashObjeto(Double coordX, Double coordY, TipoPunto e) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.tipoOb = e;
    }
}
