package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AlimentacaoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alimentacao);
		
		Button btAlimentos = (Button) findViewById(R.id.bt_alimentos);
		btAlimentos.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {						
					openAlimentos();					
				}
			});

		Button btDietas = (Button) findViewById(R.id.bt_dietas);
		btDietas.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {						
					openDietas();					
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.alimentacao, menu);
		return true;
	}

	public void openDietas(){
		Intent intent = new Intent(this, DietasActivity.class);
		startActivity(intent);
	}

	public void openAlimentos(){
		Intent intent = new Intent(this, AlimentoActivity.class);
		startActivity(intent);
	}
}
