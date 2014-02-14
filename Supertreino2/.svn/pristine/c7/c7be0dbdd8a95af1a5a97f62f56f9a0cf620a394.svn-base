package br.ufba.matc89.dao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.ufba.matc89.model.Treino;
import br.ufba.matc89.provider.SuperTreinoContract;


public class TreinoDAO {
	
	Context context;
	
	public TreinoDAO(Context context){
		this.context = context;
	}
	
	public Treino getById(long id){
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(SuperTreinoContract.Treinos.CONTENT_URI,
				SuperTreinoContract.Treinos.PROJECTION_ALL, SuperTreinoContract.Treinos._ID+"="+String.valueOf(id), null, null);

		Treino treino = new Treino();
		if (cursor != null){
			cursor.moveToFirst();
			treino = cursorToTreino(cursor);
		}
		
		cursor.close();
		
		return treino;
	}
	
	public Cursor getAll() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Treinos.CONTENT_URI,
				SuperTreinoContract.Treinos.PROJECTION_ALL, null, null, SuperTreinoContract.Treinos.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		return cursor; 
	}
	
	public void save(Treino treino){
		long id = treino.getId();
		
		if(id > 0){
			update(treino);
		}else{
			insert(treino);
		}
	}
	
	public void insert(Treino treino){
		
		ContentResolver resolver = context.getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Treinos.NOME, treino.getNome());
		
		resolver.insert(SuperTreinoContract.Treinos.CONTENT_URI, values);
	}
	
	public long update(Treino treino){
		
		ContentResolver resolver = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Treinos.NOME, treino.getNome());
		
		long id = treino.getId();
		Uri uri = ContentUris.withAppendedId(SuperTreinoContract.Treinos.CONTENT_URI, id);
		long noUpdated = resolver.update(uri, values, null, null);
		
		return noUpdated;
	}
	
	public long deleteAll(){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Treinos.CONTENT_URI, 
		    		  SuperTreinoContract.Treinos._ID + " = ? ", 
				      new String[]{"Zaphod"});
		
		return noDeleted;
	}
	
	public long delete_byID(long id){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Treinos.CONTENT_URI, 
		    		  SuperTreinoContract.Treinos._ID + " = ? ", 
				      new String[]{Long.toString(id)});
		
		return noDeleted;
	}
	
	private Treino cursorToTreino(Cursor cursor) {
		Treino treino = new Treino();
		treino.setId(cursor.getLong(cursor.getColumnIndex(SuperTreinoContract.Treinos._ID)));
		treino.setNome(cursor.getString(cursor.getColumnIndex(SuperTreinoContract.Treinos.NOME)));
		return treino;
	}
}
