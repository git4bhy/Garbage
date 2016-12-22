package com.example.login;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.util.DBHelper;

public class UserListActivity extends Activity {
	private SimpleAdapter adapter = null;
	private List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
	private ListView listView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_list);
		
		
		DBHelper dbHelper = new DBHelper(UserListActivity.this, "user");
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		//游标对象    
		Cursor result = db.rawQuery("select * from userInfo",null);
		int columnCount = result.getColumnCount();
		while(result.moveToNext()){
			Map<String,Object> map = new HashMap<String, Object>();
			for(int i=0;i<columnCount;i++){
				String key = result.getColumnName(i);
				String value = result.getString(result.getColumnIndex(key));
				map.put(key, value);
			}
			list.add(map);
		}
		
		
		
		adapter = new SimpleAdapter(UserListActivity.this, list, R.layout.users, new String[]{"name","password","sex"}, new int[]{R.id.userT,R.id.userP,R.id.userS});
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.user_list, menu);
		return true;
	}

}
