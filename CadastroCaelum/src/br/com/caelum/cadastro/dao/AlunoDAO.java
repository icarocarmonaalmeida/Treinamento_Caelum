package br.com.caelum.cadastro.dao;

import br.com.caelum.cadastro.modelo.Aluno;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlunoDAO extends SQLiteOpenHelper {

	private static final String DATABASE = "FJ57";
	private static final int VERSAO = 1;
	private static final String TABELA = "CadastroCaelum";

	public AlunoDAO(Context context) {
		super(context, DATABASE, null, VERSAO);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String ddl = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, "
				+ " nome TEXT UNIQUE NOT NULL, telefone TEXT, endereco TEXT, "
				+ " site TEXT, nota REAL, foto TEXT);";
		db.execSQL(ddl);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dll = "DROP TABLE IF EXIST " + TABELA;
		db.execSQL(dll);
		this.onCreate(db);
	}

	public void insert(Aluno aluno) {
		getWritableDatabase().insert(TABELA, null, toValues(aluno));
	}

	private ContentValues toValues(Aluno aluno) {
		ContentValues values = new ContentValues();

		values.put("nome", aluno.getNome());
		values.put("site", aluno.getSite());
		values.put("endereco", aluno.getEndereco());
		values.put("telefone", aluno.getTelefone());
		values.put("nota", aluno.getNota());
		values.put("foto", aluno.getFoto());

		return values;

	}

}
