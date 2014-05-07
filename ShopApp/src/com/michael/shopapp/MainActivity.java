package com.michael.shopapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	
	private Button btnRegister = null;
	private Button btnLogin = null;
	private SharedPreferences sharedPreferences = null;
	private Button btnSharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        
        
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
        
        btnLogin = (Button)findViewById(R.id.btnMainLogin);
        btnLogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();	
				intent.setClass(MainActivity.this, LoginActivity.class);
				MainActivity.this.startActivity(intent);
			}
        	
        });
        
        //for test
        btnSharedPreferences = (Button)findViewById(R.id.btnClearSharedPreferences);
        btnSharedPreferences.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(null != sharedPreferences){				
					Editor editor = sharedPreferences.edit();
					editor.clear().commit();
				}
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
