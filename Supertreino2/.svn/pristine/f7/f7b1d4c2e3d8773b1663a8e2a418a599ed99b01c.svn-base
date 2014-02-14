package br.ufba.matc89;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import br.ufba.matc89.adapter.DietaListAdapter;
import br.ufba.matc89.controller.DietaController;
import br.ufba.matc89.dao.DietaDAO;
import br.ufba.matc89.model.Dieta;

public class DietasActivity extends ListActivity {
	
	//ListView lw;
	//String[] dietas = {"Hiper-Proteíca","Calorica", "Pós-Treino"};
	protected static final int INSERIR_EDITAR = 1;
	protected static final int BUSCAR = 2;
	public static DietaDAO dDieta;
	private List<Dieta> dietas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_dietas);
		dDieta = new DietaDAO(this);
		
		atualizarLista();
		
		
		
		/*ArrayAdapter<String> str = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
				android.R.id.text1,dietas);
		
		lw = (ListView)findViewById(R.id.listView1);
		lw.setAdapter(str);	
		
		lw.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				
				switch(position){
				
				case 0:
					break;
				case 1:
					break;
				case 3:
					break;					
				}
				
			}
		});
*/	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		super.onCreateOptionsMenu(menu);
		menu.add(0, INSERIR_EDITAR, 0, "Nova dieta").setIcon(R.drawable.add);
		menu.add(0, BUSCAR, 0, "Consultar").setIcon(R.drawable.consultar);
		//getMenuInflater().inflate(R.menu.dietas, menu);
		return true;
	}
	
	protected void atualizarLista(){
				
		dietas = new DietaController().getList(this);		
		
		setListAdapter(new DietaListAdapter(this, dietas));
	}
	
	public boolean onMenuItemSelected(int featureId, MenuItem item){
		switch(item.getItemId()){
			case INSERIR_EDITAR:
				startActivityForResult(new Intent(this, DietaAddActivity.class), INSERIR_EDITAR);
				break;
			case BUSCAR:
				//TODO - Alterar o itent para activity da tela de consulta de dietas. 
				startActivityForResult(new Intent(this, DietasActivity.class), BUSCAR);
				break;
		}
		
		return true;
	}
	@Override
	protected void onListItemClick(ListView l, View v, int index, long id){
		super.onListItemClick(l, v, index, id);
		editarDieta(index);
	}
	
	protected void editarDieta(int index){
		
	}
	
	protected void onActivityResult(int cod, int codReturn, Intent intent){
		super.onActivityResult(cod, codReturn, intent);
		
		if(codReturn == RESULT_OK){
			atualizarLista();
		}
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		dDieta.close();
	}
}
