package com.michael.shopapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class GoodsListRunnable implements Runnable {
	
	private String category_name = null; 
	private List<Map<String, Object>> goodsItemList = null;
	
	public GoodsListRunnable(String category_name){
		this.category_name = category_name;
	}

	@Override
	public void run() {
		Socket socket = null;
		try{
			Log.d("GoodsListRunnable", "Socket start");
			socket = new Socket("10.0.2.2", 7777);
			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream(), "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			PrintWriter pw = new PrintWriter(bw, true);
			pw.println("Goods");
			pw.println(category_name);
			
			InputStreamReader isr = new InputStreamReader(socket.getInputStream(), "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String dataFromServer = br.readLine();
			if(null == dataFromServer){
				Log.e("GoodsListRunnable", "null == dataFromServer");
			}else{
				//json
				//Log.d("GoodsListRunnable", dataFromServer);
			}
			goodsItemList = new ArrayList<Map<String, Object>>();
			JSONArray jsonArray = new JSONArray(dataFromServer);
			for(int i = 0; i < jsonArray.length(); ++i){
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				Map<String, Object> oneItem = new HashMap<String, Object>();
				oneItem.put("goods_name", jsonObject.get("name"));
				oneItem.put("info", jsonObject.get("description"));
				oneItem.put("price", jsonObject.get("price"));
				String imageString = (String) jsonObject.get("image");
				Bitmap image = convertBase64StringToBitmap(imageString);
				oneItem.put("image", image);				
				oneItem.put("goods_id", jsonObject.get("goods_id"));
				goodsItemList.add(oneItem);
			}
			
			Log.d("GoodsListRunnable", "Socket finish");
			
		}catch(Exception e){
			Log.e("GoodsListRunnable", e.toString());
		}finally{
			if(null != socket){
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public Bitmap convertBase64StringToBitmap(String image){
		Bitmap bitmap = null;
		if(null == image)
			return null;
		try{
			byte[] decodedData = Base64.decodeBase64(image.getBytes());
			for(int i = 0; i < decodedData.length; ++i){
				if(decodedData[i] < 0){
					decodedData[i] += 256;
				}
			}
			//ByteArrayInputStream bin = new ByteArrayInputStream(decodedData);
			bitmap = BitmapFactory.decodeByteArray(decodedData, 0, decodedData.length);
		}
		catch(Exception e){
			//e.printStackTrace();
			Log.e("GoodsListRunnable", e.toString());
		}
		
		return bitmap;
	}

	public List<Map<String, Object>> getGoodsItemList(){
		return this.goodsItemList;
	}
}
