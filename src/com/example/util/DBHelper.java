package com.example.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	public DBHelper(Context context, String name,
			int version) {
		this(context, name, null, version);
	}
	
	public DBHelper(Context context, String name) {
		this(context, name, null, 1);
	}

	/*
	 * ֻ�ᱻִ��һ��
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		//������
		db.execSQL("create table userInfo(id integer primary key, name varchar(20),password varchar(20),sex varchar(4))");
	}

	//���°汾��
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
