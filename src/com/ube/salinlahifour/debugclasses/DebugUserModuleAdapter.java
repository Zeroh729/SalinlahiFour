package com.ube.salinlahifour.debugclasses;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.model.UserDetail;

public class DebugUserModuleAdapter extends ArrayAdapter {
	private Context context;
	private ArrayList<UserDetail> user;
	private LayoutInflater inflater;
	
	public DebugUserModuleAdapter(Context context, int resource, ArrayList<UserDetail> objects) {
		super(context, resource, objects);
		this.context = context;
		user = objects; 
		
		inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	private class UserDetailModuleHolder{
		public TextView id;
		public TextView username;
		public TextView gender;
		public TextView date;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		UserDetail item = (UserDetail) getItem(position);
		UserDetailModuleHolder holder;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.debug_listlayout_usermodule, parent, false );
			holder = new UserDetailModuleHolder();
			holder.id = (TextView)convertView.findViewById(R.id.tv_id);
			holder.username = (TextView)convertView.findViewById(R.id.tv_username);
			holder.gender = (TextView)convertView.findViewById(R.id.tv_gender);
			holder.date = (TextView)convertView.findViewById(R.id.tv_date);
			convertView.setTag(holder);
		}else{
			holder = (UserDetailModuleHolder)convertView.getTag();
		}
		
		holder.id.setText(item.getId() + "");
		holder.username.setText(item.getName());
		holder.gender.setText(item.getGender());
		holder.date.setText(item.getDateCreated());
		
		
		return convertView;
	}
	
	

}
