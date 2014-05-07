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

public class RegisterActivity extends Activity {
	
	private Button btnRegister = null;
	private EditText etUserName = null;
	private EditText etMobilePhone = null;
	private EditText etAddress = null;
	private EditText etEmail = null;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		etUserName = (EditText)findViewById(R.id.register_user_name);
		etMobilePhone = (EditText)findViewById(R.id.register_mobile_phone);
		etAddress = (EditText)findViewById(R.id.register_address);
		etEmail = (EditText)findViewById(R.id.register_email);
		
		btnRegister = (Button)findViewById(R.id.btnRegister);
		btnRegister.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				try{
					UserInfo.getInstance().setUserName(etUserName.getText().toString());			
					UserInfo.getInstance().setMobilePhone(etMobilePhone.getText().toString());
					UserInfo.getInstance().setAddress(etAddress.getText().toString());
					UserInfo.getInstance().setEmail(etEmail.getText().toString());
					RegisterRunnable registerRunnable = new RegisterRunnable();
					Thread thread = new Thread(registerRunnable);
					thread.start();
					thread.join();
					if(true == registerRunnable.getIsRegisterSuccess()){
						
						SharedPreferences sharedPreferences = getSharedPreferences("users", MODE_PRIVATE);
						Editor editor = sharedPreferences.edit();
						editor.putString("user_name", UserInfo.getInstance().getUserName());
						editor.putString("mobile_phone", UserInfo.getInstance().getMobilePhone());
						editor.putString("address", UserInfo.getInstance().getAddress());
						editor.putString("email", UserInfo.getInstance().getEmail());
						editor.commit();
						
						Intent intent = new Intent();
						intent.setClass(RegisterActivity.this, CatalogActivity.class);
						RegisterActivity.this.startActivity(intent);
					}else{
						Toast.makeText(getApplicationContext(), "×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
					}
				}catch(Exception e){
					Log.e("RegisterActivity", e.toString());
				}
			}
			
		});
	}
}
