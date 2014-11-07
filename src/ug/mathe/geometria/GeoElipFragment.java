package ug.mathe.geometria;

import ug.mathe.R;
import ug.mathe.graficador.GraficadorActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeoElipFragment extends Fragment {
	
	Button btn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_geometria_elipse, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		btn = (Button)getActivity().findViewById(R.id.btn_calcularElipse);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, radioA = 0.0, radioB = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliK)).getText()));
				this.radioA = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliA)).getText()));
				this.radioB = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliB)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, this.radioA, this.radioB, 0.0, 0.0, 'e');
				TextView a = (TextView)getActivity().findViewById(R.id.txtview_respElipse);
				a.setText(geo.ResolverCanonica());				
			}
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_graficarElipse);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, radioA = 0.0, radioB;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliK)).getText()));
				this.radioA = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliA)).getText()));
				this.radioB = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefeliB)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, this.radioA, this.radioB, 0.0, 0.0, 'e');
				geo.Resolver();
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", geo.getFunc());
				intent.putExtra("parametros", geo.getParam());
				startActivity(intent);				
			}
		});	
	}	
	
}
