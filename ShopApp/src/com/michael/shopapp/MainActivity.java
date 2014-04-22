package com.michael.shopapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private EditText txtShoppingList = null;
	private Button btnSubmit = null;
	private Button btnNewActivity = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        txtShoppingList = (EditText)findViewById(R.id.txtShoppingList);
        btnSubmit = (Button)findViewById(R.id.btnSubmit);
        btnNewActivity = (Button)findViewById(R.id.btnNewActivity);
        
        btnSubmit.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		String message = txtShoppingList.getText().toString() + "\r\n";
        		NetworkThread networkThread = new NetworkThread("Catalog");
        		Thread thread = new Thread(networkThread);
        		thread.start();
        	}
        });
        
        btnNewActivity.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		Intent intent = new Intent();
        		//intent.setClass(MainActivity.this, GoodsListActivity.class);
        		//MainActivity.this.startActivity(intent);
        		intent.setClass(MainActivity.this, CatalogActivity.class);
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
