package com.example.project;

import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	Button log,registerbtn;
	EditText loginuser,loginPassword;
	TextView error; 
	Bundle userInfo = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_screen);
		
		registerbtn=(Button)findViewById(R.id.btnLinkToRegisterScreen);
		log=(Button)findViewById(R.id.btnLogin);
		loginuser=(EditText)findViewById(R.id.loginuser);
		loginPassword=(EditText)findViewById(R.id.loginPassword);
		error=(TextView)findViewById(R.id.LoginErrorTextView);
		//Action on a button on login 
		
		
		
		log.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try{
					
					User user = new User();
					HashMap<String,String> m = new HashMap<String, String>();
					m.put("un",loginuser.getText().toString() );
					m.put("pw", loginPassword.getText().toString());
					
					
					String json = user.execute(m).get();
				
					try {
						JSONObject mainObject = new JSONObject(json.toString());
						String success = mainObject.getString("SUCCESS");
						if(success.equalsIgnoreCase("true")){
							JSONObject profile = mainObject.getJSONObject("PROFILE");
							userInfo = new Bundle();
							userInfo.putString(Constants.BUNDLE_KEY_USER_ID, profile.getString("ID"));
							userInfo.putString(Constants.BUNDLE_KEY_USER_REAL_NAME, profile.getString("NAME"));
							
							Intent mainact=new Intent(LoginActivity.this,MainActivity.class);
							mainact.putExtras(userInfo);
							startActivity(mainact);
							
						}else{
							error.setText("Invalid user name or password !!!");
						}
				
					} catch (Exception e) {
						Log.e("log_tag", "Error Parsing Data "+e.toString());
					}
					
				
					
				}catch(Exception e){
					Log.e("bad error",e.getMessage());
					
				}
				// TODO Auto-generated method stub	
	
				
				
			}
		});
		
		registerbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub				
				Intent in=new Intent(LoginActivity.this,RegisterActivity.class);
				startActivity(in);
				
				
			}
		});
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
