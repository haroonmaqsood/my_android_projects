package com.example.project;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDetailsActivity extends Activity implements OnClickListener {
	// Bundle recievedInfo = null;
	ArrayList<String> itemsBasket = null;
	TextView dishName = null;
	TextView dishDescription = null;
	TextView totalQty = null;
	TextView totalPrc = null;

	Button increaseQty = null;
	Button descreaseQty = null;
	Button btnAdd = null;
	boolean isadded =false;
	ImageView ivItemImage = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.item_details);
		// recievedInfo = getIntent().getExtras();

		dishName = (TextView) findViewById(R.id.dishName);
		dishDescription = (TextView) findViewById(R.id.dishDescription);
		totalQty = (TextView) findViewById(R.id.totalQty);
		totalPrc = (TextView) findViewById(R.id.totalPrice);

		dishName.setText(MainActivity.userInfoRecieved
				.getString(Constants.BUNDLE_KEY_DISH_NAME));
		dishDescription.setText(MainActivity.userInfoRecieved
				.getString(Constants.BUNDLE_KEY_DISH_DESCRIPTION));
		totalQty.setText("1");
		totalPrc.setText(MainActivity.userInfoRecieved
				.getString(Constants.BUNDLE_KEY_DISH_PRICE));

		increaseQty = (Button) findViewById(R.id.btnIncrease);
		descreaseQty = (Button) findViewById(R.id.btnReduce);
		btnAdd = (Button) findViewById(R.id.btnAdd);

		increaseQty.setOnClickListener(this);
		descreaseQty.setOnClickListener(this);
		btnAdd.setOnClickListener(this);
		
		ivItemImage = (ImageView) findViewById(R.id.ivitemImage);
		
		String imageName =  MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_IMG_NAME); //  this is image file name
		int imgId = getResources().getIdentifier(getApplicationContext().getPackageName()+":drawable/"+imageName , null, null);
		

		ivItemImage.setImageBitmap(BitmapFactory.decodeResource(getResources(),imgId));
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		MainActivity.userInfoRecieved.putStringArrayList(Constants.BUNDLE_KEY_DISHES_SELECTED, itemsBasket);
		MainActivity.userInfoRecieved.putString(Constants.BUNDLE_KEY_TOTAL_PRICE,totalPrc.getText().toString() );
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
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnIncrease:
			totalQty.setText(String.valueOf(Integer.valueOf(totalQty.getText()
					.toString()) + 1));
			changePrice();
			break;
		case R.id.btnReduce:
			totalQty.setText(Integer.valueOf(totalQty.getText().toString()) > 0 ? String
					.valueOf(Integer.valueOf(totalQty.getText().toString())
							.intValue() - 1) : "0");
			changePrice();
			break;
		case R.id.btnAdd:
			if(!isadded){
				itemsBasket = (MainActivity.userInfoRecieved
						.getStringArrayList(Constants.BUNDLE_KEY_DISHES_SELECTED) == null) ? new ArrayList<String>(
						1) : MainActivity.userInfoRecieved
						.getStringArrayList(Constants.BUNDLE_KEY_DISHES_SELECTED);
						
				itemsBasket.add(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISHID ).trim()+" "
						+MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ", "").trim().toString()+
						" "+MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_PRICE).trim().toString()+" "
						+totalQty.getText().toString());
				Toast.makeText(getApplicationContext(), "added to basket", Toast.LENGTH_LONG).show();
				isadded = true;
				Basket.toRem = null == Basket.toRem?new ArrayList<String>():Basket.toRem;
				Basket.added = null == Basket.added?new ArrayList<String>():Basket.added;
				if(Basket.toRem.contains(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ", "").trim().toString())){
					Basket.toRem.remove(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ", "").trim().toString());
					Basket.added.remove(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ", "").trim().toString());
					
				}
			}else {
				Toast.makeText(getApplicationContext(), "already added to basket", Toast.LENGTH_LONG).show();
			}
			
			break;

		}

	}

	private void changePrice() {

		int prc = Integer.parseInt(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_PRICE).toString());
		int qty = Integer.parseInt(totalQty.getText().toString()) <= 0 ? 1
				: Integer.parseInt(totalQty.getText().toString());
		int t = ((qty == 0 ? Integer.parseInt(MainActivity.userInfoRecieved
				.getString(Constants.BUNDLE_KEY_DISH_PRICE).toString()) : prc) * qty);
		totalPrc.setText(String.valueOf(t));
		
	}

}
