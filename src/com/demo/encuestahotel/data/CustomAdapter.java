package com.demo.encuestahotel.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.encuestahotel.R;
import com.demo.encuestahotel.models.Room;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Room> {

	ArrayList<Room> data;
	LayoutInflater inflater;
	boolean is_list;

	public CustomAdapter(Context context, ArrayList<Room> objects, boolean isList) {
		super(context, -1, objects);
		this.data = objects;
		this.inflater = LayoutInflater.from(context);
		this.is_list = isList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ViewHolder holder;
		Room actual = data.get(position);
		String roomType = actual.getRoomType();

		int imgResource = 0;

		if (roomType.equals(Room.STANDARD_ROOM)) {
			imgResource = R.drawable.habit01;
		} else {
			imgResource = R.drawable.habit02;
		}
		
		int layout = is_list?R.layout.list_row : R.layout.grid_element;
		
		if (convertView == null) {
			
			convertView = inflater.inflate(layout, null);
			
			holder = new ViewHolder();
			holder.img = (ImageView)convertView.findViewById(R.id.img_row);
			holder.title = (TextView)convertView.findViewById(R.id.txt_row_title);
			holder.subtitle = (TextView)convertView.findViewById(R.id.txt_row_subtitle);
			
			convertView.setTag(holder);
		}else{
			
			holder = (ViewHolder)convertView.getTag();
			
		}
		
		holder.title.setText(actual.getRoomNumber());
		holder.subtitle.setText(roomType);
		holder.img.setImageResource(imgResource);		

		return convertView;
	}

	private static class ViewHolder {
		public ImageView img;
		public TextView title;
		public TextView subtitle;

	}

}
