package com.example.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;

public class DataGetter extends AsyncTask<String, Void, String> {

	@Override
	protected String doInBackground(String... params) {
		String result = null;
		if(params[0].equals(Constants.MENU_CATEGORIES)){
			try {
				result = getData("class.php");
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e("in getting menu items",e.getMessage());
			} catch (IOException e) {
				Log.e("in getting menu items",e.getMessage());
			}
			
			
		}else if(params[0].equals(Constants.DEALS)){
			try {
				result = getData("deals.php?method=getdeals");
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				Log.e("in getting deals ",e.getMessage());
			} catch (IOException e) {
				Log.e("in getting deals ",e.getMessage());
			}
		}
		else if(params[0].equals(Constants.INSERT_ORDER)){
			try {
				//order id,item_id,qty,tbl no, amount
				result = getData("insert_order.php?&table_id="+params[1]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		else if(params[0].equals(Constants.INSERT_ORDER_DETAILS)){
			try {
				/*
				 * $order_id=$_GET['order_id'];
$item_id=$_GET['item_id'];
$item_quantity=$_GET['item_quantity'];
				 * */
				result = getData("insert_order_details.php?&order_id="+params[1]+"&item_id="+params[2]+"&item_quantity="+params[3]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		else if(params[0].equals(Constants.GET_ORDER_STATUS)){
			try {
				result = getData("order_status.php?&table_id="+params[1]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		else if(params[0].equals(Constants.MENU_CATEGORY_ITEMS)){
			try {
				result = getData("each_menu_item.php?&id="+params[1]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		else if(Constants.PAY_BILL.equals(params[0])){
			try {
				result = getData("insert_bill.php?&order_id="+params[1]+"&amount="+params[2]+"&discount="+params[3]+
				"&paid="+params[4]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		else  if(Constants.INSERT_BILL.equals(params[0])){
			try {
				result = getData("bill_paid.php?&table_Id="+params[1]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		else  if(Constants.CANCEL_ORDER.equals(params[0])){
			try {
				result = getData("cancel_order.php?&table_id="+params[1]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		else  if(Constants.CANCEL_ORDER_ITEM.equals(params[0])){
			try {
				result = getData("cancel_each_item.php?&order_details_id="+params[1]);
			} catch (ClientProtocolException e) {
				Log.e("in inserting order ",e.getMessage());
			} catch (IOException e) {
				Log.e("in inserting order ",e.getMessage());
			}
		}
		return result;
	}

	private String getData(String... params) throws ClientProtocolException,
			IOException {
		String data = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://10.0.2.2/android/" + params[0]);
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		InputStream input = entity.getContent();

		BufferedReader reader = new BufferedReader(new InputStreamReader(input,
				"iso-8859-1"), 8);
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		input.close();
		data = sb.toString();
		return data;
	}

}
