package ug.mathe.geometria_plana;

public class GeometriaPlana {

	private String figura;
	private int caso;
	
	
	//Circulo
	
	private double radio = 0.0;
	private double angulo = 0.0;
	private double area = 0.0;
	private double perimetro = 0.0;
	private double area_sector = 0.0;
	private double area_segmento = 0.0;
	
	//Rectangulo
	private double ladoa = 0.0;
	private double ladob = 0.0;
	private double areaR = 0.0;
	private double perimetroR = 0.0;
	
	//Triangulo
	private double ladoaT = 0.0;
	private double ladobT = 0.0;
	private double ladocT = 0.0;
	private double alfaT = 0.0;
	private double betaT = 0.0;
	private double tetaT = 0.0;
	private double areaT = 0.0;
	private double perimetroT = 0.0;
	
	public GeometriaPlana(double[] param, String fig, int caso) {
		if (fig.equals("CIRCULO")) {
			switch (caso) {
				case 0:
						this.radio = param[0];
						this.angulo = param[1];
						break;
				case 1:
						this.area = param[0];
						this.angulo = param[1];
						break;
				case 2:
						this.perimetro = param[0];
						this.angulo = param[1];
					break;
				case 3:
						this.area_sector = param[0];
						this.angulo = param[1];
					break;
				case 4:
						this.area_sector = param[0];
						this.radio = param[1];
					break;
				case 5:
						this.area_sector = param[0];
						this.area = param[1];
					break;
				case 6:
					this.area_segmento = param[0];
					this.angulo = param[1];
				break;					
			}
			this.figura = fig;
			this.caso = caso;
		} else if (fig.equals("RECTANGULO")) {
			switch (caso) {
				case 0:
					this.ladoa = param[0];
					this.ladob = param[1];
					break;
				case 1:
						this.ladoa = param[0];
						this.perimetroR = param[1];
						break;
				case 2:
						this.ladoa = param[0];
						this.areaR = param[1];
					break;
				case 3:
						this.perimetroR = param[0];
						this.areaR = param[1];
					break;
			}
			this.figura = fig;
			this.caso = caso;			
		} else if (fig.equals("TRIANGULO")) {
			switch (caso) {
			case 0:
				this.ladoaT = param[0];
				this.ladobT = param[1];
				this.ladocT = param[2];				
				break;
			case 1:
				this.ladoaT = param[0];
				this.ladobT = param[1];
				this.alfaT = param[2];
				break;
			case 2:
				this.ladoaT = param[0];
				this.alfaT = param[1];
				this.betaT = param[2];
				break;
			case 3:
				this.areaT = param[0];
				this.ladoaT = param[1];
				this.ladobT = param[2];
				break;
			case 4:
				this.perimetroT = param[0];
				this.ladoaT = param[1];
				this.ladobT = param[2];				
				break;
			}
			this.figura = fig;
			this.caso = caso;			
		}
	}
	
	public double getRadio() {
		return this.radio;
	}
	
	public double getAngulo() {
		return this.angulo;
	}
	
	public double getArea() {
		return this.area;
	}

	public double getPerimetro() {
		return this.perimetro;
	}	
	
	public double getAreaSector() {
		return this.area_sector;
	}	
	
	public double getAreaSegmento() {
		return this.area_segmento;
	}	
	
