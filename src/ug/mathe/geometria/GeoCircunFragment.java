package ug.mathe.geometria;

import ug.mathe.R;
import ug.mathe.graficador.GraficadorActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GeoCircunFragment extends Fragment {
	
	Button btn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		//return inflater.inflate(R.layout.fragment_geometria_circunferencia, container, false);
		return inflater.inflate(R.layout.fragment_geometria_circunferencia, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		btn = (Button)getActivity().findViewById(R.id.btn_calcularCircunferencia);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, radio = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefcirH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefcirK)).getText()));
				this.radio = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefcirR)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, 0.0, 0.0, 0.0, this.radio, 'c');
				TextView a = (TextView)getActivity().findViewById(R.id.txtview_respCircunferencia);
				a.setText(geo.ResolverCanonica());				
			}
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_graficarCircunferencia);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, radio = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefcirH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefcirK)).getText()));
				this.radio = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefcirR)).getText()));				
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, 0.0, 0.0, 0.0, this.radio, 'c');
				geo.ResolverCanonica();
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", geo.getFunc());
				intent.putExtra("parametros", geo.getParam());
				startActivity(intent);				
			}
		});		
		
	}

}
