package com.michael.shopapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShoppingCarActivity extends ListActivity {
	
	private TextView tvTotalCost = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_car);
		
		tvTotalCost = (TextView)findViewById(R.id.total_cost);
		tvTotalCost.setText("Total Price: ");
	}
}
