//This Class is Home Page Class
package com.example.project;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView log, waitername;
	Button btmenu, deals, basket, btnOrderStatus;
	final User user = new User();
	public static Bundle userInfoRecieved = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btmenu = (Button) findViewById(R.id.btnmenu);
		log = (TextView) findViewById(R.id.logoutTextview);
		btnOrderStatus = (Button) findViewById(R.id.btnOrdrStatus);
		deals = (Button) findViewById(R.id.btndeals);
		basket = (Button) findViewById(R.id.btnbasket);
		waitername = (TextView) findViewById(R.id.username);
		userInfoRecieved = null == userInfoRecieved ? getIntent().getExtras()
				: userInfoRecieved;

		waitername.setText("Hi "
				+ userInfoRecieved
						.getString(Constants.BUNDLE_KEY_USER_REAL_NAME));
		// user=(TextView)findViewById(R.id.username);

		// Logout button
		log.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(in);
			}
		});

		btmenu.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent ii = new Intent(MainActivity.this, RestaurantMenu.class);
				startActivity(ii);

			}
		});

		btnOrderStatus.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent iii = new Intent(MainActivity.this, OrderStatus.class);
				iii.putExtras(userInfoRecieved);
				startActivity(iii);

			}
		});

		deals.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent iiii = new Intent(MainActivity.this, Deals.class);
				startActivity(iiii);

			}
		});

		basket.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent in = new Intent(MainActivity.this, Basket.class);
				startActivity(in);
			}
		});

	}

}
