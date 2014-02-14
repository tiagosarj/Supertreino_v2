package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ExercicioActivity extends Activity {
	
	private EditText nome;
	private EditText repeticao;
	private EditText serie;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exercicio);
		
		nome = (EditText) findViewById(R.id.valor_nome_ex);
		repeticao = (EditText) findViewById(R.id.valor_repeticao);
		serie = (EditText) findViewById(R.id.valor_serie);
		
		Button bt_salvarexercicio = (Button) findViewById(R.id.bt_salvarexercicio);
		bt_salvarexercicio.setOnClickListener (new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent changeActivity = new Intent(ExercicioActivity.this, TreinoActivity.class);
		    	startActivity(changeActivity);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.exercicio, menu);
		return true;
	}

}
