package br.ufba.matc89.dao;

import java.util.HashMap;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.controller.Security;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Genero;
import br.ufba.matc89.util.ErroUtil;


public class AtletaDAO extends GenericDAO {
	public static Atleta atletaLogado = new Atleta("");
	static{
		TABLE_NAME = "atleta";		
	}
	
	public AtletaDAO(Context ctx){
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);				
	}
	
	public boolean save(Atleta atleta, long idUsuario){
		boolean sucess = false;
		long id = atleta.getId();
		
		if(id > 0){
			sucess = (update(atleta, idUsuario)>0);
			
		}else{
			sucess = insert(atleta, idUsuario);
		}
		
		return sucess;
	}
	
	public boolean insert(Atleta atleta, long idUsuario){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		
		values.put("id_usuario", idUsuario);
		
		if(atleta.getGenero() == 1){
			values.put("genero", String.valueOf(Genero.MASCULINO));
		}
		if(atleta.getGenero() == 2){
			values.put("genero", String.valueOf(Genero.FEMININO));
		}
		
		long id = db.insert(TABLE_NAME, null, values);
		
		if(id > 0){
			sucess = true;
			atletaLogado = atleta;
			atletaLogado.setId(id);
		}else{
			setErro("Erro no banco. Os dados de atleta não foram salvos", "insert");			
		}
		return sucess;
	}
	
	public int update(Atleta atleta, long idUsuario){
		
		ContentValues values = new ContentValues();
		values.put("genero", atleta.getGenero());
		
		String where = "id_usuario=?";
		String[] whereArgs = new String[]{String.valueOf(idUsuario)};
		
		int count = db.update(TABLE_NAME, null, where, whereArgs);
		
		if(count <= 0){
			ErroUtil.erroLocal = "Atualizacao de atleta";
			ErroUtil.erroMensagem = "Nenhum registro foi atualizado";
		}
		
		return count;
	}
	
	public Atleta getByIdUsuario(){
		Atleta atleta = new Atleta(" ");
		String where = "id_usuario='"+Security.getUsuarioLogado().getId()+"'";
		Cursor c = db.query(true, TABLE_NAME, null, where, null, null, null, null, null);
		
		if(c.getCount() > 0){
			c.moveToFirst();			
			atleta = getAtletaByRegistro(c);
		}		
		return atleta;
	}
	
	private Atleta getAtletaByRegistro(Cursor c){
		Atleta atleta = new Atleta("");
		
		int indexColId = c.getColumnIndex("id");
		int indexColGenero = c.getColumnIndex("genero");
		int indexColIdUsuario = c.getColumnIndex("id_usuario");
		
		atleta.setId(c.getInt(indexColId));
		
		HashMap<String, Long> foreignKey = new HashMap<String, Long>();
		foreignKey.put("id_usuario", c.getLong(indexColIdUsuario));
		atleta.setId_other(foreignKey);
		
		Locale locale = new Locale("pt", "BR");
		int genero = (c.getString(indexColGenero).trim().toLowerCase(locale).contains("MA"))?1:2;
		atleta.setGenero(genero);
	
		return atleta;
	}
	
	
}
