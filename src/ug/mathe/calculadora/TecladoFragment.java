package ug.mathe.calculadora;

import ug.mathe.R;
import ug.mathe.graficador.Graficador3dActivity;
import ug.mathe.graficador.GraficadorActivity;
import ug.mathe.util.Algebra;
import ug.mathe.util.InfixToPostfix;
import ug.mathe.util.Matrices;
import ug.mathe.util.Numero;
import ug.mathe.util.Stack;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TecladoFragment extends Fragment implements  OnClickListener {

	EditText expr;
	EditText resultado;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-genera ted method stub
		super.onActivityCreated(savedInstanceState);
		EditText myEditText = (EditText) getActivity().findViewById(R.id.txtexpr);  
		myEditText.clearFocus();
		myEditText.setKeyListener(null);
				
		resultado = (EditText) getActivity().findViewById(R.id.txt_Resp);
		//resultado.setEnabled(false);
		resultado.setKeyListener(null);
		/*InputMethodManager imm = (InputMethodManager)getSystemService(
		      Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(myEditText.getWindowToken(), 0);*/
		
        Button boton = (Button)getActivity().findViewById(R.id.btn_0);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_0);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_1);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_2);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_3);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_4);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_5);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_6);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_7);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_8);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_9);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_x);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_y);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_z);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_w);
        boton.setOnClickListener(this);        
        boton = (Button)getActivity().findViewById(R.id.btn_division);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_multiplicacion);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_resta);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_suma);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_eleva);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_sen);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_cos);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_tan);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_ln);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_e);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_sqrt);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_parizq);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_parder);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_puntoycoma);
        boton.setOnClickListener(this);        
        boton = (Button)getActivity().findViewById(R.id.btn_coma);
        boton.setOnClickListener(this);        
        boton = (Button)getActivity().findViewById(R.id.btn_del);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_igual);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_punto);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_solve);
        boton.setOnClickListener(this);
        boton = (Button)getActivity().findViewById(R.id.btn_diff);
        boton.setOnClickListener(this);        
        boton = (Button)getActivity().findViewById(R.id.btn_graph);
        boton.setOnClickListener(this);        
        
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_calculadora_teclado, container, false);
	}
	
	private void escribir(String carac) {
		//expr = (EditText)findViewById(R.id.txtexpr);
		//expr.append("0");
		expr = (EditText)getActivity().findViewById(R.id.txtexpr);
		int ind =expr.getSelectionEnd();
		String s = expr.getText().toString(), nuevo="";
		
		if (s.length() == 0) {
			expr.setText(carac);
			expr.setSelection(expr.getText().length());
		} else if (s.length() == ind) {
			expr.append(carac);
		} else {
			int j=0;
			for (int i=0; i<s.length()+1;i++){
				if (i!=ind) {
					nuevo += s.charAt(j);
					j++;
				}
				else
					nuevo += carac;
			}
			
			expr.setText(nuevo);
			if (ind>=0)
				expr.setSelection(ind);
		}
	}
	
	private void escribirsolucion(String cadena) {
		resultado = (EditText)getActivity().findViewById(R.id.txt_Resp);
		resultado.setText(cadena);
	}
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.btn_0:
				escribir("0");
			break;
			case R.id.btn_1:
				escribir("1");
			break;
			case R.id.btn_2:
				escribir("2");
			break;
			case R.id.btn_3:
				escribir("3");
			break;
			case R.id.btn_4:
				escribir("4");
			break;
			case R.id.btn_5:
				escribir("5");
			break;
			case R.id.btn_6:
				escribir("6");
			break;
			case R.id.btn_7:
				escribir("7");
			break;
			case R.id.btn_8:
				escribir("8");
			break;
			case R.id.btn_9:
				escribir("9");
			break;
			case R.id.btn_x:
				escribir("x");
			break;
			case R.id.btn_y:
				escribir("y");
			break;
			case R.id.btn_z:
				escribir("z");
			break;
			case R.id.btn_w:
				escribir("w");
			break;			
			case R.id.btn_division:
				escribir("/");
			break;
			case R.id.btn_multiplicacion:
				escribir("*");
			break;
			case R.id.btn_resta:
				escribir("-");
			break;
			case R.id.btn_suma:
				escribir("+");
			break;
			case R.id.btn_punto:
				escribir(".");
			break;
			case R.id.btn_eleva:
				escribir("^");
			break;
			case R.id.btn_sen:
				escribir("sen(");
			break;
			case R.id.btn_cos:
				escribir("cos(");
			break;
			case R.id.btn_tan:
				escribir("tan(");
			break;
			case R.id.btn_ln:
				escribir("ln(");
			break;
			case R.id.btn_e:
				escribir("e(");
			break;
			case R.id.btn_sqrt:
				escribir("sqrt(");
			break;
			case R.id.btn_parizq:
				escribir("(");
			break;
			case R.id.btn_parder:
				escribir(")");
			break;
			case R.id.btn_puntoycoma:
				escribir(";");
			break;
			case R.id.btn_coma:
				escribir(",");
			break;			
			case R.id.btn_del:
				expr = (EditText)getActivity().findViewById(R.id.txtexpr);
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
				
				
				//for (int k = 0; k < ecuaciones.length; k++) {
				String cadena = ((EditText)getActivity().findViewById(R.id.txtexpr)).getText().toString();
				//String cadena = ecuaciones[k];
				int ecuas = cadena.split(";").length;
				if (ecuas > 1) {
					Algebra[] al = new Algebra[ecuas];
					String[] expr = cadena.split(";");
					Matrices ma = new Matrices();
		            double[][] p = new double[ecuas][ecuas + 1];
		            double [] r = new double[ecuas];
					for (int i = 0; i < ecuas; i++) {
						String[] arr = expr[i].split("=");
						String ecuacion = "-(" + arr[1] +")+" + arr[0];
						al[i] = new Algebra(ecuacion);
						Stack coef = al[i].coef();
						double[] b = new double[ecuas + 1];
						while (!coef.isEmpty()) {
							Numero num = (Numero)coef.Pop();
							if (!num.getVar().equals("")) {
								switch (num.getVar().charAt(0)) {
									case 'x': b[0] = num.getCof(); break;
									case 'y': b[1] = num.getCof(); break;
									case 'z': b[2] = num.getCof(); break;
									case 'w': b[3] = num.getCof(); break;
								}
							} else {
								b[ecuas] = num.getCof()*-1;
							}
						}
						p[i] = b;
					}
					if (ma.GaussianElimination(p, r)) {
						String result = "";
						for (int i = 0; i < r.length; i++) {
							switch (i) {
							case 0: result += "x=" + r[i] + " "; break;
							case 1: result += "y=" + r[i] + " "; break;
							case 2: result += "z=" + r[i] + " "; break;
							case 3: result += "w=" + r[i] + " "; break;
						}							 
						}
						escribirsolucion(result);
						//Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
					}
				} 
				else 
				{
					int val = cadena.split("=").length;
					if (val == 2)
					{
						String[] arr = cadena.split("=");
						String ecuacion = "-(" + arr[1] +")+" + arr[0];
						Algebra al = new Algebra(ecuacion);					
						try {
							String res = al.Expand();
							//Log.i("SOLVE",al.Expand() + " SOLUC " + soluciones[k]);
							escribirsolucion(res);
							//Toast.makeText(getActivity(), res, Toast.LENGTH_LONG).show();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					
					} else if (val == 1) 
					{
					
					InfixToPostfix a = new InfixToPostfix(cadena);
					expr = (EditText)getActivity().findViewById(R.id.txtexpr);
					try 
					{
						expr.setText(String.valueOf(a.evaluar(0)));
						expr.setSelection(expr.length());
					} catch (Exception e) {
						Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
					}
					} else {
						Toast.makeText(getActivity(), "Expresion invalida", Toast.LENGTH_LONG).show();
					}
				}
				//  }
			break;
			case R.id.btn_diff:
				String diff = ((EditText)getActivity().findViewById(R.id.txtexpr)).getText().toString();
				Algebra al = new Algebra(diff);
				expr = (EditText)getActivity().findViewById(R.id.txtexpr);
				try 
				{
					escribirsolucion(al.Diff());
					expr.setSelection(expr.length());
				} catch (Exception e) {
					Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
				}				
				break;
			case R.id.btn_graph:
				if (valida3d(((EditText)getActivity().findViewById(R.id.txtexpr)).getText().toString())) {
					expr = (EditText)getActivity().findViewById(R.id.txtexpr);
					String nu = quitarvariable(expr.getText().toString());
					Intent intent = new Intent(getActivity(),Graficador3dActivity.class);
					
					intent.putExtra("funciones", nu);
					intent.putExtra("parametros", "-5,5,-5,5,-5,5");
					startActivity(intent);
				} else if (valida2d(((EditText)getActivity().findViewById(R.id.txtexpr)).getText().toString())) {
					expr = (EditText)getActivity().findViewById(R.id.txtexpr);
					String nu = quitarvariable(expr.getText().toString());
					Intent intent = new Intent(getActivity(),GraficadorActivity.class);
					intent.putExtra("funciones", nu);
					intent.putExtra("parametros", "-5,5,-5,5");
					startActivity(intent);					
				} else {
					Toast.makeText(getActivity(), "Ingreso no permitido!", Toast.LENGTH_SHORT).show();
				}
		break;
			case R.id.btn_igual:
					// Implementar la calcu
				escribir("=");
			break;			
		}
		expr = (EditText)getActivity().findViewById(R.id.txtexpr);
		//expr.selectAll();
		expr.setFocusable(true);
		
	}
	
	private String quitarvariable(String cadena) {
		String result = "";
		String[] t = cadena.split(";");
		for (int i = 0; (i < t.length) ; i++){
			String[] v = t[i].split("=");
			if (v[0].equals("z") || v[0].equals("y")) {
				result += v[1] + ";";
			} else if (v[1].equals("z") || v[1].equals("y")) {
				result += v[0] + ";";	
			}
		}
		return result;
	}
	
	private boolean valida3d(String cadena) {
		boolean result = true;
		if (!cadena.equals("")) {
			String[] t = cadena.split(";");
			for (int i = 0; (i < t.length) && result; i++){
				String[] v = t[i].split("=");
				if (v.length == 2) {
					if (!((v[0].equals("z") && !v[1].equals("z")) || (!v[0].equals("z") && v[1].equals("z")))) {
						result = false;
					}
				}
			}
		} else
			result = false;
		return result;
	}
	
	private boolean valida2d(String cadena) {
		boolean result = true;
		if (!cadena.equals("")) {
			String[] t = cadena.split(";");
			for (int i = 0; (i < t.length) && result; i++){
				String[] v = t[i].split("=");
				if (v.length == 2) {
					if (!((v[0].equals("y") && !v[1].equals("y")) || (!v[0].equals("y") && v[1].equals("y")))) {
						result = false;
					}
				}
			}
		} else
			result = false;
		return result;
	}	
}
