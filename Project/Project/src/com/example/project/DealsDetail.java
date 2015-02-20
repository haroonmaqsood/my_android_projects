package com.example.project;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DealsDetail extends Activity implements OnClickListener {
	public DealsDetail() {

	}

	TextView tvDealName = null;
	TextView tvDealDesc = null;
	TextView tvDealQty = null;
	TextView tvDealPrice = null;
	TextView hidnstat = null;

	Button btnAddToBasket = null;
	Button btnIncDealQty = null;
	Button btnDecDealQty = null;
	
	Button btnLogout = null;
	int orignaPrice = 0;
	boolean isInserted = false;
	ImageView ivDI = null;
	 ArrayList<String> dealsList = null;
	 String dealId = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deal_detail);
		tvDealName = (TextView) findViewById(R.id.tvDealDetailName);
		tvDealDesc = (TextView) findViewById(R.id.tvDDDescVal);
		tvDealQty = (TextView) findViewById(R.id.tvDealQtyV);
		tvDealPrice = (TextView) findViewById(R.id.tvDealTotalVal);
		ivDI = (ImageView)findViewById(R.id.ivDDDPic);
		hidnstat = (TextView)findViewById(R.id.tvHidnDealSts);
		/*
		 * StringBuilder sb =  new StringBuilder(tvHidnId.getText().toString().trim()+" ");
					sb.append(tvHidnDesc.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnName.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnPrice.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnImg.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnStatus.getText().toString().replaceAll(" ","").trim());
		 * */
		String dealToDisplay = MainActivity.userInfoRecieved
				.getString(Constants.BUNDLE_KEY_DEAL_TO_CHECK);
		String[] tokens = dealToDisplay.split(" ");
		tvDealName.setText(tokens[2]);
		tvDealDesc.setText(tokens[1]);
		tvDealQty.setText("1");
		tvDealPrice.setText(tokens[3]);
		dealId = tokens[0];
		hidnstat.setText(tokens[5]);
		
		String dealImg = tokens[4];
		int imgId = getApplicationContext().getResources().getIdentifier(getApplicationContext().getPackageName()+":drawable/"+dealImg , null, null);
		

		ivDI.setImageBitmap(BitmapFactory.decodeResource(getApplicationContext().getResources(),imgId));
	   
		
		
		orignaPrice = Integer.parseInt(tokens[3]);

		btnAddToBasket = (Button) findViewById(R.id.btnADTB);
		btnIncDealQty = (Button) findViewById(R.id.btnIncDealQty);
		btnDecDealQty = (Button) findViewById(R.id.btnDecDealQty);
		
		btnLogout = (Button) findViewById(R.id.btnLogoutFromDD);

		btnAddToBasket.setOnClickListener(this);
		btnIncDealQty.setOnClickListener(this);
		btnDecDealQty.setOnClickListener(this);
		
		btnLogout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnADTB:
			if(!isInserted){
				dealsList = null == MainActivity.userInfoRecieved.getStringArrayList(Constants.BUNDLE_KEY_DEALS_SELECTED)?new ArrayList<String>(1):
					MainActivity.userInfoRecieved.getStringArrayList(Constants.BUNDLE_KEY_DEALS_SELECTED);
				StringBuilder sb =  new StringBuilder(dealId+" ");
				sb.append(tvDealDesc.getText().toString().replaceAll(" ","").trim()+" ");
				sb.append(tvDealQty.getText().toString().replaceAll(" ","").trim()+" ");
				sb.append(tvDealPrice.getText().toString().replaceAll(" ","").trim()+" ");
				sb.append(tvDealName.getText().toString().replaceAll(" ","").trim()+" ");
				sb.append(hidnstat.getText().toString().replaceAll(" ","").trim());
				dealsList.add(sb.toString());
				Basket.toRem = null == Basket.toRem?new ArrayList<String>():Basket.toRem;
				Basket.added = null == Basket.added?new ArrayList<String>():Basket.added;
				if(Basket.toRem.contains(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ", "").trim().toString())){
					Basket.toRem.remove(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ", "").trim().toString());
					Basket.added.remove(MainActivity.userInfoRecieved.getString(Constants.BUNDLE_KEY_DISH_NAME).replaceAll(" ", "").trim().toString());
				}
				MainActivity.userInfoRecieved.putStringArrayList(Constants.BUNDLE_KEY_DEALS_SELECTED, dealsList);
				isInserted = true;
				Toast.makeText(getApplicationContext(), "deal added to basket", Toast.LENGTH_LONG).show();
			}else{
				Toast.makeText(getApplicationContext(), "already inserted", Toast.LENGTH_LONG).show();
			}
			
			break;

		case R.id.btnIncDealQty:
			tvDealQty.setText(String.valueOf(Integer.parseInt(tvDealQty.getText().toString())+1));
			changeTotal();
			break;

		case R.id.btnDecDealQty:
			tvDealQty.setText( 
					
					String.valueOf
					(
							Integer.parseInt(tvDealQty.getText().toString()) <=1 ? "1" : 
								String.valueOf(
											Integer.parseInt(tvDealQty.getText().toString())-1
										)
					)
		);
			changeTotal();
			
			break;

		case R.id.btnLogoutFromDD:
			Intent in = new Intent(DealsDetail.this, LoginActivity.class);
			MainActivity.userInfoRecieved = null;
			startActivity(in);
			break;
		}

	}
	
	private void changeTotal(){
		int qty = Integer.parseInt(tvDealQty.getText().toString());
		//int prc = Integer.parseInt(tvDealPrice.getText().toString());
		int totalFinalPrice = qty*orignaPrice;
		tvDealPrice.setText(String.valueOf(totalFinalPrice));
	}

}
