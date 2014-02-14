package br.ufba.matc89.dao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.ufba.matc89.model.Refeicao;
import br.ufba.matc89.provider.SuperTreinoContract;


public class RefeicaoDAO {
	
	Context context;
	
	public RefeicaoDAO(Context context){
		this.context = context;
	}
	
	public Refeicao getById(long id){
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(SuperTreinoContract.Refeicoes.CONTENT_URI,
				SuperTreinoContract.Refeicoes.PROJECTION_ALL, SuperTreinoContract.Refeicoes._ID+"="+String.valueOf(id), null, null);

		Refeicao refeicao = new Refeicao();
		if (cursor != null){
			cursor.moveToFirst();
			refeicao = cursorToRefeicao(cursor);
		}
		
		cursor.close();
		
		return refeicao;
	}
	
	public Cursor getAll() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Refeicoes.CONTENT_URI,
				SuperTreinoContract.Refeicoes.PROJECTION_ALL, null, null, SuperTreinoContract.Refeicoes.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		return cursor; 
	}
	
	public long save(Refeicao refeicao){
		long id = refeicao.getId();
		
		if(id > 0){
			update(refeicao);
		}else{
			id = insert(refeicao);
		}
		return id;
	}
	
	public long insert(Refeicao refeicao){
		
		ContentResolver resolver = context.getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Refeicoes.NOME, refeicao.getNome());
		
		Uri uri = resolver.insert(SuperTreinoContract.Refeicoes.CONTENT_URI, values);
		
		return Integer.parseInt(uri.getLastPathSegment());
	}
	
	public long update(Refeicao refeicao){
		
		ContentResolver resolver = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Refeicoes.NOME, refeicao.getNome());
		
		long id = refeicao.getId();
		Uri uri = ContentUris.withAppendedId(SuperTreinoContract.Refeicoes.CONTENT_URI, id);
		long noUpdated = resolver.update(uri, values, null, null);
		
		return id;
	}
	
	public long deleteAll(){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Refeicoes.CONTENT_URI, 
		    		  SuperTreinoContract.Refeicoes._ID + " = ? ", 
				      new String[]{"Zaphod"});
		
		return noDeleted;
	}
	
	public long delete_byID(long id){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Refeicoes.CONTENT_URI, 
		    		  SuperTreinoContract.Refeicoes._ID + " = ? ", 
				      new String[]{Long.toString(id)});
		
		return noDeleted;
	}
	
	private Refeicao cursorToRefeicao(Cursor cursor) {
		Refeicao refeicao = new Refeicao();
		refeicao.setId(cursor.getLong(cursor.getColumnIndex(SuperTreinoContract.Refeicoes._ID)));
		refeicao.setNome(cursor.getString(cursor.getColumnIndex(SuperTreinoContract.Refeicoes.NOME)));
		return refeicao;
	}
}
