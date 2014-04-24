package com.michael.shopapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GoodsListActivity extends ListActivity{
	
	private String category_name = null;
	private List<Map<String, Object>> mData = null;
	//private Map<Integer, Integer> order = new HashMap<Integer, Integer>();
	private ArrayList<Model_Order> orderList = new ArrayList<Model_Order>();
	private Button btnOpenShoppingCar = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_list);
        Intent intent = getIntent();
        if(null == intent){
        	Log.e("GoodsListActivity", "null == intent");
        }
        Bundle bundle = getIntent().getExtras();
        if(null == bundle){
        	Log.e("GoodsListActivity", "null == bundle");
        }
        category_name = bundle.getString("category_name");
        if(null == category_name){
        	Log.e("GoodsListActivity", "null == category_name");
        }
        Log.d("GoodsListActivity", category_name);
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
        setListAdapter(adapter);
        
        //
        btnOpenShoppingCar = (Button)findViewById(R.id.btnOpenShoppingCar);
        btnOpenShoppingCar.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				Bundle bundle = new Bundle();
				bundle.putParcelableArrayList("data", orderList);
				Intent intent = new Intent();
				intent.setClass(GoodsListActivity.this, ShoppingCarActivity.class);
				intent.putExtras(bundle);
				GoodsListActivity.this.startActivity(intent);
			}
        	
        });
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = null;
		try {
			GoodsListRunnable runnable = new GoodsListRunnable(category_name);
			Thread thread = new Thread(runnable);
			thread.start();
			thread.join();
			list = runnable.getGoodsItemList();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.e("GoodsListActivity", e.toString());
		}
		return list;
	}
	
	public final class ViewHolder{
		public ImageView img;
		public TextView goods_name;
		public TextView info;
		public Button viewBtn;
	}
	
	public class MyAdapter extends BaseAdapter{
		
		private LayoutInflater mInflater = null;
		
		public MyAdapter(Context context) {
			this.mInflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			return mData.size();
		}
		
		@Override
		public Object getItem(int arg0){
			return null;
		}
		
		@Override
		public long getItemId(int arg0) {
			return 0;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			//Log.d("GoodsListAcitivity", this.toString());
			ViewHolder holder = null;
			final int selectedItem = position;
			if(null == convertView) {
				//Log.d("GoodsListActivity", "null == convertView");
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.goods_list_item, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img);
				holder.goods_name = (TextView)convertView.findViewById(R.id.title);
				holder.info = (TextView)convertView.findViewById(R.id.info);
				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
				if(null == holder.goods_name) {
					Log.d("GoodsActivity", "null == holder.goods_name");
				}
				convertView.setTag(holder);
			}else {
				Log.d("GoodsListActivity", "null != convertView");
				holder = (ViewHolder)convertView.getTag();
			}
			if(null == holder) {
				Log.e("GoodsActivity", "holder is null");
			}
			if(null == mData) {
				Log.e("GoodsActivity", "mData is null");
			}
			//Log.i("GoodsListActivity", "ok here");
			Log.d("GoodsActivity", String.valueOf(position));
			Object obj = mData.get(position);
			if(null == obj){
				Log.d("GoodsActivity", "obj is null");
			}
			holder.goods_name.setText((String)mData.get(position).get("goods_name"));
			Log.v("GoodsActivity", (String)mData.get(position).get("goods_name"));
			holder.info.setText((String)mData.get(position).get("info"));
			//holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.img.setImageBitmap((Bitmap)(mData.get(position).get("image")));
			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					final Integer goods_id = (Integer) mData.get(selectedItem).get("goods_id");
					final String goods_name = (String)mData.get(selectedItem).get("goods_name");
					//final Float goods_price = (Float)(mData.get(selectedItem).get("price"));
					final int nGoodsId = goods_id.intValue();
					//final float fGoodsPrice = goods_price.floatValue();
					//Boolean isInOrder = false;
					int i = 0;
					for(i = 0; i < orderList.size(); ++i){
						if(nGoodsId == orderList.get(i).getGoodsId()){
							int quantity = orderList.get(i).getQuantity();
							quantity += 1;
							orderList.get(i).setQuantity(quantity);
							break;
						}
					}
					if(orderList.size() == i){
						Model_Order oneOrder = new Model_Order();
						oneOrder.setGoodsId(nGoodsId);
						oneOrder.setGoodsName(goods_name);
						oneOrder.setGoodsPrice(1.12f);
						oneOrder.setQuantity(1);
						orderList.add(oneOrder);
					}
					Log.d("GoodsListActivity", goods_id + ": " + orderList.get(i).getQuantity());
				}
			});
			return convertView;
		}
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Log.v("MyListView4-click", (String)mData.get(position).get("goods_name"));
	}
}
