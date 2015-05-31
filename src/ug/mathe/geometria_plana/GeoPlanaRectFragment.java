package ug.mathe.geometria_plana;

import ug.mathe.R;
import ug.mathe.graficador.GraficadorActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class GeoPlanaRectFragment extends Fragment {
	
	Button btn, btn2;
	int caso = -1;	
	
	private double ladoa = 0.0;
	private double ladob = 0.0;
	private double area = 0.0;
	private double perimetro = 0.0;
	double[] param = new double[2];
	
	GeometriaPlana geo;
	
	public void calcular() {
		switch (caso) {
		case 0:
			this.ladoa =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoa)).getText()));
			this.ladob = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladob)).getText()));
			param[0] = this.ladoa;
			param[1] = this.ladob;
			break;
		case 1:
			this.ladoa =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoa)).getText()));
			this.perimetro = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_perimetroR)).getText()));
			param[0] = this.ladoa;
			param[1] = this.perimetro;						
			break;
		case 2:
			this.ladoa =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoa)).getText()));
			this.area = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_areaR)).getText()));
			param[0] = this.ladoa;
			param[1] = this.area;						
			break;
		case 3:
			this.perimetro =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_perimetroR)).getText()));
			this.area = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_areaR)).getText()));
			param[0] = this.perimetro;
			param[1] = this.area;						
			break;
		}
	
		geo = new GeometriaPlana(param, "RECTANGULO", caso);
		TextView a = (TextView)getActivity().findViewById(R.id.txt_RespRectangulo);
		if (geo.resolve())
			a.setText(geo.toString());
		else
			a.setText("Error");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_geoplana_rectangulo, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Spinner sp = (Spinner) getActivity().findViewById(R.id.spn_rectangulo);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.rectangulo, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView a = (TextView)getActivity().findViewById(R.id.txt_RespRectangulo);
				a.setText("");
				caso = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_calcular_rectangulo);
		btn.setOnClickListener(new OnClickListener() {
						
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				calcular();
			}
		});	
		
		btn2 = (Button)getActivity().findViewById(R.id.btn_graficar_rectangulo);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calcular();
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", "#Rect("+ geo.getladoa() +"," + geo.getladob() + ")");
				intent.putExtra("parametros", "-10,10,-10,10");
				startActivity(intent);		
			}
		});		
	}
	
	
	
}
