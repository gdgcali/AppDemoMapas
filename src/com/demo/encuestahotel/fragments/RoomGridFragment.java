package com.demo.encuestahotel.fragments;

import java.util.ArrayList;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;

import com.demo.encuestahotel.MainActivity;
import com.demo.encuestahotel.R;
import com.demo.encuestahotel.RoomDetailActivity;
import com.demo.encuestahotel.data.CustomAdapter;
import com.demo.encuestahotel.models.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class RoomGridFragment extends Fragment implements OnRefreshListener,
		OnItemClickListener {

	private GridView grid;
	private PullToRefreshAttacher pull_to_refresh_attaches;

	@Override
	public View onCreateView(LayoutInflater infalter, ViewGroup group,
			Bundle svaedInstanceState) {
		View view = infalter.inflate(R.layout.fragment_room_grid, null);
		grid = (GridView) view.findViewById(R.id.grid_rooms);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		pull_to_refresh_attaches = ((MainActivity) getActivity()).getAttacher();
		pull_to_refresh_attaches.addRefreshableView(grid, this);

		ArrayList<Room> rooms = new ArrayList<Room>();

		for (String room : getResources().getStringArray(
				R.array.array_rooms_standard)) {
			Room one = new Room(room, Room.STANDARD_ROOM);
			rooms.add(one);
		}

		for (String room : getResources().getStringArray(
				R.array.array_rooms_luxury)) {
			Room one = new Room(room, Room.LUXURY_ROOM);
			rooms.add(one);
		}

		CustomAdapter adapter = new CustomAdapter(getActivity(), rooms, false);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(this);
	}

	@Override
	public void onRefreshStarted(View view) {
		new AsyncTask<Void, Void, Void>() {

			@Override
			protected Void doInBackground(Void... params) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
				}

				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				pull_to_refresh_attaches.setRefreshComplete();
			}

		}.execute();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		Room clickedRoom = (Room) grid.getItemAtPosition(position);
		Intent intent = new Intent(getActivity(), RoomDetailActivity.class);
		intent.putExtra(RoomDetailActivity.ROOM_TYPE, clickedRoom.getRoomType());
		intent.putExtra(RoomDetailActivity.ROOM_NUMBER,
				clickedRoom.getRoomNumber());
		startActivity(intent);

	}

}
