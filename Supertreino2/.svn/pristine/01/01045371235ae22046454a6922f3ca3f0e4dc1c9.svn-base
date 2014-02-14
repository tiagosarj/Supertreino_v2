package br.ufba.matc89.dao;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import br.ufba.matc89.model.Medida;
import br.ufba.matc89.provider.SuperTreinoContract;


public class MedidaDAO {
	
	Context context;
	
	public MedidaDAO(Context context){
		this.context = context;
	}
	
	public Medida getById(long id){
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(SuperTreinoContract.Medidas.CONTENT_URI,
				SuperTreinoContract.Medidas.PROJECTION_ALL, SuperTreinoContract.Medidas._ID+"="+String.valueOf(id), null, null);

		Medida medida = new Medida();
		if (cursor != null){
			cursor.moveToFirst();
			medida = cursorToMedida(cursor);
		}
		
		cursor.close();
		
		return medida;
	}
	
	public Cursor getAll() {
		ContentResolver resolver = context.getContentResolver();
		
		Cursor cursor = resolver.query(SuperTreinoContract.Medidas.CONTENT_URI,
				SuperTreinoContract.Medidas.PROJECTION_ALL, null, null, SuperTreinoContract.Medidas.SORT_ORDER_DEFAULT+" COLLATE NOCASE");
		
		return cursor; 
	}
	
	public void save(Medida medida){
		long id = medida.getId();
		
		if(id > 0){
			update(medida);
		}else{
			insert(medida);
		}
	}
	
	public void insert(Medida medida){
		
		ContentResolver resolver = context.getContentResolver();
		
		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Medidas.PESO, medida.getPeso());
		values.put(SuperTreinoContract.Medidas.ALTURA, medida.getAltura());
		values.put(SuperTreinoContract.Medidas.CINTURA, medida.getCintura());
		values.put(SuperTreinoContract.Medidas.QUADRIL, medida.getQuadril());
		values.put(SuperTreinoContract.Medidas.SUBESCAPULAR, medida.getSubescapular());
		values.put(SuperTreinoContract.Medidas.TRIPICIAL, medida.getTripicial());
		values.put(SuperTreinoContract.Medidas.PEITORAL, medida.getPeitoral());
		values.put(SuperTreinoContract.Medidas.AXILAR_MEDIA, medida.getAxilarMedia());
		values.put(SuperTreinoContract.Medidas.SUPRA_ILIACA, medida.getSupraIliaca());
		values.put(SuperTreinoContract.Medidas.ABDOMINAL, medida.getAbdominal());
		values.put(SuperTreinoContract.Medidas.ANTEBRACO, medida.getAntebraco());
		values.put(SuperTreinoContract.Medidas.DATA_AFERICAO, medida.getDataAfericao());
		
		resolver.insert(SuperTreinoContract.Medidas.CONTENT_URI, values);
	}
	
	public long update(Medida medida){
		
		ContentResolver resolver = context.getContentResolver();

		ContentValues values = new ContentValues();
		values.put(SuperTreinoContract.Medidas.PESO, medida.getPeso());
		values.put(SuperTreinoContract.Medidas.ALTURA, medida.getAltura());
		values.put(SuperTreinoContract.Medidas.CINTURA, medida.getCintura());
		values.put(SuperTreinoContract.Medidas.QUADRIL, medida.getQuadril());
		values.put(SuperTreinoContract.Medidas.SUBESCAPULAR, medida.getSubescapular());
		values.put(SuperTreinoContract.Medidas.TRIPICIAL, medida.getTripicial());
		values.put(SuperTreinoContract.Medidas.PEITORAL, medida.getPeitoral());
		values.put(SuperTreinoContract.Medidas.AXILAR_MEDIA, medida.getAxilarMedia());
		values.put(SuperTreinoContract.Medidas.SUPRA_ILIACA, medida.getSupraIliaca());
		values.put(SuperTreinoContract.Medidas.ABDOMINAL, medida.getAbdominal());
		values.put(SuperTreinoContract.Medidas.ANTEBRACO, medida.getAntebraco());
		values.put(SuperTreinoContract.Medidas.DATA_AFERICAO, medida.getDataAfericao());
		
		long id = medida.getId();
		Uri uri = ContentUris.withAppendedId(SuperTreinoContract.Medidas.CONTENT_URI, id);
		long noUpdated = resolver.update(uri, values, null, null);
		
		return noUpdated;
	}
	
	public long deleteAll(){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Medidas.CONTENT_URI, 
		    		  SuperTreinoContract.Medidas._ID + " = ? ", 
				      new String[]{"Zaphod"});
		
		return noDeleted;
	}
	
	public long delete_byID(long id){
		
		ContentResolver resolver = context.getContentResolver();
		
		long noDeleted = resolver.delete
				      (SuperTreinoContract.Medidas.CONTENT_URI, 
		    		  SuperTreinoContract.Medidas._ID + " = ? ", 
				      new String[]{Long.toString(id)});
		
		return noDeleted;
	}
	
	private Medida cursorToMedida(Cursor cursor) {
		Medida medida = new Medida();
		medida.setId(cursor.getLong(cursor.getColumnIndex(SuperTreinoContract.Medidas._ID)));
		medida.setPeso(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.PESO)));
		medida.setAltura(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.ALTURA)));
		medida.setCintura(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.CINTURA)));
		medida.setQuadril(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.QUADRIL)));
		medida.setSubescapular(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.SUBESCAPULAR)));
		medida.setTripicial(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.TRIPICIAL)));
		medida.setPeitoral(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.PEITORAL)));
		medida.setAxilarMedia(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.AXILAR_MEDIA)));
		medida.setSupraIliaca(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.SUPRA_ILIACA)));
		medida.setAbdominal(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.ABDOMINAL)));
		medida.setAntebraco(cursor.getDouble(cursor.getColumnIndex(SuperTreinoContract.Medidas.ANTEBRACO)));
		medida.setDataAfericao(cursor.getString(cursor.getColumnIndex(SuperTreinoContract.Medidas.DATA_AFERICAO)));
		return medida;
	}
}
