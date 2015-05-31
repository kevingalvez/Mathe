package ug.mathe.graficador;

import ug.mathe.util.InfixToPostfix;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Graph2d {
    private int scalaX,scalaY, longitudX, longitudY;
    private double limiX, limsX, limiY, limsY;
    private String expr;
    private int margenX,margenY;
    private double step;
    private double[] puntos;
    private int ind;
    private boolean ready;

    
    
    public Graph2d(int x, int y, int margenX, int margenY){
    	Log.i("Graph2D","X="+x+" Y="+y);
        this.scalaX = x-2*margenX;
        this.scalaY = y-2*margenY;
        this.margenX = margenX;
        this.margenY = margenY;
        puntos = new double[2];
        ind = 0;
        ready = false;
    }
    
    public void setPunto(double punto) {
  
    	if (ind < 2) {
    		puntos[ind] = punto;
    		ind++;
    	}
    }
       
    public boolean isReady() {
    	return this.ready;
    }
    
    public double getArea(Canvas canvas, Paint paint) throws Exception {
    	double puntoX, puntoY, result = 0;
    	int lineaY, actY, actX;
    	try {
        if (this.ready) {
        	InfixToPostfix a = new InfixToPostfix(this.expr);
        	paint.setColor(Color.parseColor("#336600"));
        	puntoX = puntos[0];
        	lineaY = ConvertToPixelY(0);
        	result =-1;
        	while (puntoX < puntos[1]) {
          		puntoY = a.evaluar(puntoX);
            	actY = this.ConvertToPixelY(puntoY);
            	actX = this.ConvertToPixelX(puntoX);
        		canvas.drawLine(actX, lineaY, actX, actY, paint);
        		result += Math.abs(puntoX*puntoY);
        		puntoX +=this.step;
        	}
        }
    	} catch (Exception ex) {
    		result = 0;
    	}
    	return result;
    }
    
    public double getInterseccion() throws Exception{
    	double result = 0;
    	try {
    		if (this.ready){
	    		InfixToPostfix a = new InfixToPostfix(this.expr);
	        	double li = puntos[0], ls = puntos[1], pn = 0, error = -1;
	        	int itera = 100;
	        	boolean punto = false;
	        	double tolerancia = 1/Math.pow(10, 10);
	        	for (int i = 0; (i <= itera)&&((!punto)||(error != 0)); i++) {
	        		pn = (li+ls)/2;
	        		
	        		if (a.evaluar(li)*a.evaluar(pn) >= 0) {
	        			li = pn;
	        		}
	        		
	        		if (a.evaluar(ls)*a.evaluar(pn) >= 0) {
	        			ls = pn;
	        		}
	        		
	        		error = Math.abs(ls-li)/Math.pow(2, 100);
	        		if (Math.abs(a.evaluar(pn)) <= tolerancia) {
	        			punto = true;
	        		}
	        	}
	        	if (punto) 
	        		result = pn;
	        	else
	        		throw new Exception("No se encontro interseccion");
	        } 
    	} catch (Exception ex) {
    		throw new Exception(ex.getMessage());
    	}
    	return result;
    }
    
    public void setLimitesX(double xi, double xs)
    {
        this.limiX = xi;
        this.limsX = xs;
        longitudX = (int)(xs-xi);
    }
    
    public void setLimitesY(double yi, double ys)
    {
        this.limiY = yi;
        this.limsY = ys;
        longitudY = (int)(ys-yi);
    }
    
    public void setZoomIn(){
    	this.limiX -= 1;
    	this.limiY -= 1;
    	this.limsX += 1;
    	this.limiY += 1;
    	longitudY = (int)(this.limsY-this.limiY);
    	longitudX = (int)(this.limsX-this.limiX);
    }
    
    public void setZoomOut(){
    	this.limiX += 1;
    	this.limiY += 1;
    	this.limsX -= 1;
    	this.limiY -= 1;
    	longitudY = (int)(this.limsY-this.limiY);
    	longitudX = (int)(this.limsX-this.limiX);    	
    }
    
    public void setMove(int x, int y){
    	this.limsX += x;
    	this.limiX = this.limsX-this.longitudX;
    	this.limsY += y;    	
    	this.limiY = this.limsY-this.longitudY;
    }
        
    public void setStep(int presicion)
    {
        step = (double)1/presicion;
    }    
    
    public int ConvertToPixelX(double puntoX)
    {
        return (int)(scalaX*(puntoX-limiX)/longitudX+margenX);
    }
    
    public int ConvertToPixelY(double puntoY)
    {
        return (scalaY - (int)(scalaY*(puntoY-limiY)/longitudY+margenY));
    }
    
    public double ConvertToPuntoX(double pixelX)
    {
        return ((pixelX-margenX)*longitudX+limiX*scalaX)/scalaX;
    }
    
    public double ConvertToPuntoY(double pixelY)
    {
        return (scalaY*limiY-longitudY*(pixelY-scalaY-margenY))/scalaY;
    }    
    
    public void setExpr(String expr)
    {
        this.expr = expr;
    }
    
    public void dibujalinea(Canvas canvas, Paint  paint) {
    	if (ind > 0) {
	    	int y1 = ConvertToPixelY(this.limiY);
	    	int y2 = ConvertToPixelY(this.limsY);            
	        for (int i = 0; i >=0 && i < 2; i++) {
	        	int x = ConvertToPixelX(puntos[i]);
	        	canvas.drawLine(x, y1, x, y2, paint);
	        }
    	}
    }
    
    public void dibujaplano(Canvas canvas, int origenX, int origenY, Paint paint){
    	int lineaX = origenX, lineaY=origenY;

        canvas.drawLine(this.margenX*2, lineaY, this.scalaX, lineaY, paint);
        canvas.drawLine(lineaX, this.margenY*2, lineaX, this.scalaY, paint);    	
    	
        for (int i=1;i <= this.limsX;i++)
        {
        	int nuevaX =this.ConvertToPixelX(i);
        	canvas.drawLine(nuevaX, lineaY-5, nuevaX, lineaY+5, paint);
        	canvas.drawText(String.valueOf(i), nuevaX-2, lineaY+15, paint);
        }
        
        for (int i=1;i <= this.limsY;i++)
        {
        	int nuevaY =this.ConvertToPixelY(i);
        	canvas.drawLine(lineaX-5, nuevaY, lineaX+5, nuevaY, paint);
        	canvas.drawText(String.valueOf(i), lineaX-15, nuevaY, paint);
        }
        
        for (int i=-1;i >= this.limiX;i--)
        {
        	int nuevaX =this.ConvertToPixelX(i);
        	canvas.drawLine(nuevaX, lineaY-5, nuevaX, lineaY+5, paint);
        	canvas.drawText(String.valueOf(i), nuevaX-2, lineaY+15, paint);
        }
        
        for (int i=-1;i >= this.limiY;i--)
        {
        	int nuevaY =this.ConvertToPixelY(i);
        	canvas.drawLine(lineaX-5, nuevaY, lineaX+5, nuevaY, paint);
        	canvas.drawText(String.valueOf(i), lineaX-15, nuevaY, paint);
        }    	
    }
    
    public void graficar(Canvas canvas, Paint paint) {
    	if (this.expr.charAt(0) != '#') {
	        try 
	        {
	            //InfixToPostfix a = new InfixToPostfix(grap.expr);
	        	InfixToPostfix a = new InfixToPostfix(this.expr);
	            double puntoX = this.limiX;
	            boolean iter = true;
	            int actX = 0,actY = 0, lineaY = 0, lineaX = 0;
	            paint.setColor(Color.parseColor("#DF0101"));
	            while (puntoX < this.limsX)
	            {
	            	double puntoY = 0.0;
	                try
	                {
	              		puntoY = a.evaluar(puntoX);
	                	actY = this.ConvertToPixelY(puntoY);
	                	actX = this.ConvertToPixelX(puntoX);
	                    if (iter)
	                    {
	                        canvas.drawLine(lineaX, lineaY, lineaX, lineaY,paint);
	                    	//canvas.drawCircle(lineaX, lineaY, 1, paint);
	                        lineaX = actX;
	                        lineaY = actY;
	                        iter = false;
	                    }
	                    else
	                    {
	                    	//canvas.drawLine(actX, actY, lineaX, lineaY,paint);
	                    	canvas.drawLine(lineaX, lineaY, actX, actY, paint);
	                    	//canvas.drawCircle(actX, actY, 1, paint);
	                        lineaX = actX;
	                        lineaY = actY;
	                        
	                    }
	                    /*actX = lineaX;
	                    actY = lineaY;
	                    iter = false;*/
	                } catch (Exception e) {
	                	Log.i("Graficador",e.getMessage());
	                	iter = true;
	                }
	                puntoX +=this.step;	                    		                    
	            }
	            dibujalinea(canvas, paint);
	            ready = false;
	            if (ind == 2)
	            	ready = true;
	        } catch (Exception e) 
	        {
	            //System.err.println("Exception: " + e.getMessage());
	        }    	
    	} else {
    		if (this.expr.contains("Circ")) {
    			int idxizq = this.expr.indexOf("(");
    			int idxder = this.expr.indexOf(")");
    			String param = this.expr.substring(idxizq + 1,idxder - 1);
    			String[] valueparam = param.split(",");
    			double radio = Double.parseDouble(valueparam[0]);
    			double angulo = Double.parseDouble(valueparam[1]);
    			
    			paint.setColor(Color.parseColor("#DF0101"));
    			paint.setStyle(Paint.Style.STROKE);
    			canvas.drawOval(new RectF((float)this.ConvertToPixelX(-radio),(float)this.ConvertToPixelY(radio),(float)this.ConvertToPixelX(radio),(float)this.ConvertToPixelY(-radio)), paint);
    			
    			canvas.drawLine((float)this.ConvertToPixelX(0), (float)this.ConvertToPixelY(0), (float)this.ConvertToPixelX(radio*Math.cos(angulo*Math.PI/180)), (float)this.ConvertToPixelY(radio*Math.sin(angulo*Math.PI/180)),paint);
    			canvas.drawLine((float)this.ConvertToPixelX(radio*Math.cos(angulo*Math.PI/180)), (float)this.ConvertToPixelY(radio*Math.sin(angulo*Math.PI/180)),(float)this.ConvertToPixelX(radio), (float)this.ConvertToPixelY(0),paint);
    			
    			canvas.drawLine((float)this.ConvertToPixelX(0), (float)this.ConvertToPixelY(0), (float)this.ConvertToPixelX(radio), (float)this.ConvertToPixelY(0),paint);
    		} else if (this.expr.contains("Rect")) {
    			int idxizq = this.expr.indexOf("(");
    			int idxder = this.expr.indexOf(")");
    			String param = this.expr.substring(idxizq + 1,idxder - 1);
    			String[] valueparam = param.split(",");
    			double ladoa = Double.parseDouble(valueparam[0]);
    			double ladob = Double.parseDouble(valueparam[1]);
    			
    			paint.setColor(Color.parseColor("#DF0101"));
    			paint.setStyle(Paint.Style.STROKE);
    			canvas.drawRect((float)this.ConvertToPixelX(0), (float)this.ConvertToPixelY(ladob), (float)this.ConvertToPixelX(ladoa), (float)this.ConvertToPixelY(0), paint);
    		} else if (this.expr.contains("Trian")) {
    			int idxizq = this.expr.indexOf("(");
    			int idxder = this.expr.indexOf(")");
    			String param = this.expr.substring(idxizq + 1,idxder - 1);
    			String[] valueparam = param.split(",");
    			double ladoaT = Double.parseDouble(valueparam[0]);
    			double ladocT = Double.parseDouble(valueparam[1]);
    			double angbeta = Double.parseDouble(valueparam[2]);
    			
    			paint.setColor(Color.parseColor("#DF0101"));
    			paint.setStyle(Paint.Style.STROKE);
    			
    			canvas.drawLine((float)this.ConvertToPixelX(0), (float)this.ConvertToPixelY(0), (float)this.ConvertToPixelX(ladocT*Math.cos(angbeta*Math.PI/180)), (float)this.ConvertToPixelY(ladocT*Math.sin(angbeta*Math.PI/180)), paint);
    			canvas.drawLine((float)this.ConvertToPixelX(ladocT*Math.cos(angbeta*Math.PI/180)), (float)this.ConvertToPixelY(ladocT*Math.sin(angbeta*Math.PI/180)), (float)this.ConvertToPixelX(ladoaT), (float)this.ConvertToPixelY(0), paint);
    			canvas.drawLine((float)this.ConvertToPixelX(ladoaT), (float)this.ConvertToPixelY(0), (float)this.ConvertToPixelX(0), (float)this.ConvertToPixelY(0), paint);
    			   			
    		}
    	}
    }
   
}
