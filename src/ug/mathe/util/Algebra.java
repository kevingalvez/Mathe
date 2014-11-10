package ug.mathe.util;

public class Algebra {

	private Arbol ar;
	private boolean expander;
	private Stack num = new Stack(255); 
	String postoder = "";
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
				result.setHojaIzquierda(nodo1);
				result.setHojaDerecha(new Nodo(dato3));
			} else if (isVar(dato3) && dato1.equals(dato3)) {
				result = new Nodo("^");
				result.setHojaIzquierda(nodo1);
				result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato2) + 1)));				
			}
		} if (nodo2.getValor().equals("^")) {
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
				result = new Nodo("*");
				result.setHojaIzquierda(nodo2);
				result.setHojaDerecha(new Nodo(dato1));
			} else if (isVar(dato1) && dato1.equals(dato1)) {
				result = new Nodo("^");
				result.setHojaIzquierda(nodo2);
				result.setHojaDerecha(new Nodo(String.valueOf(Double.valueOf(dato4) + 1)));				
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
	    			n = Distizq(nodo.getHojaIzquierda(),nodo.getHojaDerecha(),String.valueOf(nodo.getHojaIzquierda().getValor()));
	    			expander = expand = true;
				} else if (isHoja(nodo)) {
					expand = true;
					n = evaluarHoja(nodo);
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
	
	private Nodo evaluarHoja(Nodo nodo) {
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
		Nodo result = null;
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
			}
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
		}  else if (nodo.getValor().equals("^") && nodo.getHojaIzquierda().getValor().equals("-") && nodo.getHojaIzquierda().getHojaIzquierda().getValor().equals("0")) {
			String dato1 = "", dato2 = "", dato3 = "";
			dato1 = "1";
			dato2 = nodo.getHojaIzquierda().getHojaDerecha().getValor();
			dato3 = nodo.getHojaDerecha().getValor();
			result = new Numero(Double.valueOf(dato1)*-1,dato2,Double.valueOf(dato3));
		} else if (nodo.getValor().equals("^") && nodo.getHojaDerecha().getValor().equals("-") && nodo.getHojaDerecha().getHojaIzquierda().getValor().equals("0")) {
			String dato1 = "", dato2 = "", dato3 = "";
			dato1 = "1";
			dato2 = nodo.getHojaDerecha().getHojaDerecha().getValor();
			dato3 = nodo.getHojaIzquierda().getValor();
			result = new Numero(Double.valueOf(dato1)*-1,dato2,Double.valueOf(dato3));
		}  else if (nodo.getValor().equals("*") && nodo.getHojaIzquierda().getValor().equals("-") && nodo.getHojaIzquierda().getHojaIzquierda().getValor().equals("0")) {
			String dato1 = "", dato2 = "", dato3 = "";
			if (isNumeric(nodo.getHojaIzquierda().getHojaDerecha().getValor())) {
				dato1 = nodo.getHojaIzquierda().getHojaDerecha().getValor();
				dato2 = "";
				dato3 = "1";
			} else if (isVar(nodo.getHojaIzquierda().getHojaDerecha().getValor())) {
				dato1 = "1";
				dato2 = nodo.getHojaIzquierda().getHojaDerecha().getValor();
				dato3 = "1";
			}
			result = new Numero(Double.valueOf(dato1)*-1,dato2,Double.valueOf(dato3));
		} else if (nodo.getValor().equals("*") && nodo.getHojaDerecha().getValor().equals("-") && nodo.getHojaDerecha().getHojaIzquierda().getValor().equals("0")) {
			String dato1 = "", dato2 = "", dato3 = "";
			if (isNumeric(nodo.getHojaDerecha().getHojaDerecha().getValor())) {
				dato1 = nodo.getHojaDerecha().getHojaDerecha().getValor();
				dato2 = "";
				dato3 = "1";
			} else if (isVar(nodo.getHojaDerecha().getHojaDerecha().getValor())) {
				dato1 = "1";
				dato2 = nodo.getHojaDerecha().getHojaDerecha().getValor();
				dato3 = "1";
			}			

			result = new Numero(Double.valueOf(dato1)*-1,dato2,Double.valueOf(dato3));
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
	
	private String SolvePolinomio(double a, double b, double c) {
		String result = "";
		double x1 = 0.0, x2 = 0.0;
		if (a != 0) {
			x1 = (-b+Math.sqrt(Math.pow(b, 2)-4*a*c))/(2*a);
			x2 = (-b-Math.sqrt(Math.pow(b, 2)-4*a*c))/(2*a);
			result = "X1= " + x1 + "; X2= " + x2;
		} else if (a == 0) {
			x1 = -c/b;
			result = "X= " + x1;
		} else if (a == 0 && b == 0) {
			if (c == 0)
				result = "Verdadero";
			else
				result = "False";
		}
		return result;
	}
	
	private String evaluarNum() {
		Numero[] arr = new Numero[255];
		int len_arr = -1;
		while (!num.isEmpty()) {
			Numero aux = (Numero)num.Pop();
			if (len_arr > -1) {
				boolean suma = false;
				for (int i = 0; i <= len_arr; i++) {
					if (aux.getVar().equals(arr[i].getVar()) && aux.getExp() == arr[i].getExp()) {
						arr[i].setCof(arr[i].getCof()+aux.getCof());
						suma = true;
					}
				}
				if (!suma) {
					len_arr++;
					arr[len_arr] = new Numero(aux);					
				}
			} else {
				len_arr++;
				arr[len_arr] = new Numero(aux);  
			}
		}
		
		double a = 0.0, b = 0.0, c = 0.0;
		for (int i=0; i <= len_arr;i++) {
			
			if (arr[i].getVar().equals("x") && arr[i].getExp() == 2 )
				a = arr[i].getCof();
			if (arr[i].getVar().equals("x") && arr[i].getExp() == 1 )
				b = arr[i].getCof();
			if (arr[i].getVar().equals("") && arr[i].getExp() == 1 )
				c = arr[i].getCof();
		}
		return SolvePolinomio(a, b, c);
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
		expander = true;
		while (expander) {
			expander = false;
			ar = new Arbol(Order(ar.getRaiz()));
			ar.Order(ar.getRaiz());
		}	
		ar.Order(ar.getRaiz());
		Simplificar(ar.getRaiz(),false);
		String result = evaluarNum();
		return result; 
		//ar.Order(ar.getRaiz());
	}
}
