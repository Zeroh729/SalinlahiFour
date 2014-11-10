package com.ube.salinlahifour.debugclasses;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ube.salinlahifour.R;
import com.ube.salinlahifour.database.UserDetailOperations;
import com.ube.salinlahifour.model.UserDetail;

public class DebugUserModuleActivity extends Activity {
	private ArrayList<UserDetail> userDetails;
	private ListView listview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debug_user_module);
		
		UserDetailOperations userdb = new UserDetailOperations(this);
		userdb.open();
		
		userDetails = userdb.getAllUserDetails();
		listview = (ListView) this.findViewById(R.id.listview);
		
		DebugUserModuleAdapter adapter = new DebugUserModuleAdapter(this, R.layout.debug_listlayout_usermodule, userDetails);
		listview.setAdapter(adapter);
		listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(DebugUserModuleActivity.this);
				 
                // Setting Dialog Title
                alertDialog.setTitle("Action");
 
                // Setting Dialog Message
                alertDialog.setMessage("What do you want to do?");
 
                // Setting Icon to Dialog
//                alertDialog.setIcon(R.drawable.save);
 
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    // User pressed YES button. Write Logic Here
                    Toast.makeText(getApplicationContext(), "You clicked on Update",
                                        Toast.LENGTH_SHORT).show();
                    }
                });
 
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                    // User pressed No button. Write Logic Here
                    	UserDetailOperations userDetailOperator = new UserDetailOperations(DebugUserModuleActivity.this);
                    	userDetailOperator.open();
                    	userDetailOperator.deleteUserDetail(userDetails.get(position));
                    	userDetails.remove(position);
                    	finish();
                    	startActivity(getIntent());
                    }
                });
 
                // Setting Netural "Cancel" Button
                alertDialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    // User pressed Cancel button. Write Logic Here
//                    Toast.makeText(getApplicationContext(), "You clicked on Cancel",
//                                        Toast.LENGTH_SHORT).show();
                    }
                });
 
                // Showing Alert Message
                alertDialog.show();
			}
			
		});
	}
}
