package com.example.project;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class RegisterActivity extends Activity {
	Button backtologin;
	
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
	
		backtologin=(Button)findViewById(R.id.btnLinkToLoginScreen);
		backtologin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub				
				Intent in=new Intent(RegisterActivity.this,LoginActivity.class);
				startActivity(in);
				
				
			}
		});
		
	
	}
	
	// validating email id

		public boolean isValidEmail(String email) {
			Pattern pattern;
			Matcher matcher;
			final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(email);
			return matcher.matches();
		}

}
