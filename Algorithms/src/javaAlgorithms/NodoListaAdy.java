/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

public class NodoListaAdy {

    private int destino;
    private int peso;
    private NodoListaAdy siguiente;

    NodoListaAdy(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int p) {
        this.peso = p;
    }

    public int getDestino() {
        return destino;
    }

    public void setDestino(int dest) {
        this.destino = dest;
    }

    public NodoListaAdy getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoListaAdy siguiente) {
        this.siguiente = siguiente;
    }

    public NodoListaAdy(int dest, int p, NodoListaAdy s) {
        this.destino = dest;
        this.peso = p;
        this.siguiente = s;
    }

    public NodoListaAdy(int dest, int p) {
        this.destino = dest;
        this.peso = p;
        this.siguiente = null;
    }

    public NodoListaAdy() {
        this.siguiente = null;
    }
}
