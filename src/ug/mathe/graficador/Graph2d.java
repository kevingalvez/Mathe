package ug.mathe.graficador;

import ug.mathe.util.InfixToPostfix;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Graph2d {
    private int scalaX,scalaY, longitudX, longitudY;
    private double limiX, limsX, limiY, limsY;
    private String expr;
    private int margenX,margenY;
    private double step;
    
    public Graph2d(int x, int y, int margenX, int margenY){
    	Log.i("Graph2D","X="+x+" Y="+y);
        this.scalaX = x-2*margenX;
        this.scalaY = y-2*margenY;
        this.margenX = margenX;
        this.margenY = margenY;
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
    
    public void setExpr(String expr)
    {
        this.expr = expr;
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
        try 
        {
            //InfixToPostfix a = new InfixToPostfix(grap.expr);
        	InfixToPostfix a = new InfixToPostfix(this.expr);
            double puntoX = this.limiX;
            boolean iter = true;
            int actX = 0,actY = 0;
            paint.setColor(Color.parseColor("#DF0101"));
            while (puntoX < this.limsX)
            {
            	double puntoY = 0.0;
                try
                {
                	puntoY = a.evaluar(puntoX);
                    int lineaY = this.ConvertToPixelY(puntoY);
                    int lineaX = this.ConvertToPixelX(puntoX);
                    if (iter)
                        canvas.drawLine(lineaX, lineaY, lineaX, lineaY,paint);
                    	//canvas.drawCircle(lineaX, lineaY, 1, paint);
                    else
                    	//canvas.drawLine(actX, actY, lineaX, lineaY,paint);
                    	canvas.drawLine(lineaX, lineaY, actX, actY, paint);
                    	//canvas.drawCircle(actX, actY, 1, paint);
                    
                    actX = lineaX;
                    actY = lineaY;
                    iter = false;
                } catch (Exception e) {
                	Log.i("Graficador",e.getMessage());
                	iter = true;
                }
                puntoX +=this.step;	                    		                    
            }
        } catch (Exception e) 
        {
            //System.err.println("Exception: " + e.getMessage());
        }    	
    }
   
}
