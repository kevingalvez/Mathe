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

public class GeoGenFragment extends Fragment {
	
	Button btn;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		btn = (Button)getActivity().findViewById(R.id.btn_calcular);
		btn.setOnClickListener(new OnClickListener() {
			
			private double a = 0.0;
			private double b = 0.0;
			private double c = 0.0;
			private double d = 0.0;
			private double e = 0.0;
			private double f = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.a = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefA)).getText()));
				this.b = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefB)).getText()));
				this.c = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefC)).getText()));
				this.d = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefD)).getText()));
				this.e = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefE)).getText()));
				this.f = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefF)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(a,b,c,d,e,f);
				TextView a = (TextView)getActivity().findViewById(R.id.txtview_resp);
				a.setText(geo.Resolver());
			}
		});
		
		btn = (Button)getActivity().findViewById(R.id.btn_graficar);
		btn.setOnClickListener(new OnClickListener() {
			
			private double a = 0.0;
			private double b = 0.0;
			private double c = 0.0;
			private double d = 0.0;
			private double e = 0.0;
			private double f = 0.0;
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				this.a = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefA)).getText()));
				this.b = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefB)).getText()));
				this.c = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefC)).getText()));
				this.d = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefD)).getText()));
				this.e = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefE)).getText()));
				this.f = Double.valueOf(String.valueOf(((EditText)getActivity().findViewById(R.id.TxtcoefF)).getText()));
				
				GeometriaAnalitica geo = new GeometriaAnalitica(a,b,c,d,e,f);
				geo.Resolver();
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", geo.getFunc());
				intent.putExtra("parametros", geo.getParam());
				startActivity(intent);				
			}
		});		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//return super.onCreateView(inflater, container, savedInstanceState);
		//return inflater.inflate(R.layout.fragment_geometria_circunferencia, container, false);
		return inflater.inflate(R.layout.fragment_geometria_general, container, false);
	}
}
