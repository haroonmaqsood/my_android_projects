package com.example.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Basket extends Activity {
	Button btnDelete = null;
	Button btnInsrtOrder = null;
	Button btnAddMoreItems = null;
	Button btnLogout = null;

	ListView itemList = null;

	TextView tvTblNo = null;
	TextView tvPrice = null;
	TextView tvSubtotal = null;
	TextView tvSubtotalv = null;

	TextView tvDiscount = null;
	TextView tvDiscountv = null;
	TextView tvTotal = null;
	TextView tvTotalv = null;

	EditText etTableNo = null;
	
	EditText etDiscount = null;

	double subTotal = 0;
	double total = 0;
	double tax = 0;
	double discount = 0;
	double orgTotal = 0;
	boolean isInserted = false;
	ArrayList<Items> basketItems = new ArrayList<Items>(1);
	ArrayList<String> basketItemsPost = new ArrayList<String>();
	static List<Map<String, String>> listData = null == Basket.listData ? new ArrayList<Map<String, String>>(
			1) : Basket.listData;
	String from[] = { "qty", "name", "price" };
	int to[] = { R.id.tvQty, R.id.tvItemName, R.id.tvPrice };
	static List<String> toRem = null== Basket.toRem ? new ArrayList<String>() : Basket.toRem;
	static List<String> added =  null== Basket.added ? new ArrayList<String>() : Basket.added;

	@SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.basket);

		etTableNo = (EditText) findViewById(R.id.etTableNo);
		
		etDiscount = (EditText) findViewById(R.id.etDiscount);
		listData = null == Basket.listData ? new ArrayList<Map<String, String>>(
				1) : Basket.listData;

		btnAddMoreItems = (Button) findViewById(R.id.btnAddMoreItems);
		btnDelete = (Button) findViewById(R.id.btnDelete);
		btnInsrtOrder = (Button) findViewById(R.id.btnInserOrder);
		btnLogout = (Button) findViewById(R.id.btnLogout);

		btnDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// set list adapter with true flag for delete
				SimpleAdapter s = new BasketRowAdapter(getApplicationContext(),
						Basket.listData, R.layout.basket_row_layout, from, to,
						true);
				itemList.setAdapter(s);
			}
		});

		btnInsrtOrder.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String lastorder = null;
				if (null != etTableNo.getText()
						&& !etTableNo.getText().toString().equals("") && null != etDiscount.getText()
								&& !etDiscount.getText().toString().equals("")) {
					if (!isInserted) {
						// insert order first

						DataGetter dg = new DataGetter();
						dg.execute(Constants.INSERT_ORDER, etTableNo.getText().toString());
						String r;
						try {
							r = dg.get();
							JSONObject jr = new JSONObject(r.toString());
							if ("1".equals(jr.getString("StatusID"))) {
								lastorder = jr.getString("Last_Order_id");
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
						

						// add the order details and pay bill
						for (int i = 0; i < basketItems.size(); i++) {
							// insert order and then insert order details;

							String b = etTableNo.getText().toString() + " "+ basketItems.get(i).getItemName() + " "+ basketItems.get(i).getStatus() + " ";
							basketItemsPost.add(b);
//							MainActivity.userInfoRecieved.putStringArrayList(
//									Constants.BUNDLE_KEY_ITEMS_IN_BASKET,
//									basketItemsPost);

							DataGetter dg2 = new DataGetter();
							dg2.execute(Constants.INSERT_ORDER_DETAILS,lastorder, basketItems.get(i).getItemId(),basketItems.get(i).getQty());

//							DataGetter dg3 = new DataGetter();
//							//"insert_bill.php?&order_id="+params[1]+"&amount="+params[2]+"&discount="+params[3]+
////							/"&tax="+params[4]+"&paid="+params[5]
//							dg3.execute(Constants.PAY_BILL,lastorder,tvTotalv.getText().toString(),etDiscount.getText().toString(),etTax.getText().toString(),"0");
						}
						DataGetter dg3 = new DataGetter();
						//"insert_bill.php?&order_id="+params[1]+"&amount="+params[2]+"&discount="+params[3]+
//						/"&tax="+params[4]+"&paid="+params[5]
						dg3.execute(Constants.PAY_BILL,lastorder,tvTotalv.getText().toString(),etDiscount.getText().toString(),"0");
						MainActivity.userInfoRecieved.putStringArrayList(
								Constants.BUNDLE_KEY_ITEMS_IN_BASKET,
								basketItemsPost);
						Toast.makeText(getApplicationContext(),
								"order inserted", Toast.LENGTH_LONG).show();
						isInserted = true;

					} else {
						Toast.makeText(getApplicationContext(),
								"order already inserted", Toast.LENGTH_LONG)
								.show();
					}
					String userName = MainActivity.userInfoRecieved
							.getString(Constants.BUNDLE_KEY_USER_REAL_NAME);
					MainActivity.userInfoRecieved = null;
					MainActivity.userInfoRecieved = new Bundle();
					MainActivity.userInfoRecieved.putString(
							Constants.BUNDLE_KEY_USER_REAL_NAME, userName);
					listData= null;
				} else {
					Toast.makeText(getApplicationContext(),
							"enter valid values in all fields", Toast.LENGTH_LONG).show();
				}
			}

		});

		btnAddMoreItems.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(Basket.this, MainActivity.class);
				startActivity(in);
			}
		});

		btnLogout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent in = new Intent(Basket.this, LoginActivity.class);
				startActivity(in);
			}
		});

		tvTotal = (TextView) findViewById(R.id.tvTotal);
		tvTotalv = (TextView) findViewById(R.id.tvTotalv);

		tvSubtotalv = (TextView) findViewById(R.id.tvSubtotalv);
		tvSubtotalv.setText(MainActivity.userInfoRecieved
				.getString(Constants.BUNDLE_KEY_TOTAL_PRICE));
		tvSubtotal = (TextView) findViewById(R.id.tvSubtotal);

		tvPrice = (TextView) findViewById(R.id.tvPrice);
		tvTblNo = (TextView) findViewById(R.id.tvTblNo);

		itemList = (ListView) findViewById(R.id.list);
		// String from[] = { "qty", "name","price" };
		// int to[] = { R.id.tvQty,R.id.tvItemName, R.id.tvPrice };
		// List<Map<String, String>> listData = new ArrayList<Map<String,
		// String>>(
		// 1);

		if (null != MainActivity.userInfoRecieved
				.getStringArrayList(Constants.BUNDLE_KEY_DISHES_SELECTED)) {
			for (int i = 0; i < MainActivity.userInfoRecieved
					.getStringArrayList(Constants.BUNDLE_KEY_DISHES_SELECTED)
					.size(); i++) {
				
				/**
				 * MainActivity.userInfoRecieved.getString(Constants.
				 * BUNDLE_KEY_DISHID ).trim()+" "
				 * +MainActivity.userInfoRecieved.
				 * getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ",
				 * "").trim().toString()+
				 * " "+MainActivity.userInfoRecieved.getString
				 * (Constants.BUNDLE_KEY_DISH_PRICE).trim().toString()+" "
				 * +totalQty.getText().toString());
				 * */

				String[] da = MainActivity.userInfoRecieved
						.getStringArrayList(
								Constants.BUNDLE_KEY_DISHES_SELECTED).get(i)
						.split(" ");
				Items it = new Items(da[0], "0", da[3], "0", tvTblNo.getText()
						.toString(), da[1], "1");
//				basketItems.add(it);
				Map<String, String> map = new HashMap<String, String>(1);
				map.put("name", da[1]);
				map.put("price", da[2]);
				total = total + Double.parseDouble(da[2]);
				map.put("qty", da[3]);
				
					if(!Basket.toRem.contains(da[1]) && !added.contains(da[1])){
						added.add(da[1]);
						listData.add(map);
						basketItems.add(it);
				}

			}
		}
		if (null != MainActivity.userInfoRecieved
				.getStringArrayList(Constants.BUNDLE_KEY_DEALS_SELECTED)) {
			for (int i = 0; i < MainActivity.userInfoRecieved
					.getStringArrayList(Constants.BUNDLE_KEY_DEALS_SELECTED)
					.size(); i++) {
				String[] da = MainActivity.userInfoRecieved
						.getStringArrayList(Constants.BUNDLE_KEY_DEALS_SELECTED)
						.get(i).split(" ");
				
				Items it = new Items(da[0], "0", da[2], "0", "0", da[4], da[5]);
//				basketItems.add(it);
				Map<String, String> map = new HashMap<String, String>(1);
				map.put("name", da[4]);
				map.put("price", da[3]);
				total = total + Integer.parseInt(da[3]);
				map.put("qty", da[2]);

				if(!Basket.toRem.contains(da[4])&& !added.contains(da[4]) ){
					listData.add(map);
					added.add(da[4]);
					basketItems.add(it);
				}
			}
		}
		tvTotalv.setText(String.valueOf(total));
		tvSubtotalv.setText(String.valueOf(total));
		orgTotal = total;

		etDiscount.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				String dv = etDiscount.getText().toString();
				if(null != dv && !dv.equals("") && !dv.contains(".")){
					double dvv = Double.parseDouble(dv);
					if (dvv > 0) {
						total = total - dvv;
						tvTotalv.setText(String.valueOf(total));
						tvSubtotalv.setText(String.valueOf(total));
					}
				}
				else {
					tvTotalv.setText(String.valueOf(orgTotal));
					Toast.makeText(getApplicationContext(), "enter valid discont amount ", Toast.LENGTH_LONG).show();
				}
				

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

		SimpleAdapter s = new BasketRowAdapter(getApplicationContext(),
				listData == null ? new ArrayList<Map<String,?>>() : listData, R.layout.basket_row_layout, from, to, false);
		itemList.setAdapter(s);
	}

	@Override
	public void onBackPressed() {
		moveTaskToBack(true);
		Basket.this.finish();
	}

}
