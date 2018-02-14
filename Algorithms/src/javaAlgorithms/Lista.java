/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

public class Lista {

    public NodoLista inicio;
    public NodoLista fin;
    public int tamanio;

    //Constructor
    public void Lista() {
        this.inicio = null;
        this.fin = null;
    }
    //Inicio
    public void setInicio(NodoLista i) {
        inicio = i;
    }

    public NodoLista getInicio() {
        return inicio;
    }

    //Fin
    public void setFin(NodoLista f) {
        fin = f;
    }

    public NodoLista getFin() {
        return fin;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    /**
     * ***Métodos Básicos****
     */
    //PRE:
    //POS: Retorna true si la lista no tiene nodos
    public boolean esVacia() {
        return this.tamanio == 0;
    }

    //PRE: 
    //POS: Agrega un nuevo NodoLista al principio de la lista
    public void agregarInicio(Object o) {
        NodoLista nuevo = new NodoLista(o);
        nuevo.setSiguiente(inicio);
        this.inicio = nuevo;
        this.tamanio++;
        if (this.fin == null)//estoy insertando el primer nodo
        {
            this.fin = nuevo;
        }
    }

    //PRE:
    //POS: Agrega un nuevo NodoLista al final de la lista
    public void agregarFinal(Object o) {
        //NodoLista nuevo= new NodoListaAdy(n);
        if (this.esVacia()) {
            this.agregarInicio(o);
        } else {
            NodoLista aux = this.inicio;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            NodoLista nuevo = new NodoLista(o);
            aux.setSiguiente(nuevo);
            this.fin = nuevo;
            this.tamanio++;
        }
    }
}
