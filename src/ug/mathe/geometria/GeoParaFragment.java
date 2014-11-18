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

public class GeoParaFragment extends Fragment {
	
	Button btn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_geometria_parabola, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		btn = (Button)getActivity().findViewById(R.id.btn_graficarParabola);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, p = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefparH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefparK)).getText()));
				this.p = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefparP)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, 0.0, 0.0, this.p, 0.0, 'p');
				TextView a = (TextView)getActivity().findViewById(R.id.txtview_respParabola);
				a.setText(geo.ResolverCanonica());				
			}
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_calcularParabola);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, p = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefparH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefparK)).getText()));
				this.p = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefparP)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, 0.0, 0.0, this.p, 0.0, 'p');
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", geo.getFuncCanonica());
				intent.putExtra("parametros", geo.getParam());
				startActivity(intent);				
			}
		});		
		
	}	
}
