package ug.mathe.geometria;

import ug.mathe.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class GeometriaActivity extends ActionBarActivity implements TabListener {

	Fragment[] fragments = new Fragment[] {
					new GeoGenFragment()
					, new GeoCircunFragment()
					, new GeoElipFragment()
					, new GeoParaFragment()
					, new GeoHiperFragment()
				};	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geometria);		
		
		ActionBar actionbar = getSupportActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_general))
					.setTabListener(this));		
							
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_circunferencia))
					.setTabListener(this));
		
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_elipse))
					.setTabListener(this));
		
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_parabola))
					.setTabListener(this));
		
		actionbar.addTab(
				actionbar.newTab()
					.setText(getString(R.string.title_fragment_hiperbola))
					.setTabListener(this));	
		
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction()
			.add(R.id.mainGeometriaContent,fragments[0])
			.add(R.id.mainGeometriaContent,fragments[1])
			.add(R.id.mainGeometriaContent,fragments[2])
			.add(R.id.mainGeometriaContent,fragments[3])
			.add(R.id.mainGeometriaContent,fragments[4])
			.commit();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.geometria, menu);
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
					.hide(fragments[4])
					.show(fragments[0]);
				break;
			case 1:
				ft.hide(fragments[0])
				.hide(fragments[2])
				.hide(fragments[3])
				.hide(fragments[4])
				.show(fragments[1]);
				break;
			case 2:
				ft.hide(fragments[0])
				.hide(fragments[1])
				.hide(fragments[3])
				.hide(fragments[4])
				.show(fragments[2]);
				break;
			case 3:
				ft.hide(fragments[0])
				.hide(fragments[1])
				.hide(fragments[2])
				.hide(fragments[4])
				.show(fragments[3]);
				break;
			case 4:
				ft.hide(fragments[0])
				.hide(fragments[1])
				.hide(fragments[2])
				.hide(fragments[3])
				.show(fragments[4]);
				break;							
		}	
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}
}
