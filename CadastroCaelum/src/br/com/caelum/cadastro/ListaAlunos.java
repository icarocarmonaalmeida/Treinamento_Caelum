package br.com.caelum.cadastro;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import br.com.caelum.cadastro.dao.AlunoDAO;
import br.com.caelum.cadastro.modelo.Aluno;

@SuppressLint("NewApi")
public class ListaAlunos extends ActionBarActivity {

	private ListView listaAlunos;
	private List<Aluno> alunos;
	private Aluno aluno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.lista_clientes);

		this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

		registerForContextMenu(listaAlunos);

		listaAlunos.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view,
					int posicao, long id) {
				Toast.makeText(ListaAlunos.this,
						"Posição selecionada:" + posicao, Toast.LENGTH_LONG)
						.show();
			}
		});

		listaAlunos.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> adapter, View view,
					int posicao, long id) {

				aluno = (Aluno) adapter.getItemAtPosition(posicao);
				return false;
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_novo:
			Intent intent = new Intent(ListaAlunos.this,
					FormularioActivity.class);
			startActivity(intent);

			return false;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menu_example = getMenuInflater();
		menu_example.inflate(R.menu.menu_principal, menu);
		return true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.carregaLista();
	}

	private void carregaLista() {
		AlunoDAO dao = new AlunoDAO(this);
		alunos = dao.getlista();
		dao.close();
		ArrayAdapter<Aluno> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, alunos);
		listaAlunos.setAdapter(adapter);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		menu.add("Ligar");
		menu.add("Enviar SMS");
		menu.add("Achar no Mapa");
		menu.add("Navegar no Site");
		MenuItem deletar = menu.add("deletar");
		deletar.setOnMenuItemClickListener(new OnMenuItemClickListener() {

			@Override
			public boolean onMenuItemClick(MenuItem item) {
				AlunoDAO dao = new AlunoDAO(ListaAlunos.this);
				String[] args = { aluno.getId().toString() };
				dao.deletar(args);
				dao.close();

				carregaLista();

				return false;
			}
		});
		menu.add("Envia e-mail");

	}
}
