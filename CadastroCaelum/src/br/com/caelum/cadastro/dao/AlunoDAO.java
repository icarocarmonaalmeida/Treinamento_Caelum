package br.com.caelum.cadastro.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.com.caelum.cadastro.modelo.Aluno;

public class AlunoDAO extends DAOHelper {
	private static final String TABELA = "CadastroCaelum";
	private static final String[] COLUNAS = { "id", "nome", "telefone",
			"endereco", "site", "nota", "foto" };

	public AlunoDAO(Context context) {
		super(context, TABELA);
	}

	public void insert(Aluno aluno) {
		insert(toValues(aluno));
	}

	public void deletar(String[] args) {
		delete(args);
	}

	public List<Aluno> getlista() {
		List<Aluno> alunos = new ArrayList<Aluno>();

		Cursor cursor = listar(COLUNAS);

		while (cursor.moveToNext()) {
			Aluno aluno = new Aluno();

			aluno.setId(cursor.getLong(0));
			aluno.setNome(cursor.getString(1));
			aluno.setTelefone(cursor.getString(2));
			aluno.setEndereco(cursor.getString(3));
			aluno.setSite(cursor.getString(4));
			aluno.setNota(cursor.getDouble(5));
			aluno.setFoto(cursor.getString(6));

			alunos.add(aluno);
		}
		cursor.close();
		return alunos;
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
