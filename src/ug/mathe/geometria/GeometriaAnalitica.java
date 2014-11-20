package ug.mathe.geometria;

public class GeometriaAnalitica {

	private double a,b,c,d,e,f;
	private double centroX,centroY,radio,angulo,distA,distB, foco, anchoF, directriz, focoX, focoY, radioA, radioB, p;
	private char tipo = '\0';
	private String fx = "", gx = "";
	
	public GeometriaAnalitica(double a, double b, double c, double d, double e, double f) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;
		this.e = e;
		this.f = f;
	}
	
	public GeometriaAnalitica(double centroX, double centroY, double A, double B, double P, double radio, char tipo) {
		switch (tipo) {
		case 'c':
				this.centroX = centroX;
				this.centroY = centroY;
				this.radio = radio;
				this.tipo = 'c';			
			break;
		case 'e':
			this.centroX = centroX;
			this.centroY = centroY;
			this.radioA = A;
			this.radioB = B;
			this.tipo = 'e';			
		break;
		case 'h':
			this.centroX = centroX;
			this.centroY = centroY;
			this.distA = A;
			this.distB = B;
			this.tipo = 'h';			
		break;
		case 'p':
			this.centroX = centroX;
			this.centroY = centroY;
			this.p = P;
			this.tipo = 'p';
		break;
		}		
	}
	
	
	private void init(){
		centroX = 0.0;
		centroY = 0.0;
		radio = 0.0;
		angulo = 0.0;
		distA = 0.0;
		distB = 0.0;
		foco = 0.0;
		anchoF = 0.0;
		directriz = 0.0;
		focoX = 0.0;
		focoY = 0.0;
		radioA = 0.0;
		radioB = 0.0;
		p = 0.0;
		tipo = '\0';
		fx = "";
		gx = "";		
	}
	
	private void initcanonica(){
		a = 0.0;
		b = 0.0;
		c = 0.0;
		d = 0.0;
		e = 0.0;
		f = 0.0;
		fx = "";
		gx = "";		
	}	
	
	private void ResolverHiperbola(){
		this.centroX = ((this.d/this.a)/2)*-1;
		this.centroY = ((this.e/this.c)/2)*-1;
		double aux  = Math.pow(this.centroX, 2)*this.a+Math.pow(this.centroY, 2)*this.c+this.f*-1;
		this.distB = Math.sqrt(Math.abs(aux/this.a));
		this.distA = Math.sqrt(Math.abs(aux/this.c));
		if (this.distA > this.distB) {
			this.foco = Math.sqrt(Math.pow(this.distA, 2)+Math.pow(this.distB, 2));
			this.anchoF = 2*Math.pow(this.distB, 2)/this.distA;
			this.fx = "("+this.distA+"^2*"+this.centroY+"-sqrt("+this.distA+"^4*(-"+this.distB+"^2)+"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"^2-2*"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"*x+"+this.distA+"^2*"+this.distB+"^2*x^2))/"+this.distA+"^2";
			this.gx = "("+this.distA+"^2*"+this.centroY+"+sqrt("+this.distA+"^4*(-"+this.distB+"^2)+"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"^2-2*"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"*x+"+this.distA+"^2*"+this.distB+"^2*x^2))/"+this.distA+"^2";			
		} else {
			this.foco = Math.sqrt(Math.pow(this.distB, 2)+Math.pow(this.distA, 2));
			this.anchoF = 2*Math.pow(this.distA, 2)/this.distB;
			this.fx = "("+this.distA+"^2*"+this.centroY+"-sqrt("+this.distA+"^4*("+this.distB+"^2)+"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"^2-2*"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"*x+"+this.distA+"^2*"+this.distB+"^2*x^2))/"+this.distA+"^2";
			this.gx = "("+this.distA+"^2*"+this.centroY+"+sqrt("+this.distA+"^4*("+this.distB+"^2)+"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"^2-2*"+this.distA+"^2*"+this.distB+"^2*"+this.centroX+"*x+"+this.distA+"^2*"+this.distB+"^2*x^2))/"+this.distA+"^2";			
		}
		this.angulo = 0.0;
		
		/*
		this.fx = "(a^2*k-sqrt(a^4*(-b^2)+a^2*b^2*h^2-2*a^2*b^2*h*x+a^2*b^2*x^2))/a^2";
		this.gx = "(a^2*k+sqrt(a^4*(-b^2)+a^2*b^2*h^2-2*a^2*b^2*h*x+a^2*b^2*x^2))/a^2";
		
		this.hx = "(a^2*k-sqrt(a^4*( b^2)+a^2*b^2*h^2-2*a^2*b^2*h*x+a^2*b^2*x^2))/a^2";
		this.ux = "(a^2*k+sqrt(a^4*( b^2)+a^2*b^2*h^2-2*a^2*b^2*h*x+a^2*b^2*x^2))/a^2";
		*/		
	
	}
	
	private void ResolverParabola(){
	    if (this.c == 0) {     	    
    	    this.centroX = ((this.d/this.a)/2)*-1;
    	    double aux = Math.pow(this.centroX, 2)*this.a+this.f*-1;
    	    this.centroY = (aux/(this.e*-1))*-1;
    	    aux = (this.e*-1)/this.a;
    	    this.p = aux / 4;
    	    this.anchoF=Math.abs(aux);
    	    this.focoX = this.centroX;
    	    this.focoY = this.centroY+this.p;
    	    if (this.centroY > this.focoY)
        	    this.directriz = this.centroY - this.p;
    	    else
    	    	this.directriz = this.centroY + this.p;
    	    //this.fx = "(h^2-2*h*x+4*k*p+x^2)/(4*p)";    	    
    	    this.fx = "("+this.centroX+"^2-2*"+this.centroX+"*x+4*"+this.centroY*this.p+"+x^2)/(4*"+this.p+")";
	    } else if (this.a == 0) {
	    	this.centroY = ((this.e/this.c)/2)*-1;
	    	double aux = Math.pow((this.e/this.c)/2, 2)*this.c+(this.f*-1);
	    	this.centroX = aux/this.d;
	    	aux = (this.d*-1)/this.c;
	    	this.p = aux / 4;
	    	this.anchoF = Math.abs(aux);
    	    this.focoX = this.centroX+this.p;
    	    this.focoY = this.centroY;
    	    if (this.centroX > this.focoX)
        	    this.directriz = this.centroX - this.p;
    	    else
    	    	this.directriz = this.centroX + this.p;
    	    /*this.fx = "k-2*sqrt(p*x-h*p)";
    	    this.gx = "k+2*sqrt(p*x-h*p)";*/
    	    this.fx = this.centroY+"-2*sqrt("+this.p+"*x-"+this.centroX*this.p+")";
    	    this.gx = this.centroY+"+2*sqrt("+this.p+"*x-"+this.centroX*this.p+")";
	    }
	    
	}
	
	private void ResolverElipse(){
		this.centroX = ((this.d/this.a)/2)*-1;
		this.centroY = ((this.e/this.c)/2)*-1;
		
		double aux = Math.pow((this.d/this.a)/2, 2)*this.a+Math.pow((this.e/this.c)/2, 2)*this.c+(this.f*-1);
	    this.radioA = Math.sqrt(aux/this.a);
	    this.radioB = Math.sqrt(aux/this.c);
	    
	    if (this.radioA > this.radioB) {
	    	this.foco = Math.sqrt(Math.pow(this.radioA, 2)-Math.pow(this.radioB, 2));
	    	this.anchoF = 2*Math.pow(this.radioB, 2)/this.radioA;
	    } else {
	    	this.foco = Math.sqrt(Math.pow(this.radioB, 2)-Math.pow(this.radioA, 2));
	    	this.anchoF = 2*Math.pow(this.radioA, 2)/this.radioB;	    	
	    }	
	    /*this.fx = "(a^2*k-sqrt(a^4*b^2-a^2*b^2*h^2+2*a^2*b^2*h*x-a^2*b^2*x^2))/a^2";
	    this.gx = "(a^2*k+sqrt(a^4*b^2-a^2*b^2*h^2+2*a^2*b^2*h*x-a^2*b^2*x^2))/a^2";*/
	    this.fx = "("+this.radioA+"^2*"+this.centroY+"-sqrt("+this.radioA+"^4*"+this.radioB+"^2-"+this.radioA+"^2*"+this.radioB+"^2*"+this.centroX+"^2+2*"+this.radioA+"^2*"+this.radioB+"^2*"+this.centroX+"*x-"+this.radioA+"^2*"+this.radioB+"^2*x^2))/"+this.radioA+"^2";
	    this.gx = "("+this.radioA+"^2*"+this.centroY+"+sqrt("+this.radioA+"^4*"+this.radioB+"^2-"+this.radioA+"^2*"+this.radioB+"^2*"+this.centroX+"^2+2*"+this.radioA+"^2*"+this.radioB+"^2*"+this.centroX+"*x-"+this.radioA+"^2*"+this.radioB+"^2*x^2))/"+this.radioA+"^2";
	}
	
	private void ResolverCircunferencia(){
		this.centroX = ((this.d/this.a)/2)*-1;
		this.centroY = ((this.e/this.c)/2)*-1;
		this.radio = Math.sqrt(Math.pow((this.d/this.a)/2, 2) + Math.pow((this.e/this.c)/2, 2) + (this.f*-1/this.a));
		this.angulo = 0.0;
		/*this.fx = "k-sqrt(-h^2+2*h*x+r^2-x^2)";
		this.gx = "k+sqrt(-h^2+2*h*x+r^2-x^2)";*/
		this.fx = this.centroY+"+sqrt("+this.radio+"^2-(x-"+this.centroX+")^2)";
		this.gx = this.centroY+"-sqrt("+this.radio+"^2-(x-"+this.centroX+")^2)";
		/*this.fx = this.centroY+"-sqrt(-"+this.centroX+"^2+2*"+this.centroX+"*x+"+this.radio+"^2-x^2)";
		this.gx = this.centroY+"+sqrt(-"+this.centroX+"^2+2*"+this.centroX+"*x+"+this.radio+"^2-x^2)";*/
	}	
	
	public String Resolver(){
		init();
		if ((this.a < 0 && this.c > 0)||(this.a > 0 && this.c < 0)) {
			// Hiperbola h
			tipo = 'h';
			ResolverHiperbola();
		     
		} else if (this.a == 0 || this.c ==0) {
			// Parabola p
			tipo = 'p';			
			ResolverParabola();
		} else if (this.a == this.c) {
			tipo = 'c';
			ResolverCircunferencia();
		} else {
			// Elipse e
			tipo = 'e';
			ResolverElipse();
		}
		return toString();
	}
	
	private void ResolverCircunferenciaCanonica(){
		this.a = 1;
		this.b = 0;
		this.c = 1;
		this.d = -2*this.centroX;
		this.e = -2*this.centroY;
		this.f = Math.pow(this.centroX, 2) + Math.pow(this.centroY, 2) - Math.pow(this.radio, 2);
	}
	
	private void ResolverElipseCanonica(){
		this.a = Math.pow(this.radioB, 2);
		this.b = 0;
		this.c = Math.pow(this.radioA, 2);
		this.d = -2*Math.pow(this.radioB, 2)*this.centroX;
		this.e = -2*Math.pow(this.radioA, 2)*this.centroY;
		this.f = Math.pow(this.radioA, 2)*Math.pow(this.centroY, 2)+Math.pow(this.radioB, 2)*Math.pow(this.centroX, 2) - Math.pow(this.radioA, 2)*Math.pow(this.radioB, 2);
	}
	
	private void ResolverHiperbolaCanonica(){
		this.a = Math.pow(this.distB, 2);
		this.b = 0;
		this.c = -Math.pow(this.distA, 2);
		this.d = -2*Math.pow(this.distB, 2)*this.centroX;
		this.e = 2*Math.pow(this.distA, 2)*this.centroY;
		this.f = -Math.pow(this.distA, 2)*Math.pow(this.centroY, 2)+Math.pow(this.distB, 2)*Math.pow(this.centroX, 2) - Math.pow(this.distA, 2)*Math.pow(this.distB, 2);
	}
	
	private void ResolverParabolaCanonica(){
		this.a = 1;
		this.b = 0;
		this.c = 0;
		this.d = -2*this.centroX;
		this.e =-4*this.p;
		this.f = Math.pow(this.centroX, 2) + 4*this.centroY*this.p; 
	}		
	
	public String ResolverCanonica(){
		initcanonica();
		switch (this.tipo) {
			case 'c':
					ResolverCircunferenciaCanonica();
				break;
			case 'e':
				ResolverElipseCanonica();
			break;
			case 'h':
				ResolverHiperbolaCanonica();
			break;
			case 'p':
				ResolverParabolaCanonica();
			break;			
		}
		return toStringCanonica();
	}	
	
	public String getFunc(){
		String result = "";
		if (!this.fx.equals(""))
		{
			result = this.fx;
			if (!this.gx.equals(""))
				result += ";" + this.gx;
		}
		return result;
	}
	
	public String getFuncCanonica(){
		ResolverCanonica();
		Resolver();
		String result = "";
		if (!this.fx.equals(""))
		{
			result = this.fx;
			if (!this.gx.equals(""))
				result += ";" + this.gx;
		}
		return result;
	}	
	
	public String getParam(){
		String result = "";
		switch (this.tipo) {
			case 'h': case 'p':
				result = Double.valueOf(this.centroX-this.anchoF-5) + ","
							+ Double.valueOf(this.centroX+this.anchoF+5) + ","
							+ Double.valueOf(this.centroY-this.anchoF-5) + ","
							+ Double.valueOf(this.centroY+this.anchoF+5);
				break;
			case 'c':
				result = Double.valueOf(this.centroX-this.radio-5) + ","
						+ Double.valueOf(this.centroX+this.radio+5) + ","
						+ Double.valueOf(this.centroY-this.radio-10) + ","
						+ Double.valueOf(this.centroY+this.radio+10);				
				break;
			case 'e':
					result = Double.valueOf(this.centroX-this.radioB*2) + ","
							+ Double.valueOf(this.centroX+this.radioB*2) + ","
							+ Double.valueOf(this.centroY-this.radioA*2) + ","
							+ Double.valueOf(this.centroY+this.radioA*2);
				break;
		}
		return result;
	}
	
	public String toString(){
		String result = "";
		switch (this.tipo){
			case 'h':
					result = "Es una hiperbola, Centro X = " + this.centroX +
					" Centro Y = " + this.centroY +
					" Dist A = " + this.distA +
					" Dist B = " + this.distB +
					" Foco = " + this.foco +
					" Ancho F = " + this.anchoF;				
				break;
			case 'p':
	    	    result = "Es una parabola, Vertice X = " + this.centroX +
	    	    			" Vertice Y = " + this.centroY +
	    	    			" Foco = (" + this.focoX + "," + this.focoY +")" +
	    	    			" Ancho F = " + this.anchoF +
	    	    			" Directriz = " + this.directriz +
	    	    			" P = " + this.p;
			break; 
			case 'c':
				result = "Es una circunferencia, Centro X = " + this.centroX + 
					" Centro Y = " + this.centroY + 
					" Radio = " + this.radio;
				break;
			case 'e':
				double mayor = 0.0, menor = 0.0;
				
				if (this.radioA > this.radioB) {
					mayor = this.radioA;
					menor = this.radioB;
				} else {
					mayor = this.radioB;
					menor = this.radioA;					
				}
				result = "Es una elipse, Centro X = " + this.centroX +
				" Centro Y = " + this.centroY +
				" Radio Mayor = " + mayor +
				" Radio Menor = " + menor +
				" Foco = " + this.foco +
				" Ancho F = " + this.anchoF;				
			break; 			
		}
		return result;
	}
	
	public String toStringCanonica(){
		String result = "";
		String a = "", c = "", d = "", e = "", f = "";
		switch (this.tipo){
			case 'h': case 'c': case 'e':
					a = this.a+"*X^2";
					c = String.valueOf((this.c >= 0)?"+"+this.c+"*Y^2":this.c+"*Y^2");
					d = String.valueOf((this.d >= 0)?"+"+this.d+"*X":this.d+"*X");
					e = String.valueOf((this.e >= 0)?"+"+this.e+"*Y":this.e+"*Y");
					f = String.valueOf((this.f >= 0)?"+"+this.f:this.f+"");
					f += "=0";
					result = a + c + d + e + f;
					/*result = this.a+"*X^2"+
								(+"*Y^2" +
								this.d+"*X+"+
								this.e+"*Y+"+
								this.f;*/				
				break;
			case 'p':
				a = this.a+"*X^2";
				d = String.valueOf((this.d >= 0)?"+"+this.d+"*X":this.d+"*X");
				e = String.valueOf((this.e >= 0)?"+"+this.e+"*Y":this.e+"*Y");
				f = String.valueOf((this.f >= 0)?"+"+this.f:this.f+"");
				f += "=0";
				result = a + c + d + e + f;
				/*result = this.a+"*X^2+"+
						this.d+"*X+"+
						this.e+"*Y+"+
						this.f;*/
			break; 		
		}
		return result;
	}	
}
