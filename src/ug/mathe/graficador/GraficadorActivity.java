package ug.mathe.graficador;

import java.util.LinkedList;
import java.util.Queue;

import ug.mathe.MainActivity;
import ug.mathe.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("ClickableViewAccessibility")
public class GraficadorActivity extends Activity {

	Button btn_graficar;
	Graph2d grap;
	String[] funciones;
	String[] parametros;
	String menu;
	public int algo = 0;
	double ini_x, ini_y, fin_x, fin_y;
	boolean normal = true, integrales, interseccion, area;
	double area_comb = 0.0;
	Canvas canvas;
	private SparseArray<PointF> mActivePointers;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graficador);
		
		EditText a = (EditText)findViewById(R.id.txtFuncion);
		a.setKeyListener(null);
		
		mActivePointers = new SparseArray<PointF>();
		Bundle bundle = getIntent().getExtras();
		//funciones = bundle.getString("funciones").split(";");
		EditText func = (EditText) findViewById(R.id.txtFuncion);
		func.setText(bundle.getString("funciones"));
		parametros = bundle.getString("parametros").split(",");
		menu = bundle.getString("menu");
		btn_graficar = (Button) findViewById(R.id.btn_graficar);
		btn_graficar.setOnClickListener(new OnClickListener() {

	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
			    
		        Paint paint = new Paint();
		        paint.setColor(Color.parseColor("#000000"));
		        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
		        
				
		        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
		        
				
		        canvas = new Canvas(bg);

			    grap = new Graph2d(ll.getWidth(),ll.getHeight(),5,5);

			    if (parametros.length > 0) {
			    	
				    grap.setLimitesX(Double.valueOf(parametros[0]) , Double.valueOf(parametros[1]));
				    grap.setLimitesY(Double.valueOf(parametros[2]), Double.valueOf(parametros[3]));			    
			    } else {
				    grap.setLimitesX( -5, 5);
				    grap.setLimitesY(-10, 10);
			    }
			    grap.setStep(100); 		        
		        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
		        
		        for (int i = 0; i < func.length;i++) {
			        grap.setExpr(func[i].toString());
			        grap.graficar(canvas, paint);
		        }
		        //-------------------------------				
		        
		        ll.setBackgroundDrawable(new BitmapDrawable(bg));				
			}
		});
		
		LinearLayout rel = (LinearLayout)findViewById(R.id.graph2d);
		rel.setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (normal) {
				try {
			    // get pointer index from the event object
			    int pointerIndex = event.getActionIndex();

			    // get pointer ID
			    int pointerId = event.getPointerId(pointerIndex);

			    // get masked (not specific to a pointer) action
			    int maskedAction = event.getActionMasked();

			    switch (maskedAction) {

			    case MotionEvent.ACTION_DOWN:
			    case MotionEvent.ACTION_POINTER_DOWN: {
			      // We have a new pointer. Lets add it to the list of pointers

			      PointF f = new PointF();
			      f.x = event.getX(pointerIndex);
			      f.y = event.getY(pointerIndex);
			      mActivePointers.put(pointerId, f);
			      break;
			    }
			    case MotionEvent.ACTION_MOVE:  
			    	// a pointer was moved
			      break;
			    case MotionEvent.ACTION_UP:
			    case MotionEvent.ACTION_POINTER_UP:
			    case MotionEvent.ACTION_CANCEL: {
			    	if (event.getPointerCount() == 1) {
				        PointF point = mActivePointers.get(event.getPointerId(0));
				        if (point != null) {
				        	ini_x = point.x;
				    	    ini_y = point.y;
				    	    fin_x = event.getX();
				    	    fin_y = event.getY();
				        }
			    		
			            if ((ini_x-fin_x) > 100)
			            	grap.setMove(1, 0);
			            else if (ini_x-fin_x < -100)
			            	grap.setMove(-1, 0);
			            	
			            
			            if (ini_y-fin_y > 100)
			            	grap.setMove(0, -1);
			            else if (ini_y-fin_y < -100)
			            	grap.setMove(0, 1);

			            
						LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
					    
				        Paint paint = new Paint();
				        paint.setColor(Color.parseColor("#000000"));
				        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
				        
						
				        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
				        
				        canvas = new Canvas(bg);
				     		        
				        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
				        
				        for (int i = 0; i < func.length;i++) {
					        grap.setExpr(func[i].toString());
					        grap.graficar(canvas, paint);
				        }
				        //-------------------------------				
				        
				        ll.setBackgroundDrawable(new BitmapDrawable(bg));
			    	} else if (event.getPointerCount() == 2) {	    		
			    		if (Math.abs(event.getY(0) - event.getY(1)) < 200) {
							LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
						    
					        Paint paint = new Paint();
					        paint.setColor(Color.parseColor("#000000"));
					        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
					        
					        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
					        
					        canvas = new Canvas(bg);
					     		        
					        grap.setZoomIn();
					        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
					        
					        for (int i = 0; i < func.length;i++) {
						        grap.setExpr(func[i].toString());
						        grap.graficar(canvas, paint);
					        }
					        //-------------------------------				
					        
					        ll.setBackgroundDrawable(new BitmapDrawable(bg));
			    		} else 	if (Math.abs(event.getY(0) - event.getY(1)) > 400) {
								LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
							    
						        Paint paint = new Paint();
						        paint.setColor(Color.parseColor("#000000"));
						        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
						        
								
						        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
						        
						        canvas = new Canvas(bg);
						     		        
						        grap.setZoomOut();
						        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
						        
						        for (int i = 0; i < func.length;i++) {
							        grap.setExpr(func[i].toString());
							        grap.graficar(canvas, paint);
						        }
						        //-------------------------------				
						        
						        ll.setBackgroundDrawable(new BitmapDrawable(bg));
			    		}
			    	}
			    	mActivePointers.remove(pointerId);
			    	break;
			    }
			    }
			    //invalidate();


				} catch (Exception ex) {
						Log.e(MainActivity.TAG,GraficadorActivity.class.toString() + " - Error en Touch!");
						return false;
				}
				// TODO Auto-generated method stub
				
				/*Log.i("TOUCH","evento="+String.valueOf((int)(e.getAction())));
			    switch (e.getAction()) {
		    	case MotionEvent.ACTION_DOWN:
		    	    ini_x = e.getX();
		    	    ini_y = e.getY();
		    		break;
		        case MotionEvent.ACTION_MOVE:
		        {
		            fin_x = e.getX();
		            fin_y = e.getY();
		            //Log.i("TOUCH",String.valueOf((int)(ini_x-fin_x))+","+String.valueOf((int)(ini_y-fin_y)));
		            if ((ini_x-fin_x) > 100)
		            	grap.setMove(-1, 0);
		            else if (ini_x-fin_x < -100)
		            	grap.setMove(1, 0);
		            	
		            
		            if (ini_y-fin_y > 100)
		            	grap.setMove(0, -1);
		            else if (ini_y-fin_y < -100)
		            	grap.setMove(0, 1);
		            			            
		            
					LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
				    
			        Paint paint = new Paint();
			        paint.setColor(Color.parseColor("#000000"));
			        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
			        
					
			        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
			        
			        Canvas canvas = new Canvas(bg);
			     		        
			        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
			        
			        //-------------------------------				
			        
			        ll.setBackgroundDrawable(new BitmapDrawable(bg));				

		        }   
		            break;
		            
		        case MotionEvent.ACTION_UP:
		        {
					LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
				    
			        Paint paint = new Paint();
			        paint.setColor(Color.parseColor("#000000"));
			        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
			        
					
			        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
			        
			        Canvas canvas = new Canvas(bg);
			     		        
			        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
			        
			        for (int i = 0; i < func.length;i++) {
				        grap.setExpr(func[i].toString());
				        grap.graficar(canvas, paint);
			        }
			        //-------------------------------				
			        
			        ll.setBackgroundDrawable(new BitmapDrawable(bg));
		        }
		            break;
		        case MotionEvent.ACTION_POINTER_1_UP: break;
		    }
				
   			 return false;*/
				} else if (integrales) {
					switch (event.getAction()) {
			    		case MotionEvent.ACTION_DOWN:
			    			ini_x = event.getX();
			    			ini_y = event.getY();
			    		break;
			    		case MotionEvent.ACTION_UP:
							LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
						    
					        Paint paint = new Paint();
					        paint.setColor(Color.parseColor("#000000"));
					        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
					        
							
					        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
					        
					        Canvas canvas = new Canvas(bg);
					     		
					        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
					        
					        grap.setPunto(grap.ConvertToPuntoX(ini_x));

					        for (int i = 0; i < func.length;i++) {
						        grap.setExpr(func[i].toString());
						        grap.graficar(canvas, paint);
					        }
					        try 
					        {
						        if (grap.isReady()) {
						        	Toast.makeText(getApplicationContext(), "Area Total = "+grap.getArea(canvas,paint), Toast.LENGTH_LONG).show();
						        }
					        } catch (Exception ex) {
					        	
					        }
					        //-------------------------------				
					        
					        ll.setBackgroundDrawable(new BitmapDrawable(bg));
		    			break;
					}
				} else if (interseccion) {
					switch (event.getAction()) {
		    		case MotionEvent.ACTION_DOWN:
		    			ini_x = event.getX();
		    			ini_y = event.getY();
		    		break;
		    		case MotionEvent.ACTION_UP:
						LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
					    
				        Paint paint = new Paint();
				        paint.setColor(Color.parseColor("#000000"));
				        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
				        
						
				        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
				        
				        Canvas canvas = new Canvas(bg);
				     		
				        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
				        
				        grap.setPunto(grap.ConvertToPuntoX(ini_x));
				        for (int i = 0; i < func.length;i++) {
					        grap.setExpr(func[i].toString());
					        grap.graficar(canvas, paint);
				        }
				        try {
				        	if (grap.isReady()) {
				        		Toast.makeText(getApplicationContext(), "Interseccion = "+grap.getInterseccion(), Toast.LENGTH_LONG).show();
				        	}
				        } catch (Exception ex) {
				        	Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
				        }
				        //-------------------------------				
				        
				        ll.setBackgroundDrawable(new BitmapDrawable(bg));
	    			break;
				}
				} else if (area) {
					switch (event.getAction()) {
		    		case MotionEvent.ACTION_DOWN:
		    			ini_x = event.getX();
		    			ini_y = event.getY();
		    		break;
		    		case MotionEvent.ACTION_UP:
						LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
					    
				        Paint paint = new Paint();
				        paint.setColor(Color.parseColor("#000000"));
				        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
				        
						
				        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
				        
				        Canvas canvas = new Canvas(bg);
				     		
				        //grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
				        
				        grap.setPunto(grap.ConvertToPuntoX(ini_x));

				        for (int i = 0; i < func.length;i++) {
					        grap.setExpr(func[i].toString());
					        grap.graficar(canvas, paint);
				        }
				        try 
				        {
				        	paint.setColor(Color.parseColor("#336600"));
				        	int x = (int)ini_x;
				        	int y = (int)ini_y;
				        	
				        	final Point p1 = new Point();
				        	p1.x=(int) x; //x co-ordinate where the user touches on the screen
				        	p1.y=(int) y; //y co-ordinate where the user touches on the screen  

				        	area_comb = 0.0;
				        	FloodFill(bg, p1, bg.getPixel(x, y), Color.parseColor("#336600"));
				        	
				        	/*for (int j = 1; j < ll.getHeight(); j++)
				        		for (int i = 1; i < ll.getWidth(); i++)
				        			if (bg.getPixel(i, j) == Color.parseColor("#336600"))
				        				//Log.i("VALUAR","P("+i+","+j+")="+grap.ConvertToPuntoX(i));
				        				area_comb += 0.0007257;//Math.abs(grap.ConvertToPuntoX(i));*/
				        	/*canvas.drawPoint(x, y, paint);
				        	canvas.drawColor(Color.parseColor("#336600"));
				        	Path path = new Path();
				        	while (bg.getPixel(x, y) != Color.parseColor("#DF0101")) {
				        		//path.moveTo(x, y);
					        	while (bg.getPixel(x, y) != Color.parseColor("#DF0101")) {
					        		canvas.drawPoint(x, y, paint);
					        		x++;
					        		//path.lineTo(x, y);
					        	}
					        	//canvas.drawPath(path, paint);
					        	x = (int)ini_x;
					        	i++;
					        	y++;
					        	
				        	}*/
				        	
				        	
				        	
				        	//Toast.makeText(getApplicationContext(), "Color: " + bg.getPixel(grap.ConvertToPixelX(-5), grap.ConvertToPixelY(0)), Toast.LENGTH_LONG).show();
				        	//Toast.makeText(getApplicationContext(), "Color2: " + Color.parseColor("#DF0101") , Toast.LENGTH_LONG).show();
				        	
				        	//fillarea((int)ini_x, (int)ini_y, ll.getWidth(), ll.getHeight(), bg, canvas, paint);
				        	Toast.makeText(getApplicationContext(), "Area Total = " + area_comb, Toast.LENGTH_LONG).show();
				        } catch (Exception ex) {
				        	Toast.makeText(getApplicationContext(), "Error: " + ex.getMessage(), Toast.LENGTH_LONG).show();
				        }
				        //-------------------------------				
				        
				        ll.setBackgroundDrawable(new BitmapDrawable(bg));
	    			break;
				}
				}
			    return true;
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	    if ((this.menu != null) && (!this.menu.equals(""))) {
	    	if (this.menu.equals("geometriacomb"))
	    		getMenuInflater().inflate(R.menu.geometria_comb, menu);
	    } else		
	    	getMenuInflater().inflate(R.menu.graficador, menu);
		return true;
	}	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    menu.setHeaderTitle(R.string.abc_action_mode_done);
	    MenuInflater inflater = getMenuInflater();
	    if ((this.menu != null) && (!this.menu.equals(""))) {
	    	if (this.menu.equals("geometria_comp"))
	    		inflater.inflate(R.menu.geometria_comb, menu);	
	    } else
	    inflater.inflate(R.menu.graficador, menu);
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.normal) {
			normal = true;
			integrales = interseccion = area =false;
			item.setChecked(true);
		} else 	if (id == R.id.integrales) {
			integrales = true;
			normal = interseccion = false;
			item.setChecked(true);
		} else 	if (id == R.id.interseccion) {
			interseccion = true;
			normal = integrales = area = false;
			item.setChecked(true);
		} else 	if (id == R.id.area) {
			area = true;
			normal = integrales = interseccion = false;
			item.setChecked(true);
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void FloodFill(Bitmap bmp, Point pt, int targetColor, int replacementColor) 
	{
	    Queue<Point> q = new LinkedList<Point>();
	    q.add(pt);
	    while (q.size() > 0) {
	        Point n = q.poll();
	        if (bmp.getPixel(n.x, n.y) != targetColor)
	            continue;

	        Point w = n, e = new Point(n.x + 1, n.y);
	        while ((w.x > 0) && (bmp.getPixel(w.x, w.y) == targetColor)) {
	            bmp.setPixel(w.x, w.y, replacementColor);
	            area_comb += 0.0007257;
	            
	            if ((w.y > 0) && (bmp.getPixel(w.x, w.y - 1) == targetColor))
	                q.add(new Point(w.x, w.y - 1));
	            if ((w.y < bmp.getHeight() - 1)
	                    && (bmp.getPixel(w.x, w.y + 1) == targetColor))
	                q.add(new Point(w.x, w.y + 1));
	            w.x--;
	        }
	        while ((e.x < bmp.getWidth() - 1)
	                && (bmp.getPixel(e.x, e.y) == targetColor)) {
	            bmp.setPixel(e.x, e.y, replacementColor);
	            area_comb += 0.0007257;
	            
	            if ((e.y > 0) && (bmp.getPixel(e.x, e.y - 1) == targetColor))
	                q.add(new Point(e.x, e.y - 1));
	            if ((e.y < bmp.getHeight() - 1)
	                    && (bmp.getPixel(e.x, e.y + 1) == targetColor))
	                q.add(new Point(e.x, e.y + 1));
	            e.x++;
	        }
	    }
	}	
}


