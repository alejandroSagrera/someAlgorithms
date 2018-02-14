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
public interface ISistema {

    public enum TipoError {
        OK, ERROR_1, ERROR_2, ERROR_3, ERROR_4, ERROR_5, NO_IMPLEMENTADA
    }

    Retorno inicializarSistema(int cantPuntos);

    Retorno destruirSistema();

    Retorno registrarProductor(String cedula, String nombre, String direccion, String email, String celular);

    Retorno registrarCiudad(String nombre, Double coordX, Double coordY);

    Retorno registrarTambo(String nombre, Double coordX, Double coordY, String cedula_productor, int capacidad);

    Retorno registrarCentro(String nombre, Double coordX, Double coordY, int capacidad);

    Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso);

    Retorno eliminarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf);

    Retorno eliminarPunto(Double coordX, Double coordY);

    Retorno mapaEstado();

    Retorno rutaACentroMasCercano(Double coordX, Double coordY);

    Retorno listadoDeTambosEnCiudad(Double coordX, Double coordY);

    Retorno listadoProductores();

    Retorno listadoDeCentros();
}
