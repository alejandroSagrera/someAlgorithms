package javaAlgorithms;

public class NodoAB {

	Object o;
	NodoAB izq;
	NodoAB der;


    public NodoAB() {
        this.izq = null;
        this.der = null;
    }
    
    	public NodoAB(Object o1) {
            this.o=o1;
            this.izq = null;
            this.der = null;
	}
        public NodoAB(Object o1, NodoAB izq, NodoAB der) {
            this.o=o1;
            this.izq = izq;
            this.der = der;
	}

	public NodoAB getIzq(){
		return this.izq;
	}
	
	public NodoAB getDer(){
		return this.der;
	}

	public Object getO(){
		return this.o;
	}
	
	public void setDato(Object o1){
		this.o = o1;
	}
	
	public void setDer(NodoAB der){
		this.der = der;
	}
	
	public void setIzq(NodoAB izq){
		this.izq = izq;
	}


	
}
