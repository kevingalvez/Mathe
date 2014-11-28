package ug.mathe.util;

import android.util.Log;

public class Algebra {

	private Arbol ar;
	private boolean expander;
	private boolean reducir;
	private boolean reparo;
	private Stack num = new Stack(255); 
	String postoder = "";
	
	public Algebra(Arbol ar){
		this.ar = ar;
	}
	
	public Algebra(String expr) {
		InfixToPostfix a = new InfixToPostfix(expr);
		try {
			String apostfix = a.ConvertToPostfix();
			Log.i("INFIXTOPOSTFIX",apostfix);
			char car = apostfix.charAt(apostfix.length()-1);
			Arbol ar = null;
			if (car == '#'){
				ar = new Arbol(new Nodo(String.valueOf(a.getData(apostfix.length()-1))));	
			}
			else 
				ar = new Arbol(new Nodo(String.valueOf(car)));
			for (int i = apostfix.length()-2; i >= 0 ; i--) {
				if (apostfix.charAt(i) == '#')
					ar.addNodo(new Nodo(String.valueOf(a.getData(i))));
				else
					ar.addNodo(new Nodo(String.valueOf(apostfix.charAt(i))));
			}
			this.ar = ar;
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
		
	private boolean isDist(String a) {
		boolean result = false;
		if (a.equals("+") || a.equals("-"))
			result = true;
		return result;
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
		if (cadena.equals("x") || cadena.equals("y") || cadena.equals("z") || cadena.equals("w"))
			return true;
		else
			return false;
	}	
	
	private boolean isHoja(Nodo nodo) {
		if (nodo.getValor().equals("*") || nodo.getValor().equals("^"))
		{
			if (nodo.getHojaIzquierda() != null && nodo.getHojaDerecha() != null) 
			{
			String izq = nodo.getHojaIzquierda().getValor();
			String der = nodo.getHojaDerecha().getValor();
			if (isNumeric(izq) || isVar(izq)) 
				if (isNumeric(der) || isVar(der))
					return true;
			} else {
				return true;
			}
		} else if (isNumeric(nodo.getValor()) || isVar(nodo.getValor())) {
			return true;
		}
		return false;
	}

	private boolean isNegative(Nodo nodo){
		boolean result = false;
		if (nodo.getValor().equals("~")) {
			if (isNumeric(nodo.getHojaDerecha().getValor()))
				result = true;
		}
		return result;
			
	}
	
	private String getValueNegative(Nodo nodo){
		return "-"+nodo.getHojaDerecha().getValor(); 			
	}	
	
	private boolean isHojaAdicion(Nodo nodo) {
		boolean result = false;
		if (nodo.getValor().equals("+") || nodo.getValor().equals("-"))
		{
			if (nodo.getHojaIzquierda() != null && nodo.getHojaDerecha() != null) 
			{
			String izq = nodo.getHojaIzquierda().getValor();
			String der = nodo.getHojaDerecha().getValor();
			if (isNumeric(izq) || isVar(izq) || isNegative(nodo.getHojaIzquierda())) 
				if (isNumeric(der) || isVar(der))
					result = true;
			}
		}
		return result;
	}
	
	private Nodo DistderProducto(Nodo nodo1, Nodo nodo2) {
		Nodo result = null;
		if (nodo1.getValor().equals("^")) {
			String dato1 = "", dato2 = "", dato3 = "", dato4="";
			if (nodo1.getHojaIzquierda() != null  && nodo1.getHojaDerecha() != null)
			{
			dato1 = nodo1.getHojaIzquierda().getValor();
			dato2 = nodo1.getHojaDerecha().getValor();
			} else
				dato1 = nodo1.getValor();
			
			if (nodo2.getHojaIzquierda() != null && nodo2.getHojaDerecha() != null)
			{
				dato3 = nodo2.getHojaIzquierda().getValor();
				dato4 = nodo2.getHojaDerecha().getValor();
			} else
				dato3 = nodo2.getValor();
			
			if (isNumeric(dato3) || (isVar(dato3) && !dato1.equals(dato3))) {
				result = new Nodo("*");
				result.setHojaDerecha(nodo1);
				result.setHojaIzquierda(new Nodo(dato3));
			} else if (isVar(dato3) && dato1.equals(dato3)) {
				if (nodo2.getValor().equals("^")) {
					result = new Nodo("^");
					result.setHojaIzquierda(new Nodo(dato1));
					result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato2) + Double.valueOf(dato4))));
				} else {
					if (Double.valueOf(dato2+1) > 2) {
						result = new Nodo("^");
						result.setHojaIzquierda(new Nodo(dato3));
						result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato2) + 1)));
					} else {
						result = new Nodo("^");
						result.setHojaIzquierda(nodo1);
						result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato2) + 1)));
					}
				}
			}
		} else if (nodo2.getValor().equals("^")) {
			String dato1 = "", dato2 = "", dato3 = "", dato4="";
			if (nodo1.getHojaIzquierda() != null  && nodo1.getHojaDerecha() != null)
			{
			dato1 = nodo1.getHojaIzquierda().getValor();
			dato2 = nodo1.getHojaDerecha().getValor();
			} else
				dato1 = nodo1.getValor();
			
			if (nodo2.getHojaIzquierda() != null && nodo2.getHojaDerecha() != null)
			{
				dato3 = nodo2.getHojaIzquierda().getValor();
				dato4 = nodo2.getHojaDerecha().getValor();
			} else
				dato3 = nodo2.getValor();
			if (isNumeric(dato1) || (isVar(dato1) && !dato3.equals(dato1))) {
				double a = 0.0;
				if (isNumeric(dato1))
					a = Double.valueOf(dato1);
				if (a == 0) {
					result = new Nodo("0");
				} else {
					result = new Nodo("*");
					result.setHojaIzquierda(new Nodo(dato1));
					result.setHojaDerecha(evaluarHoja(nodo2));
				}
			} else if (isVar(dato1)) {
				if (nodo1.getValor().equals("^")) {
					result = new Nodo("^");
					result.setHojaIzquierda(new Nodo(dato1));
					result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato4) + Double.valueOf(dato2))));
				} else if (isVar(dato2) && (dato1.equals(dato2))) {
					result = new Nodo("^");
					result.setHojaIzquierda(new Nodo(dato1));
					result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato4) + 2)));					
				} else {
					if (Double.valueOf(dato4+1) > 2) {
						result = new Nodo("^");
						result.setHojaIzquierda(new Nodo(dato1));
						result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato4) + 1)));
					} else {
						result = new Nodo("^");
						result.setHojaIzquierda(nodo2);
						result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato4) + 1)));
					}
				}
			}			
		} else {
			String dato1 = "", dato2 = "", dato3 = "", dato4="";
			if (nodo1.getHojaIzquierda() != null  && nodo1.getHojaDerecha() != null)
			{
			dato1 = nodo1.getHojaIzquierda().getValor();
			dato2 = nodo1.getHojaDerecha().getValor();
			} else
				dato1 = nodo1.getValor();
			
			if (nodo2.getHojaIzquierda() != null && nodo2.getHojaDerecha() != null)
			{
				dato3 = nodo2.getHojaIzquierda().getValor();
				dato4 = nodo2.getHojaDerecha().getValor();
			} else
				dato3 = nodo2.getValor();
				
			Stack num = new Stack(4);
			double res = 0.0;
			int exp = 0;
			boolean exist_numero = false, exist_var = false;
			if (isNumeric(dato1)) {
				num.Push(dato1);
				exist_numero = true;
			}
			else if (!dato1.equals("")) {
				exp++;
				exist_var = true;
			}
			if (isNumeric(dato2)){
				exist_numero = true;
				num.Push(dato2);
			}
			else if (!dato2.equals("")) {
				exp++;
				exist_var = true;
			}
			if (isNumeric(dato3)) { 
				num.Push(dato3);
				exist_numero = true;
			}
			else if (!dato3.equals("")) {
				exp++;
				exist_var = true;
			}
			if (isNumeric(dato4)) { 
				num.Push(dato4);
				exist_numero = true;
			}
			else if (!dato4.equals("")) {
				exp++;
				exist_var = true;
			}
			
			if (!num.isEmpty())
				res = Double.valueOf(num.Pop().toString());
			while (!num.isEmpty()) {
				res = res*Double.valueOf(num.Pop().toString());
			}
			
			if (!exist_numero && exist_var)
				res = 1;
			
			if (!exist_var) {
				result = new Nodo(String.valueOf(res));
			}
			else
			if (exp == 1) {
				if (res != 1) {
					result = new Nodo("*");
					result.setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.setHojaDerecha(new Nodo(String.valueOf("x")));
				} else {
					result = new Nodo("x");	
				}
			} else {
				if (res != 1) {
					result = new Nodo("*");
					result.setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.setHojaDerecha(new Nodo(String.valueOf("^")));
					result.getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf("x")));
					result.getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf(String.valueOf(exp)))); 
				} else {
					result = new Nodo("^");
					result.setHojaIzquierda(new Nodo(String.valueOf("x")));
					result.setHojaDerecha(new Nodo(String.valueOf(String.valueOf(exp))));						
				}
			}
		}
		return result;
	}
	
	private Nodo Distder(Nodo nodoizq, Nodo nododer, String signo) {
		Nodo result =  new Nodo(signo);
		boolean val = true;
		if (isHoja(nodoizq))
		{
			if (isHoja(nododer.getHojaIzquierda())) {
				val = false;
				result.setHojaIzquierda(DistderProducto(nodoizq,nododer.getHojaIzquierda()));
			}
		}
		
		if (val) {
		result.setHojaIzquierda(new Nodo("*"));
		result.getHojaIzquierda().setHojaIzquierda(nodoizq);
		result.getHojaIzquierda().setHojaDerecha(nododer.getHojaIzquierda());
			}
		val = true;
		if (isHoja(nodoizq)){
			if (isHoja(nododer.getHojaDerecha())) {	
				val = false;
				result.setHojaDerecha(DistderProducto(nodoizq,nododer.getHojaDerecha()));
			} 
		}
		
		if (val){
		
		result.setHojaDerecha(new Nodo("*"));
		result.getHojaDerecha().setHojaIzquierda(nodoizq);
		result.getHojaDerecha().setHojaDerecha(nododer.getHojaDerecha());
			}
		return result;
	}
	
	private Nodo Distizq(Nodo nodoizq, Nodo nododer, String signo) {	
		Nodo result =  new Nodo(signo);
		boolean val = true;
		if (isHoja(nodoizq.getHojaIzquierda())) {
			if (isHoja(nododer)) {
				val = false;
				result.setHojaIzquierda(DistderProducto(nodoizq.getHojaIzquierda(),nododer));
			}
		}
		
		if (val){		
				result.setHojaIzquierda(new Nodo("*"));
				result.getHojaIzquierda().setHojaIzquierda(nodoizq.getHojaIzquierda());
				result.getHojaIzquierda().setHojaDerecha(nododer);		
		}
		
		val = true;
		
		if (isHoja(nodoizq.getHojaDerecha())) {
			if (isHoja(nododer)) {
				val = false;
				result.setHojaDerecha(DistderProducto(nodoizq.getHojaDerecha(),nododer));
			} 
		}
		if (val) {
				result.setHojaDerecha(new Nodo("*"));
				result.getHojaDerecha().setHojaIzquierda(nodoizq.getHojaDerecha());
				result.getHojaDerecha().setHojaDerecha(nododer);			
			}
		return result;		
	}		
		
	private Nodo Binomio(Nodo nodoizq, Nodo nododer) {
		Nodo result = null;
		if (!isVar(nodoizq.getValor()) && (isNumeric(nododer.getValor()) || isNegative(nododer))) {
			double a = 0.0;
			if (isNegative(nododer)) {
				a = Double.valueOf(getValueNegative(nododer));
			} else {
				a = Double.valueOf(nododer.getValor());				
			}

			if (a == 2 && reducir) {
				result = new Nodo("*");
				result.setHojaIzquierda(nodoizq);
				result.setHojaDerecha(nodoizq);
			} else if (a > 2 && reducir) {
				result = new Nodo("*");
				result.setHojaIzquierda(nodoizq);
				result.setHojaDerecha(new Nodo("^"));
				result.getHojaDerecha().setHojaIzquierda(nodoizq);
				result.getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf(a - 1)));
			} else if ( a == 1) {
				result = nodoizq;
			} else if( a == 0) {
				result = new Nodo("1");
			} else if (a < 0) {
				result = new Nodo("/");
				result.setHojaIzquierda(new Nodo("1"));
				result.setHojaDerecha(nodoizq);
			}
		}
		return result;
	}
	
	private Nodo Exponentes(Nodo nodoizq, Nodo nododer) {
		Nodo result = null;
		result = new Nodo(nodoizq.getValor());
		result.setHojaIzquierda(nodoizq.getHojaIzquierda());
		result.setHojaDerecha(new Nodo("*"));
		result.getHojaDerecha().setHojaIzquierda(nodoizq.getHojaDerecha());
		result.getHojaDerecha().setHojaDerecha(nododer);
		return result;
	}
	
	private Nodo Order(Nodo nodo)
	{
		Nodo n = null;
	    if (nodo != null) {
 	    	boolean expand = false;
	    	if (nodo.getValor().equals("*")) {
	    		if (isDist(String.valueOf(nodo.getHojaDerecha().getValor()))&& reducir)
				{
	    			n = Distder(nodo.getHojaIzquierda(),nodo.getHojaDerecha(),String.valueOf(nodo.getHojaDerecha().getValor()));
	    			expander = expand = true;
	    			
				} else if (isDist(String.valueOf(nodo.getHojaIzquierda().getValor())) && reducir)
				{
	    			//n = Distizq(nodo.getHojaIzquierda(),nodo.getHojaDerecha(),String.valueOf(nodo.getHojaIzquierda().getValor()));
					n = Distder(nodo.getHojaDerecha(),nodo.getHojaIzquierda(),String.valueOf(nodo.getHojaIzquierda().getValor()));
	    			expander = expand = true;
				} else if (isHoja(nodo)) {
					expand = true;
					//expander = expand = true;
					n = evaluarHoja(nodo);
				} else if (isHoja(nodo.getHojaDerecha()) && isHoja(nodo.getHojaIzquierda())){
					expand = true;
					Nodo aux = new Nodo("*");;
					aux.setHojaIzquierda(evaluarHoja(nodo.getHojaIzquierda()));
					aux.setHojaDerecha(evaluarHoja(nodo.getHojaDerecha()));
					n = DistderProducto(aux.getHojaIzquierda(),aux.getHojaDerecha());
					//n = DistderProducto(nodo.getHojaIzquierda(),nodo.getHojaDerecha());
				} else if (isNumeric(nodo.getHojaDerecha().getValor())) {
					if (Double.valueOf(nodo.getHojaDerecha().getValor()) == 1) {
						expander = expand = true;
						n = nodo.getHojaIzquierda();
					} else if (isNegative(nodo.getHojaDerecha())) {
						n = new Nodo("~");
						n.setHojaDerecha(nodo.getHojaIzquierda());
					} else if (Double.valueOf(nodo.getHojaDerecha().getValor()) == 0) {
						expander = expand = true;
						n = new Nodo("0");
					} else {
						n = new Nodo("*");
						n.setHojaDerecha(Order(nodo.getHojaIzquierda()));
						n.setHojaIzquierda(Order(nodo.getHojaDerecha()));
						expander = expand = true;
					}
				}  else if (isNumeric(nodo.getHojaIzquierda().getValor())) {
					if (Double.valueOf(nodo.getHojaIzquierda().getValor()) == 1) {
						expander = expand = true;
						n = nodo.getHojaDerecha();
					} else if (Double.valueOf(nodo.getHojaIzquierda().getValor()) == 0) {
						expander = expand = true;
						n = new Nodo("0");
					} else {
						n = evaluarNodo(nodo.getHojaIzquierda(), nodo.getHojaDerecha(), nodo.getValor());
						if (n != null)
							expander = expand = true;
					}
				} else if (isHoja(nodo.getHojaIzquierda())) {
					n = evaluarNodo(nodo.getHojaIzquierda(), nodo.getHojaDerecha(), nodo.getValor());
					if (n != null)
						expander = expand = true;
				} else if (isHoja(nodo.getHojaDerecha())) {
					n = evaluarNodo(nodo.getHojaDerecha(), nodo.getHojaIzquierda(),nodo.getValor());
					if (n != null)
						expander = expand = true;
				} 
	    	} else if (nodo.getValor().equals("^")) {
	    		if (nodo.getHojaIzquierda().getValor().equals("^") && isNumeric(nodo.getHojaDerecha().getValor())) {
	    			n = Exponentes(nodo.getHojaIzquierda(),nodo.getHojaDerecha());
	    			expander = expand = true;
	    		} else 	if (!isVar(nodo.getHojaIzquierda().getValor()) && (isNumeric(nodo.getHojaDerecha().getValor()) || isNegative(nodo.getHojaDerecha()))) {
	    			n = Binomio(nodo.getHojaIzquierda(),nodo.getHojaDerecha());
	    			if ( n != null)
	    				expander = expand = true;
		    	}  else if (isVar(nodo.getHojaIzquierda().getValor()) && (isNumeric(nodo.getHojaDerecha().getValor()) || isNegative(nodo.getHojaDerecha()))) {
		    		n = evaluarHoja(nodo);
		    		expand = true;
		    	}
	    	} else if (isHojaAdicion(nodo)) {
				expand = true;
				n = evaluarHoja(nodo);	    		
	    	} else if (nodo.getValor().equals("+") || nodo.getValor().equals("-")) {
	    		if (isNumeric(nodo.getHojaIzquierda().getValor())){
	    			double a = Double.valueOf(nodo.getHojaIzquierda().getValor());
	    			if (a == 0) {
	    				if (nodo.getValor().equals("-")) {
	    					n = new Nodo("~");
	    					n.setHojaDerecha(nodo.getHojaDerecha());
	    					expand = true;
	    				} else {
	    					n = nodo.getHojaDerecha();
	    					expand = true;
	    				}
	    			} else {
	    				n = evaluarNodo(nodo.getHojaIzquierda(), nodo.getHojaDerecha(), nodo.getValor());
	    				if (n != null)
	    					expander = expand = true;
	    			}
	    		} else if (isNumeric(nodo.getHojaDerecha().getValor())){
	    			double a = Double.valueOf(nodo.getHojaDerecha().getValor());
	    			if (a == 0) {
    					n = nodo.getHojaIzquierda();
    					expand = true;
	    			}  else {
	    				n = evaluarNodo(nodo.getHojaDerecha(), nodo.getHojaIzquierda(), nodo.getValor());
	    				if (n != null)
	    					expander = expand = true;
	    			}
	    		}
	    	}
	    	
	    	if (!expand) {
				n = new Nodo(nodo.getValor());
				n.setHojaIzquierda(Order (nodo.getHojaIzquierda()));
				n.setHojaDerecha(Order (nodo.getHojaDerecha()));
	    	}
	    }
    	return n;
	}
	
	private Nodo evaluarNodo(Nodo nodoizq, Nodo nododer, String signo) {
		Nodo result = null;
		if (nododer.getValor().equals(signo)) {
			if (isHoja(nodoizq) && isHoja(nododer.getHojaIzquierda())) {
				result = new Nodo(nododer.getValor());
				result.setHojaDerecha(nododer.getHojaDerecha());
				result.setHojaIzquierda(new Nodo(signo));
				result.getHojaIzquierda().setHojaIzquierda(nodoizq);
				result.getHojaIzquierda().setHojaDerecha(nododer.getHojaIzquierda());
			} else if (isHoja(nodoizq) && isHoja(nododer.getHojaDerecha())) {
				result = new Nodo(nododer.getValor());
				result.setHojaDerecha(nododer.getHojaIzquierda());
				if (signo.equals("-")) {
					if (nododer.equals("-"))
						result.setHojaIzquierda(new Nodo("+"));
					else
						result.setHojaIzquierda(new Nodo("-"));
				} else if (signo.equals("+")) {
					if (nododer.equals("+"))
						result.setHojaIzquierda(new Nodo("+"));
					else
						result.setHojaIzquierda(new Nodo("-"));
				} else
					result.setHojaIzquierda(new Nodo(signo));
				
				result.getHojaIzquierda().setHojaIzquierda(nodoizq);
				result.getHojaIzquierda().setHojaDerecha(nododer.getHojaDerecha());				
			}
		}
		return result;
	}
	
	
	private Nodo evaluarHoja(Nodo nodo) {
		Nodo result = null;
		if (nodo.getValor().equals("+") || nodo.getValor().equals("-")) {
			String dato1 = "", dato2 = "";
			if (isNegative(nodo.getHojaIzquierda())) {
				dato1 = getValueNegative(nodo.getHojaIzquierda());
			} else 
				dato1 = nodo.getHojaIzquierda().getValor();
			
			dato2 = nodo.getHojaDerecha().getValor();
			if (isNumeric(dato1) && isNumeric(dato2)) {
				double a1 = Double.valueOf(dato1);
				double a2 = Double.valueOf(dato2);
				double resp = 0.0;
				if (nodo.getValor().equals("+"))
					resp = a1 + a2;
				if (nodo.getValor().equals("-"))
					resp = a1 - a2;
				
				if (resp < 0) {
					result = new Nodo("~");
					result.setHojaDerecha(new Nodo(String.valueOf(Math.abs(resp))));
				} else if (resp >= 0) {
					result = new Nodo(String.valueOf(Math.abs(resp)));
				}
				 
			} else if (isVar(dato1) && isVar(dato2)) {
				String a1 = "", a2 = "";
				a1 = nodo.getHojaIzquierda().getValor();
				a2 = nodo.getHojaDerecha().getValor();
				if (a1.equals(a2)) {
					result = new Nodo("*");
					result.setHojaIzquierda(new Nodo("2"));
					result.setHojaDerecha(new Nodo(a1));
				}
			} else {
				double a1 = 0.0;
				String a2 = "";
				if (isNumeric(nodo.getHojaIzquierda().getValor())) { 
					a1 = Double.valueOf(dato1);
					a2 = dato2;
				}
				else {
					a1 = Double.valueOf(dato2);
					a2 = dato1;
				}
				
				if (a1 != 0) {
					result = new Nodo(nodo.getValor());
					result.setHojaIzquierda(nodo.getHojaIzquierda());
					result.setHojaDerecha(nodo.getHojaDerecha());
				} else {
					result = new Nodo(a2);						
				}

			}
				
		} else if (nodo.getValor().equals("*")) {
			String dato1 = "", dato2 = "";
			dato1 = nodo.getHojaIzquierda().getValor();
			dato2 = nodo.getHojaDerecha().getValor();
			Stack num = new Stack(4);
			double res = 0.0;
			int exp = 0;
			if (isNumeric(dato1)) 
				num.Push(dato1);
			else if (!dato1.equals(""))
				exp++;
			if (isNumeric(dato2)) 
				num.Push(dato2);
			else if (!dato2.equals(""))
				exp++;
			
			if (!num.isEmpty())
				res = Double.valueOf(num.Pop().toString());
			
			while (!num.isEmpty()) {
				res = res*Double.valueOf(num.Pop().toString());
			}
	
			if (isNumeric(dato1) && isNumeric(dato2))
			{
				result = new Nodo(String.valueOf(res));
			} else if (isVar(dato1) && isVar(dato2)) {
				if (exp > 1 || exp <=-1) {
					result = new Nodo("^");
					result.setHojaIzquierda(new Nodo("x"));
					result.setHojaDerecha(new Nodo(String.valueOf(exp)));
				} else if (exp == 1){
					result = new Nodo("x");			
				} else if (exp == 0) {
					result = new Nodo("1");
				}
			}
			else if (isNumeric(dato1) && isVar(dato2) || isVar(dato1) && isNumeric(dato2)) {
				if (res != 0 && res != 1){
					result = new Nodo("*");
					result.setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.setHojaDerecha(new Nodo("x"));
				} else if (res == 1) {
					result = new Nodo("x");
				} else if (res == 0) {
					result = new Nodo("0");
				}
			}
		} else if (nodo.getValor().equals("^")) {
			if (isNumeric(nodo.getHojaIzquierda().getValor()) && isNumeric(nodo.getHojaDerecha().getValor())) {
				double a = Double.valueOf(nodo.getHojaIzquierda().getValor());
				double b = Double.valueOf(nodo.getHojaDerecha().getValor());
				result = new Nodo(String.valueOf(Math.pow(a, b)));
			} else {
				double a = 0.0;
				if (isNegative(nodo.getHojaDerecha())) {
					a = Double.valueOf(getValueNegative(nodo.getHojaDerecha()));
				} else {
					a = Double.valueOf(nodo.getHojaDerecha().getValor());
				}
				if (a > 1) {
					result = new Nodo("^");
					result.setHojaIzquierda(nodo.getHojaIzquierda());
					result.setHojaDerecha(nodo.getHojaDerecha());
				} else if (a == 1) {
					result = nodo.getHojaIzquierda();
				} else if (a == 0) {
					result = new Nodo("1");
				} else if (a < 0) {
					result = new Nodo("/");
					result.setHojaIzquierda(new Nodo("1"));
					if (a == -1) {
						result.setHojaDerecha(nodo.getHojaIzquierda());
					} else if (a != -1) {
						result.setHojaDerecha(new Nodo(String.valueOf(Math.abs(a))));
					}
				}
			}
		} else {
			result = nodo;
		}
		return result;
	}
			
	private Numero dato(Nodo nodo) {
		Numero result = null;
		if (isNumeric(nodo.getValor())) {
			result = new Numero(Double.valueOf(nodo.getValor()), "", 1);
		} else if (isVar(nodo.getValor())) {
			result = new Numero(1, nodo.getValor(), 1);
		} else if (isHoja(nodo)) {
			String dato1 = "", dato2 = "";
			dato1 = nodo.getHojaIzquierda().getValor();
			dato2 = nodo.getHojaDerecha().getValor();
			if (nodo.getValor().equals("^")) {
				result = new Numero(1, dato1, Double.valueOf(dato2));
			} else {
				if (isNumeric(dato1)) {
					result = new Numero(Double.valueOf(dato1) , dato2, 1); 
				} else if (isNumeric(dato2)) {
					result = new Numero(Double.valueOf(dato2) , dato1, 1);
				}
			}
		} else if (nodo.getHojaDerecha().getValor().equals("^")) {
			String dato1 = "", dato2 = "", dato3 = "";
			dato1 = nodo.getHojaIzquierda().getValor();
			dato2 = nodo.getHojaDerecha().getHojaIzquierda().getValor();
			dato3 = nodo.getHojaDerecha().getHojaDerecha().getValor();
			result = new Numero(Double.valueOf(dato1),dato2,Double.valueOf(dato3));
		} else if (nodo.getHojaIzquierda().getValor().equals("^")) {
			String dato1 = "", dato2 = "", dato3 = "";
			dato1 = nodo.getHojaDerecha().getValor();
			dato2 = nodo.getHojaIzquierda().getHojaIzquierda().getValor();
			dato3 = nodo.getHojaIzquierda().getHojaDerecha().getValor();
			result = new Numero(Double.valueOf(dato1),dato2,Double.valueOf(dato3));
		}
		return result;
	}
	
	private void Simplificar(Nodo nodo, boolean minus) {
	    if (nodo != null) {
	    	boolean val = true;
	    	if ((nodo.getValor().equals("-") || nodo.getValor().equals("+")) && isHoja(nodo.getHojaDerecha())) {
	    		Numero aux = dato(nodo.getHojaDerecha()); 
	    		if (nodo.getValor().equals("-")) {
	    				aux.setCof(!minus?aux.getCof()*-1:aux.getCof());
	    		} else if (minus){
	    			aux.setCof(aux.getCof()*-1);
	    		}
	    		num.Push(aux);
	    		if (isNumeric(nodo.getHojaIzquierda().getValor())||isVar(nodo.getHojaIzquierda().getValor())) {
		    		Numero aux2 = dato(nodo.getHojaIzquierda()); 
		    		if (minus){
		    			aux2.setCof(aux2.getCof()*-1);	
		    		}
		    		num.Push(aux2);
		    		val = false;
	    		}
	    	} else if (isNumeric(nodo.getValor()) || isVar(nodo.getValor())) {
	    		Numero aux = dato(nodo); 
	    		if (minus){
	    			aux.setCof(aux.getCof()*-1);
	    		}
	    		num.Push(aux);
	    	} else if (nodo.getValor().equals("~")) {
	    		Simplificar(nodo.getHojaDerecha(),!minus);
	    	}
	    	else if (nodo.getValor().equals("*")) { 
	    		Numero aux = dato(nodo); 
	    		if (minus) {
	    			aux.setCof(aux.getCof()*-1);
	    			//aux.setCof(aux.getCof()*-1);
	    		}
	    		num.Push(aux);
	    		val = false;
	    	} else if (nodo.getValor().equals("^")) { 
	    		Numero aux = dato(nodo);
	    		if (minus) 
	    			aux.setCof(aux.getCof()*-1);	    		
	    		num.Push(aux);
	    		val = false;
	    	} else if (!(isNumeric(nodo.getValor())||isVar(nodo.getValor()))) {
	    			Simplificar(nodo.getHojaDerecha(), nodo.getValor().equals("-")?!minus:minus);
	    	}
	    	
	    	if (val)
	    		Simplificar(nodo.getHojaIzquierda(),minus);
	    }		
	}
	
	
	private String SolvePolinomio(double a, double b, double c, double d) {
		String result = "";
		double x1 = 0.0, x2 = 0.0, x3 = 0.0;
		if (a != 0) {
			b = b/a;
			c = c/a;
			d = d/a;
			double q = (3*c-Math.pow(b, 2))/9;
			double r = (9*b*c-27*d-2*Math.pow(b, 3))/54;
			double s1 = Math.pow(r+Math.pow(Math.pow(q, 3)+Math.pow(r, 2), 1/2), 1/3);
			double s2 = Math.pow(r-Math.pow(Math.pow(q, 3)+Math.pow(r, 2), 1/2), 1/3);
			x1=s1+s2-b/3;
		} else if (b != 0) {
			x1 = (-c+Math.sqrt(Math.pow(c, 2)-4*b*d))/(2*b);
			x2 = (-c-Math.sqrt(Math.pow(c, 2)-4*b*d))/(2*b);
			result = "X1= " + x1 + "; X2= " + x2;
		} else if (b == 0 && c != 0) {
			x1 = -d/c;
			result = "X= " + x1;
		} else if (b == 0 && c == 0) {
			if (d == 0)
				result = "Verdadero";
			else
				result = "False";
		}
		return result;
	}
	
	private String evaluarNum() {
		Numero[] arr = new Numero[255];
		int len_arr = -1;
		double grado = 0;
		while (!num.isEmpty()) {
			Numero aux = (Numero)num.Pop();
			if (len_arr > -1) {
				boolean suma = false;
				for (int i = 0; i <= len_arr; i++) {
					if (aux.getVar().equals(arr[i].getVar()) && aux.getExp() == arr[i].getExp()) {
						arr[i].setCof(arr[i].getCof()+aux.getCof());
						suma = true;
						if (aux.getVar().equals("x"))
							if (grado < aux.getExp())
								grado = aux.getExp();						
					}
				}
				if (!suma) {
					len_arr++;
					arr[len_arr] = new Numero(aux);
					if (aux.getVar().equals("x"))
						if (grado < aux.getExp())
							grado = aux.getExp();
				}
			} else {
				len_arr++;
				arr[len_arr] = new Numero(aux);
				grado = aux.getVar().equals("x")?aux.getExp():grado;
			}
		}
		
		String result = "";
		boolean primero = true;
		double a = 0.0, b = 0.0, c = 0.0, d = 0;
		for (int i=0; i <= len_arr;i++) {
			if (arr[i].getVar().equals("x") && arr[i].getExp() == 3 )
				a = arr[i].getCof();
			if (arr[i].getVar().equals("x") && arr[i].getExp() == 2 )
				b = arr[i].getCof();
			if (arr[i].getVar().equals("x") && arr[i].getExp() == 1 )
				c = arr[i].getCof();
			if (arr[i].getVar().equals("") && arr[i].getExp() == 1 )
				d = arr[i].getCof();
			
			if (arr[i].getCof() != 0)
				if (primero) {
					if (arr[i].getVar().equals("")) {
						result += arr[i].getCof();
					} else {
						String expo = "";
						String coef = "";
						if (arr[i].getExp() > 1) {
							expo = "^"+arr[i].getExp();
						}
						if (arr[i].getCof() != 1 && arr[i].getCof() != -1) 
							coef = arr[i].getCof()+"";
						else if (arr[i].getCof() == -1)
							coef = "-";
						 if (arr[i].getCof() == 1)
								coef = "";						
						result += coef+arr[i].getVar()+expo;
					}
					primero = false;
				} else {
					String signo = ""; 
					String coef = "";
					if (arr[i].getCof()>0)
						signo = "+";
					
					if (arr[i].getVar().equals("")) {
						result += signo+arr[i].getCof();
					} else {
						String expo = "";
						if (arr[i].getExp() > 1) {
							expo = "^"+arr[i].getExp();
						}
						if (arr[i].getCof() != 1 && arr[i].getCof() != -1) 
							coef = arr[i].getCof()+"";
						else if (arr[i].getCof() == -1)
							coef = "-";
						else if (arr[i].getCof() == 1)
							coef = "";						
						result += signo+coef+arr[i].getVar()+expo;
					}
				}
					
		}
		if (grado >= 0 && grado <= 2)
			result = SolvePolinomio(a, b, c, d);
		return result;
		/*String result = "";
		for (int i=0; i <= len_arr;i++) {
			if (i == 0) {
				if (arr[i].getCof() != 0) {
					if (arr[i].getExp() == 1)
						result += String.valueOf(arr[i].getCof()) + arr[i].getVar();
					else 
						result += String.valueOf(arr[i].getCof())+ arr[i].getVar() + "^" + String.valueOf(arr[i].getExp());
				}
			} else {
				if (arr[i].getCof() != 0) {
					if (arr[i].getExp() == 1)
						result += arr[i].getCof()>0?"+":"" + String.valueOf(arr[i].getCof()) + arr[i].getVar();
					else 
						result += arr[i].getCof()>0?"+":"" + String.valueOf(arr[i].getCof()) + arr[i].getVar() + "^" + String.valueOf(arr[i].getExp());
				}
			}
		}
		return result;*/
	}
		
	public String Expand() {
		
		reducir = true;
		String ant = "", act = "";
		int cant = 0;
		for (int i = 0; i < 5; i++) {
			expander = true;
			cant = 0;
			while (expander) {
				expander = false;
				ar = new Arbol(Order(ar.getRaiz()));
				act = toString(ar.getRaiz());
				if (ant.equals(act))
					cant++;
				ant = act;
				if (cant == 3) {
					expander = false;
				}
				Log.i("ORDER",toString(ar.getRaiz()));
				//ar.Order(ar.getRaiz());
				
			}	
		}
		ar.Order(ar.getRaiz());
		Simplificar(ar.getRaiz(),false);
		String result = evaluarNum();
		//String result = toString(ar.getRaiz());
		return result; 
		//ar.Order(ar.getRaiz());
	}
	
	private Nodo Deriva(Nodo nodo) {
			Nodo n = null;
		    if (nodo != null) {
		    	if (nodo.getValor().equals("+" )) {
		    		n = new Nodo("+");
		    		n.setHojaIzquierda(Deriva(nodo.getHojaIzquierda()));
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));
		    	} else if (nodo.getValor().equals("-" )) {
		    		n = new Nodo("-");
		    		n.setHojaIzquierda(Deriva(nodo.getHojaIzquierda()));
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));		    		
		    	} else if (nodo.getValor().equals("~" )) {
		    		n = new Nodo("~");
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));		    		
		    	} else if (nodo.getValor().equals("*" )) {
		    		n = new Nodo("+");
		    		n.setHojaIzquierda(new Nodo("*"));
		    		n.getHojaIzquierda().setHojaIzquierda(Deriva(nodo.getHojaIzquierda()));
		    		n.getHojaIzquierda().setHojaDerecha(nodo.getHojaDerecha());
		    		n.setHojaDerecha(new Nodo("*"));
		    		n.getHojaDerecha().setHojaIzquierda(nodo.getHojaIzquierda());
		    		n.getHojaDerecha().setHojaDerecha(Deriva(nodo.getHojaDerecha()));	    		
		    	} else if (nodo.getValor().equals("/" )) {
		    		n = new Nodo("/");
		    		n.setHojaIzquierda(new Nodo("-"));
		    		n.getHojaIzquierda().setHojaIzquierda(new Nodo("*"));
		    		n.getHojaIzquierda().getHojaIzquierda().setHojaIzquierda(Deriva(nodo.getHojaIzquierda()));
		    		n.getHojaIzquierda().getHojaIzquierda().setHojaDerecha(nodo.getHojaDerecha());
		    		n.getHojaIzquierda().setHojaDerecha(new Nodo("*"));
		    		n.getHojaIzquierda().getHojaDerecha().setHojaIzquierda(nodo.getHojaIzquierda());
		    		n.getHojaIzquierda().getHojaDerecha().setHojaDerecha(Deriva(nodo.getHojaDerecha()));
		    		
		    		n.setHojaDerecha(new Nodo("^"));
		    		n.getHojaDerecha().setHojaIzquierda(nodo.getHojaDerecha());
		    		n.getHojaDerecha().setHojaDerecha(new Nodo("2"));
		    	} else if (nodo.getValor().equals("^" )) {
		    		n = new Nodo("*");
		    		n.setHojaIzquierda(new Nodo("*"));
		    		n.getHojaIzquierda().setHojaIzquierda(new Nodo("^"));
		    		n.getHojaIzquierda().setHojaDerecha(nodo.getHojaDerecha());
		    		
		    		n.getHojaIzquierda().getHojaIzquierda().setHojaIzquierda(nodo.getHojaIzquierda());
		    		n.getHojaIzquierda().getHojaIzquierda().setHojaDerecha(new Nodo("-"));
		    		n.getHojaIzquierda().getHojaIzquierda().getHojaDerecha().setHojaIzquierda(nodo.getHojaDerecha());
		    		n.getHojaIzquierda().getHojaIzquierda().getHojaDerecha().setHojaDerecha(new Nodo("1"));
		    		
		    		n.setHojaDerecha(Deriva(nodo.getHojaIzquierda()));
		    	} else if (nodo.getValor().equals("c")) {
		    		n = new Nodo("*");
		    		n.setHojaIzquierda(new Nodo("~"));
		    		n.getHojaIzquierda().setHojaDerecha(new Nodo("s"));
		    		n.getHojaIzquierda().getHojaDerecha().setHojaDerecha(nodo.getHojaDerecha());
		    		
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));
		    	} else if (nodo.getValor().equals("s")) {
		    		n = new Nodo("*");
		    		n.setHojaIzquierda(new Nodo("c"));
		    		n.getHojaIzquierda().setHojaDerecha(nodo.getHojaDerecha());
		    		
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));		    		
		    		
		    	} else if (nodo.getValor().equals("t")) {
		    		n = new Nodo("*");
		    		Nodo temp = new Nodo("/");
		    		temp.setHojaIzquierda(new Nodo("s"));
		    		temp.getHojaIzquierda().setHojaDerecha(nodo.getHojaDerecha());
		    		temp.setHojaDerecha(new Nodo("c"));
		    		temp.getHojaDerecha().setHojaDerecha(nodo.getHojaDerecha());		    		
		    		
		    		n.setHojaIzquierda(Deriva(temp));	    		
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));		    				    		
		    	} else if (nodo.getValor().equals("e")) {
		    		n = new Nodo("*");
		    		n.setHojaIzquierda(new Nodo("e"));
		    		n.getHojaIzquierda().setHojaDerecha(nodo.getHojaDerecha());
		    		
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));
		    	} else if (nodo.getValor().equals("l")) {
		    		n = new Nodo("*");
		    		n.setHojaIzquierda(new Nodo("/"));
		    		n.getHojaIzquierda().setHojaIzquierda(new Nodo("1"));
		    		n.getHojaIzquierda().setHojaDerecha(nodo.getHojaDerecha());
		    		
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));
		    	} else if (nodo.getValor().equals("q")) {
		    		n = new Nodo("*");
		    		n.setHojaIzquierda(new Nodo("/"));
		    		n.getHojaIzquierda().setHojaIzquierda(new Nodo("1"));
		    		n.getHojaIzquierda().setHojaDerecha(new Nodo("q"));
		    		n.getHojaIzquierda().getHojaDerecha().setHojaDerecha(nodo.getHojaDerecha());
		    		
		    		n.setHojaDerecha(Deriva(nodo.getHojaDerecha()));
		    	}
		    	else if (isNumeric(nodo.getValor())) {
		    		n = new Nodo("0");
		    	} else if (isVar(nodo.getValor())) {
		    		n = new Nodo("1");
		    	}
		    }
	    	return n;			
	}
		
	public String Diff() {
		String result = "";
		ar = new Arbol(Deriva(ar.getRaiz()));
		ar.Order(ar.getRaiz());
		Log.i("Derivadas",toString(ar.getRaiz()));
		reducir = false;
		for (int i = 0; i < 5; i++) {
			expander = true;
			while (expander) {
				expander = false;
				ar = new Arbol(Order(ar.getRaiz()));
				ar.Order(ar.getRaiz());
			}
		}		
		result = toString(ar.getRaiz());
		return result;
	}
		
	public String toString(Nodo nodo) {

		String signo = "", izq = "", der = "";
	    if (nodo != null) {
	    	if (nodo.getValor().equals("*") || nodo.getValor().equals("/") || nodo.getValor().equals("^")) {
	    		
	    		if (!(isNumeric(nodo.getHojaDerecha().getValor())||isVar(nodo.getHojaDerecha().getValor()))) {
	    			der = "(" + toString(nodo.getHojaDerecha()) + ")";
	    		} else {
	    			der = toString(nodo.getHojaDerecha());
	    		}
	    		signo = nodo.getValor();
	    		if (!(isNumeric(nodo.getHojaIzquierda().getValor())||isVar(nodo.getHojaIzquierda().getValor()))) {
	    			izq = "(" +toString(nodo.getHojaIzquierda()) + ")";
	    		} else {
	    			izq = toString(nodo.getHojaIzquierda());
	    		}	    		
	    	} else if (nodo.getValor().equals("-") || nodo.getValor().equals("~")) { 
	    		if (!(isNumeric(nodo.getHojaDerecha().getValor())||isVar(nodo.getHojaDerecha().getValor()))) {
	    			der = "(" + toString(nodo.getHojaDerecha()) + ")";
	    		} else {
	    			der = toString(nodo.getHojaDerecha());
	    		}
	    		signo = "-";
	    		
	    		izq = toString(nodo.getHojaIzquierda());
	    	} else if (nodo.getValor().equals("+")) {
	    		der = toString(nodo.getHojaDerecha());
	    		signo = "+";
	    		izq = toString(nodo.getHojaIzquierda());	    		
	    	}  else if (nodo.getValor().equals("c") 
	    			|| nodo.getValor().equals("s") 
	    			|| nodo.getValor().equals("t") 
	    			|| nodo.getValor().equals("e") || nodo.getValor().equals("l") || nodo.getValor().equals("q")) {
	    		der = "(" + toString(nodo.getHojaDerecha()) + ")";
	    		switch (nodo.getValor().charAt(0)) {
	    			case 'c': signo = "cos"; break;
	    			case 's': signo = "sen"; break;
	    			case 't': signo = "tan"; break;
	    			case 'e': signo = "e"; break;
	    			case 'l': signo = "ln"; break;
	    			case 'q': signo = "sqrt"; break;
	    		}
	    		izq = toString(nodo.getHojaIzquierda());
	    	} else if (isNumeric(nodo.getValor()) || isVar(nodo.getValor())) {
	    		izq = "";
	    		signo = nodo.getValor();
	    		der = "";
	    	}
	    }
    	return izq + signo + der;		
	}


	public Stack coef() {
		Simplificar(ar.getRaiz(),false);
		return this.num;
	}
}
