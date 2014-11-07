package ug.mathe.util;

public class Numero {
	private double coeficiente, exponente;
	private String variable;
	
	public Numero(double coeficiente, String variable, double exponente) {
		this.coeficiente = coeficiente;
		this.variable = variable;
		this.exponente = exponente;
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
	
	public void getCof(double coeficiente){
		this.coeficiente = coeficiente;
	}
	
	public void getExp(double exponente) {
		this.exponente =  exponente;
	}	
	
	public void getVar(String variable) {
		this.variable = variable;
	}
}
