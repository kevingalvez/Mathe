package ug.mathe.graficador;

import ug.mathe.util.InfixToPostfix;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Graph3d {
    private int scalaX,scalaY, longitudX, longitudY, longitudZ;
    private double limiX, limsX, limiY, limsY, limiZ, limsZ;
    private String expr;
    private int margenX,margenY;
    private double step;
    private double inclinacion, rotacion;
    private double mover = 25*Math.PI/180;
    
    public Graph3d(int x, int y, int margenX, int margenY){
    	Log.i("Graph2D","X="+x+" Y="+y);
        this.scalaX = x-2*margenX;
        this.scalaY = y-2*margenY;
        this.margenX = margenX;
        this.margenY = margenY;
        this.inclinacion = Math.PI/3;
        this.rotacion = 11*Math.PI/6;  
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
    
    public void setLimitesZ(double zi, double zs)
    {
        this.limiZ = zi;
        this.limsZ = zs;
        longitudZ = (int)(zs-zi);
    }    
    
    public void setRotacionUp(){
    	this.rotacion = this.rotacion + mover;
    }

    public void setRotacionDown(){
    	this.rotacion = this.rotacion - mover;
    }
    
    public void setInclinacionLeft(){
    	this.inclinacion = this.inclinacion - mover;
    }
    
    public void setInclinacionRight(){
    	this.inclinacion = this.inclinacion + mover;
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
        
    public int ConvertToPixelX2d(double puntoX)
    {
        return (int)(scalaX*(puntoX-limiX)/longitudX+margenX);
    }
    
    public int ConvertToPixelY2d(double puntoY)
    {
        return (scalaY - (int)(scalaY*(puntoY-limiY)/longitudY+margenY));
    }
    
    public int ConvertToPixelX3d(double puntoX, double puntoY, double puntoZ)
    {
    	return  ConvertToPixelX2d(puntoX*Math.sin(inclinacion)-puntoY*Math.cos(inclinacion));
    }
    
    public int ConvertToPixelY3d(double puntoX, double puntoY, double puntoZ)
    {
        return  ConvertToPixelY2d((puntoX*Math.cos(inclinacion)*Math.sin(rotacion))  + (puntoY*Math.sin(inclinacion)*Math.sin(rotacion)) + (puntoZ*Math.cos(rotacion)) );
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
    
    public void dibujaplano(Canvas canvas, Paint paint){
    	canvas.drawLine(ConvertToPixelX3d(this.limiX,0,0),ConvertToPixelY3d(this.limiX,0,0),ConvertToPixelX3d(this.limsX,0,0),ConvertToPixelY3d(this.limsX,0,0),paint);
        canvas.drawLine(ConvertToPixelX3d(0,this.limiY,0),ConvertToPixelY3d(0,this.limiY,0),ConvertToPixelX3d(0,this.limsY,0),ConvertToPixelY3d(0,this.limsY,0),paint);
        canvas.drawLine(ConvertToPixelX3d(0,0,this.limiZ),ConvertToPixelY3d(0,0,this.limiZ),ConvertToPixelX3d(0,0,this.limsZ),ConvertToPixelY3d(0,0,this.limsZ),paint);
       
        canvas.drawText("X",ConvertToPixelX3d(this.limsX+0.5,0,0),ConvertToPixelY3d(this.limsX+0.5,0,0),paint);
        canvas.drawText("Y",ConvertToPixelX3d(0,this.limsY+0.5,0),ConvertToPixelY3d(0,this.limsY+0.5,0),paint);
        canvas.drawText("Z",ConvertToPixelX3d(0,0,this.limsZ+0.5),ConvertToPixelY3d(0,0,this.limsZ+0.5),paint);        
    }
    
    public void graficar(Canvas canvas, Paint paint) {
        try 
        {
            //InfixToPostfix a = new InfixToPostfix(grap.expr);
        	InfixToPostfix a = new InfixToPostfix(this.expr);
            double puntoY = this.limiY;
            double puntoX = 0.0;
            double puntoZ = 0.0;
            boolean iter = true;
            double[] vector = new double[2];
            int actX = 0,actY = 0, alt = 1;
            paint.setColor(Color.parseColor("#DF0101"));
            boolean asinto = false;
  		  	int lineaX = 0;
  		  	int lineaY = 0;
  		  	double paso3 = step;
  		  	double paso4 = step;
  		  	
  		  	
            while (puntoY <= this.limsY) {
            	  try {
            		  puntoZ = (a.evaluar(this.limiX,puntoY)*alt);
            		  lineaX = ConvertToPixelX3d(this.limiX,puntoY, puntoZ);
                      lineaY = ConvertToPixelY3d(this.limiX,puntoY,puntoZ);
                      //canvas.drawPoint(ConvertToPixelX3d(this.limiX,puntoY,(a.evaluar(this.limiX,puntoY)*alt)), ConvertToPixelY3d(this.limiX,puntoY,(a.evaluar(this.limiX,puntoY)*alt)), paint);
                      
            	  }
            	  catch (Exception e)
            	  {
            		  asinto = true;
            	  }
            	  
            	  puntoX = this.limiX;
            	  asinto = false;
            	  while (puntoX <= this.limsX) {
            		  try {
            		  puntoZ = a.evaluar(puntoX,puntoY)*alt;
            		  vector[1] = puntoZ;
            		  actX =  ConvertToPixelX3d(puntoX,puntoY, puntoZ);
            		  actY = ConvertToPixelY3d(puntoX,puntoY,puntoZ);
            		  
            		  
            		  if (!asinto) {
            			  canvas.drawLine(lineaX, lineaY, actX, actY,paint);
                          lineaX = actX;
                          lineaY = actY;
            		  } else {
                          lineaX = actX;
                          lineaY = actY;
                          asinto = false;
            		  }

            		  } catch (Exception ex) {
            			  asinto = true;
            		  }
            		    puntoX = puntoX + paso3;            		  
            		  
            		    /*if (asinto) {
            		     try {
            		    		 canvas.drawLine(lineaX, lineaY, actX, actY,paint);           		    		 
            		     } catch (Exception ex) {
            		     }
            		     asinto = false;
            		     //control = true;
            		    }
            		    else if ((!asinto) && (((vector[0]+ 2 *((this.scalaY / 2) -30)/12.56) < vector[1]) || ((vector[0]-2*((this.scalaY / 2) -30)/12.56) > vector[1]))) {
            		     try {
            		    	 canvas.drawLine(lineaX, lineaY, actX, actY,paint);
            		     } catch (Exception ex) {
            		    	 
            		     }
            		     //image2.Canvas.Pen.Color:= clblue;
            		     try {
            		    	 //canvas.drawLine(lineaX, lineaY, ConvertToPixelX3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)), ConvertToPixelY3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)),paint);
            		    	 //canvas.drawPoint(ConvertToPixelX3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)), ConvertToPixelY3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)), paint);
            		    	 canvas.drawLine(lineaX, lineaY, actX, actY,paint);
            		    	 vector[0] = puntoZ;
            		     } catch (Exception ex) {
            		    	 
            		     }
            		    } else {
            		     try {
            		     vector[0] = puntoZ;
            		     } catch (Exception ex) {
            		    	 
            		     }
            		     asinto = true;
            		    }
            		    puntoX = puntoX + paso3;
                        lineaX = actX;
                        lineaY = actY;*/
            	  }
            	  puntoY = puntoY + paso4;
            }
            
            puntoX = this.limiX;
            asinto = false;
            while (puntoX <= this.limsX) {
             try {
            	puntoZ = (a.evaluar(puntoX,this.limiX)*alt);
       		  	lineaX = ConvertToPixelX3d(puntoX,this.limiX, puntoZ);
       		  	lineaY = ConvertToPixelY3d(puntoX,this.limiX, puntoZ); 
   		  	
             } catch (Exception ex) {
            	 asinto = true;
             }

             puntoY = this.limiY;
             while (puntoY <= this.limsY) {
               try {
               puntoZ =(a.evaluar(puntoX,puntoY)*alt);
               vector[1] = puntoZ;
     		  actX =  ConvertToPixelX3d(puntoX,puntoY,puntoZ);
     		  actY = ConvertToPixelY3d(puntoX,puntoY,puntoZ);
      		  if (!asinto) {
    			  canvas.drawLine(lineaX, lineaY, actX, actY,paint);
                  lineaX = actX;
                  lineaY = actY;
    		  } else {
                  lineaX = actX;
                  lineaY = actY;
                  asinto = false;
    		  }   
               }
               catch (Exception ex) {
            	   asinto = true;
               }
               puntoY = puntoY + paso4;
               //image2.canvas.Pen.color:=clblue;

              /* if (asinto) {
                try {
                	//canvas.drawPoint( ConvertToPixelX3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)) ,ConvertToPixelY3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)),paint );
                	canvas.drawLine(lineaX, lineaY, actX, actY,paint);
                } catch (Exception ex) {
                	
                }
                asinto = false;
                //control = true;
               }
               else
               if ((!asinto) && (((vector[0]+2*((this.scalaY / 2) -30)/12.56)<vector[1]) || ((vector[0]-2*((this.scalaY / 2) -30)/12.56)>vector[1]))) {
                try {
                		//canvas.drawPoint( ConvertToPixelX3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)) ,ConvertToPixelY3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)),paint );
                		canvas.drawLine(lineaX, lineaY, actX, actY,paint);
                } catch (Exception ex) {
                	
                }
               
                //image2.Canvas.Pen.Color:= clblue;
                try {
                	//canvas.drawPoint( ConvertToPixelX3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)) ,ConvertToPixelY3d(puntoX,puntoY,(a.evaluar(puntoX,puntoY)*alt)),paint );
                	canvas.drawLine(lineaX, lineaY, actX, actY,paint);
                	vector[0] = puntoZ;
                } catch (Exception ex) {
                	
                }
               } else {
                try {
                	vector[0] = puntoZ;
                } catch (Exception ex) {
                	
                }
                asinto = true;
               }
               puntoY = puntoY + paso4;
               lineaX = actX;
               lineaY = actY;*/
             }
             puntoX = puntoX + paso3;
            }
                      
            /*while (puntoX < this.limsX)
            {
            	double puntoY = 0.0;
                try
                {
                	puntoY = a.evaluar(puntoX);
                    int lineaY = this.ConvertToPixelY2d(puntoY);
                    int lineaX = this.ConvertToPixelX2d(puntoX);
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
            }*/              
    } catch (Exception e) 
    {
        //System.err.println("Exception: " + e.getMessage());
    }  
    }
}
