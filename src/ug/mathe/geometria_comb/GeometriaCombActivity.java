package ug.mathe.geometria_comb;

import ug.mathe.R;
import ug.mathe.R.id;
import ug.mathe.R.layout;
import ug.mathe.R.menu;
import ug.mathe.geometria_plana.GeoPlanaCircFragment;
import ug.mathe.geometria_plana.GeoPlanaRectFragment;
import ug.mathe.geometria_plana.GeoPlanaTrapeFragment;
import ug.mathe.geometria_plana.GeoPlanaTrianFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class GeometriaCombActivity extends ActionBarActivity {

	Fragment[] fragments = new Fragment[] {
			new GeoCombFragment()
		};		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geometria_comb);
		
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
			.add(R.id.mainGeoCombContent,fragments[0])
			.commit();		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.geometria_comb, menu);
		//return true;
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
