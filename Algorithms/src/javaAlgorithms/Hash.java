/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

public class Hash {

    private HashObjeto[] v;
    private int tamanio;

    public HashObjeto[] getV() {
        return v;
    }

    public void setV(HashObjeto[] v) {
        this.v = v;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public Hash(int t) {
        int t2 = calculoPrimo(t * 2);
        v = new HashObjeto[t2];
        tamanio = t2;
    }

    public static int calculoPrimo(int t) {
        if (esPrimo(t)) {
            return t;
        } else {
            return calculoPrimo(t + 1);
        }
    }

    public static boolean esPrimo(int numero) {
        int contador = 2;
        boolean primo = true;
        while ((primo) && (contador != numero)) {
            if (numero % contador == 0) {
                primo = false;
            }
            contador++;
        }
        return primo;
    }

    public int calcular(Double x, Double y) {
        int a = (int) Math.round(x);
        int b = (int) Math.round(y);
        int c = (Math.abs(a) + Math.abs(b));
        return c;
    }

    public int insertar(String nom, Double x, Double y, int e, int capacidad) {
        int n = colision(x, y);
        HashObjeto o = new HashObjeto(nom, x, y, capacidad);
        o.setIndice(n);
        switch (e) {
            case 0:
                o.setTipoOb(HashObjeto.TipoPunto.CIUDAD);
                break;
            case 1:
                o.setTipoOb(HashObjeto.TipoPunto.TAMBO);
                break;
            case 2:
                o.setTipoOb(HashObjeto.TipoPunto.CENTRO_PASTEURIZADO);
                break;
        }
        v[n] = o;
        return n;
    }

    public void eliminarObjeto(Double x, Double y) {
        int objeto = this.perteneceYesEl(x, y);
        HashObjeto o = v[objeto];
        HashObjeto a = new HashObjeto();
        o = a;
        v[objeto] = o;
    }

    public int colision(Double x, Double y) {
        int cal = calcularCoord(x, y);
        int i = 1;
        while (existeEsquina(cal) && i <= tamanio) {
            cal = (cal + i) % tamanio;
            i++;
        }
        return cal;
    }

    public int calcularCoord(Double x, Double y) {
        int calc = calcular(x, y);
        return calc = (calc < 0) ? calc * (-1) : calc % tamanio;
    }

    public HashObjeto esquinaLibre(Double x, Double y) {
        boolean flagWhile = false;
        int calc = this.calcularCoord(x, y);
        int i = 1;
        HashObjeto o = v[calc];
        HashObjeto retorno = null;
        if (o != null) {
            if (o.getCoordX().equals(x) && o.getCoordY().equals(y)) {
                retorno = o;
            } else {
                while (flagWhile == false) {
                    calc = (calc + i) % tamanio;
                    o = v[calc];
                    if (o.getCoordX().equals(x) && o.getCoordY().equals(y)) {
                        flagWhile = true;
                        retorno = o;
                    } else {
                        i++;
                    }
                }
            }
        }
        return retorno;
    }

    public int perteneceYesEl(Double x, Double y) {
        boolean flagWhile = false;
        int calc = this.calcularCoord(x, y);
        int i = 1;
        int indice = -1;
        HashObjeto o = v[calc];
        if (o != null) {
            if (o.getCoordX().equals(x) && o.getCoordY().equals(y)) {
                indice = calc;
            } else {
                while (flagWhile == false && i <= tamanio) {
                    calc = ((calc + i) % tamanio);
                    o = v[calc];
                    if (o != null) {
                        if (o.getCoordX().equals(x) && o.getCoordY().equals(y)) {
                            flagWhile = true;
                            indice = calc;
                        } else {
                            i++;
                        }
                    } else {
                        indice = -1;
                        flagWhile = true;
                    }
                }
            }
        }
        return indice;
    }

    public boolean mismaEsquina(Double x, Double y) {
        boolean flagWhile = false;
        int a = 0;
        int i = 0;
        int cal = calcularCoord(x, y);
        while (!flagWhile && i <= tamanio) {
            HashObjeto o = v[cal];
            if (o == null) {
                return false;
            } else if (o.getCoordY() == null && o.getCoordX() == null) {
                flagWhile = true;
                a = 1;
            } else {
                if (o.getCoordX().equals(x) && o.getCoordY().equals(y)) {
                    flagWhile = true;
                    //break;
                } else {
                    cal = ((cal + i) % tamanio);
                }
                i++;
            }

        }
        if (a == 0) {
            return flagWhile;
        } else {
            return false;
        }
    }

    public HashObjeto devuelvoPunto(Double x, Double y) {
        boolean flagWhile = false;
        int a = 0;
        int i = 0;
        int cal = calcularCoord(x, y);
        HashObjeto o = v[cal];
        while (!flagWhile && i <= tamanio) {
            if (o.getCoordX().equals(x) && o.getCoordY().equals(y)) {
                flagWhile = true;
            } else {
                cal = ((cal + i) % tamanio);
                o = v[cal];
            }
            i++;
        }
        return o;
    }

    public boolean existeEsquina(int indice) {
        return v[indice] != null;
    }

}
