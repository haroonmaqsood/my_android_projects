package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class RestaurantMenu extends ExpandableListActivity {

	ArrayList<HashMap<String, Object>> result = new ArrayList<HashMap<String, Object>>(
			1);

	

	ArrayList categoryItemNames = new ArrayList();
	// Bundle userInfo = null; //checked items
	Intent itemDetailActivity = null;

	List<String> addedGroups = new ArrayList<String>();

	List<String> grpIds = new ArrayList<String>();

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.main);// main is the XML file from layout
			// getActionBar().setDisplayHomeAsUpEnabled(true);
			// userInfo = getIntent().getExtras();
			//createCategoryAndItemList();
			SimpleExpandableListAdapter expListAdapter = new SimpleExpandableListAdapter(
					this, createGroupList() /* categoryNames */, // Creating
																	// group
																	// List.
					R.layout.group_row, // Group item layout XML.
					new String[] { "Group Item" }, // the key of group item.
					new int[] { R.id.row_name }, // ID of each group item.-Data
													// under the key goes into
													// this TextView.
					createCategoryAndItemList(), // childData describes
													// second-level
					// entries.
					R.layout.child_row, // Layout for sub-level entries(second
										// level).
					new String[] { "price", "name", "desc", "id", "img" }, // Keys
																			// in
																			// childData
																			// maps
					// to display.
					new int[] { R.id.price, R.id.dishName, R.id.tvHidnDesctip,
							R.id.tvHidnID, R.id.tvHidnImg } // Data under the
															// keys
			// above go into
			// these TextViews.
			);
			setListAdapter(expListAdapter); // setting the adapter in the list.

		} catch (Exception e) {
			System.out.println("Errrr +++ " + e.getMessage());
		}
	}

	private List createGroupList() {
		ArrayList gl = new ArrayList();
		DataGetter g = new DataGetter();
		g.execute(Constants.MENU_CATEGORIES);
		try {
			String d = g.get();
			JSONObject mainObject = new JSONObject(d);
			String success = mainObject.getString("SUCCESS");
			if (success.equalsIgnoreCase("true")) {
				JSONArray Dishes = mainObject.getJSONArray("products");
				for (int i = 0; i < Dishes.length(); i++) {
					JSONObject j = Dishes.getJSONObject(i);
					HashMap m = new HashMap();
					m.put("Group Item", j.get("name"));
					gl.add(m);
					grpIds.add(j.getString("id"));
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gl;
	}

	private List createCategoryAndItemList() {
	ArrayList finalList = new ArrayList();
		for (int o = 0; o < grpIds.size(); o++) {
			DataGetter dg = new DataGetter();
			 ArrayList secList = new ArrayList(); 
			dg.execute(Constants.MENU_CATEGORY_ITEMS, grpIds.get(o));
			String data = null;
			
			try {
				data = dg.get();
				if (null != data) {
					JSONObject mainObject = new JSONObject(data.toString());

					String success = mainObject.getString("SUCCESS");
					if (success.equalsIgnoreCase("true")) {
						JSONArray Dishes = mainObject.getJSONArray("MenuItem");
						if(null != Dishes && Dishes.length() >0){
							for (int i = 0; i < Dishes.length(); i++) {
								JSONObject product = (JSONObject) Dishes.get(i);
								HashMap mi = new HashMap();
								mi.put("price", product.get("price").toString());
								mi.put("name", product.get("name").toString());
								mi.put("desc", product.get("description")
										.toString());
								mi.put("id", product.get("id").toString());
								mi.put("img", product.get("img").toString());
								secList.add( mi );
								
							}
						}
					
					}
				}
			} catch (Exception e) {
				Log.e("exception", e.getMessage());
			}
			finalList.add(secList);
		}

		return finalList;
	}

	public void onContentChanged() {
		System.out.println("onContentChanged");
		super.onContentChanged();
	}

	/* This function is called on each child click */
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {

		TextView hid = (TextView) v.findViewById(R.id.tvHidnID);
		TextView name = (TextView) v.findViewById(R.id.dishName);
		TextView price = (TextView) v.findViewById(R.id.price);
		TextView desc = (TextView) v.findViewById(R.id.tvHidnDesctip);
		TextView img = (TextView) v.findViewById(R.id.tvHidnImg);

		MainActivity.userInfoRecieved.putString(Constants.BUNDLE_KEY_DISHID,
				hid.getText().toString());
		MainActivity.userInfoRecieved.putString(
				Constants.BUNDLE_KEY_DISH_DESCRIPTION, desc.getText()
						.toString());
		MainActivity.userInfoRecieved.putString(
				Constants.BUNDLE_KEY_DISH_PRICE, price.getText().toString());
		MainActivity.userInfoRecieved.putString(Constants.BUNDLE_KEY_DISH_NAME,
				name.getText().toString());
		MainActivity.userInfoRecieved.putString(
				Constants.BUNDLE_KEY_DISH_IMG_NAME, img.getText().toString());

		itemDetailActivity = new Intent(RestaurantMenu.this,
				ItemDetailsActivity.class);
		itemDetailActivity.putExtras(MainActivity.userInfoRecieved);
		startActivity(itemDetailActivity);

		return true;
	}

	/* This function is called on expansion of the group */
	public void onGroupExpand(int groupPosition) {
		try {
			System.out.println("Group exapanding Listener => groupPosition = "
					+ groupPosition);
		} catch (Exception e) {
			System.out.println(" groupPosition Errrr +++ " + e.getMessage());
		}
	}

	// //Here The Code of Nevigation Starts
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Respond to the action bar's Up/Home button
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
		RestaurantMenu.this.finish();
	}

}
