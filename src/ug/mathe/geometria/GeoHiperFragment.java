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

public class GeoHiperFragment extends Fragment {
	
	Button btn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment_geometria_hiperbola, container, false);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		btn = (Button)getActivity().findViewById(R.id.btn_calcularHiperbola);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, distA = 0.0, distB = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipK)).getText()));
				this.distA = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipA)).getText()));
				this.distB = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipB)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, this.distA, this.distB, 0.0, 0.0, 'h');
				TextView a = (TextView)getActivity().findViewById(R.id.txtview_respHiperbola);
				a.setText(geo.ResolverCanonica());				
			}
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_graficarHiperbola);
		btn.setOnClickListener(new OnClickListener() {
			
			private double centroX = 0.0, centroY = 0.0, distA = 0.0, distB = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.centroX = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipH)).getText()));
				this.centroY = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipK)).getText()));
				this.distA = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipA)).getText()));
				this.distB = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefhipB)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(this.centroX, this.centroY, this.distA, this.distB, 0.0, 0.0, 'h');
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", geo.getFuncCanonica());
				intent.putExtra("parametros", geo.getParam());
				startActivity(intent);				
			}
		});	
	}	
}
