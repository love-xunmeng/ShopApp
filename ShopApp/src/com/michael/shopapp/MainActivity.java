package com.michael.shopapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnRegister = null;
	private SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
        if(sharedPreferences.contains("mobile_phone")){
        	UserInfo.getInstance().setUserName(sharedPreferences.getString("user_name", "guest"));
        	UserInfo.getInstance().setMobilePhone(sharedPreferences.getString("mobile_phone", "Invalid"));
        	UserInfo.getInstance().setAddress(sharedPreferences.getString("address", "Invalid"));
        	UserInfo.getInstance().setEmail(sharedPreferences.getString("email", "Invalide"));
        	Intent intent = new Intent();
        	intent.setClass(MainActivity.this, CatalogActivity.class);
        	this.startActivity(intent);
        }else{
        	Log.d("MainActivity","SharedPreferences doesnt' exist.");
        }
        
        btnRegister = (Button)findViewById(R.id.btnMainRegister);
        btnRegister.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent();
        		intent.setClass(MainActivity.this, RegisterActivity.class);
        		MainActivity.this.startActivity(intent);
        	}
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
