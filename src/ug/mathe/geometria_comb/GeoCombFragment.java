package ug.mathe.geometria_comb;

import java.util.ArrayList;

import ug.mathe.R;
import ug.mathe.graficador.GraficadorActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GeoCombFragment extends Fragment {
	
	ListView listView ;
	Button btn, btn2;
	ArrayAdapter<String> adapter2 ;
	ArrayList<String> val = new ArrayList<String>();
	String[] values = new String[] { };	
	String result = "";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		Spinner sp = (Spinner) getActivity().findViewById(R.id.spn_geocomb);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.geocomp, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(adapter);		
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});	
		
		btn = (Button) getActivity().findViewById(R.id.btn_Agregar);
		/*btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Spinner spn = (Spinner) getActivity().findViewById(R.id.spn_geocomb);
				
				adapter2.add(spn.getSelectedItem().toString());
				adapter2.setNotifyOnChange(true);
			}
		});*/
		
		// add button listener
		btn.setOnClickListener(new OnClickListener() {
 
			@Override
			public void onClick(View arg0) {
 
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(getActivity());
				View promptsView = li.inflate(R.layout.prompt, null);
 
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						getActivity());
 
				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);
 
				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.editTextDialogUserInput);
 
				TextView txt = (TextView)promptsView.findViewById(R.id.txtView_msg);
				
				Spinner spn = (Spinner) getActivity().findViewById(R.id.spn_geocomb);
				if (spn.getSelectedItem().toString().equals("Circulo"))
					txt.setText("Ingrese: Centro X, Centro Y, Radio");
				else if (spn.getSelectedItem().toString().equals("Rectangulo"))
					txt.setText("Ingrese: Centro X, Centro Y, LadoA, LadoB");
				else if (spn.getSelectedItem().toString().equals("Linea Recta"))
					txt.setText("Ingrese: PuntoX1, PuntoY1, PuntoX2, PuntoY2");
				
				// set dialog message
				alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text
					    	
					    	Spinner spn = (Spinner) getActivity().findViewById(R.id.spn_geocomb);
							adapter2.add("$" + spn.getSelectedItem().toString() + "(" + userInput.getText().toString() + ")");
							adapter2.setNotifyOnChange(true);
							
						//result.setText(userInput.getText());
					    }
					  })
					.setNegativeButton("Cancel",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					    }
					  });
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
 
			}
		});		
		
		
        // Get ListView object from xml
        listView = (ListView)getActivity().findViewById(R.id.lst_figuras);
        
        // Defined Array values to show in ListView
        
        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data
        adapter2 = new ArrayAdapter<String>(getActivity(),
          android.R.layout.simple_list_item_1, android.R.id.text1, val);


        // Assign adapter to ListView
        listView.setAdapter(adapter2); 
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
                
               // ListView Clicked item index
               int itemPosition     = position;
               
               // ListView Clicked item value
               final String  itemValue    = (String) listView.getItemAtPosition(position);
                  
                // Show Alert 
                /*Toast.makeText(getActivity(),
                  "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                  .show();*/
             
				// get prompts.xml view
				LayoutInflater li = LayoutInflater.from(getActivity());
				View promptsView = li.inflate(R.layout.prompt, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						getActivity());

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.editTextDialogUserInput);

				TextView txt = (TextView)promptsView.findViewById(R.id.txtView_msg);
				
				if (itemValue.contains("Circulo")) {
					txt.setText("Ingrese: Centro X, Centro Y, Radio");
	       			int idxizq = itemValue.indexOf("(");
	    			int idxder = itemValue.indexOf(")");
	    			String param = itemValue.substring(idxizq + 1,idxder);		
	    			userInput.setText(param);
				}
				else if (itemValue.contains("Rectangulo")) {
					txt.setText("Ingrese: Centro X, Centro Y, LadoA, LadoB");
	       			int idxizq = itemValue.indexOf("(");
	    			int idxder = itemValue.indexOf(")");
	    			String param = itemValue.substring(idxizq + 1,idxder);		
	    			userInput.setText(param);					
				}
				else if (itemValue.contains("Linea Recta")) {
					txt.setText("Ingrese: PuntoX1, PuntoY1, PuntoX2, PuntoY2");
	       			int idxizq = itemValue.indexOf("(");
	    			int idxder = itemValue.indexOf(")");
	    			String param = itemValue.substring(idxizq + 1,idxder);		
	    			userInput.setText(param);					
				}
				
				// set dialog message
				alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text
					    	
					    	if (!userInput.getText().toString().equals("")) {
						    	Spinner spn = (Spinner) getActivity().findViewById(R.id.spn_geocomb);
						    	adapter2.remove(itemValue);
								adapter2.add("$" + spn.getSelectedItem().toString() + "(" + userInput.getText().toString() + ")");
								adapter2.setNotifyOnChange(true);
					    	} else 
					    	{
					    		adapter2.remove(itemValue);
					    		adapter2.setNotifyOnChange(true);
					    	}
							
						//result.setText(userInput.getText());
					    }
					  })
					.setNegativeButton("Cancel",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					    }
					  });

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();               
               
              }

         });
        
        btn2 = (Button) getActivity().findViewById(R.id.btn_graficar_geocomb);
        
        btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String res = "";
				for (int i = 0; i < adapter2.getCount(); i++) {
					res += adapter2.getItem(i) + ";"; 
				}
				Intent intent = new Intent(getActivity(),GraficadorActivity.class);
				intent.putExtra("funciones", res);
				intent.putExtra("parametros", "-10,10,-10,10");
				intent.putExtra("menu", "geometriacomb");
				startActivity(intent);					
			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return inflater.inflate(R.layout.fragment_geocomb, container, false);
	}

}
