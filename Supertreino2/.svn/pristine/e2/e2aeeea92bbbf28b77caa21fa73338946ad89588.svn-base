package br.ufba.matc89;

import java.util.ArrayList;
import java.util.List;

import br.ufba.matc89.model.Treino;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ListaTreinosActivity extends Activity {
	
	private List<Treino> treinos = new ArrayList<Treino>();
    private ArrayAdapter<Treino> adaptador = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_treinos);
		
		Button bt_addtreino = (Button) findViewById(R.id.bt_addtreino);
		bt_addtreino.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent changeActivity = new Intent(ListaTreinosActivity.this, TreinoActivity.class);
				startActivity(changeActivity);
			}
		});
		
		ListView lista_treino = (ListView) findViewById(R.id.lista_treinos);
        adaptador = new ArrayAdapter<Treino>(this,
                android.R.layout.simple_list_item_1, treinos);
        lista_treino.setAdapter(adaptador);
        
        lista_treino.setOnItemClickListener(new OnItemClickListener() {
            
            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {

            	Intent changeActivity = new Intent( ListaTreinosActivity.this, TreinoActivity.class);
            	startActivity(changeActivity);

            }
 
                
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lista_treinos, menu);
		return true;
	}
	
	
}
