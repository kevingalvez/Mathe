package ug.mathe.geometria_plana;

import ug.mathe.R;
import ug.mathe.geometria.GeometriaAnalitica;
import ug.mathe.graficador.GraficadorActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GeoPlanaCircFragment extends Fragment {

	Button btn, btn2;
	int caso = -1;
	
	private double radio = 0.0;
	private double angulo = 0.0;
	private double area = 0.0;
	private double perimetro = 0.0;
	private double area_sector = 0.0;
	private double area_segmento = 0.0;
	double[] param = new double[2];	
	
	public GeometriaPlana geo;
	
	public void inicializar(){
		EditText txt = (EditText)getActivity().findViewById(R.id.txt_radio);
		txt.setText("");
		
		txt = (EditText)getActivity().findViewById(R.id.txt_angulo);
		txt.setText("");
		
		txt = (EditText)getActivity().findViewById(R.id.txt_area_segmento);
		txt.setText("");	
		
		txt = (EditText)getActivity().findViewById(R.id.txt_area);
		txt.setText("");
		
		txt = (EditText)getActivity().findViewById(R.id.txt_perimetro);
		txt.setText("");
		
		txt = (EditText)getActivity().findViewById(R.id.txt_area_sector);
		txt.setText("");		
	}
	
	public void calcular() {
		switch (caso) {
		case 0:
			this.radio =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_radio)).getText()));
			this.angulo = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_angulo)).getText()));
			param[0] = this.radio;
			param[1] = this.angulo;
			break;
		case 1:
			this.area =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_area)).getText()));
			this.angulo = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_angulo)).getText()));
			param[0] = this.area;
			param[1] = this.angulo;						
			break;
		case 2:
			this.perimetro =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_perimetro)).getText()));
			this.angulo = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_angulo)).getText()));
			param[0] = this.perimetro;
			param[1] = this.angulo;						
			break;
		case 3:
			this.area_sector =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_area_sector)).getText()));
			this.angulo = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_angulo)).getText()));
			param[0] = this.area_sector;
			param[1] = this.angulo;						
			break;
		case 4:
			this.area_sector =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_area_sector)).getText()));
			this.radio = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_radio)).getText()));
			param[0] = this.area_sector;
			param[1] = this.radio;							
			break;
		case 5:
			this.area_sector =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_area_sector)).getText()));
			this.area = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_area)).getText()));
			param[0] = this.area_sector;
			param[1] = this.area;							
			break;
		case 6:
			this.area_segmento =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_area_segmento)).getText()));
			this.angulo = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_angulo)).getText()));
			param[0] = this.area_segmento;
			param[1] = this.angulo;							
			break;
			
		}
		
		geo = new GeometriaPlana(param, "CIRCULO", caso);
		if (geo.resolve()) {
			EditText txt = (EditText)getActivity().findViewById(R.id.txt_radio);
			txt.setText(String.valueOf(geo.getRadio()));
			
			txt = (EditText)getActivity().findViewById(R.id.txt_angulo);
			txt.setText(String.valueOf(geo.getAngulo()));
			
			txt = (EditText)getActivity().findViewById(R.id.txt_area_segmento);
			txt.setText(String.valueOf(geo.getAreaSegmento()));	
			
			txt = (EditText)getActivity().findViewById(R.id.txt_area);
			txt.setText(String.valueOf(geo.getArea()));
			
			txt = (EditText)getActivity().findViewById(R.id.txt_perimetro);
			txt.setText(String.valueOf(geo.getPerimetro()));
			
			txt = (EditText)getActivity().findViewById(R.id.txt_area_sector);
			txt.setText(String.valueOf(geo.getAreaSector()));
			//a.setText(geo.toString());
		}
		else
			Toast.makeText(getActivity(), "Error!", Toast.LENGTH_LONG).show();
		
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_geoplana_circulo, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Spinner sp = (Spinner) getActivity().findViewById(R.id.spn_circulo);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.circulo, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				inicializar();
				caso = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_calcular_circulo);
		btn.setOnClickListener(new OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				calcular();
			}
		});
		
		btn2 = (Button)getActivity().findViewById(R.id.btn_graficar_circulo);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				calcular();
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", "#Circ("+ geo.getRadio() +"," + geo.getAngulo() + ")");
				intent.putExtra("parametros", "-10,10,-10,10");
				intent.putExtra("menu", "geometriaplana");
				startActivity(intent);		
			}
		});
	}
	
	

}
