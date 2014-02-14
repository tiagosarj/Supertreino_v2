package br.ufba.matc89.dao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.ufba.matc89.model.RefeicaoHasAlimento;
import br.ufba.matc89.provider.SuperTreinoContract;


public class RefeicaoHasAlimentoDAO {
	
	Context context;
	
	public RefeicaoHasAlimentoDAO(Context context){
		this.context = context;
	}
	
	public RefeicaoHasAlimento getById(long id){
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(SuperTreinoContract.Refeicoes.CONTENT_URI,
				SuperTreinoContract.RefeicaoHasAlimentos.PROJECTION_ALL, SuperTreinoContract.RefeicaoHasAlimentos._ID+"="+String.valueOf(id), null, null);

		RefeicaoHasAlimento refeicao_has_alimento = new RefeicaoHasAlimento();
		if (cursor != null){
			cursor.moveToFirst();
			refeicao_has_alimento = cursorToRefeicao(cursor);
		}
		
		cursor.close();
		
		return refeicao_has_alimento;
	}
	
	public Cursor getAll() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Refeicoes.CONTENT_URI,
				SuperTreinoContract.RefeicaoHasAlimentos.PROJECTION_ALL, null, null, SuperTreinoContract.RefeicaoHasAlimentos.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		return cursor; 
	}
	
	public Cursor getAll(long id_refeicao) {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Refeicoes.CONTENT_URI,
				SuperTreinoContract.RefeicaoHasAlimentos.PROJECTION_ALL, SuperTreinoContract.RefeicaoHasAlimentos.ID_REFEICAO + " = " + id_refeicao, null, SuperTreinoContract.Refeicoes.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		return cursor; 
	}
	
	public void save(RefeicaoHasAlimento refeicao_has_alimento){
		long id = refeicao_has_alimento.getId();
		
		if(id > 0){
			update(refeicao_has_alimento);
		}else{
			insert(refeicao_has_alimento);
		}
	}
	
	public void insert(RefeicaoHasAlimento refeicao_has_alimento){
		
		ContentResolver resolver = context.getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.RefeicaoHasAlimentos.ID_ALIMENTO, refeicao_has_alimento.getIdAlimento());
		values.put(SuperTreinoContract.RefeicaoHasAlimentos.ID_REFEICAO, refeicao_has_alimento.getIdRefeicao());
		values.put(SuperTreinoContract.RefeicaoHasAlimentos.QUANTIDADE, refeicao_has_alimento.getQuantidade());
		
		resolver.insert(SuperTreinoContract.RefeicaoHasAlimentos.CONTENT_URI, values);
	}
	
	public long update(RefeicaoHasAlimento refeicao_has_alimento){
		
		ContentResolver resolver = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.RefeicaoHasAlimentos.ID_ALIMENTO, refeicao_has_alimento.getIdAlimento());
		values.put(SuperTreinoContract.RefeicaoHasAlimentos.ID_REFEICAO, refeicao_has_alimento.getIdRefeicao());
		values.put(SuperTreinoContract.RefeicaoHasAlimentos.QUANTIDADE, refeicao_has_alimento.getQuantidade());
		
		long id = refeicao_has_alimento.getId();
		Uri uri = ContentUris.withAppendedId(SuperTreinoContract.RefeicaoHasAlimentos.CONTENT_URI, id);
		long noUpdated = resolver.update(uri, values, null, null);
		
		return noUpdated;
	}
	
	public long deleteAll(){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.RefeicaoHasAlimentos.CONTENT_URI, 
		    		  SuperTreinoContract.RefeicaoHasAlimentos._ID + " = ? ", 
				      new String[]{"Zaphod"});
		
		return noDeleted;
	}
	
	public long delete_byID(long id){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.RefeicaoHasAlimentos.CONTENT_URI, 
		    		  SuperTreinoContract.RefeicaoHasAlimentos._ID + " = ? ", 
				      new String[]{Long.toString(id)});
		
		return noDeleted;
	}
	
	private RefeicaoHasAlimento cursorToRefeicao(Cursor cursor) {
		RefeicaoHasAlimento refeicao_has_alimento = new RefeicaoHasAlimento();
		refeicao_has_alimento.setId(cursor.getInt(cursor.getColumnIndex(SuperTreinoContract.RefeicaoHasAlimentos._ID)));
		refeicao_has_alimento.setIdAlimento(cursor.getInt(cursor.getColumnIndex(SuperTreinoContract.RefeicaoHasAlimentos.ID_REFEICAO)));
		refeicao_has_alimento.setIdAlimento(cursor.getInt(cursor.getColumnIndex(SuperTreinoContract.RefeicaoHasAlimentos.ID_ALIMENTO)));
		refeicao_has_alimento.setQuantidade(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.RefeicaoHasAlimentos.QUANTIDADE)));
		return refeicao_has_alimento;
	}
}
