package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DAOHelper extends SQLiteOpenHelper {
	private String name;
	private String queryCreate;
	private String queryUpdate;

	public DAOHelper(Context context, String name, CursorFactory factory,
			int version, String queryCreate, String queryUpdate) {
		super(context, name, factory, version);
		this.name = name;
		this.queryCreate = queryCreate;
		this.queryUpdate = queryUpdate;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(queryCreate);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(queryUpdate);
		this.onCreate(db);
	}

	public void insert(ContentValues values) {
		getWritableDatabase().insert(name, null, values);
	}

	public Cursor listar(String[] colunas) {
		Cursor cursor = getWritableDatabase().query(name, colunas, null, null,
				null, null, null);
		return cursor;
	}

}
