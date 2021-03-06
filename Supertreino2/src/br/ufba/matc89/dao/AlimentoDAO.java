package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.ufba.matc89.model.Alimento;
import br.ufba.matc89.provider.SuperTreinoContract;


public class AlimentoDAO {
	
	Context context;
	
	public AlimentoDAO(Context context){
		this.context = context;
	}
	
	public Alimento getById(long id){
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(SuperTreinoContract.Alimentos.CONTENT_URI,
				SuperTreinoContract.Alimentos.PROJECTION_ALL, SuperTreinoContract.Alimentos._ID+"="+String.valueOf(id), null, null);

		Alimento alimento = new Alimento();
		if (cursor != null){
			cursor.moveToFirst();
			alimento = cursorToAlimento(cursor);
		}
		
		cursor.close();
		
		return alimento;
	}
	
	public Cursor getAll() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Alimentos.CONTENT_URI,
				SuperTreinoContract.Alimentos.PROJECTION_ALL, null, null, SuperTreinoContract.Alimentos.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		return cursor; 
	}
	
	public List<Alimento> getAllList() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Alimentos.CONTENT_URI,
				SuperTreinoContract.Alimentos.PROJECTION_ALL, null, null, SuperTreinoContract.Alimentos.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		List<Alimento> alimentos = new ArrayList<Alimento>();
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Alimento alimento = cursorToAlimento(cursor);
			alimentos.add(alimento);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		
		return alimentos;
	}
	
	public HashMap<Integer,Alimento> getAllMap() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Alimentos.CONTENT_URI,
				SuperTreinoContract.Alimentos.PROJECTION_ALL, null, null, SuperTreinoContract.Alimentos.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		HashMap<Integer,Alimento> alimentos = new HashMap<Integer,Alimento>();
		
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Alimento alimento = cursorToAlimento(cursor);
			alimentos.put(cursor.getInt(cursor.getColumnIndex(SuperTreinoContract.Alimentos._ID)),alimento);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		
		return alimentos; 
	}
	
	public void save(Alimento alimento){
		long id = alimento.getId();
		
		if(id > 0){
			update(alimento);
		}else{
			insert(alimento);
		}
	}
	
	public void insert(Alimento alimento){
		
		ContentResolver resolver = context.getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Alimentos.NOME, alimento.getNome());
		values.put(SuperTreinoContract.Alimentos.FONTE, alimento.getFonte());
		values.put(SuperTreinoContract.Alimentos.CARBOIDRATO, alimento.getCarboidrato());
		values.put(SuperTreinoContract.Alimentos.PROTEINA, alimento.getProteina());
		values.put(SuperTreinoContract.Alimentos.GORDURA, alimento.getGordura());
		values.put(SuperTreinoContract.Alimentos.CALORIA, alimento.getCaloria());
		
		resolver.insert(SuperTreinoContract.Alimentos.CONTENT_URI, values);
	}
	
	public long update(Alimento alimento){
		
		ContentResolver resolver = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Alimentos.NOME, alimento.getNome());
		values.put(SuperTreinoContract.Alimentos.FONTE, alimento.getFonte());
		values.put(SuperTreinoContract.Alimentos.CARBOIDRATO, alimento.getCarboidrato());
		values.put(SuperTreinoContract.Alimentos.PROTEINA, alimento.getProteina());
		values.put(SuperTreinoContract.Alimentos.GORDURA, alimento.getGordura());
		values.put(SuperTreinoContract.Alimentos.CALORIA, alimento.getCaloria());
		
		long id = alimento.getId();
		Uri uri = ContentUris.withAppendedId(SuperTreinoContract.Alimentos.CONTENT_URI, id);
		long noUpdated = resolver.update(uri, values, null, null);
		
		return noUpdated;
	}
	
	public long deleteAll(){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Alimentos.CONTENT_URI, 
		    		  SuperTreinoContract.Alimentos._ID + " = ? ", 
				      new String[]{"Zaphod"});
		
		return noDeleted;
	}
	
	public long delete_byID(long id){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Alimentos.CONTENT_URI, 
		    		  SuperTreinoContract.Alimentos._ID + " = ? ", 
				      new String[]{Long.toString(id)});
		
		return noDeleted;
	}
	
	private Alimento cursorToAlimento(Cursor cursor) {
		Alimento alimento = new Alimento();
		alimento.setId(cursor.getLong(cursor.getColumnIndex(SuperTreinoContract.Alimentos._ID)));
		alimento.setNome(cursor.getString(cursor.getColumnIndex(SuperTreinoContract.Alimentos.NOME)));
		alimento.setFonte(cursor.getString(cursor.getColumnIndex(SuperTreinoContract.Alimentos.FONTE)));
		alimento.setCarboidrato(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Alimentos.CARBOIDRATO)));
		alimento.setProteina(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Alimentos.PROTEINA)));
		alimento.setGordura(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Alimentos.GORDURA)));
		alimento.setCaloria(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Alimentos.CALORIA)));
		return alimento;
	}
}
