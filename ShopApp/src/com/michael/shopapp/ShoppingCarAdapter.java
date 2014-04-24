package com.michael.shopapp;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ShoppingCarAdapter extends BaseAdapter {

	private List<Map<String, Object>> data = null;
	private LayoutInflater layoutInflater = null;
	
	public ShoppingCarAdapter(Context context, List<Map<String, Object>> data){
		this.data = data;
		this.layoutInflater = LayoutInflater.from(context);
	}
	
	public final class ZuJian{
		public TextView goods_name;
		public TextView goods_price;
		public TextView goods_quantity;
	}
	
	@Override
	public int getCount() {		
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ZuJian zuJian = null;
		if(null == convertView){
			zuJian = new ZuJian();
			convertView = layoutInflater.inflate(R.layout.activity_shopping_car_list_item, null);
			zuJian.goods_name = (TextView)convertView.findViewById(R.id.shoping_car_goods_name);
			zuJian.goods_price = (TextView)convertView.findViewById(R.id.shopping_car_goods_price);
			zuJian.goods_quantity = (TextView)convertView.findViewById(R.id.shopping_car_goods_quantity);
			convertView.setTag(zuJian);
		}else{
			zuJian =(ZuJian)convertView.getTag();
		}
		Log.d("ShoppingCarAdapter::data.size()", String.valueOf(data.size()));
		Log.d("ShoppingCarAdapter::position", String.valueOf(position));
		zuJian.goods_name.setText((String)(data.get(position).get("goods_name")));
		Float price =(Float)data.get(position).get("goods_price");
		zuJian.goods_price.setText("\t" + price.toString());
		Integer quantity = (Integer)data.get(position).get("goods_quantity");
		zuJian.goods_quantity.setText("\t" + quantity.toString());
		return convertView;
	}

}