	public boolean resolve()
	{
		boolean result = false;
		if (this.figura.equals("CIRCULO")) {
			switch (this.caso){
				case 0:
					this.area_sector = (this.angulo/360)*Math.PI*Math.pow(this.radio, 2);
					this.area_segmento = ((this.angulo/360)*Math.PI*Math.pow(radio,2))-((Math.pow(radio,2)*Math.sin(this.angulo*Math.PI/180))/2);
					this.area = Math.PI*Math.pow(this.radio, 2);
					this.perimetro = 2*Math.PI*this.radio;
					result = true;
					break;
				case 1:
					this.radio = Math.sqrt(this.area/Math.PI);
					this.perimetro = 2*Math.PI*this.radio;
					this.area_sector = (this.angulo/360)*Math.PI*Math.pow(radio,2);
					this.area_segmento = ((this.angulo/360)*Math.PI*Math.pow(radio,2))-((Math.pow(radio,2)*Math.sin(this.angulo*Math.PI/180))/2);
					result = true;
					break;
				case 2:
					  this.radio = this.perimetro/(2*Math.PI);
					  this.area = Math.PI*Math.pow(radio,2);
					  this.area_sector = (this.angulo/360)*Math.PI*Math.pow(this.radio,2);
					  this.area_segmento = ((this.angulo/360)*Math.PI*Math.pow(this.radio,2))-((Math.pow(this.radio,2)*Math.sin(this.angulo*Math.PI/180))/2);
					  result = true;
					break;
				case 3:
					this.area_sector = (this.angulo/360)*Math.PI*Math.pow(this.radio, 2);
					this.area_segmento = ((this.angulo/360)*Math.PI*Math.pow(radio,2))-((Math.pow(radio,2)*Math.sin(this.angulo*Math.PI/180))/2);
					this.area = Math.PI*Math.pow(this.radio, 2);
					this.perimetro = 2*Math.PI*this.radio;
					result = true;
					break;
				case 4:
					  this.angulo = (360*this.area_sector)/(Math.PI*Math.pow(radio,2));
					  this.perimetro = 2*Math.PI*this.radio;
					  this.area = Math.PI*Math.pow(radio,2);
					  this.area_segmento = ((this.angulo/360)*Math.PI*Math.pow(radio,2))-((Math.pow(radio,2)*Math.sin(this.angulo*Math.PI/180))/2);
					  result = true;
					break;
				case 5:
					  this.radio = Math.sqrt(this.area/Math.PI);
					  this.angulo = (360*this.area_sector)/this.area;
					  this.perimetro = 2*Math.PI*this.radio;
					  this.area_segmento = ((this.angulo/360)*Math.PI*Math.pow(radio,2))-((Math.pow(radio,2)*Math.sin(this.angulo*Math.PI/180))/2);
					  result = true;
					break;
				case 6:
					  this.radio = Math.sqrt((360*this.area_segmento)/(this.angulo*Math.PI-180*Math.sin(this.angulo*Math.PI/180)));
					  this.perimetro = 2*Math.PI*this.radio;
					  this.area = Math.PI*Math.pow(radio,2);
					  this.area_sector = (this.angulo/360)*Math.PI*Math.pow(radio,2);
					  result = true;
					break;					
			}
		} else if (this.figura.equals("RECTANGULO")) {
			switch (this.caso){
				case 0:
					this.perimetroR = 2*this.ladoa+2*this.ladob;
				  	this.areaR = this.ladoa*this.ladob;
					result = true;
					break;
				case 1:
					this.ladob = (this.perimetroR/2-this.ladoa);
				  	this.areaR = this.ladoa*this.ladob;
					result = true;
					break;
				case 2:
					  this.ladob = this.areaR/this.ladoa;
				  	  this.perimetroR = 2*this.ladoa+2*this.ladob;
					  result = true;
					break;
				case 3:
					this.ladoa = ((this.perimetroR/2)+(Math.sqrt(Math.pow(this.perimetroR/2,2)-4*1*this.areaR)))/2;
					this.ladob = this.areaR/this.ladoa;
					result = true;
					break;	
			}
		} else if (this.figura.equals("TRIANGULO")) {
			double h = 0.0;
			switch (this.caso) {
				case 0:
					  this.alfaT = (Math.pow(this.ladoaT,2)-Math.pow(this.ladobT,2)-Math.pow(this.ladocT,2))/(-2*this.ladobT*this.ladocT);
					  this.betaT = (Math.pow(this.ladobT,2)-Math.pow(this.ladoaT,2)-Math.pow(this.ladocT,2))/(-2*this.ladoaT*this.ladocT);
					  this.alfaT = Math.acos(this.alfaT);
					  this.alfaT = this.alfaT*180/Math.PI;
					  this.betaT = Math.acos(this.betaT);
					  this.betaT = this.betaT*180/Math.PI;
					  this.tetaT = 180-this.betaT-this.alfaT;
					  h = this.ladobT*Math.sin(this.betaT*Math.PI/180);
					  //{area:=ladoc*h/2;}
					  h = (this.ladoaT+this.ladobT+this.ladocT)/2;
					  this.areaT = Math.sqrt(h*(h-this.ladoaT)*(h-this.ladobT)*(h-this.ladocT));
					  this.perimetroT = this.ladoaT+this.ladobT+this.ladocT;
					  result = true;
					break;
				case 1:
					  this.betaT = (this.ladoaT*Math.sin(this.alfaT*Math.PI/180))/this.ladobT;
					  this.betaT = Math.asin(this.betaT);
					  this.betaT = this.betaT*180/Math.PI;
					  this.tetaT = 180-this.betaT-this.alfaT;
					  this.ladocT = (this.ladobT*Math.sin(this.tetaT*Math.PI/180))/Math.sin(this.alfaT*Math.PI/180);
					  h = this.ladobT*Math.sin(this.betaT*Math.PI/180);
					//  area:=this.ladocT*h/2;
					  h = (this.ladoaT+this.ladobT+this.ladocT)/2;
					  this.areaT = Math.sqrt(h*(h-this.ladoaT)*(h-this.ladobT)*(h-this.ladocT));
					  this.perimetroT = this.ladoaT+this.ladobT+this.ladocT;
					  result = true;
					break;
				case 2:
					  this.ladobT=(this.ladoaT*Math.sin(this.alfaT*Math.PI/180))/Math.sin(this.betaT*Math.PI/180);
					  this.tetaT=180-this.alfaT-this.betaT;
					  this.ladocT=(this.ladoaT*Math.sin(this.tetaT*Math.PI/180))/Math.sin(this.betaT*Math.PI/180);
					  h=this.ladobT*Math.sin(this.betaT*Math.PI/180);
					//  area=this.ladocT*h/2;
					  h=(this.ladoaT+this.ladobT+this.ladocT)/2;
					  this.areaT=Math.sqrt(h*(h-this.ladoaT)*(h-this.ladobT)*(h-this.ladocT));
					  this.perimetroT=this.ladoaT+this.ladobT+this.ladocT;
					  result = true;
					break;	
				case 3:
					  h=2*this.areaT/this.ladobT;
					  this.ladocT=Math.sqrt(Math.pow(h,2)+Math.pow(this.ladobT,2));
					  this.alfaT=(Math.pow(this.ladoaT,2)-Math.pow(this.ladobT,2)-Math.pow(this.ladocT,2))/(-2*this.ladobT*this.ladocT);
					  this.betaT=(Math.pow(this.ladobT,2)-Math.pow(this.ladoaT,2)-Math.pow(this.ladocT,2))/(-2*this.ladoaT*this.ladocT);
					  this.alfaT=Math.acos(this.alfaT);
					  this.alfaT=this.alfaT*180/Math.PI;
					  this.betaT=Math.acos(this.betaT);
					  this.betaT=this.betaT*180/Math.PI;
					  this.tetaT=180-this.betaT-this.alfaT;
					  this.perimetroT=this.ladoaT+this.ladobT+this.ladocT;	
					  result = true;
					break;
				case 4:
					  this.ladocT=this.perimetroT-this.ladoaT-this.ladobT;
					  this.alfaT=(Math.pow(this.ladoaT,2)-Math.pow(this.ladobT,2)-Math.pow(this.ladocT,2))/(-2*this.ladobT*this.ladocT);
					  this.betaT=(Math.pow(this.ladobT,2)-Math.pow(this.ladoaT,2)-Math.pow(this.ladocT,2))/(-2*this.ladoaT*this.ladocT);
					  this.alfaT=Math.acos(this.alfaT);
					  this.alfaT=this.alfaT*180/Math.PI;
					  this.betaT=Math.acos(this.betaT);
					  this.betaT=this.betaT*180/Math.PI;
					  this.tetaT=180-this.betaT-this.alfaT;
					  h=this.ladobT*Math.sin(this.betaT*Math.PI/180);
					  h=(this.ladoaT+this.ladobT+this.ladocT)/2;
					  this.areaT=Math.sqrt(h*(h-this.ladoaT)*(h-this.ladobT)*(h-this.ladocT));
					  result = true;
					break;						
			}
		}
		return result;
	}
	
	public String toString() {
		String result = "";
		if (this.figura.equals("CIRCULO")){
			result = "RADIO = " + this.radio + 
					"; ANGULO = " + this.angulo + 
					"; AREA = " + this.area + 
					"; PERIMETRO = " + this.perimetro + 
					"; AREA SECTOR = " + this.area_sector + 
					"; AREA SEGMENTO = " + this.area_segmento;
		} else	if (this.figura.equals("RECTANGULO")){
			result = "LADOA = " + this.ladoa + 
					"; LADOB = " + this.ladob + 
					"; AREA = " + this.areaR + 
					"; PERIMETRO = " + this.perimetroR;
		}  else	if (this.figura.equals("TRIANGULO")){
			result = "LADOA = " + this.ladoaT + 
					"; LADOB = " + this.ladobT + 
					"; LADOC = " + this.ladocT + 
					"; ALFA = " + this.alfaT + 
					"; BETA = " + this.betaT + 
					"; TETA = " + this.tetaT + 
					";AREA = " + this.areaT + 
					"; PERIMETRO = " + this.perimetroT;
		}
		return result;
	}
	
}
