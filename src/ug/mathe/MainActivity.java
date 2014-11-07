package ug.mathe;

import ug.mathe.calculadora.CalculadoraActivity;
import ug.mathe.geometria.GeometriaActivity;
import ug.mathe.graficador.GraficadorActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnGraficador;
	Button btnGeometria;
	Button btnEcuaciones;
	Button btnSistEcua;
	public static final String TAG = MainActivity.class.toString();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setContentView(new MyView(this));
		btnGraficador = (Button) findViewById(R.id.btn_graficador);
		btnGeometria = (Button) findViewById(R.id.btn_geometria);
		btnEcuaciones = (Button) findViewById(R.id.btn_ecuaciones);
		btnSistEcua = (Button) findViewById(R.id.btn_sistecua);
		
		btnGraficador.setOnClickListener(new ButtonListener(1));
		btnGeometria .setOnClickListener(new ButtonListener(2));
		btnEcuaciones .setOnClickListener(new ButtonListener(3));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
	class ButtonListener implements OnClickListener {

		private int menu;
		
		public ButtonListener(int menu){
			this.menu = menu;
		}
		
		@Override
		public void onClick(View v) {
			if (menu == 1)
			{
				Intent intent = new Intent(getApplicationContext(),GraficadorActivity.class);
				startActivity(intent);
			} else if (menu == 2) {
				Intent intent = new Intent(getApplicationContext(),GeometriaActivity.class);
				startActivity(intent);				
			} else if (menu == 3) {
				Intent intent = new Intent(getApplicationContext(),CalculadoraActivity.class);
				startActivity(intent);				
			}
		}
		
	}
} 
