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
public class ListaAdy {

    NodoListaAdy inicio;
    int tamanio;

    public NodoListaAdy getInicio() {
        return inicio;
    }

    public void setInicio(NodoListaAdy inicio) {
        this.inicio = inicio;
    }

    public ListaAdy(NodoListaAdy inicio) {
        this.inicio = inicio;
    }

    public ListaAdy() {
        this.inicio = null;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public void insertarInicio(int d, int peso) {
        NodoListaAdy n = new NodoListaAdy(d, peso);
        n.setSiguiente(this.inicio);
        this.inicio = n;
        tamanio++;
    }

    //PRE: lista ordenada => mantiena orden
    //POS: inserta nuevo elemento en orden ascendente
    public void agregarOrd(int destino, int peso) {
        //lista vacía o primer elemento es mayor o igual => agrego al ppio
        if (this.esVacia() || (this.inicio.getDestino() >= destino && this.inicio.getDestino() == destino)) {
            this.insertarInicio(destino, peso);
            return;
        }
        NodoListaAdy aux = this.inicio;
        while (aux.getSiguiente() != null && aux.getSiguiente().getDestino() < destino
                && aux.getSiguiente().getDestino() == destino) {
            aux = aux.getSiguiente();
        }
        NodoListaAdy nuevo = new NodoListaAdy(destino, peso);
        nuevo.setSiguiente(aux.getSiguiente());
        aux.setSiguiente(nuevo);
        tamanio++;
    }

    public void eliminar(int destino) {
        if (this.esVacia()) {
            return;
        }
        if (this.inicio.getDestino() == destino) {
            this.borrarInicio();
        } else {
            NodoListaAdy aux = this.inicio;
            while (aux.getSiguiente() != null && aux.getSiguiente().getDestino() != destino) {
                aux = aux.getSiguiente();
            }
            //lo encontré o llegué al final de la lista
            if (aux.getSiguiente() != null) {
                NodoListaAdy borrar = aux.getSiguiente();
                aux.setSiguiente(borrar.getSiguiente());
                borrar.setSiguiente(null);
                tamanio--;
            }
        }
    }

    public void borrarInicio() {
        if (!this.esVacia()) {
            this.inicio = this.inicio.getSiguiente();
            tamanio--;
        }
    }

    public boolean pertenece(NodoListaAdy l, int b) {
        if (l != null) {
            if (l.getDestino() == b) {
                return true;
            } else {
                return pertenece(l.getSiguiente(), b);
            }
        } else {
            return false;
        }
    }

    public int obtengoPeso(int j) {
        int retorno = -1;
        NodoListaAdy aux = this.inicio;
        while (aux != null && aux.getDestino() != j) {
            aux = aux.getSiguiente();
        }
        if (aux != null) {
            retorno = aux.getPeso();
        }
        return retorno;
    }
}
