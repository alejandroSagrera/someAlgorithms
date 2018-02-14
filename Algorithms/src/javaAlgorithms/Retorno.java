/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

import javaAlgorithms.ISistema.TipoError;

public class Retorno {

    public int valorEntero;
    public String valorString;
    public TipoError resultado;

    public Retorno(int valorEntero, String valorString, TipoError resultado) {
        this.valorEntero = valorEntero;
        this.valorString = valorString;
        this.resultado = resultado;
    }

    public Retorno() {
    }

    public int getValorEntero() {
        return valorEntero;
    }

    public void setValorEntero(int valorEntero) {
        this.valorEntero = valorEntero;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }

    public TipoError getResultado() {
        return resultado;
    }

    public void setResultado(TipoError resultado) {
        this.resultado = resultado;
    }

}
