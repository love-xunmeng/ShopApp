package com.michael.shopapp;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

public class Model_Order implements Parcelable {
	private int goodsId = 0;
	private String goods_name = null;
	private float goods_price = 0.00f;
	private int quantity = 0;
	
	public int getGoodsId(){
		return this.goodsId;
	}
	
	public void setGoodsId(int goodsId){
		this.goodsId = goodsId;
	}
	
	public String getGoodsName(){
		return this.goods_name;
	}
	
	public void setGoodsName(String name){
		this.goods_name = name;
	}
	
	public float getGoodsPrice(){
		return this.goods_price;
	}
	
	public void setGoodsPrice(float price){
		this.goods_price = price;
	}
	
	public int getQuantity(){
		return this.quantity;
	}
	
	public void setQuantity(int quantity){
		this.quantity = quantity;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeInt(this.goodsId);
		dest.writeString(this.goods_name);
		dest.writeFloat(this.goods_price);
		dest.writeInt(this.goodsId);
	}
	
	public static final Parcelable.Creator<Model_Order> CREATOR = new Creator<Model_Order>(){

		@Override
		public Model_Order createFromParcel(Parcel source) {
			Model_Order oneOrder = new Model_Order();
			oneOrder.goodsId = source.readInt();
			oneOrder.goods_name = source.readString();
			oneOrder.goods_price = source.readFloat();
			oneOrder.quantity = source.readInt();
			return oneOrder;
		}

		@Override
		public Model_Order[] newArray(int size) {
			return new Model_Order[size];
		}
		
	};
}
