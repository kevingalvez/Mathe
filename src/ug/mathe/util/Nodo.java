package ug.mathe.util;

public class Nodo {
	   /* Declaraciones de variables */
    private String valor;
 
    private Nodo padre;
    private Nodo hojaIzquierda;
    private Nodo hojaDerecha;
 
    /* Constructor */
    public Nodo(String valor) {
        this.valor = valor;
    }
 
    /* Setters y Getters */
    public void setValor(String valor) {
        this.valor = valor;
    }
 
    public String getValor() {
        return valor;
    }
 
    public Nodo getPadre() {
        return padre;
    }
 
    public void setPadre(Nodo padre) {
        this.padre = padre;
    }
 
    public Nodo getHojaIzquierda() {
        return hojaIzquierda;
    }
 
    public void setHojaIzquierda(Nodo hojaIzquierda) {
        this.hojaIzquierda = hojaIzquierda;
    }
 
    public Nodo getHojaDerecha() {
        return hojaDerecha;
    }
 
    public void setHojaDerecha(Nodo hojaDerecha) {
        this.hojaDerecha = hojaDerecha;
    }
 
}
