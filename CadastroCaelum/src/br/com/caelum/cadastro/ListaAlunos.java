package br.com.caelum.cadastro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ListaAlunos extends ActionBarActivity {

	private ListView listaAlunos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.lista_clientes);

		this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);
		final String[] nomes = { "Anderson", "Filipe", "Guilherme" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, nomes);

		listaAlunos.setAdapter(adapter);
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
				Toast.makeText(ListaAlunos.this,
						"Selecionado " + adapter.getItemAtPosition(posicao),
						Toast.LENGTH_LONG).show();
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

		// getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
}
