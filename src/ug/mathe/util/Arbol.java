package ug.mathe.util;

import android.util.Log;

public class Arbol {

    /* Atributos */
    private Nodo raiz;

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

    private void addNodo( Nodo nodo, Nodo raiz ) {
        /* 2.- Partiendo de la raíz preguntamos: Nodo == null ( o no existe ) ? */
        if ( raiz == null ) {
            /* 
             * 3.- En caso afirmativo X pasa a ocupar el lugar del nodo y ya 
             * hemos ingresado nuestro primer dato. 
             * ==== EDITO =====
             * Muchas gracias a @Espectro por la corrección de esta línea
             */
            this.setRaiz(nodo);
        }
        else {
        /* 4.- En caso negativo preguntamos: X < Nodo */
            /* 
             * 5.- En caso de ser menor pasamos al Nodo de la IZQUIERDA del
             * que acabamos de preguntar y repetimos desde el paso 2 
             * partiendo del Nodo al que acabamos de visitar 
             */
            if (raiz.getHojaIzquierda() == null) {
                raiz.setHojaIzquierda(nodo);
            }
            else if (raiz.getHojaDerecha() == null) {
            	raiz.setHojaDerecha(nodo);
            } else {
            	addNodo( nodo , raiz.getHojaIzquierda() );
            }
        }
    }

    public void addNodo( Nodo nodo ) {
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
