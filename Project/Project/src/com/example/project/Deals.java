package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class Deals extends Activity {

	ListView lvDeals = null;
	Button btnLogout = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deals_main);

		lvDeals = (ListView) findViewById(R.id.lvDeals);
		btnLogout = (Button) findViewById(R.id.btnLogout);
		btnLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				MainActivity.userInfoRecieved = null;
				Intent in = new Intent(Deals.this, LoginActivity.class);
				startActivity(in);

			}
		});
		
		String[] from = { "dname","dprice","dstrttime","dendtime" };
	
		int[] to = { R.id.tvDealNamev,R.id.tvDealPriceV,R.id.tvStartTimev,R.id.tvEndTimeV};

		List<Map<String,String>> dataList = new ArrayList <Map<String,String>>(1);
		DataGetter dg = new DataGetter();
		dg.execute(Constants.DEALS);
		try{
			JSONObject mainObject = new JSONObject(dg.get());
			String success = mainObject.getString("SUCCESS");
			if(success.equalsIgnoreCase("true")){
				JSONArray deals = mainObject.getJSONArray("Dishes");
				for(int i= 0;i<deals.length();i++){
					JSONObject o = deals.getJSONObject(i);
					String id = o.getString("id");
					String name = o.getString("name");
					String description = o.getString("description");
					String start_time = o.getString("start_time");
					String end_time = o.getString("end_time");
					String price = o.getString("price");
					String available = o.getString("available");
					String img = o.getString("img");
					Map tmap = new HashMap<String,String >();
					tmap.put("did", id);
					tmap.put("dname", name);
					tmap.put("ddesc", description);
					tmap.put("dstrttime", start_time);
					tmap.put("dendtime", end_time);
					tmap.put("dprice", price);
					tmap.put("available", available);
					tmap.put("dimg", img);
					dataList.add(tmap);
				}
				DealsListAdapter dla = new DealsListAdapter(getBaseContext(), dataList, R.layout.deals_list_view_layout, from, to);
				lvDeals.setAdapter(dla);
				
			}
			}catch(Exception e ){
			Log.e("exception in getting delas info ", e.getMessage());
		}
		
	}

	@Override
	public void onBackPressed() {

		moveTaskToBack(true);
		Deals.this.finish();
	}
}