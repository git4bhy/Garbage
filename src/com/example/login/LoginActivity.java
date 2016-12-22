package com.example.login;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.util.DBHelper;

public class LoginActivity extends Activity {

	private EditText username = null;
	private EditText password = null;
	Button loginButton = null;
	Button enrollButton = null;
	private int sign = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		enrollButton = (Button)findViewById(R.id.enroll_button);
		loginButton = (Button)findViewById(R.id.login_button);
		username = (EditText)findViewById(R.id.login_username);
		password = (EditText)findViewById(R.id.login_password);
		enrollButton.setOnClickListener(new enrollButtonListener());
		
		
		
		loginButton.setOnClickListener(new loginButtonListener());
		
		//设置用户名输入框的监听器
		username.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				username.setText("111");
			}
		});
	
		//设置密码输入框的监听器
		password.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					password.setText("");
				}
			});
		}
	
	public class enrollButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(LoginActivity.this, EnrollActivity.class);
			startActivity(intent);
		}
	}
	
	public class loginButtonListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			DBHelper helper = new DBHelper(LoginActivity.this,"user");
			SQLiteDatabase db =  helper.getReadableDatabase();
			Cursor cursor = db.query("userInfo", new String[]{"name","password","sex"}, 
					null, null, null, null, null);
			while(cursor.moveToNext()){
				//获得用户名和密码
				String name1 = cursor.getString(cursor.getColumnIndex("name"));
				String password1 = cursor.getString(cursor.getColumnIndex("password"));
				if(name1.equals(username.getText().toString())&&password1.equals(password.getText().toString())){
					sign=1;
					break;
				}
				if(cursor.isLast()){
					Toast.makeText(LoginActivity.this,"登录失败", Toast.LENGTH_SHORT).show();
				}
			}
			
			if(sign==1){
				Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
				startActivity(intent);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
