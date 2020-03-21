package com.example.tripvault.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.tripvault.R;
import com.google.android.material.navigation.NavigationView;

public class userDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_drawer);

		Toolbar toolbar;
		toolbar=findViewById(R.id.main_toolbar);
		setSupportActionBar(toolbar);
		DrawerLayout drawerLayout;
		NavigationView navigationView;

		drawerLayout=findViewById(R.id.drawer_layout);


		navigationView=findViewById(R.id.nav_menu);
		ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(
				this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer
		);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
		navigationView.setNavigationItemSelectedListener(this);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		return false;
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}
}
