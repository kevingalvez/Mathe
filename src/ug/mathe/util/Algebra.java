package ug.mathe.util;


public class Algebra {

	private Arbol ar;
	private String expr = "";
	private boolean expander;
	private Stack num = new Stack(255); 
	private int ind_num = 0;
	
	public Algebra(Arbol ar){
		this.ar = ar;
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
		if (cadena.equals("x") || cadena.equals("y"))
			return true;
		else
			return false;
	}	
	
	private boolean isHoja(Nodo nodo) {
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
		return false;
	}
	

	private Nodo Distder(Nodo nodoizq, Nodo nododer, String signo) {
		Nodo result =  new Nodo(signo);
		if (isHoja(nodoizq))
			if (isHoja(nododer.getHojaIzquierda())) {
				String dato1 = "", dato2 = "", dato3 = "", dato4="";
				if (nodoizq.getHojaIzquierda() != null  && nodoizq.getHojaDerecha() != null)
				{
				dato1 = nodoizq.getHojaIzquierda().getValor();
				dato2 = nodoizq.getHojaDerecha().getValor();
				} else
					dato1 = nodoizq.getValor();
				
				if (nododer.getHojaIzquierda().getHojaIzquierda() != null && nododer.getHojaIzquierda().getHojaDerecha() != null)
				{
					dato3 = nododer.getHojaIzquierda().getHojaIzquierda().getValor();
					dato4 = nododer.getHojaIzquierda().getHojaDerecha().getValor();
				} else
					dato3 = nododer.getHojaIzquierda().getValor();
					
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
				if (isNumeric(dato3)) 
					num.Push(dato3);
				else if (!dato3.equals(""))
					exp++;
				if (isNumeric(dato4)) 
					num.Push(dato4);
				else if (!dato4.equals(""))
					exp++;
				
				if (!num.isEmpty())
					res = Double.valueOf(num.Pop().toString());
				while (!num.isEmpty()) {
					res = res*Double.valueOf(num.Pop().toString());
				}
				
				if (exp == 1) {
					result.setHojaIzquierda(new Nodo("*"));
					result.getHojaIzquierda().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaIzquierda().setHojaDerecha(new Nodo(String.valueOf("x")));
				} else {
					result.setHojaIzquierda(new Nodo("*"));
					result.getHojaIzquierda().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaIzquierda().setHojaDerecha(new Nodo(String.valueOf("^")));
					result.getHojaIzquierda().getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf("x")));
					result.getHojaIzquierda().getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf(String.valueOf(exp))));
				}			
			} else {
		result.setHojaIzquierda(new Nodo("*"));
		result.getHojaIzquierda().setHojaIzquierda(nodoizq);
		result.getHojaIzquierda().setHojaDerecha(nododer.getHojaIzquierda());
			}
		
		if (isHoja(nodoizq))
			if (isHoja(nododer.getHojaDerecha())) {	
				String dato1 = "", dato2 = "", dato3 = "", dato4="";
				if (nodoizq.getHojaIzquierda() != null  && nodoizq.getHojaDerecha() != null)
				{
				dato1 = nodoizq.getHojaIzquierda().getValor();
				dato2 = nodoizq.getHojaDerecha().getValor();
				} else
					dato1 = nodoizq.getValor();
				if (nododer.getHojaDerecha().getHojaIzquierda() != null && nododer.getHojaDerecha().getHojaDerecha() != null)
				{
					dato3 = nododer.getHojaDerecha().getHojaIzquierda().getValor();
					dato4 = nododer.getHojaDerecha().getHojaDerecha().getValor();
				} else
					dato3 = nododer.getHojaDerecha().getValor();
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
				if (isNumeric(dato3)) 
					num.Push(dato3);
				else if (!dato3.equals(""))
					exp++;
				if (isNumeric(dato4)) 
					num.Push(dato4);
				else if (!dato4.equals(""))
					exp++;
				
				if (!num.isEmpty())
					res = Double.valueOf(num.Pop().toString());
				while (!num.isEmpty()) {
					res = res*Double.valueOf(num.Pop().toString());
				}
				
				if (exp == 1) {
					result.setHojaDerecha(new Nodo("*"));
					result.getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf("x")));
				} else {
					result.setHojaDerecha(new Nodo("*"));
					result.getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf("^")));
					result.getHojaDerecha().getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf("x")));
					result.getHojaDerecha().getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf(String.valueOf(exp))));
				}
			} else {
		
		result.setHojaDerecha(new Nodo("*"));
		result.getHojaDerecha().setHojaIzquierda(nodoizq);
		result.getHojaDerecha().setHojaDerecha(nododer.getHojaDerecha());
			}
		return result;
	}
	
	private Nodo Distizq(Nodo nodoizq, Nodo nododer, String signo) {	
		Nodo result =  new Nodo(signo);
		if (isHoja(nodoizq.getHojaIzquierda()))
			if (isHoja(nododer)) {
				String dato1 = "", dato2 = "", dato3 = "", dato4="";
				if (nodoizq.getHojaIzquierda().getHojaIzquierda() != null  && nodoizq.getHojaIzquierda().getHojaDerecha() != null)
				{
				dato1 = nodoizq.getHojaIzquierda().getHojaIzquierda().getValor();
				dato2 = nodoizq.getHojaIzquierda().getHojaDerecha().getValor();
				} else
					dato1 = nodoizq.getHojaIzquierda().getValor();
				
				if (nododer.getHojaIzquierda() != null && nododer.getHojaDerecha() != null)
				{
					dato3 = nododer.getHojaIzquierda().getValor();
					dato4 = nododer.getHojaDerecha().getValor();
				} else
					dato3 = nododer.getValor();
					
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
				if (isNumeric(dato3)) 
					num.Push(dato3);
				else if (!dato3.equals(""))
					exp++;
				if (isNumeric(dato4)) 
					num.Push(dato4);
				else if (!dato4.equals(""))
					exp++;
				
				if (!num.isEmpty())
					res = Double.valueOf(num.Pop().toString());
				while (!num.isEmpty()) {
					res = res*Double.valueOf(num.Pop().toString());
				}
				
				if (exp == 1) {
					result.setHojaIzquierda(new Nodo("*"));
					result.getHojaIzquierda().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaIzquierda().setHojaDerecha(new Nodo(String.valueOf("x")));
				} else {
					result.setHojaIzquierda(new Nodo("*"));
					result.getHojaIzquierda().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaIzquierda().setHojaDerecha(new Nodo(String.valueOf("^")));
					result.getHojaIzquierda().getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf("x")));
					result.getHojaIzquierda().getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf(String.valueOf(exp))));
				}			
			} else {		
				result.setHojaIzquierda(new Nodo("*"));
				result.getHojaIzquierda().setHojaIzquierda(nodoizq.getHojaIzquierda());
				result.getHojaIzquierda().setHojaDerecha(nododer);		
			}
		
		if (isHoja(nodoizq))
			if (isHoja(nododer.getHojaDerecha())) {	
				String dato1 = "", dato2 = "", dato3 = "", dato4="";
				if (nodoizq.getHojaDerecha().getHojaIzquierda() != null  && nodoizq.getHojaDerecha().getHojaDerecha() != null)
				{
				dato1 = nodoizq.getHojaDerecha().getHojaIzquierda().getValor();
				dato2 = nodoizq.getHojaDerecha().getHojaDerecha().getValor();
				} else
					dato1 = nodoizq.getHojaDerecha().getValor();
				if (nododer.getHojaIzquierda() != null && nododer.getHojaDerecha() != null)
				{
					dato3 = nododer.getHojaIzquierda().getValor();
					dato4 = nododer.getHojaDerecha().getValor();
				} else
					dato3 = nododer.getValor();
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
				if (isNumeric(dato3)) 
					num.Push(dato3);
				else if (!dato3.equals(""))
					exp++;
				if (isNumeric(dato4)) 
					num.Push(dato4);
				else if (!dato4.equals(""))
					exp++;
				
				if (!num.isEmpty())
					res = Double.valueOf(num.Pop().toString());
				while (!num.isEmpty()) {
					res = res*Double.valueOf(num.Pop().toString());
				}
				
				if (exp == 1) {
					result.setHojaDerecha(new Nodo("*"));
					result.getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf("x")));
				} else {
					result.setHojaDerecha(new Nodo("*"));
					result.getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf(res)));
					result.getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf("^")));
					result.getHojaDerecha().getHojaDerecha().setHojaIzquierda(new Nodo(String.valueOf("x")));
					result.getHojaDerecha().getHojaDerecha().setHojaDerecha(new Nodo(String.valueOf(String.valueOf(exp))));
				}
			} else {
				result.setHojaDerecha(new Nodo("*"));
				result.getHojaDerecha().setHojaIzquierda(nodoizq.getHojaDerecha());
				result.getHojaDerecha().setHojaDerecha(nododer);			
			}
		return result;		
	}		
	
	/*private Nodo Distizq(Nodo nodoizq, Nodo nododer, String signo) {
		Nodo result =  new Nodo(signo);
		result.setHojaIzquierda(new Nodo("*"));
		result.getHojaIzquierda().setHojaIzquierda(nodoizq.getHojaIzquierda());
		result.getHojaIzquierda().setHojaDerecha(nododer);
		result.setHojaDerecha(new Nodo("*"));
		result.getHojaDerecha().setHojaIzquierda(nodoizq.getHojaDerecha());
		result.getHojaDerecha().setHojaDerecha(nododer);		
		return result;
	}	*/
	
	private Nodo Order(Nodo nodo)
	{
		Nodo n = null;
	    if (nodo != null) {
	    	boolean expand = false;
	    	if (nodo.getValor().equals("*")) {
	    		if (isDist(String.valueOf(nodo.getHojaDerecha().getValor())))
				{
	    			n = Distder(nodo.getHojaIzquierda(),nodo.getHojaDerecha(),String.valueOf(nodo.getHojaDerecha().getValor()));
	    			expander = expand = true;
	    			
				} else if (isDist(String.valueOf(nodo.getHojaIzquierda().getValor())))
				{
	    			n = Distizq(nodo.getHojaIzquierda(),nodo.getHojaDerecha(),String.valueOf(nodo.getHojaDerecha().getValor()));
	    			expander = expand = true;
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
	
	private boolean isSimp(String a) {
		boolean result = false;
		if (a.equals("*") || a.equals("^"))
			result = true;
		return result;
	}
		
	private Nodo Simplificar(Nodo nodo) {
		Nodo n = null;
	    if (nodo != null) {
	    	boolean expand = false;
	    	if (nodo.getValor().equals("*")) {
	    		String der = String.valueOf(nodo.getHojaDerecha().getValor()); 
	    		String izq = String.valueOf(nodo.getHojaIzquierda().getValor());
	    		if (isSimp(der) && isSimp(izq))
				{
	    			
	    			n = Distder(nodo.getHojaIzquierda(),nodo.getHojaDerecha(),String.valueOf(nodo.getHojaDerecha().getValor()));
	    			expander = expand = true;
	    			
				} else if (isDist(String.valueOf(nodo.getHojaIzquierda().getValor())))
				{
	    			n = Distizq(nodo.getHojaIzquierda(),nodo.getHojaDerecha(),String.valueOf(nodo.getHojaDerecha().getValor()));
	    			expander = expand = true;
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
	
	public void Expand() {
		expander = true;
		while (expander) {
			expander = false;
			ar = new Arbol(Order(ar.getRaiz()));
		}
		ar.Order(ar.getRaiz());
	}
}
