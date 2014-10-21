package ug.mathe.graficador;

import ug.mathe.R;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class GraficadorActivity extends ActionBarActivity {

	Button btn_graficar;
	Graph2d grap;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graficador);
		
		btn_graficar = (Button) findViewById(R.id.btn_graficar);
		btn_graficar.setOnClickListener(new OnClickListener() {

	
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				LinearLayout ll = (LinearLayout) findViewById(R.id.graph2d);
			    
		        Paint paint = new Paint();
		        paint.setColor(Color.parseColor("#000000"));
		        Bitmap bg = Bitmap.createBitmap(ll.getWidth(), ll.getHeight(), Bitmap.Config.ARGB_8888);
		        
				EditText func = (EditText) findViewById(R.id.txtFuncion);
		        
		        Canvas canvas = new Canvas(bg);

			    grap = new Graph2d(ll.getWidth(),ll.getHeight(),5,5);
			    grap.setLimitesX( -5, 5);
			    grap.setLimitesY(-10, 10);
			    grap.setStep(100); 		        
		        grap.setExpr(func.getText().toString());		        
		        grap.dibujaplano(canvas, grap.ConvertToPixelX(0), grap.ConvertToPixelY(0), paint);
		        grap.graficar(canvas, paint);
		        //-------------------------------				
		        
		        ll.setBackgroundDrawable(new BitmapDrawable(bg));				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graficador, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
