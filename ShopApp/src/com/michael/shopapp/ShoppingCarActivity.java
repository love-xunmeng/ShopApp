package com.michael.shopapp;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ShoppingCarActivity extends ListActivity {
	
	private final String CLASS_NAME = "ShoppingCarActivity";
	private TextView tvTotalCost = null;
	private ArrayList<Model_Order> orderList = null;
	private Button btnBuy = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_car);
		
		Bundle bundle = getIntent().getExtras();
		orderList = bundle.getParcelableArrayList("data");
		if(null == orderList){
			Log.e(CLASS_NAME, "null == orderList");
		}
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < orderList.size(); ++i){
			Map<String, Object> oneItem = new HashMap<String, Object>();
			oneItem.put("goods_id", orderList.get(i).getGoodsId());
			oneItem.put("goods_name", orderList.get(i).getGoodsName());
			oneItem.put("goods_price", orderList.get(i).getGoodsPrice());
			oneItem.put("goods_quantity", orderList.get(i).getQuantity());
			dataList.add(oneItem);
		}
		
		ShoppingCarAdapter adapter = new ShoppingCarAdapter(this, dataList);
		setListAdapter(adapter);
		
		btnBuy = (Button)findViewById(R.id.btnBuy);
		btnBuy.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				sentOrderToServer();
			}
			
		});
		
		tvTotalCost = (TextView)findViewById(R.id.total_cost);
		tvTotalCost.setText("Total Price: ");
	}
	
	private void sentOrderToServer(){
		try{
			JSONArray jsonArray = new JSONArray();
			for(int i = 0; i < orderList.size(); ++i){
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("goods_id", orderList.get(i).getGoodsId());
				jsonObject.put("quantity", orderList.get(i).getQuantity());
				jsonArray.put(jsonObject);
			}
			Thread thread = new Thread(new ShoppingCarRunnable(jsonArray));
			thread.start();
			thread.join();
		}catch(Exception e){
			Log.e(CLASS_NAME, e.toString());
		}
		
	}
}
