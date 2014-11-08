package ug.mathe.util;

public class Numero {
	private double coeficiente, exponente;
	private String variable;
	
	public Numero(double coeficiente, String variable, double exponente) {
		this.coeficiente = coeficiente;
		this.variable = variable;
		this.exponente = exponente;
	}
	
	public Numero(Numero datos) {
		this.coeficiente = datos.coeficiente;
		this.variable = datos.variable;
		this.exponente = datos.exponente;
	}	
	
	public double getCof(){
		return this.coeficiente;
	}
	
	public double getExp() {
		return this.exponente;
	}
	
	public String getVar() {
		return this.variable;
	}
	
	public void setCof(double coeficiente){
		this.coeficiente = coeficiente;
	}
	
	public void setExp(double exponente) {
		this.exponente =  exponente;
	}	
	
	public void setVar(String variable) {
		this.variable = variable;
	}
	
}
