package com.demo.encuestahotel;

import com.demo.encuestahotel.fragments.MainFragment;
import com.demo.encuestahotel.fragments.TermsFragment;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@TargetApi(14)
public class MainActivity extends FragmentActivity implements
		ListView.OnItemClickListener {

	private ListView drawerList;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToogle;
	private PullToRefreshAttacher pull_to_refresh_attaches;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);

		pull_to_refresh_attaches = PullToRefreshAttacher.get(this);

		drawerList = (ListView) findViewById(R.id.left_drawer);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		ArrayAdapter<String> drawerAdapter = new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, getResources().getStringArray(
						R.array.array_drawer_options));
		drawerList.setAdapter(drawerAdapter);
		drawerList.setOnItemClickListener(this);

		drawerToogle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			@Override
			public void onDrawerClosed(View drawerView) {
				invalidateOptionsMenu();
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				invalidateOptionsMenu();
			}

		};

		drawerLayout.setDrawerListener(drawerToogle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		selectItem(0);

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToogle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToogle.onOptionsItemSelected(item)) {
			return true;
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToogle.syncState();
	}

	public void selectItem(int position) {
		Fragment f;

		if (position == 0) {
			f = new MainFragment();
		} else {
			f = new TermsFragment();
		}

		FragmentManager fm = getSupportFragmentManager();
		fm.beginTransaction().replace(R.id.main_content, f).commit();
		drawerList.setItemChecked(position, true);
		setTitle(drawerList.getItemAtPosition(position).toString());
		drawerLayout.closeDrawer(drawerList);

	}

	public PullToRefreshAttacher getAttacher() {
		return pull_to_refresh_attaches;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		selectItem(arg2);
	}

}
