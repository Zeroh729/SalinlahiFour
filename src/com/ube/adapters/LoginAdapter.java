package com.ube.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.model.UserDetail;

public class LoginAdapter extends ArrayAdapter<UserDetail>{
	private ArrayList<UserDetail> userDetails;
	private Context context;
	private LayoutInflater inflater;

	public LoginAdapter(Context context, int resource, ArrayList<UserDetail> objects) {
		super(context, resource, objects);
		this.context = context;
		userDetails = objects;
		
		inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	private class UserDetailHolder{
		public ImageView img_gender;
		public TextView tv_name;
		public TextView tv_loggedin;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		UserDetail item = getItem(position);
		UserDetailHolder holder;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.item_user, parent, false);
			holder = new UserDetailHolder();
			holder.img_gender = (ImageView)convertView.findViewById(R.id.img_gender);
			holder.tv_loggedin = (TextView)convertView.findViewById(R.id.tv_loggedin);
			holder.tv_name = (TextView)convertView.findViewById(R.id.tv_name);
			convertView.setTag(holder);
		}else{
			holder = (UserDetailHolder) convertView.getTag();
		}
		if(item.getGender().equals("male"))
			holder.img_gender.setImageResource(R.drawable.boy);
		else
			holder.img_gender.setImageResource(R.drawable.girl);
		holder.tv_name.setText(item.getName());
		SharedPreferences prefs = context.getSharedPreferences("appData", context.MODE_PRIVATE);
		final int lastUserID = prefs.getInt("lastUserID", -1);
		if(lastUserID == item.getId())
			holder.tv_loggedin.setText("logged in");
		else
			holder.tv_loggedin.setText("");
		
		return convertView;
	}
	
	

}
