package com.example.project;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class BasketRowAdapter extends SimpleAdapter{
	static List<? extends Map<String,?>> vals = null;
	String [] from = null;
	int [] to ;
	Context context = null;
	boolean showDel = false;
	public BasketRowAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to,boolean flag) {
		
		super(context, data, resource, from, to);
		vals = data;
		this.from = from;
		this.to = to;
		this.context  = context;
		this.showDel = flag;
		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    final View rowView = inflater.inflate(R.layout.basket_row_layout, parent, false);
		    TextView tvQty = (TextView)rowView.findViewById(R.id.tvQty);
		   
		    TextView tvItemName = (TextView) rowView.findViewById(R.id.tvItemName);
		    TextView tvPrice = (TextView) rowView.findViewById(R.id.tvPrice);
		    
		    tvItemName.setText("name : "+vals.get(position).get("name").toString());
		    tvQty.setText("qty :  "+vals.get(position).get("qty").toString());
		    tvPrice.setText("price : "+vals.get(position).get("price").toString());
		    if(showDel){
		    	Button btndel = (Button) rowView.findViewById(R.id.btnDel);
		    	btndel.setVisibility(View.VISIBLE);
		    	tvQty.setVisibility(View.INVISIBLE);
		    	
		    	btndel.setOnClickListener(new OnClickListener() {
					
		    		
					@Override
					public void onClick(View v) {
						//parent.removeViewAt(position);
						// TODO Auto-generated method stub
						if(!Basket.toRem.contains(Basket.listData.get(position).get("name"))){
							Basket.toRem.add(Basket.listData.get(position).get("name"));
							Basket.total = Basket.total-Double.parseDouble(Basket.listData.get(position).get("price"));
							
							Basket.tvTotalv.setText(String.valueOf(Basket.total));
							Basket.tvSubtotalv.setText(String.valueOf(Basket.total));
						}
						
						Basket.listData.remove(position);
						 notifyDataSetChanged();
						//rowView.refreshDrawableState();
					}
				});
		    }
		   
		    
		    
		    
		    rowView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(context, "ds", Toast.LENGTH_LONG).show();
					
				}
			});
		    // change the icon for Windows and iPhone
		   
		    return rowView;
	}
	
	

}
