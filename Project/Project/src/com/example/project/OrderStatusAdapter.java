package com.example.project;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class OrderStatusAdapter extends SimpleAdapter{

	List<? extends Map<String,?>> vals = null;
	String [] from = null;
	int [] to ;
	Context context = null;
	 
	    
	    TextView itemName ;
	    TextView qty ;
	    TextView price;
	    TextView et;
	    TextView status;
	   
	    
	public OrderStatusAdapter(Context context,
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		    View rowView = inflater.inflate(R.layout.order_list_item, parent, false);
		     itemName = (TextView) rowView.findViewById(R.id.tvItNmeV);;
		     qty = (TextView) rowView.findViewById(R.id.tvQtyV);;
		     price = (TextView) rowView.findViewById(R.id.tvPriceV);;
		     et = (TextView) rowView.findViewById(R.id.tvEndTmV);;
		     status = (TextView) rowView.findViewById(R.id.tvStatusV);;
		     TextView tvCncl = (TextView)rowView.findViewById(R.id.textView6);
		     final TextView tvHidnsta = (TextView)rowView.findViewById(R.id.tvHidnDe);
		     tvHidnsta.setText(vals.get(position).get("tvHidnDet").toString());
		     tvCncl.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					OrderStatus.listData.remove(position);
					DataGetter d = new DataGetter();
					d.execute(Constants.CANCEL_ORDER_ITEM,tvHidnsta.getText().toString());
					notifyDataSetChanged();
				}
			});
		     itemName.setText(vals.get(position).get("tvItNmeV").toString());
		     qty.setText(vals.get(position).get("tvQtyV").toString());
		     price.setText(vals.get(position).get("tvPriceV").toString());
		     et.setText(vals.get(position).get("tvEndTmV").toString());
		     status.setText(vals.get(position).get("tvStatusV").toString());
		    return rowView;
	}	
	
	

}
