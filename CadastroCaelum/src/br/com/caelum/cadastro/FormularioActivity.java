package br.com.caelum.cadastro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends Activity {

	private FormularioHelper helper;
	protected AlunoDAO dao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.formulario);

		helper = new FormularioHelper(this);

		Button botao = (Button) findViewById(R.id.botao);
		botao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Aluno aluno = helper.pegaAlunoDoFormulario();

				dao = new AlunoDAO(FormularioActivity.this);
				dao.insert(aluno);
				finish();
				;
			}
		});

	}

	@Override
	protected void onDestroy() {
		dao.close();
		super.onDestroy();
	}
}
