package com.michael.shopapp;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class CatalogActivity extends ListActivity {
	
	private List<String> items = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog);
		items = fillArray();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_catalog_list_item, items);
		this.setListAdapter(adapter);
	}
	
	private List<String> fillArray() {
		List<String> items = null;
		try{
			//Log.d("CatalogActivity", "thread start");
			CatalogRunnable networker = new CatalogRunnable("Catalog");
			Thread thread = new Thread(networker);
			thread.start();
			thread.join();
			items = networker.getCatalogList();
			if(null == items){
				items = new ArrayList<String>();
				items.add("Error, can not get data");
			}
			//Log.d("CatalogActivity", "thread finish");
		}catch(Exception e){
			Log.e("CatalogActivity", e.toString());
		}
		return items;
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		try{	
			Intent intent = new Intent();
			intent.setClass(CatalogActivity.this, GoodsListActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("category_name", items.get(position));
			intent.putExtras(bundle);
			CatalogActivity.this.startActivity(intent);
		}
		catch(Exception e) {
			Log.e("CatlogActivity", e.toString());
		}
	}
}
