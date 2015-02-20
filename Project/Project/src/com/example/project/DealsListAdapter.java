package com.example.project;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class DealsListAdapter extends SimpleAdapter{
	List<? extends Map<String,?>> vals = null;
	String [] from = null;
	int [] to ;
	Context context = null;
	 	TextView tvDealNameV ;
	    TextView tvDealPriceV;
	    TextView tvStartTimeV;
	    TextView tvEndTimeV ;
	    
	    TextView tvDealName ;
	    TextView tvDealPrice;
	    TextView tvStartTime;
	    TextView tvEndTime;
	    
	    
	    TextView tvHidnName ;
	    TextView tvHidnPrice; 
	    TextView tvHidnDesc; 
	    TextView tvHidnId ;
	    TextView tvHidnImg;
	    TextView tvHidnStatus;
	    
	    ImageView iv = null;
	    
	public DealsListAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		
		super(context, data, resource, from, to);
		vals = data;
		this.from = from;
		this.to = to;
		this.context  = context;
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.deals_list_view_layout, parent, false);
		  
		    
		    iv = (ImageView) rowView.findViewById(R.id.tvDealImage);
		    
		    tvDealName = (TextView)rowView.findViewById(R.id.tvDealName);
		     tvDealPrice = (TextView) rowView.findViewById(R.id.tvDealPRice);
		     tvStartTime = (TextView) rowView.findViewById(R.id.tvStartTime);
		     tvEndTime = (TextView) rowView.findViewById(R.id.tvEndTime);
		     
		     tvDealNameV = (TextView)rowView.findViewById(R.id.tvDealNamev);
		     tvDealPriceV = (TextView) rowView.findViewById(R.id.tvDealPriceV);
		     tvStartTimeV = (TextView) rowView.findViewById(R.id.tvStartTimev);
		     tvEndTimeV = (TextView) rowView.findViewById(R.id.tvEndTimeV);
		    
		     tvHidnName = (TextView)rowView.findViewById(R.id.hidnName);
		    tvHidnPrice = (TextView) rowView.findViewById(R.id.hidnPrice);
		     tvHidnDesc = (TextView) rowView.findViewById(R.id.hidnDesc);
		    tvHidnId = (TextView) rowView.findViewById(R.id.hidnID);
		    tvHidnStatus = (TextView) rowView.findViewById(R.id.tvHidnStatus);
		    
		    tvDealNameV.setText(vals.get(position).get("dname").toString());
    		tvDealPriceV.setText(vals.get(position).get("dprice").toString());
    		tvStartTimeV.setText(vals.get(position).get("dstrttime").toString());
    		tvEndTimeV.setText(vals.get(position).get("dendtime").toString());
    		
    		
    		tvHidnName.setText(tvDealNameV.getText().toString());
    		tvHidnDesc.setText(vals.get(position).get("ddesc").toString());
    		tvHidnPrice.setText(tvDealPriceV.getText().toString());
    		tvHidnId.setText(vals.get(position).get("did").toString());
    		tvHidnImg = (TextView)rowView.findViewById(R.id.hidnImgName);
    		tvHidnStatus.setText(vals.get(position).get("available").toString());
    		
    		String imageName =  vals.get(position).get("dimg").toString(); //  this is image file name
    		tvHidnImg.setText(imageName);
    		int imgId = context.getResources().getIdentifier(context.getPackageName()+":drawable/"+imageName , null, null);
    		

    		iv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),imgId));
		   
 
		    rowView.setOnClickListener(new OnClickListener() {
		
				@Override
				public void onClick(View v) {
				

					 tvHidnName = (TextView)v.findViewById(R.id.tvDealNamev);
					    tvHidnPrice = (TextView) v.findViewById(R.id.hidnPrice);
					     tvHidnDesc = (TextView) v.findViewById(R.id.hidnDesc);
					    tvHidnId = (TextView) v.findViewById(R.id.hidnID);
					    tvHidnImg = (TextView)v.findViewById(R.id.hidnImgName);
					    tvHidnStatus = (TextView)v.findViewById(R.id.tvHidnStatus);
					    
					StringBuilder sb =  new StringBuilder(tvHidnId.getText().toString().trim()+" ");
					sb.append(tvHidnDesc.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnName.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnPrice.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnImg.getText().toString().replaceAll(" ","").trim()+" ");
					sb.append(tvHidnStatus.getText().toString().replaceAll(" ","").trim());
					
					MainActivity.userInfoRecieved.putString(Constants.BUNDLE_KEY_DEAL_TO_CHECK, sb.toString());
					Intent i = new Intent(context,DealsDetail.class);
					i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(i);
					
				}
			});
		    // change the icon for Windows and iPhone
		   
		    return rowView;
	}	
	
}
