package com.example.login;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.example.util.DBHelper;

public class EnrollActivity extends Activity {

	private Button enrollButton = null;
	private EditText username = null;
	private EditText password = null;
	private RadioGroup group= null;
	private String sex = "男";
	private RadioButton manButton = null;
	private RadioButton woManButton = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enroll);
		enrollButton = (Button)findViewById(R.id.enroll1_button);
		username = (EditText)findViewById(R.id.enroll_username);
		password = (EditText)findViewById(R.id.enroll_password);
		group = (RadioGroup)findViewById(R.id.gb);
		manButton = (RadioButton) findViewById(R.id.man);
		woManButton = (RadioButton) findViewById(R.id.woman);
		enrollButton.setOnClickListener(new enrollButtonListener());
		group.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if(manButton.getId()==checkedId){
					sex = manButton.getText().toString();
				}else{
					sex = woManButton.getText().toString();
				}
			}
		});
	}
	
	
	public class enrollButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			//获得用户输入的用户名
			String name = username.getText().toString();
			//获得用户输入的密码
			String password1 = password.getText().toString();
			//通过DBHelper创建数据库，user为数据库的名
			DBHelper helper = new DBHelper(EnrollActivity.this, "user");
			//获得可写的SQLiteDatabase对象
			SQLiteDatabase db = helper.getWritableDatabase();
			
			ContentValues values = new ContentValues();
			values.put("name",name);
			values.put("password", password1);
			values.put("sex", sex);
			db.insert("userInfo", null, values);
			//页面跳转
			Intent intent = new Intent();
			Toast.makeText(EnrollActivity.this, "注册用户成功", Toast.LENGTH_SHORT).show();
			intent.setClass(EnrollActivity.this,LoginActivity.class);
			startActivity(intent);
			finish();
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enroll, menu);
		return true;
	}

}
