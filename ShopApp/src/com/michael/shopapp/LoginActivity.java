package com.michael.shopapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private Button btnLogin = null;
	private EditText etMobilePhone = null;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		etMobilePhone = (EditText)findViewById(R.id.login_mobile_phone);
		
		btnLogin = (Button)findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				try{
					LoginRunnable loginRunnable = new LoginRunnable(etMobilePhone.getText().toString());
					Thread thread = new Thread(loginRunnable);
					thread.start();
					thread.join();
					Boolean isValid = loginRunnable.getIsValid();
					if(isValid){
						SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
						Editor editor = sharedPreferences.edit();
						editor.putString("user_name", UserInfo.getInstance().getUserName());
						editor.putString("mobile_phone", UserInfo.getInstance().getMobilePhone());
						editor.putString("address", UserInfo.getInstance().getAddress());
						editor.putString("email", UserInfo.getInstance().getEmail());
						editor.commit();
						Intent intent = new Intent();
						intent.setClass(LoginActivity.this, CatalogActivity.class);
						LoginActivity.this.startActivity(intent);
					}else{
						Toast.makeText(getApplicationContext(), "该手机号还没注册！", Toast.LENGTH_SHORT).show();
					}
				}catch(Exception e){
					Log.e("LoginActivity", e.toString());
				}
			}
			
		});
	}
}
