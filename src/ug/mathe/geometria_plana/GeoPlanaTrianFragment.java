package ug.mathe.geometria_plana;

import ug.mathe.R;
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

public class GeoPlanaTrianFragment extends Fragment {

	Button btn;
	int caso = -1;		
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_geoplana_triangulo, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Spinner sp = (Spinner) getActivity().findViewById(R.id.spn_triangulo);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.triangulo, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				TextView a = (TextView)getActivity().findViewById(R.id.txt_RespTriangulo);
				a.setText("");
				caso = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_calcular_triangulo);
		btn.setOnClickListener(new OnClickListener() {
			
			private double ladoa = 0.0;
			private double ladob = 0.0;
			private double ladoc = 0.0;
			private double alfa = 0.0;
			private double beta = 0.0;
			private double teta = 0.0;
			private double area = 0.0;
			private double perimetro = 0.0;
			double[] param = new double[3];
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				switch (caso) {
					case 0:
						this.ladoa =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoaT)).getText()));
						this.ladob = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladobT)).getText()));
						this.ladoc = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladocT)).getText()));
						param[0] = this.ladoa;
						param[1] = this.ladob;
						param[2] = this.ladoc;
						break;
					case 1:
						this.ladoa =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoaT)).getText()));
						this.ladob = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladobT)).getText()));
						this.alfa = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_anguloalfaT)).getText()));
						param[0] = this.ladoa;
						param[1] = this.ladob;
						param[2] = this.alfa;
						break;
					case 2:
						this.ladoa =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoaT)).getText()));
						this.alfa = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_anguloalfaT)).getText()));
						this.beta = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_angulobetaT)).getText()));
						param[0] = this.ladoa;
						param[1] = this.alfa;
						param[2] = this.beta;						
						break;
					case 3:
						this.area =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_areaT)).getText()));
						this.ladoa = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoaT)).getText()));
						this.ladob = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladobT)).getText()));
						param[0] = this.area;
						param[1] = this.ladoa;
						param[2] = this.ladob;						
						break;
					case 4:
						this.perimetro =  Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_perimetroT)).getText()));
						this.ladoa = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladoaT)).getText()));
						this.ladob = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.txt_ladobT)).getText()));
						param[0] = this.perimetro;
						param[1] = this.ladoa;
						param[2] = this.ladob;						
						break;
				}

				GeometriaPlana geo = new GeometriaPlana(param, "TRIANGULO", caso);
				TextView a = (TextView)getActivity().findViewById(R.id.txt_RespTriangulo);
				if (geo.resolve())
					a.setText(geo.toString());
				else
					a.setText("Error");
			}
		});			
		
		
	}
	
	
}
