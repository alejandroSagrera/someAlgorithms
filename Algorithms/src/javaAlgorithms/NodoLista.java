/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

/**
 *
 * @author Alejandro Sagrera
 */
public class NodoLista {

    public Object o;
    public NodoLista siguiente;

    public NodoLista(Object o) {
        this.o = o;
        this.siguiente = null;
    }

    public NodoLista() {
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }

}
