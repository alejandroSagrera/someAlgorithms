/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaAlgorithms;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro Sagrera
 */
public class Sistema implements ISistema {

    private ABB productor = new ABB();
    private Hash h;
    private GrafoL g;
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PATTERN_PHONE = "^09\\d{7}$";
    private static final String PATTERN_CI = "^[0-9]([.][0-9]{3}){2}[-][0-9]$";

    public Sistema() {
    }

    @Override
    public Retorno inicializarSistema(int cantPuntos) {
        Retorno ret = new Retorno();
        h = new Hash(cantPuntos);
        g = new GrafoL(h.getTamanio(), cantPuntos);
        ret.resultado = TipoError.OK;
        return ret;
    }

    @Override
    public Retorno registrarProductor(String cedula, String nombre, String direccion, String email, String celular) {
        Retorno ret = new Retorno();
        String Cedtrim = cedula.trim();
        if (esCIValida(Cedtrim)) {
            Pattern pattern = Pattern.compile(PATTERN_PHONE);
            Matcher matcher = pattern.matcher(celular);
            if (matcher.matches()) {
                Pattern pattern2 = Pattern.compile(PATTERN_EMAIL);
                Matcher matcher2 = pattern2.matcher(email);
                if (matcher2.matches()) {
                    if (!productor.existeProductor(cedula)) {
                        productor.agregarProductor(new Productor(cedula, nombre, direccion, email, celular));
                        ret.resultado = TipoError.OK;
                    } else {
                        ret.resultado = TipoError.ERROR_4;
                    }

                } else {
                    ret.resultado = TipoError.ERROR_3;
                }
            } else {
                ret.resultado = TipoError.ERROR_2;
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    public static boolean esCIValida(String cedula) {
        Pattern pattern = Pattern.compile(PATTERN_CI);
        Matcher matcher = pattern.matcher(cedula);
        if (matcher.matches()) {
            String ci = formateoCi(cedula);
            if (ci.length() != 7 && ci.length() != 8) {
                return false;
            } else {
                try {
                    Integer.parseInt(ci);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            int digVerificador = Integer.parseInt((ci.charAt(ci.length() - 1)) + "");
            int[] factores;
            if (ci.length() == 7) { // CI viejas 
                factores = new int[]{9, 8, 7, 6, 3, 4};
            } else {
                factores = new int[]{2, 9, 8, 7, 6, 3, 4};
            }
            int suma = 0;
            for (int i = 0; i < ci.length() - 1; i++) {
                int digito = Integer.parseInt(ci.charAt(i) + "");
                suma += digito * factores[i];
            }
            int resto = suma % 10;
            int checkdigit = 10 - resto;

            if (checkdigit == 10) {
                return (digVerificador == 0);
            } else {
                return (checkdigit == digVerificador);
            }
        } else {
            return false;
        }
    }

    public static String formateoCi(String ci) {
        String cedula = "";
        int pos = 0;
        for (int j = 0; j <= ci.length() - 1; j++) {
            if (ci.charAt(j) == '-') {
                pos = j + 1;
            }
            if (ci.charAt(j) != '.' && ci.charAt(j) != '-') {
                cedula += ci.charAt(j);
            }
        }
        return cedula;
    }

    @Override
    public Retorno registrarCiudad(String nombre, Double coordX, Double coordY) {
        Retorno ret = new Retorno();
        if (g.getSize() < g.getTope()) {
            if (!existeEsquina(coordX, coordY)) {
                int n = h.insertar(nombre, coordX, coordY, 0, 0); //0=ciudad,0=capacidad(no aplica para ciudad)
                g.agregarVertice(n);
                ret.resultado = TipoError.OK;
            } else {
                ret.resultado = TipoError.ERROR_2;
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    public boolean existeEsquina(Double x, Double y) {
        return h.mismaEsquina(x, y);
    }

    @Override
    public Retorno registrarTambo(String nombre, Double coordX, Double coordY, String cedula_productor, int capacidad) {
        Retorno ret = new Retorno();
        if (g.getSize() < g.getTope()) {
            if (capacidad > 0) {
                if (!existeEsquina(coordX, coordY)) {
                    if (productor.existeProductor(cedula_productor)) {
                        int n = h.insertar(nombre, coordX, coordY, 1, capacidad); //1=tambo
                        g.agregarVertice(n);
                        ret.resultado = TipoError.OK;
                    } else {
                        ret.resultado = TipoError.ERROR_4;
                    }
                } else {
                    ret.resultado = TipoError.ERROR_3;
                }
            } else {
                ret.resultado = TipoError.ERROR_2;
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    @Override
    public Retorno registrarCentro(String nombre, Double coordX, Double coordY, int capacidad) {
        Retorno ret = new Retorno();
        if (g.getSize() < g.getTope()) {
            if (capacidad > 0) {
                if (!existeEsquina(coordX, coordY)) {
                    int n = h.insertar(nombre, coordX, coordY, 2, capacidad); //2=centroPasteurización
                    g.agregarVertice(n);
                    ret.resultado = TipoError.OK;
                } else {
                    ret.resultado = TipoError.ERROR_3;
                }
            } else {
                ret.resultado = TipoError.ERROR_2;
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    @Override
    public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int peso) {
        Retorno ret = new Retorno();
        if (peso > 0) {
            if (existeEsquina(coordXi, coordYi) && existeEsquina(coordXf, coordYf)) {
                int esquina1Grafo = h.perteneceYesEl(coordXi, coordYi);
                int esquina2Grafo = h.perteneceYesEl(coordXf, coordYf);
                if ((esquina1Grafo >= 0) && (esquina2Grafo >= 0)) {
                    if (!g.sonAdyacentes(esquina1Grafo, esquina2Grafo)) {
                        if ((g.agregarArista(esquina1Grafo, esquina2Grafo, peso))
                                && (g.agregarArista(esquina2Grafo, esquina1Grafo, peso))) {
                            ret.resultado = TipoError.OK;
                        }
                    } else {
                        ret.resultado = TipoError.ERROR_3;
                    }
                }
            } else {
                ret.resultado = TipoError.ERROR_2;
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    @Override
    public Retorno eliminarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf) {
        Retorno ret = new Retorno();
        if (existeEsquina(coordXi, coordYi) && existeEsquina(coordXf, coordYf)) {
            int esquina1Grafo = h.perteneceYesEl(coordXi, coordYi);
            int esquina2Grafo = h.perteneceYesEl(coordXf, coordYf);
            if ((esquina1Grafo >= 0) && (esquina2Grafo >= 0)) {
                if (g.sonAdyacentes(esquina1Grafo, esquina2Grafo)) {
                    g.eliminarArista(esquina1Grafo, esquina2Grafo);
                    g.eliminarArista(esquina2Grafo, esquina1Grafo);
                    ret.resultado = TipoError.OK;

                } else {
                    ret.resultado = TipoError.ERROR_2;
                }
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }

        return ret;
    }

    @Override
    public Retorno eliminarPunto(Double coordX, Double coordY) {
        Retorno ret = new Retorno();
        if (h.mismaEsquina(coordX, coordY)) {
            HashObjeto o = h.esquinaLibre(coordX, coordY);
            if (o != null) {
                int esquinaGrafo = h.perteneceYesEl(coordX, coordY);
                g.eliminarVertice(esquinaGrafo);
                h.eliminarObjeto(coordX, coordY);
                ret.resultado = TipoError.OK;
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    @Override
    public Retorno mapaEstado() {
        Retorno ret = new Retorno();

        int i = 0;
        String datos = "";
        String url_mapa = "";
        HashObjeto o[] = h.getV();
        int a = 0;

        while (i < o.length) {
            if (o[i] != null) {
                a++;
                if (o[i].getTipOb().equals(HashObjeto.TipoPunto.CIUDAD)) {
                    datos += "&markers=color:red%7Clabel:" + a + "%7C" + o[i].getCoordX() + "," + o[i].getCoordY();
                }
                if (o[i].getTipOb().equals(HashObjeto.TipoPunto.TAMBO)) {
                    datos += "&markers=color:yellow%7Clabel:" + a + "%7C" + o[i].getCoordX() + "," + o[i].getCoordY();
                }
                if (o[i].getTipOb().equals(HashObjeto.TipoPunto.CENTRO_PASTEURIZADO)) {
                    datos += "&markers=color:green%7Clabel:" + a + "%7C" + o[i].getCoordX() + "," + o[i].getCoordY();
                }
            }
            i++;
        }
        url_mapa = "http://maps.googleapis.com/maps/api/staticmap?center=Montevideo,Uruguay&zoom=12&size=1500x950&maptype=roadmap" + datos + "&sensor=false";
        try {
            Desktop.getDesktop().browse(new URI(url_mapa));
        } catch (IOException | URISyntaxException ex) {
            Logger.getLogger(Sistema.class.getName()).log(Level.SEVERE, null, ex);
        }
        ret.resultado = TipoError.OK;
        return ret;
    }

    @Override
    public Retorno rutaACentroMasCercano(Double coordX, Double coordY) {
        Retorno ret = new Retorno();
        if (h.mismaEsquina(coordX, coordY)) {
            HashObjeto o = h.devuelvoPunto(coordX, coordY);
            if ((o != null) && (o.getTipOb().equals(HashObjeto.TipoPunto.TAMBO))) {
                ret.valorString = g.buscoRutaCp(o.getIndice(), h);
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    @Override
    public Retorno listadoDeTambosEnCiudad(Double coordX, Double coordY) {
        Retorno ret = new Retorno();
        if (h.mismaEsquina(coordX, coordY)) {
            HashObjeto o = h.esquinaLibre(coordX, coordY);
            if (o != null) {
                ret.valorString = g.tambosEnCiudad(o.getIndice(), 20, h);
                ret.resultado = TipoError.OK;
            }
        } else {
            ret.resultado = TipoError.ERROR_1;
        }
        return ret;
    }

    @Override
    public Retorno listadoProductores() {
        Retorno ret = new Retorno();
        ret.valorString = "";
        Lista l = new Lista();
        l = productor.insertarArbolEnLista();
        if (l.tamanio > 0) {
            NodoLista aux = l.inicio;
            while (aux != null) {
                if (aux.getSiguiente() != null) {
                    ret.valorString += ((Productor) aux.o).getCedula() + ";" + ((Productor) aux.o).getNombre() + ";"
                            + ((Productor) aux.o).getCelular() + "|";
                } else {
                    ret.valorString += ((Productor) aux.o).getCedula() + ";" + ((Productor) aux.o).getNombre() + ";"
                            + ((Productor) aux.o).getCelular();
                }
                aux = aux.getSiguiente();
            }
        } else {
            ret.valorString = "NO HAY PRODUCTORES REGISTRADOS.";
        }
        ret.resultado = (!ret.valorString.isEmpty()) ? TipoError.OK : TipoError.NO_IMPLEMENTADA;
        return ret;
    }

	@Override
	public Retorno destruirSistema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retorno listadoDeCentros() {
		// TODO Auto-generated method stub
		return null;
	}

    /*@Override
    public Retorno listadoDeCentros() {
        Retorno ret = new Retorno();
        Lista puntos = g.devuelvoPuntos(h);
        String salida = "";
        if(puntos.getTamanio()>0){
            NodoLista aux = puntos.getInicio();
            while(aux!=null){
                if(((HashObjeto)aux.getO()).TipoPunto.equals(TipoPunto.CENTRO_PASTEURIZADO)){
                     if(aux.getSiguiente()!=null){
                        salida+= ((HashObjeto)aux.getCoordX()) + ";" + ((HashObjeto)aux.getCoordY()) + ";" + ((HashObjeto)aux.getCapacidad()) + ";"
                         + ((HashObjeto)aux.getCapacidadRemanenete()) + "|";
                      }else{
                          salida+= ((HashObjeto)aux.getCoordX()) + ";" + ((HashObjeto)aux.getCoordY()) + ";" + ((HashObjeto)aux.getCapacidad()) + ";"
                         + ((HashObjeto)aux.getCapacidadRemanenete());
                      }
                }
                aux = aux.getSiguiente();
            }
        }
        ret.valorString=salida;
        return ret;
    }

    @Override
    public Retorno destruirSistema() {
        Retorno ret = new Retorno();
        productor = null;
        h = null;
        g = null;
        ret.resultado = TipoError.OK;
        return ret;
    }*/
}
