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
public class Tambo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema sis = new Sistema();
        sis.inicializarSistema(10);
        sis.registrarProductor("..702.510-0", "", "", "o@g.com", "098206900");

        sis.registrarProductor("4.024.615-9", "Alejandro", "Su casa",
                "alejandro.sagrera@gmail.com", "099367319");

        sis.registrarCiudad("Montevideo", -34.903083, -56.169994);
        sis.registrarCiudad("Pando", -34.913675, -56.179840);
        sis.registrarCiudad("Las Piedras", -34.910040, -56.199673);

        sis.registrarTambo("Colonia", -34.897914, -56.194170, "4.024.615-9", 145);
        sis.registrarTambo("Juan Lacaze", -34.884354, -56.144428, "4.024.615-9", 105);
        sis.registrarTambo("Vergara", -34.904514, -56.125694, "4.024.615-9", 185);

        sis.registrarCentro("Rivera", -34.910854, -56.130590, 185);
        sis.registrarCentro("Maldonado", -34.892942, -56.156982, 265);
        sis.registrarCentro("Melo", -34.849478, -56.139381, 150);


        sis.registrarTramo(-34.897914, -56.194170, -34.910854, -56.130590, 50);
        sis.registrarTramo(-34.910854, -56.130590, -34.849478, -56.139381, 35);
        sis.registrarTramo(-34.897914, -56.194170, -34.892942, -56.156982, 21);
        
     

        sis.rutaACentroMasCercano(-34.897914, -56.194170);

    }

}
