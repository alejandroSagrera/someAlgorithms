package javaAlgorithms;

import java.lang.Math;

public class ABB {

    NodoAB raiz;

    public ABB() {
        this.raiz = null;
    }

    public void agregarProductor(Productor dato) {
        NodoAB nuevo = new NodoAB(dato, null, null);
        agregarProductor(nuevo, raiz);
    }

    private void agregarProductor(NodoAB n, NodoAB m) {
        if (esVacio()) {
            raiz = n;
        } else if (((Productor) n.getO()).getCedula().compareToIgnoreCase(((Productor) m.getO()).getCedula()) < 0) {
            if (m.getIzq() == null) {
                m.setIzq(n);
            } else {
                agregarProductor(n, m.getIzq());
            }
        } else if (m.getDer() == null) {
            m.setDer(n);
        } else {
            agregarProductor(n, m.getDer());
        }
    }

    public NodoAB buscarProductor(String mat) {
        return buscarProductor(mat, raiz);
    }

    private NodoAB buscarProductor(String mat, NodoAB n) {
        if (((Productor) n.getO()).getCedula().compareToIgnoreCase(mat) == 0) {
            return n;
        } else if (((Productor) n.getO()).getCedula().compareToIgnoreCase(mat) < 0) {
            return buscarProductor(mat, n.getDer());
        } else {
            return buscarProductor(mat, n.getIzq());
        }
    }

    public boolean existeProductor(String ced) {
        if (!esVacio()) {
            ABB aux = new ABB();
            if (((Productor) this.raiz.getO()).getCedula().compareToIgnoreCase(ced) == 0) {
                return true;
            }
            if (((Productor) this.raiz.getO()).getCedula().compareToIgnoreCase(ced) > 0) {
                aux.raiz = this.raiz.getIzq();
            }
            if (((Productor) this.raiz.getO()).getCedula().compareToIgnoreCase(ced) < 0) {
                aux.raiz = this.raiz.getDer();
            }
            return aux.existeProductor(ced);
        }
        return false;
    }

    public void eliminar(String mat) {
        raiz = eliminar(mat, raiz);
    }

    private NodoAB eliminar(String mat, NodoAB n) {
        if (n == null) {
            return n;
        }
        if (((Productor) n.getO()).getCedula().compareToIgnoreCase(mat) > 0) {
            n.izq = eliminar(mat, n.getIzq());
        } else if (((Productor) n.getO()).getCedula().compareToIgnoreCase(mat) < 0) {
            n.der = eliminar(mat, n.getDer());
        } else if (n.izq != null && n.der != null) {
            n.o = Minimo(n.der).getO();
            n.der = eliminarNodMin(n.der);
        } else {
            n = (n.izq != null) ? n.izq : n.der;
        }
        return n;
    }

    private NodoAB eliminarNodMin(NodoAB n) {
        if (n == null) {
            return n;
        }
        if (n.getIzq() != null) {
            n.izq = eliminarNodMin(n.getIzq());
            return n;
        } else {
            return n.der;
        }
    }

    private NodoAB Minimo(NodoAB n) {
        if (n != null) {
            while (n.izq != null) {
                n = n.izq;
            }
        }
        return n;
    }

    private NodoAB Maximo(NodoAB n) {
        if (n != null) {
            while (n.der != null) {
                n = n.der;
            }
        }
        return n;
    }

    public void preOrder() {
        preOrder(raiz);
    }

    private void preOrder(NodoAB n) {
        if (n != null) {
            System.out.print(((Productor) n.getO()).toString());
            System.out.println();
            preOrder(n.getIzq());
            preOrder(n.getDer());
        }
    }

    public void inOrder(Retorno ret) {
        if (raiz != null) {
            inOrder(raiz, ret);
        } else {
            System.out.print(" ");
        }
    }

    private void inOrder(NodoAB n, Retorno ret) {
        if (n != null) {
            inOrder(n.getIzq(), ret);
            ret.valorString += ((Productor) n.getO()).toString();
            ret.valorString += "|";
            inOrder(n.getDer(), ret);
        }

    }

    public Lista insertarArbolEnLista() {
        Lista l = new Lista();
        insertarArbolEnLista(raiz, l);
        return l;
    }

    private void insertarArbolEnLista(NodoAB n, Lista l) {
        if (n != null) {
            insertarArbolEnLista(n.getIzq(), l);
            l.agregarFinal(n.getO());
            insertarArbolEnLista(n.getDer(), l);
        }
    }

    public void postOrder() {
        postOrder(raiz);
    }

    private void postOrder(NodoAB n) {
        if (n != null) {
            postOrder(n.getIzq());
            postOrder(n.getDer());
            System.out.print(((Productor) n.getO()).toString());
            System.out.println();
        }
    }

    public int cantNodos() {
        return cantNodosAux(raiz);
    }

    private int cantNodosAux(NodoAB n) {
        if (n != null) {
            return 1 + (cantNodosAux(n.getIzq()) + cantNodosAux(n.getDer()));
        }
        return 0;
    }

    public int altura() {
        return alturaAux(raiz);
    }

    private int alturaAux(NodoAB raiz) {
        if (raiz == null) {
            return -1;
        } else if (esHoja(raiz)) {
            return 0;
        } else {
            return Math.max(alturaAux(raiz.getIzq()), alturaAux(raiz.getDer())) + 1;
        }

    }

    private boolean esHoja(NodoAB raiz) {
        return raiz.getIzq() == null && raiz.getDer() == null;
    }

    public int cantHojas() {
        return cantNodosHojas(raiz);
    }

    private int cantNodosHojas(NodoAB n) {
        if (n == null) {
            return 0;
        } else if (n.getIzq() == null && n.getDer() == null) {
            return 1;
        } else {
            return cantNodosHojas(n.getIzq()) + cantNodosHojas(n.getDer());
        }
    }

    public ABB espejo(ABB a) {
        ABB b = new ABB();
        if (!a.esVacio()) {
            b.raiz = espejo(a.raiz);
        } else {
            return b;
        }
        return b;

    }

    private NodoAB espejo(NodoAB c) {
        if (c == null) {
            return null;
        } else {
            NodoAB nuevo = new NodoAB(c.o);
            nuevo.izq = espejo(c.der);
            nuevo.der = espejo(c.izq);
            return nuevo;
        }
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

}
