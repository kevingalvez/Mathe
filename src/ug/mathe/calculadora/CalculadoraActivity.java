package ug.mathe.calculadora;

import ug.mathe.R;
import ug.mathe.graficador.GraficadorActivity;
import ug.mathe.util.Algebra;
import ug.mathe.util.Arbol;
import ug.mathe.util.InfixToPostfix;
import ug.mathe.util.Nodo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculadoraActivity extends Activity  implements OnClickListener {

	EditText expr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculadora);
		EditText myEditText = (EditText) findViewById(R.id.txtexpr);  
		myEditText.clearFocus();
		InputMethodManager imm = (InputMethodManager)getSystemService(
		      Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);
        Button boton = (Button)findViewById(R.id.btn_0);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_0);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_1);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_2);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_3);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_4);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_5);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_6);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_7);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_8);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_9);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_x);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_y);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_division);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_multiplicacion);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_resta);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_suma);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_eleva);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_sen);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_cos);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_tan);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_ln);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_e);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_sqrt);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_parizq);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_parder);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_del);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_igual);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_punto);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_solve);
        boton.setOnClickListener(this);
        boton = (Button)findViewById(R.id.btn_graph);
        boton.setOnClickListener(this);        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculadora, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.btn_0:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "0");
			break;
			case R.id.btn_1:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "1");
			break;
			case R.id.btn_2:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "2");
			break;
			case R.id.btn_3:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "3");
			break;
			case R.id.btn_4:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "4");
			break;
			case R.id.btn_5:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "5");
			break;
			case R.id.btn_6:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "6");
			break;
			case R.id.btn_7:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "7");
			break;
			case R.id.btn_8:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "8");
			break;
			case R.id.btn_9:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "9");
			break;
			case R.id.btn_x:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "x");
			break;
			case R.id.btn_y:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "y");
			break;
			case R.id.btn_division:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "/");
			break;
			case R.id.btn_multiplicacion:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "*");
			break;
			case R.id.btn_resta:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "-");
			break;
			case R.id.btn_suma:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "+");
			break;
			case R.id.btn_punto:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  ".");
			break;
			case R.id.btn_eleva:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "^");
			break;
			case R.id.btn_sen:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "sen(");
			break;
			case R.id.btn_cos:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "cos(");
			break;
			case R.id.btn_tan:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "tan(");
			break;
			case R.id.btn_ln:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "ln(");
			break;
			case R.id.btn_e:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "e(");
			break;
			case R.id.btn_sqrt:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "sqrt(");
			break;
			case R.id.btn_parizq:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  "(");
			break;
			case R.id.btn_parder:
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append(  ")");
			break;
			case R.id.btn_del:
				expr = (EditText)findViewById(R.id.txtexpr);
				int ind =expr.getSelectionEnd()-1;
				String s = expr.getText().toString(), nuevo="";
				
				for (int i=0; i<s.length();i++){
					if (i!=ind)
						nuevo += s.charAt(i);
				}
				
				expr.setText(nuevo);
				if (ind>=0)
					expr.setSelection(ind);
			break;
			case R.id.btn_solve:
				String cadena = ((EditText)findViewById(R.id.txtexpr)).getText().toString();
				int val = cadena.split("=").length;
				if (val == 2)
				{
					String[] arr = cadena.split("=");
					String ecuacion = arr[0] + "-(" + arr[1] +")";
					InfixToPostfix a = new InfixToPostfix(ecuacion);
					
					try {
						String apostfix = a.ConvertToPostfix();
						Log.i("INFIXTOPOSTFIX",apostfix);
						//Arbol ar = new Arbol(new Nodo(String.valueOf(apostfix.charAt(apostfix.length()-1))));
						ug.mathe.util.Stack pila = new ug.mathe.util.Stack(255);
						ug.mathe.util.Stack pilaexpr = new ug.mathe.util.Stack(255);
						for (int i = 0; i < apostfix.length(); i++){
							switch (apostfix.charAt(i)) {
								case '#':
									pila.Push(a.getData(i));
								break;
								case 'x': case 'y':
									pila.Push(String.valueOf(apostfix.charAt(i)));
								break;
								case '*': case '/': case '+': case '-':
										Nodo nodo = new Nodo(String.valueOf(apostfix.charAt(i)));
										if (!pila.isEmpty()) {
											int j = 0;
											while (!pila.isEmpty() && j < 2) {
												switch (j) {
													case 0:
														nodo.setHojaDerecha(new Nodo(String.valueOf(pila.Pop())));
													break;
													case 1:
														nodo.setHojaIzquierda(new Nodo(String.valueOf(pila.Pop())));
													break;													
												}
												j++;
											}
											if (j == 1)
												nodo.setHojaIzquierda((Nodo)pilaexpr.Pop());
											pilaexpr.Push(nodo);
										} else {
											nodo.setHojaDerecha((Nodo)pilaexpr.Pop());
											nodo.setHojaIzquierda((Nodo)pilaexpr.Pop());
											pilaexpr.Push(nodo);
										}
									break;
							}
						}
						Arbol ar = new Arbol((Nodo)pilaexpr.Pop());
						Algebra al = new Algebra(ar);
						al.Expand();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				
				} else if (val == 0) 
				{
				
				InfixToPostfix a = new InfixToPostfix(cadena);
				expr = (EditText)findViewById(R.id.txtexpr);
				try 
				{
					expr.setText(String.valueOf(a.evaluar(0)));
					expr.setSelection(expr.length());
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				}
				} else {
					Toast.makeText(getApplicationContext(), "Expresion invalida", Toast.LENGTH_LONG).show();
				}
			break;
			case R.id.btn_graph:
				Intent intent = new Intent(getApplicationContext(),GraficadorActivity.class);
				expr = (EditText)findViewById(R.id.txtexpr);
				intent.putExtra("funciones", expr.getText()+";");
				intent.putExtra("parametros", "-10,10,-10,10");
				startActivity(intent);
				//expr = (EditText)findViewById(R.id.txtexpr);
				//expr.append(  "graph(");
		break;			
			case R.id.btn_igual:
					// Implementar la calcu
				expr = (EditText)findViewById(R.id.txtexpr);
				expr.append("=");
			break;			
		}
		expr = (EditText)findViewById(R.id.txtexpr);
		//expr.selectAll();
		expr.setFocusable(true);
		
	}
}