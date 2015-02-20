package com.example.project;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.util.Log;


public class User extends AsyncTask<HashMap<String,String>, Void, String> {
	
	private String		result 			= "";
	private InputStream input 			= null;
	
	public String getUser(String user_name,String password){
		try{
			HttpClient 		client 		= new DefaultHttpClient();  
			HttpPost 		post 		= new HttpPost("http://10.0.2.2/android/webservices.php?method=login&user_name="+user_name+"&password="+password);
			HttpResponse	response	= client.execute(post);
			HttpEntity 		entity 		= response.getEntity();
			input						= entity.getContent();
			try{
				 
				BufferedReader reader   = new BufferedReader(new InputStreamReader(input,"iso-8859-1"),8);	 
				StringBuilder sb = new StringBuilder();	 
				String line = null;	 
				while ((line = reader.readLine()) != null) { 
					sb.append(line + "\n");	 
				} 
				input.close();	 
				result=sb.toString();
				}	 
				catch(Exception e){ 
					Log.e("log_tag", "Error  converting result "+e.toString());
				}
				 
//				try {
//					JSONObject mainObject = new JSONObject(result.toString());
//					//JSONObject uniObject = mainObject.getJSONObject("university");
//					//String  uniName = uniObject.getJSONObject("name");
//					//String uniURL = uniObject.getJSONObject("url");
//					//JSONObject oneObject = mainObject.getJSONObject("1");
//					String status = mainObject.getString("status");
//					//Log.e("Error Parsing Data ",status.toString());
//					if(status.equalsIgnoreCase("success")){
//						JSONObject profile = mainObject.getJSONObject("profile");
//						String name = profile.getString("name");
//						s = s +"Name=("+ name +")";
//					}
//					s = s+"status=("+status+")";
//				} catch (Exception e) {
//				// TODO: handle exception
//					Log.e("log_tag", "Error Parsing Data "+e.toString());
//				}
//				 
				 
		}catch(Exception e){
			Log.e("Error",e.getMessage());
		}
		return result.toString();
	}
	

	
	

	@Override
	protected String doInBackground(HashMap<String,String>...params) {
		
		return this.getUser(params[0].get("un"), params[0].get("pw"));
	}
}
