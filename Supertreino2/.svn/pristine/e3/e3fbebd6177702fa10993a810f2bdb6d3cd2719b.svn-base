package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		Button btAlimentacao = (Button) findViewById(R.id.btnAlimentacao);
		btAlimentacao.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {						
						openAlimentacao();					
					}
				});
		
		Button btAtleta = (Button) findViewById(R.id.btnAtleta);
        btAtleta.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						openAtleta();
					}
				});
        
        Button btNoticias = (Button) findViewById(R.id.btnNoticias);
        btNoticias.setOnClickListener(		
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						openNoticias();
					}
				});
        
        Button btRelatorio = (Button) findViewById(R.id.btnRelatorio);
        btRelatorio.setOnClickListener(		
       				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
				       openRelatorio();
					}
				});
        
        Button btTreino = (Button) findViewById(R.id.btnTreino);
        btTreino.setOnClickListener(		
       			new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						openTreino();
					}
				});
        
        Button btMedida = (Button) findViewById(R.id.btnMedida);
        btMedida.setOnClickListener(		
       			new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						openMedida();
					}
				});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	public void openAlimentacao(){
		Intent intent = new Intent(this, AlimentacaoActivity.class);
		startActivity(intent);
	}
	
	public void openAtleta(){
		Intent intent = new Intent(this, AtletaAddActivity.class);
		startActivity(intent);
	}
	
	public void openNoticias(){
		Intent intent = new Intent(this, NoticiaActivity.class);
		startActivity(intent);
		
	}
	
	public void openRelatorio(){
		Intent intent = new Intent(this, RelatorioActivity.class);
		startActivity(intent);
	}
	
	public void openTreino(){
		Intent intent = new Intent(this,TreinoMicrocicloActivity.class);
		startActivity(intent);
	}
	
	public void openMedida(){
		
		Intent intent = new Intent(this, MedidasActivity.class);
		startActivity(intent);
	}
		
	}

	

	

