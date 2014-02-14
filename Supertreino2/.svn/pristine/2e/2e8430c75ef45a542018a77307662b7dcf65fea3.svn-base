package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import br.ufba.matc89.model.Exercicio;

public class TreinoActivity extends Activity{

	private List<Exercicio> exercicios = new ArrayList<Exercicio>();
    private ArrayAdapter<Exercicio> adaptador = null;
	
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treino);
        
        Button bt_addexercicio = (Button) findViewById(R.id.add_exercicio);
        bt_addexercicio.setOnClickListener (new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent changeActivity = new Intent( TreinoActivity.this, ExercicioActivity.class);
		    	startActivity(changeActivity);
				
			}
		});
        
        
        ListView lista_ex = (ListView) findViewById(R.id.lista_exercicio);
        adaptador = new ArrayAdapter<Exercicio>(this,
                android.R.layout.simple_list_item_1, exercicios);
        lista_ex.setAdapter(adaptador);
        
        lista_ex.setOnItemClickListener(new OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            	Intent changeActivity = new Intent( TreinoActivity.this, ExercicioActivity.class);
            	startActivity(changeActivity);

            }
 
                
        });
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.treino, menu);
		return true;
	}

}
