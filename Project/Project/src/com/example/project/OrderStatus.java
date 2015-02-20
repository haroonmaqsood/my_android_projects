package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class OrderStatus extends Activity{

	OrderStatusAdapter oa =null;
	EditText etTblNo ;
	Button btn;
	Button pay= null;
	Button btnCnclOrder = null;
	TextView textView7 = null;
	public static List<Map<String, String>> listData =null== OrderStatus.listData? new ArrayList<Map<String,String>>() : OrderStatus.listData;
	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderstatus);
		etTblNo =(EditText) findViewById(R.id.etTableNo);
		btn =(Button)findViewById(R.id.btnSearch);
		btnCnclOrder =(Button)findViewById(R.id.btnCnclOrder);
		pay = (Button)findViewById(R.id.btmnPayBill);
		textView7 = (TextView)findViewById(R.id.textView7);
		
		btnCnclOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(etTblNo.getText() != null && !etTblNo.getText().equals("")){
					DataGetter d = new DataGetter();
					d.execute(Constants.CANCEL_ORDER,etTblNo.getText().toString());
					Toast.makeText(getApplicationContext(), "Order Cancelled", Toast.LENGTH_LONG).show();
					
					// TODO Auto-generated method stub
					String from[] = { "tvItNmeV", "tvQtyV","tvPriceV","tvEndTmV","tvStatusV","tvHidnDet" };
					int to[] = { R.id.tvItNmeV,R.id.tvQtyV, R.id.tvPriceV,R.id.tvEndTmV,R.id.tvStatusV,R.id.tvHidnDe };
					OrderStatus.listData = new ArrayList<Map<String,String>>();
					DataGetter d2 = new DataGetter();
					d2.execute(Constants.GET_ORDER_STATUS,etTblNo.getText().toString());
					try {
						String r =d.get();
						try {
							JSONObject j = new JSONObject(r);
							if("true".equals(j.getString("SUCCESS"))){
								JSONArray stat = j.getJSONArray("Order_Status");
								for(int i=0;i<stat.length();i++){
									JSONObject jo  = (JSONObject)stat.get(i);
									HashMap is = new HashMap();
									is.put("tvItNmeV", jo.get("name"));
									is.put("tvQtyV", jo.get("item_quantity"));
									is.put("tvPriceV", jo.get("price"));
									is.put("tvEndTmV", jo.get("e_time"));
									is.put("tvStatusV", (null != jo.get("status") && !"null".equals(jo.get("status").toString())) ? jo.get("status") : "N/A");
									is.put("tvHidnDet", jo.get("odid"));
									textView7.setText(null != jo.get("amount") ? String.valueOf(jo.get("amount")) :"0");
									listData.add(is);
								}
								
								//{"Order_Status":[{"id":"1","item_quantity":"1","name":"None","price":"1234","paid":"0","e_time":"00:00:00","status":null}],"SUCCESS":true}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(null != listData){
						ListView l = (ListView)findViewById(R.id.list_view);
						l.setVisibility(View.VISIBLE);
						 oa = new OrderStatusAdapter(getApplicationContext(), listData, R.layout.order_list_item, from, to);
						l.setAdapter(oa);
						
					}
				}
				textView7.setText("0");
			}
			
		});
		
		pay.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(null != etTblNo.getText()){
					DataGetter d= new DataGetter();
					d.execute(Constants.INSERT_BILL,etTblNo.getText().toString());
					Toast.makeText(getApplicationContext(), "Bill Paid", Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(getApplicationContext(), "Enter a valid table Number", Toast.LENGTH_LONG).show();
				}
			}
		});
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String from[] = { "tvItNmeV", "tvQtyV","tvPriceV","tvEndTmV","tvStatusV","tvHidnDet" };
				int to[] = { R.id.tvItNmeV,R.id.tvQtyV, R.id.tvPriceV,R.id.tvEndTmV,R.id.tvStatusV,R.id.tvHidnDe };
				listData =  new ArrayList<Map<String,String>>() ;
				if(null != etTblNo.getText() && !etTblNo.getText().toString().equals(" ")){
					DataGetter d = new DataGetter();
					d.execute(Constants.GET_ORDER_STATUS,etTblNo.getText().toString());
					try {
						String r =d.get();
						try {
							JSONObject j = new JSONObject(r);
							if("true".equals(j.getString("SUCCESS"))){
								JSONArray stat = j.getJSONArray("Order_Status");
								for(int i=0;i<stat.length();i++){
									JSONObject jo  = (JSONObject)stat.get(i);
									HashMap is = new HashMap();
									is.put("tvItNmeV", jo.get("name"));
									is.put("tvQtyV", jo.get("item_quantity"));
									is.put("tvPriceV", jo.get("price"));
									is.put("tvEndTmV", jo.get("e_time"));
									is.put("tvStatusV", (null != jo.get("status") && !"null".equals(jo.get("status").toString())) ? jo.get("status") : "N/A");
									is.put("tvHidnDet", jo.get("odid"));
									listData.add(is);
									textView7.setText(null != jo.get("amount") ? String.valueOf(jo.get("amount")) :"0");
								}
								
								//{"Order_Status":[{"id":"1","item_quantity":"1","name":"None","price":"1234","paid":"0","e_time":"00:00:00","status":null}],"SUCCESS":true}
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(null != listData){
						ListView l = (ListView)findViewById(R.id.list_view);
						l.setVisibility(View.VISIBLE);
						 oa = new OrderStatusAdapter(getApplicationContext(), listData, R.layout.order_list_item, from, to);
						l.setAdapter(oa);
						
					}
					
				}else {
					Toast.makeText(getApplicationContext(), "Enter a valid table number", Toast.LENGTH_LONG).show();
				}
			}
		});

	}
	
	 ////Here The Code of Nevigation Starts
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
	      OrderStatus.this.finish();
	   }

}
