package br.ufba.matc89.dao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.ufba.matc89.model.Exercicio;
import br.ufba.matc89.provider.SuperTreinoContract;


public class ExercicioDAO {
	
	Context context;
	
	public ExercicioDAO(Context context){
		this.context = context;
	}
	
	public Exercicio getById(long id){
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(SuperTreinoContract.Exercicios.CONTENT_URI,
				SuperTreinoContract.Exercicios.PROJECTION_ALL, SuperTreinoContract.Exercicios._ID+"="+String.valueOf(id), null, null);

		Exercicio exercicio = new Exercicio();
		if (cursor != null){
			cursor.moveToFirst();
			exercicio = cursorToExercicio(cursor);
		}
		
		cursor.close();
		
		return exercicio;
	}
	
	public Cursor getAll() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Exercicios.CONTENT_URI,
				SuperTreinoContract.Exercicios.PROJECTION_ALL, null, null, SuperTreinoContract.Exercicios.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		return cursor; 
	}
	
	public void save(Exercicio exercicio){
		long id = exercicio.getId();
		
		if(id > 0){
			update(exercicio);
		}else{
			insert(exercicio);
		}
	}
	
	public void insert(Exercicio exercicio){
		
		ContentResolver resolver = context.getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Exercicios.NOME, exercicio.getNome());
		values.put(SuperTreinoContract.Exercicios.QNT_SERIE, exercicio.getSerie());
		values.put(SuperTreinoContract.Exercicios.QNT_REPETICAO, exercicio.getRepeticao());
		
		resolver.insert(SuperTreinoContract.Exercicios.CONTENT_URI, values);
	}
	
	public long update(Exercicio exercicio){
		
		ContentResolver resolver = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Exercicios.NOME, exercicio.getNome());
		values.put(SuperTreinoContract.Exercicios.QNT_SERIE, exercicio.getSerie());
		values.put(SuperTreinoContract.Exercicios.QNT_REPETICAO, exercicio.getRepeticao());
		
		long id = exercicio.getId();
		Uri uri = ContentUris.withAppendedId(SuperTreinoContract.Exercicios.CONTENT_URI, id);
		long noUpdated = resolver.update(uri, values, null, null);
		
		return noUpdated;
	}
	
	public long deleteAll(){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Exercicios.CONTENT_URI, 
		    		  SuperTreinoContract.Exercicios._ID + " = ? ", 
				      new String[]{"Zaphod"});
		
		return noDeleted;
	}
	
	public long delete_byID(long id){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Exercicios.CONTENT_URI, 
		    		  SuperTreinoContract.Exercicios._ID + " = ? ", 
				      new String[]{Long.toString(id)});
		
		return noDeleted;
	}
	
	private Exercicio cursorToExercicio(Cursor cursor) {
		Exercicio exercicio = new Exercicio();
		exercicio.setId(cursor.getLong(cursor.getColumnIndex(SuperTreinoContract.Exercicios._ID)));
		exercicio.setNome(cursor.getString(cursor.getColumnIndex(SuperTreinoContract.Exercicios.NOME)));
		exercicio.setSerie(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Exercicios.QNT_SERIE)));
		exercicio.setRepeticao(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Exercicios.QNT_REPETICAO)));
		return exercicio;
	}
}
