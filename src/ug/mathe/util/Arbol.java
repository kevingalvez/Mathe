package ug.mathe.util;

import android.util.Log;

public class Arbol {

    /* Atributos */
    private Nodo raiz;
    private boolean insert;
    private int[] direc = new int[255];
    private int idx = -1;
    /* Contructories */    
    
    public Arbol( String valor ) {
        this.raiz = new Nodo( valor );
        veri_simbol(valor);
    }

    public Arbol( Nodo raiz ) {
        this.raiz = raiz;
        veri_simbol(raiz.getValor());
    }
    
    private void veri_simbol(String carac) {
        if (isBinary(carac)) {
        	idx++;        	
        	direc[idx] = 2;
        } else if (isUnary(carac)) {
        	idx++;        	
        	direc[idx] = 1;
        }  else {
        	idx++;        	
        	direc[idx] = 0;
        }    	
    }
    
    private boolean isBinary(String carac) {
    	boolean result = false;
    	switch (carac.charAt(0)) {
    		case '+':case '-':case '*': case '/': case '^': result = true; break;
    	}
    	return result;
    }
    
    private boolean isUnary(String carac) {
    	boolean result = false;
    	switch (carac.charAt(0)) {
    		case '~':case 'c':case 's':case 't': case 'l':case 'e':case 'q': result = true; break;
    	}
    	return result;
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
    
    private void addNodo2( Nodo nodo, Nodo raiz, int pointer ) {
    	if (direc[pointer] == 2) {
    		if (idx == pointer) {
    			raiz.setHojaDerecha(nodo);
    			insert = true;
    			veri_simbol(nodo.getValor());
    		} else {
    			addNodo2(nodo,raiz.getHojaDerecha(),pointer+1);
    			if (!insert) {
    				idx--;
    				direc[idx] = direc[idx] - 1;
    	    		if (idx == pointer) {
    	    			raiz.setHojaIzquierda(nodo);
    	    			insert = true;
    	    			veri_simbol(nodo.getValor());
    	    		}    				
    			}
    		}
    	} else if (direc[pointer] == 1 && (isBinary(raiz.getValor()))) {
    		if (idx == pointer) {
    			raiz.setHojaIzquierda(nodo);
    			insert = true;
    			veri_simbol(nodo.getValor());
    		} else {    		
	    		addNodo2(nodo,raiz.getHojaIzquierda(),pointer+1);
				if (!insert) {
					idx--;
					direc[idx] = direc[idx] - 1;   				
				}    	
    		}
    	} else if (direc[pointer] == 1 && (isUnary(raiz.getValor()))) {
    		if (idx == pointer) {
    			raiz.setHojaDerecha(nodo);
    			veri_simbol(nodo.getValor());
    			insert = true;
    		} else {		
	    		addNodo2(nodo,raiz.getHojaDerecha(),pointer+1);
				if (!insert) {
					idx--;					
					direc[idx] = direc[idx] - 1;   				
				}
    		}
    	}
    }    
    
    public void addNodo( Nodo nodo ) {
    	insert = false;
        this.addNodo2( nodo , this.raiz , 0);
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
