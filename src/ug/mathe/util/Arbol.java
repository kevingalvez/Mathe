package ug.mathe.util;

import android.util.Log;

public class Arbol {

    /* Atributos */
    private Nodo raiz;
    private boolean insert;
    /* Contructories */    
    public Arbol( String valor ) {
        this.raiz = new Nodo( valor );
    }

    public Arbol( Nodo raiz ) {
        this.raiz = raiz;
    }

    /* Setters y Getters */
    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

	private boolean isNumeric(String cadena){
		try {
			Double.parseDouble(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	private boolean isVar(String cadena){
		if (cadena.equals("x") || cadena.equals("y"))
			return true;
		else
			return false;
	}	
    	
    private void addNodo( Nodo nodo, Nodo raiz ) {
    	if (!(isNumeric(raiz.getValor()) || isVar(raiz.getValor()))) {
	   		if (raiz.getHojaDerecha() == null && !(isNumeric(raiz.getValor()) || isVar(raiz.getValor()))) {
   				raiz.setHojaDerecha(nodo);
	   			insert = true;
	   		} else {
	    		addNodo(nodo,raiz.getHojaDerecha());
	    		if (!insert) {
	    			if (raiz.getHojaIzquierda() == null && !(isNumeric(raiz.getValor()) || isVar(raiz.getValor()))) {
    					raiz.setHojaIzquierda(nodo);
	    				insert = true;
	    			} else {
	    				addNodo(nodo,raiz.getHojaIzquierda());
	    			}
	    		}
	    			
	   		}
    	}
    }
    
    public void addNodo( Nodo nodo ) {
    	insert = false;
        this.addNodo( nodo , this.raiz );
    }
    
    public void preOrder(Nodo reco)
    {
	    if (reco != null) {
	    	Log.i("PREORDER", reco.getValor());
	    	preOrder (reco.getHojaIzquierda());
	        preOrder (reco.getHojaDerecha());
	    }
    }

    public void Order(Nodo reco)
    {
	    if (reco != null) {	    	
	    	Order (reco.getHojaIzquierda());
	    	Log.i("ORDER", reco.getValor());
	    	Order (reco.getHojaDerecha());
	    }
    }
    
    public void postOrder(Nodo reco)
    {
	    if (reco != null) {
	    	postOrder (reco.getHojaIzquierda());
	    	postOrder (reco.getHojaDerecha());
	    	Log.i("POSTORDER", reco.getValor());	    	
	    }
    }    
}
