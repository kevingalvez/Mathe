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
		}
		this.figura = fig;
		this.caso = caso;
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
		}
		return result;
	}
	
	public String toString() {
		String result = "";
		if (this.figura.equals("CIRCULO")){
			result = "RADIO = " + this.radio + "; ANGULO = " + this.angulo + "; AREA = " + this.area + "; PERIMETRO = " + this.perimetro + "; AREA SECTOR = " + this.area_sector + "; AREA SEGMENTO = " + this.area_segmento;
		}
		return result;
	}
	
}
