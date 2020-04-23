package com.example.tripvault.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tripvault.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class userDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
ImageView imageView;
	private AlertDialog.Builder builder;
	private AlertDialog dialog;
	FloatingActionButton addItems;
	TextView selectedDate;
	private LayoutInflater inflater;
	Button cancelSaveTrip;
	static final int REQUEST_IMAGE_CAPTURE = 1;
	Button tripPictures;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_drawer);

		addItems = findViewById(R.id.additemsbutton);
		Toolbar toolbar;
		builder= new AlertDialog.Builder(this);
		toolbar=findViewById(R.id.main_toolbar);

		getSupportActionBar().hide();
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

		addItems.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				showPop();
			}
		});


	}

	private void showPop() {

		builder= new AlertDialog.Builder(this);
		View view = getLayoutInflater().inflate(R.layout.additems, null);
		Button dateofTrip = view.findViewById(R.id.dateoftrip);
		selectedDate = view.findViewById(R.id.selectedDate);
		cancelSaveTrip = view.findViewById(R.id.cancelsaveTrip);
		tripPictures = view.findViewById(R.id.tripPictures);

		MaterialDatePicker.Builder datebuilder = MaterialDatePicker.Builder.dateRangePicker();
		final MaterialDatePicker materialDatePicker = datebuilder.build();

		dateofTrip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				materialDatePicker.show(getSupportFragmentManager(), "SELECT DATE");

			}
		});
		materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
			@Override
			public void onPositiveButtonClick(Object selection) {
				selectedDate.setText(materialDatePicker.getHeaderText());
			}
		});
		builder.setView(view);
		dialog= builder.create();
		dialog.show();

		cancelSaveTrip.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
 			}
		});
		tripPictures.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dispatchTakePictureIntent();
			}
		});
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem item) {
		return false;
	}

	@Override
	public void onPointerCaptureChanged(boolean hasCapture) {
	}

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
 			startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), REQUEST_IMAGE_CAPTURE);
		}
	}

}
