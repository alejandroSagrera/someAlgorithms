/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javaAlgorithms.HashObjeto.TipoPunto;

public class GrafoL {

    private int size;
    private int largoV;
    private int tope;
    private ListaAdy[] listaAdyacencia;
    private boolean[] nodosUsados;

    public GrafoL(int n, int cant) {
        this.size = 0;
        this.largoV = n;
        this.tope = cant;
        this.listaAdyacencia = new ListaAdy[this.largoV];
        for (int i = 0; i <= this.largoV - 1; i++) {
            this.listaAdyacencia[i] = new ListaAdy();
        }

        this.nodosUsados = new boolean[this.largoV];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLargoV() {
        return largoV;
    }

    public void setLargoV(int largoV) {
        this.largoV = largoV;
    }

    public int getTope() {
        return tope;
    }

    public void setTope(int tope) {
        this.tope = tope;
    }

    public ListaAdy[] getListaAdyacencia() {
        return listaAdyacencia;
    }

    public void setListaAdyacencia(ListaAdy[] listaAdyacencia) {
        this.listaAdyacencia = listaAdyacencia;
    }

    public boolean[] getNodosUsados() {
        return nodosUsados;
    }

    public void setNodosUsados(boolean[] nodosUsados) {
        this.nodosUsados = nodosUsados;
    }

    public boolean agregarArista(int origen, int destino, int peso) {
        this.listaAdyacencia[origen].agregarOrd(destino, peso);
        return true;
    }

    public void agregarVertice(int v) {
        this.nodosUsados[v] = true;
        this.size++;
    }

    public void eliminarArista(int origen, int destino) {
        this.listaAdyacencia[origen].eliminar(destino);
    }

    public boolean esVacio() {
        return this.size == 0;
    }

    public boolean sonAdyacentes(int a, int b) {
        NodoListaAdy l = this.listaAdyacencia[a].inicio;
        return this.listaAdyacencia[a].pertenece(l, b);
    }

    public void eliminarVertice(int v) {
        this.nodosUsados[v] = false;
        this.size--;

        //Elimino las aristas donde v es miembro
        this.listaAdyacencia[v] = new ListaAdy();
        //BUSCAR EN TODOS LOS VERTICES LA ARISTA
        for (int i = 1; i <= largoV; i++) {
            this.listaAdyacencia[i].eliminar(v);
        }
    }

    public ListaAdy verticesAdyacentes(int v) {
        return this.listaAdyacencia[v];
    }

    public boolean estaVertice(int v) {
        return this.nodosUsados[v];
    }

    public Lista BFS(int nodoI) {
        Queue<Integer> q = new LinkedList<>();
        Lista l = new Lista();
        boolean[] visitados = new boolean[size];
        visitados[nodoI] = true;
        l.agregarFinal(nodoI);
        q.add(nodoI);
        while (!q.isEmpty()) {
            int x = q.remove();
            for (int i = 0; i < this.largoV; i++) {
                if (this.sonAdyacentes(x, i) && !visitados[i]) {
                    q.add(i);
                    l.agregarFinal(BFS(i));
                    visitados[i] = true;
                }
            }
        }
        return l;
    }

    public Lista DFS(int nodoI) {
        Lista l = new Lista();
        Stack p = new Stack();
        boolean[] visitados = new boolean[size];
        visitados[nodoI] = true;
        p.push(nodoI);
        l.agregarFinal(nodoI);
        while (!p.isEmpty()) {
            int x = (int) p.pop();
            int siguienteNodo = obtengoNodosAdyacentesNoVisitados(visitados, x);
            if (siguienteNodo == -1) {
                p.pop();
            } else {
                visitados[siguienteNodo] = true;
                p.push(siguienteNodo);
                l.agregarFinal(siguienteNodo);
            }
        }
        return l;
    }

    private int obtengoNodosAdyacentesNoVisitados(boolean[] visitados, int x) {
        for (int j = 0; j < this.largoV; j++) {
            if (this.sonAdyacentes(x, j) && !visitados[j]) {
                return j;
            }
        }
        return -1;
    }

    public String buscoRutaCp(int o, Hash h) {
        boolean[] visitados = new boolean[this.getLargoV()];
        int IndiceMin = 0;
        String salida = "";
        int[] dist = new int[this.largoV];
        int[] ant = new int[this.getLargoV()];
        int[] losQueSirven = new int[this.getLargoV()];
        caminoMinimo(o, visitados, dist, ant);
        HashObjeto elTampo = h.getV()[o];
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] != Integer.MAX_VALUE) {
                if (h.getV()[i].getCapacidad() >= elTampo.getCapacidad() /*&& h.getV()[i].getTipOb().equals(TipoPunto.CENTRO_PASTEURIZADO)*/) {
                    losQueSirven[i] = dist[i];
                }
            }
        }

        salida += elTampo.getCoordX() + ";" + elTampo.getCoordY() + "|";

        for (int k = 0; k < losQueSirven.length; k++) {
            IndiceMin = k;
            int j = k;
            while (j <= losQueSirven.length - 1) {
                if (j != o && losQueSirven[j] != 0) {
                    if (dist[j] < dist[IndiceMin]) {
                        IndiceMin = j;
                    }
                }
                j++;
            }
            if (ant[IndiceMin] != 0 && !this.sonAdyacentes(o, IndiceMin)) {
                salida += (" " + h.getV()[ant[IndiceMin]].getCoordX() + ";" + h.getV()[ant[IndiceMin]].getCoordY());
                salida += ("|");
            }

            int anterior = h.getV()[ant[IndiceMin]].getIndice();
            if (ant[anterior] != 0) {
                salida += h.getV()[anterior].getCoordX() + ";" + h.getV()[anterior].getCoordX() + "|";
            } else {
                k = losQueSirven.length;
            }

        }
        salida += (" " + h.getV()[IndiceMin].getCoordX() + ";" + h.getV()[IndiceMin].getCoordY());
        h.getV()[IndiceMin].setCapacidadRemanenete(h.getV()[IndiceMin].getCapacidad()-elTampo.getCapacidad());
        return salida;
    }

    private void caminoMinimo(int o, boolean[] visitados, int[] dist, int[] ant) {
        int inf = Integer.MAX_VALUE;
        int candidato = 0;
        visitados[o] = true;
        dist[o] = 0;
        for (int j = 0; j < dist.length; j++) {
            if (o != j && this.sonAdyacentes(o, j)) {
                dist[j] = this.listaAdyacencia[o].obtengoPeso(j);
                ant[j] = o;
            } else {
                dist[j] = inf;
            }
        }
        for (int k = 0; k < this.largoV - 2; k++) {
            boolean sirve = false;
            for (int l = 0; l < this.largoV; l++) {
                if (!visitados[l] && !sirve || dist[candidato] > dist[l]) {
                    candidato = l;
                    sirve = true;
                }
            }
        }
        visitados[candidato] = true;
        for (int m = 0; m < this.largoV; m++) {
            if (m != o) {
                if (this.sonAdyacentes(candidato, m)
                        && dist[candidato] + this.listaAdyacencia[candidato].obtengoPeso(m) < dist[m]) {
                    dist[m] = dist[candidato] + this.listaAdyacencia[candidato].obtengoPeso(m);
                    ant[m] = candidato;
                }
            }
        }
    }
    public String tambosEnCiudad(int o, int m, Hash h) {
        boolean[] visitados = new boolean[this.largoV];
        int[] dist = new int[this.largoV];
        int[] ant = new int[this.largoV];
        String salida = "";
        caminoMinimo(o, visitados, dist, ant);
        HashObjeto elTampo = h.getV()[o];
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] <= 20) {
                if (ant[i] != 0) {
                    salida += (" " + h.getV()[ant[i]].getCoordX() + ";" + h.getV()[ant[i]].getCoordY());
                }
                salida += ("|");
                salida += (" " + h.getV()[i].getCoordX() + ";" + h.getV()[i].getCoordY());
            }
        }

        return salida;
    }
    public Lista devuelvoPuntos(Hash h){
        Lista ret = new Lista();
        int largo = h.getV().length-1;
        for(int i=0;i<largo;i++){
            if(h.getV()!=null){
                ret.agregarFinal(h.getV()[i]);
            }
        }
        return ret;
    }


}
