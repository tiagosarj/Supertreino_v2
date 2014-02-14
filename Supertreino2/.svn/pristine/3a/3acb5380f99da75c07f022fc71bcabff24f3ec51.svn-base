package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.controller.AtletaController;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Dieta;

public class DietaDAO extends GenericDAO {
	static{ 
		TABLE_NAME = "dieta";		
	}
	
	public DietaDAO(Context ctx){
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);		
	}

	public boolean save(Dieta dieta){
		//add breakpoint aqui
		boolean sucess = false;
		long id = dieta.getId();
		
		if(id > 0){
			sucess = update(dieta);
		}else{
			
			sucess = insert(dieta);
		}
		
		return sucess;
	}
	
	private boolean insert(Dieta dieta) {
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		values = getValues(dieta);
		values.put("id_atleta", AtletaController.atleta.getId());
		
		if(db.insert(TABLE_NAME, null, values)>0){
			sucess = true;
		}else{
			setErro("Erro no banco. Os dados de dieta não foram salvos", "insert");
		}		
		return sucess;
	}

	public boolean update(Dieta dieta){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		
		values = getValues(dieta);
		String where = "id=?";
		String[] whereArgs = new String[]{String.valueOf(dieta.getId())};
		
		if(db.update(TABLE_NAME, values, where, whereArgs)>0){
			sucess = true;
		}else{
			setErro("Nenhum registro foi atualizado", "Dieta.update");			
		}
		
		return sucess;
	}
	
	public List<Dieta> getList(Atleta atleta){
		
		List<Dieta> dietas = new ArrayList<Dieta>();
		String where = "id_atleta="+atleta.getId();
		dietas = getList(where);
				
		return dietas;
	}	
	
	public List<Dieta> getList(String where){
		
		List<Dieta> dietas = new ArrayList<Dieta>();
		
		Cursor c = db.query(TABLE_NAME, null, where, null, null, null, null);
				
		if(c.getCount() > 0){
			c.moveToFirst();
			for(int i = 0; i < c.getCount(); i++){
				dietas.add(getDietaByRegistro(c));
				c.moveToNext();
			}
			c.close();
		}	
		return dietas;
	}
	
	private Dieta getDietaByRegistro(Cursor c){
		Dieta dieta = new Dieta();
				
		int indexColId = c.getColumnIndex("id");
		int indexColIdAtleta = c.getColumnIndex("id_atleta");
		int indexColNome = c.getColumnIndex("nome");
		
		dieta.setId(c.getInt(indexColId));
		
		HashMap<String, Long> foreignKey = new HashMap<String, Long>();
		foreignKey.put("id_atleta", c.getLong(indexColIdAtleta));
		
		dieta.setId_other(foreignKey);
		dieta.setNome(c.getString(indexColNome));
			
		return dieta;
	}
	
	private ContentValues getValues(Dieta dieta){
		ContentValues values = new ContentValues();
		
		values.put("nome", dieta.getNome());
		
		return values;
	}
}
