package com.demo.encuestahotel.fragments;

import java.util.ArrayList;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;

import com.demo.encuestahotel.MainActivity;
import com.demo.encuestahotel.R;
import com.demo.encuestahotel.RoomDetailActivity;
import com.demo.encuestahotel.data.CustomAdapter;
import com.demo.encuestahotel.models.Room;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class RoomListFragment extends ListFragment implements
		OnRefreshListener {

	
	private PullToRefreshAttacher pull_to_refresh_attaches;

	@Override
	public View onCreateView(LayoutInflater infalter, ViewGroup group,
			Bundle svaedInstanceState) {
		// TODO Auto-generated method stub
		return infalter.inflate(R.layout.fragment_room_list, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);		

		ListView list = getListView();

		pull_to_refresh_attaches = ((MainActivity)getActivity()).getAttacher();
		pull_to_refresh_attaches.addRefreshableView(list, this);

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

		CustomAdapter adapter = new CustomAdapter(getActivity(), rooms, true);
		setListAdapter(adapter);
	}	

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Room clickedRoom = (Room)l.getItemAtPosition(position);
		Intent intent = new Intent(getActivity(), RoomDetailActivity.class);
		intent.putExtra(RoomDetailActivity.ROOM_TYPE, clickedRoom.getRoomType());
		intent.putExtra(RoomDetailActivity.ROOM_NUMBER, clickedRoom.getRoomNumber());
		startActivity(intent);		
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

}
