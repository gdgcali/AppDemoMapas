package com.demo.encuestahotel;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.demo.encuestahotel.fragments.*;
import com.demo.encuestahotel.models.Room;

public class RoomDetailActivity extends FragmentActivity implements
		SendDataDialogFragment.DialogListener {

	public final static String ROOM_TYPE = "Tipo de Habitación";
	public final static String ROOM_NUMBER = "Número de Habitación";
	public final static String Dialog_TAG = "dialogo";

	private Room room;
	private boolean favorito = false;

	public void toogleClick(View v) {
		Toast.makeText(getApplicationContext(), "Toogle", Toast.LENGTH_SHORT)
				.show();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_detail);

		Intent intent = getIntent();
		room = new Room(intent.getStringExtra(ROOM_NUMBER),
				intent.getStringExtra(ROOM_TYPE));

		ToggleButton toogle_recommendation = (ToggleButton) findViewById(R.id.toggle_recomendacion);
		toogle_recommendation.setChecked(true);

		int resource = -1;
		if (room.getRoomType().equals(Room.STANDARD_ROOM)) {
			resource = R.drawable.habit01;
		} else {
			resource = R.drawable.habit02;
		}

		ImageView img_header = (ImageView) findViewById(R.id.img_header);
		img_header.setImageResource(resource);
		setTitle(room.getRoomNumber());

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_fave:
			Drawable icon = null;

			if (favorito) {
				icon = getResources().getDrawable(
						R.drawable.ic_action_not_important);
			} else {
				icon = getResources().getDrawable(
						R.drawable.ic_action_important);
			}

			favorito = !favorito;
			item.setIcon(icon);
			return true;
		case R.id.action_share:

			Intent share = new Intent();
			share.setAction(Intent.ACTION_SEND);

			Uri imgRes = Uri.parse("android.resource://" + getPackageName()
					+ "/drawable/" + R.drawable.habit01);
			String msg = getResources().getString(R.string.msg_share);
			share.putExtra(Intent.EXTRA_TEXT, msg);
			share.putExtra(Intent.EXTRA_STREAM, imgRes);
			share.setType("image/jpeg");

			startActivity(Intent.createChooser(share, "Compartir"));

			return true;
		case R.id.action_dialog:

			SendDataDialogFragment obj = new SendDataDialogFragment();
			obj.show(getSupportFragmentManager(), "Dialogo");
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}

	@Override
	public void onDialogPositiveClick(DialogFragment dialog) {
		Toast.makeText(getApplicationContext(), "Click Positive",
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onDialogNegativeClick(DialogFragment dialog) {
		Toast.makeText(getApplicationContext(), "Click Negative",
				Toast.LENGTH_SHORT).show();
	}

}
