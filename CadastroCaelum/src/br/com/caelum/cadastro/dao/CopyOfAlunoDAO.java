package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import br.com.caelum.cadastro.modelo.Aluno;

public class CopyOfAlunoDAO extends DAOHelper {

	private static final String DATABASE = "FJ57";
	private static final int VERSAO = 1;
	private static final String TABELA = "CadastroCaelum";
	private static String queryCreate;
	private static String queryUpdate;
	private DAOHelper daoHelper;

	public CopyOfAlunoDAO(Context context) {
		super(context, DATABASE, null, VERSAO, queryCreate, queryUpdate);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		queryCreate = "CREATE TABLE " + TABELA + " (id INTEGER PRIMARY KEY, "
				+ " nome TEXT UNIQUE NOT NULL, telefone TEXT, endereco TEXT, "
				+ " site TEXT, nota REAL, foto TEXT);";
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		queryUpdate = "DROP TABLE IF EXIST " + TABELA;
		this.onCreate(db);
	}

	public void insert(Aluno aluno) {
		daoHelper.insert(toValues(aluno));
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
