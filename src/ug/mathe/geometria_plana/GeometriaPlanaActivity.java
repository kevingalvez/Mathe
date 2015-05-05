package ug.mathe.geometria_plana;

import ug.mathe.R;
import ug.mathe.R.id;
import ug.mathe.R.layout;
import ug.mathe.R.menu;
import ug.mathe.geometria.GeoCircunFragment;
import ug.mathe.geometria.GeoElipFragment;
import ug.mathe.geometria.GeoGenFragment;
import ug.mathe.geometria.GeoHiperFragment;
import ug.mathe.geometria.GeoParaFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar.TabListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class GeometriaPlanaActivity extends ActionBarActivity implements TabListener {

	Fragment[] fragments = new Fragment[] {
			new GeoPlanaCircFragment()
			, new GeoPlanaRectFragment()
			, new GeoPlanaTrianFragment()
			, new GeoPlanaTrapeFragment()
		};		
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geometria_plana);
		
		ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_circulo))
					.setTabListener(this));		
							
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_rectangulo))
					.setTabListener(this));
		
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_triangulo))
					.setTabListener(this));
		
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_trapecio))
					.setTabListener(this));
				
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
			.add(R.id.mainGeoPlanaContent,fragments[0])
			.add(R.id.mainGeoPlanaContent,fragments[1])
			.add(R.id.mainGeoPlanaContent,fragments[2])
			.add(R.id.mainGeoPlanaContent,fragments[3])
			.commit();

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.geometria_plana, menu);
		return true;
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

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		switch (tab.getPosition()) {
		case 0:
			ft.hide(fragments[1])
				.hide(fragments[2])
				.hide(fragments[3])
				.show(fragments[0]);
			break;
		case 1:
			ft.hide(fragments[0])
			.hide(fragments[2])
			.hide(fragments[3])
			.show(fragments[1]);
			break;
		case 2:
			ft.hide(fragments[0])
			.hide(fragments[1])
			.hide(fragments[3])
			.show(fragments[2]);
			break;
		case 3:
			ft.hide(fragments[0])
			.hide(fragments[1])
			.hide(fragments[2])
			.show(fragments[3]);
			break;
					
		}	
		
		
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}
