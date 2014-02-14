package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class TreinoMicrocicloActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_treino_microciclo);
		
		Button btTreino = (Button) findViewById(R.id.bt_treinos2);
		btTreino.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {						
					openTreino();					
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.treino_microciclo, menu);
		return true;
	}
	
	
	public void openTreino(){
		Intent intent = new Intent(this, ListaTreinosActivity.class);
		startActivity(intent);
	}
}
