package com.michael.shopapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class GoodsListItem extends LinearLayout {
	public GoodsListItem(Context context) {
		super(context);
	}
	
	public GoodsListItem(Context context, AttributeSet attrs){
		super(context, attrs);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.goods_list_item, this);
	}
}
