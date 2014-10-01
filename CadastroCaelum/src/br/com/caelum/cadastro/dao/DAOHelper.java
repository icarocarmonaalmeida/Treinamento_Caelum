package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAOHelper extends SQLiteOpenHelper {

	private static final String DATABASE = "FJ57";
	private static final int VERSAO = 1;
	private static final String TABELA_CADATROCAELUM = "CREATE TABLE CadastroCaelum "
			+ " (id INTEGER PRIMARY KEY, "
			+ " nome TEXT UNIQUE NOT NULL, telefone TEXT, endereco TEXT, "
			+ " site TEXT, nota REAL, foto TEXT);";

	private static String TABELA;

	public DAOHelper(Context context, String tabela) {
		super(context, DATABASE, null, VERSAO);
		TABELA = tabela;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(TABELA_CADATROCAELUM);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dll = "DROP TABLE IF EXIST " + TABELA;
		db.execSQL(dll);
		this.onCreate(db);
	}

	protected void insert(ContentValues values) {
		getWritableDatabase().insert(TABELA, null, values);
	}

	protected Cursor listar(String[] colunas) {
		Cursor cursor = getWritableDatabase().query(TABELA, colunas, null,
				null, null, null, null);
		return cursor;
	}

	protected void delete(String[] args) {
		getWritableDatabase().delete(TABELA, "id=?", args);
	}
}
