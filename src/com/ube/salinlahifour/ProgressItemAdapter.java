package com.ube.salinlahifour;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ube.salinlahifour.model.ProgressListItems;
import com.ube.salinlahifour.model.UserDetail;

public class ProgressItemAdapter extends ArrayAdapter {
	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ProgressListItems> items;
	
	public ProgressItemAdapter(Context context, int resource, ArrayList<ProgressListItems> objects) {
		super(context, resource, objects);
		this.context = context;
		items = new ArrayList();
		items = objects; 
		
		for(ProgressListItems item : items)
		Log.d("ITEM: " + item.isLessonCategory() + " " + item.getLessonName() + " " + item.getEasyStarDrawableId() + " " + item.getMedStarDrawableId() + " " + item.getHardStarDrawableId()  + " " + item.getItemName() + " " + item.getProgress(), "TEST");
		
		inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private class ProgressItemHolder{
		public TextView lessonname;
		public ImageView easystar;
		public ImageView medstar;
		public ImageView hardstar;
		public TextView lessonitem;
		public ProgressBar progressbar;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ProgressListItems item = (ProgressListItems) getItem(position);
		ProgressItemHolder holder;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.progress_item, parent, false );
			holder = new ProgressItemHolder();
			holder.lessonname = (TextView)convertView.findViewById(R.id.tv_lessonname);
			holder.easystar = (ImageView)convertView.findViewById(R.id.lesson_easystar);
			holder.medstar = (ImageView)convertView.findViewById(R.id.lesson_medstar);
			holder.hardstar = (ImageView)convertView.findViewById(R.id.lesson_hardstar);
			holder.lessonitem = (TextView)convertView.findViewById(R.id.tv_lessonitem);
			holder.progressbar = (ProgressBar)convertView.findViewById(R.id.progressBar);
			convertView.setTag(holder);
		}else{
			holder = (ProgressItemHolder)convertView.getTag();
		}
		
		Log.d("ITEM: " + item.isLessonCategory() + " " + item.getLessonName() + " " + item.getEasyStarDrawableId() + " " + item.getMedStarDrawableId() + " " + item.getHardStarDrawableId()  + " " + item.getItemName() + " " + item.getProgress(), "TEST DEBUG");
		
		
		if(item.isLessonCategory()){
			convertView.findViewById(R.id.layout_lessonname).setVisibility(View.VISIBLE);
			convertView.findViewById(R.id.layout_lessonitems).setVisibility(View.GONE);
			
			holder.lessonname.setText(item.getLessonName());
			holder.easystar.setImageResource(item.getEasyStarDrawableId());
			holder.medstar.setImageResource(item.getMedStarDrawableId());
			holder.hardstar.setImageResource(item.getHardStarDrawableId());
			holder.lessonname.setTypeface(SalinlahiFour.getFontBpreplay());
		}else{
			convertView.findViewById(R.id.layout_lessonname).setVisibility(View.GONE);
			convertView.findViewById(R.id.layout_lessonitems).setVisibility(View.VISIBLE);
			
			holder.lessonitem.setText(item.getItemName());
			holder.progressbar.setProgress(item.getProgress());
			holder.lessonitem.setTypeface(SalinlahiFour.getFontBpreplay());
		}
		
		return convertView;
	}

	
}
