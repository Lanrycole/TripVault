package com.example.tripvault.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tripvault.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

public class userDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ImageView imageView;
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
		imageView= findViewById(R.id.headerimageView);


		navigationView=findViewById(R.id.nav_menu);
		ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(
				this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer
		);
		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
		navigationView.setNavigationItemSelectedListener(this);

		View inflatedView = getLayoutInflater().inflate(R.layout.header_display, null);
		TextView text = inflatedView.findViewById(R.id.profile_name);
		text.setText("hahahaha");

//		LayoutInflater layoutInflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//	View header=layoutInflater.inflate(R.layout.header_display,null);
//
// 		ImageView v = header.findViewById(R.id.headerimageView);
//		TextView tv = (TextView) header.findViewById(R.id.profile_username);
//
//		Picasso.get().load(R.drawable.camelmin)
//				.resize(200,  100)
//				.centerInside()
//				.into(  v);
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		return false;
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {

	}

}
