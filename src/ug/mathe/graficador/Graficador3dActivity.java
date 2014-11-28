package ug.mathe.graficador;

import java.util.Timer;
import java.util.TimerTask;

import ug.mathe.MainActivity;
import ug.mathe.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

@SuppressLint("ClickableViewAccessibility")
public class Graficador3dActivity extends Activity {

	Button btn_graficar;
	Graph3d grap;
	String[] funciones;
	String[] parametros;
	public int algo = 0;
	double ini_x, ini_y, fin_x, fin_y;
	Timer timer;
	boolean activa4d = false;
	Thread thread;
	 MyTimerTask myTimerTask;
	private SparseArray<PointF> mActivePointers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graficador3d);
		 
	    
		EditText a = (EditText)findViewById(R.id.txtFuncion);
		a.setKeyListener(null);
		mActivePointers = new SparseArray<PointF>();
		Bundle bundle = getIntent().getExtras();
		//funciones = bundle.getString("funciones").split(";");
		EditText func = (EditText) findViewById(R.id.txtFuncion);
		func.setText(bundle.getString("funciones"));
		parametros = bundle.getString("parametros").split(",");		
		btn_graficar = (Button) findViewById(R.id.btn_graficar);
		btn_graficar.setOnClickListener(new OnClickListener() {

			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
			    
		        Paint paint = new Paint();
		        paint.setColor(Color.parseColor("#000000"));
		        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
		        
				
		        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
		        
				
		        Canvas canvas = new Canvas(bg);

			    grap = new Graph3d(ll.getWidth(),ll.getHeight(),5,5);

			    if (parametros.length > 0) {
			    	
				    grap.setLimitesX(Double.valueOf(parametros[0]) , Double.valueOf(parametros[1]));
				    grap.setLimitesY(Double.valueOf(parametros[2]), Double.valueOf(parametros[3]));
				    grap.setLimitesZ(Double.valueOf(parametros[4]), Double.valueOf(parametros[5]));
			    } else {
				    grap.setLimitesX( -10, 10);
				    grap.setLimitesY(-10, 10);
				    grap.setLimitesZ(-10, 10);
			    }
			    grap.setStep(2); 		        
		        grap.dibujaplano(canvas, paint);
		        
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
			
			@SuppressWarnings("deprecation")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
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
			            	grap.setInclinacionLeft();
			            else if (ini_x-fin_x < -100)
			            	grap.setInclinacionRight();
			            	
			            	
			            
			            if (ini_y-fin_y > 100)
			            	grap.setRotacionUp();
			            else if (ini_y-fin_y < -100)
			            	grap.setRotacionDown();

			            
						LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
					    
				        Paint paint = new Paint();
				        paint.setColor(Color.parseColor("#000000"));
				        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
				        
						
				        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
				        
				        Canvas canvas = new Canvas(bg);
				     		        
				        grap.dibujaplano(canvas, paint);
				        
				        for (int i = 0; i < func.length;i++) {
					        grap.setExpr(func[i].toString());
					        grap.graficar(canvas, paint);
				        }
				        //-------------------------------				
				        
				        ll.setBackgroundDrawable(new BitmapDrawable(bg));
			    	} 
			    	mActivePointers.remove(pointerId);
			    	break;
			    }
			    }
			    //invalidate();

			    return true;				
				// TODO Auto-generated method stub
				} catch (Exception ex) {
					Log.e(MainActivity.TAG,Graficador3dActivity.class.toString() + " - Error en Touch!");
					return false;
				}			    
			}				
		});		

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graficador3d, menu);
		return true;
	}	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.grafica3D) {
			//return true;
			if (timer != null)
				timer.cancel();
			timer = null;
		}		
		if (id == R.id.Grafica4D) {
			//thread.start();
			if (timer == null) {
				timer = new Timer();
		    	myTimerTask = new MyTimerTask();			
		    	timer.schedule(myTimerTask, 1000,250);
			}
			//return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	class MyTimerTask extends TimerTask {

		  @Override
		  public void run() {
		   
				   runOnUiThread(new Runnable(){
		
				    @Override
				    public void run() {
							LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
							    
						        Paint paint = new Paint();
						        paint.setColor(Color.parseColor("#000000"));
						        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
						        
								
						        String func[] = String.valueOf(((EditText) findViewById(R.id.txtFuncion)).getText()).split(";");
						        
						        Canvas canvas = new Canvas(bg);
						     		        
						        grap.dibujaplano(canvas, paint);
						        grap.setTiempo(0.1);
						        for (int i = 0; i < func.length;i++) {
							        grap.setExpr(func[i].toString());
							        grap.graficar(canvas, paint);
						        }
						        //-------------------------------				
						        
						        ll.setBackgroundDrawable(new BitmapDrawable(bg));
		                }
		
				    });
				  }
	}
}
