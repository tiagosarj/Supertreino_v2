package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.model.Alimento;


public class AlimentoDAO extends GenericDAO {
	protected Context context;
	
	static String COLUMN_NAME_ID = "_id";
	static String COLUMN_NAME_NOME = "nome";
	static String COLUMN_NAME_FONTE = "fonte";
	static String COLUMN_NAME_CARBOIDRATO = "carboidrato";
	static String COLUMN_NAME_PROTEINA = "proteina";
	static String COLUMN_NAME_GORDURA = "gordura";
	static String COLUMN_NAME_CALORIA = "caloria";
	
	private String[] allColumns = { AlimentoDAO.COLUMN_NAME_ID,
			AlimentoDAO.COLUMN_NAME_NOME,
			AlimentoDAO.COLUMN_NAME_FONTE,
			AlimentoDAO.COLUMN_NAME_CARBOIDRATO,
			AlimentoDAO.COLUMN_NAME_PROTEINA,
			AlimentoDAO.COLUMN_NAME_GORDURA,
			AlimentoDAO.COLUMN_NAME_CALORIA };
	
	static{
		TABLE_NAME = "alimento";
	}
	
	public AlimentoDAO(Context ctx){
		super(ctx,DB_NAME,DB_VERSION,null,null);
		context = ctx;
		getWritableDatabase();
	}
	
	public AlimentoDAO openToRead() throws android.database.SQLException {
		db = getReadableDatabase();
		return this;	
	}
	
	public AlimentoDAO openToWrite() throws android.database.SQLException {
		db = getWritableDatabase();
		return this;
	}
	
	public void close(){
		db.close();
	}
	
	public boolean save(Alimento alimento){
		boolean success = false;
		long id = alimento.getId();
		
		if(id > 0){
			success = update(alimento);
		}else{
			success = insert(alimento);
		}

		return success;
	}
	
	public boolean insert(Alimento alimento){
		boolean success = false;
		
		ContentValues values = new ContentValues();
		values.put(AlimentoDAO.COLUMN_NAME_NOME, alimento.getNome());
		values.put(AlimentoDAO.COLUMN_NAME_FONTE, alimento.getFonte());
		values.put(AlimentoDAO.COLUMN_NAME_CARBOIDRATO, alimento.getCarboidrato());
		values.put(AlimentoDAO.COLUMN_NAME_PROTEINA, alimento.getProteina());
		values.put(AlimentoDAO.COLUMN_NAME_GORDURA, alimento.getGordura());
		values.put(AlimentoDAO.COLUMN_NAME_CALORIA, alimento.getCaloria());
		long id = db.insert(TABLE_NAME, null, values);
		
		if(id > 0){
			success = true;
		}else{
			setErro("Erro no banco. Os dados de alimento não foram salvos.", "insert");			
		}
		return success;
	}
	
	public boolean update(Alimento alimento){
		boolean success = false;

		ContentValues values = new ContentValues();
		values.put(AlimentoDAO.COLUMN_NAME_NOME, alimento.getNome());
		values.put(AlimentoDAO.COLUMN_NAME_FONTE, alimento.getFonte());
		values.put(AlimentoDAO.COLUMN_NAME_CARBOIDRATO, alimento.getCarboidrato());
		values.put(AlimentoDAO.COLUMN_NAME_PROTEINA, alimento.getProteina());
		values.put(AlimentoDAO.COLUMN_NAME_GORDURA, alimento.getGordura());
		values.put(AlimentoDAO.COLUMN_NAME_CALORIA, alimento.getCaloria());
		int id = db.update(TABLE_NAME, values, "_id="+alimento.getId(), null);

		if(id > 0){
			success = true;
		}else{
			setErro("Erro no banco. Os dados de alimento não foram salvos.", "update");			
		}

		return success;
	}
	
	public int deleteAll(){
		return db.delete(TABLE_NAME, null, null);
	}
	
	public void delete_byID(long id){
		db.delete(TABLE_NAME, COLUMN_NAME_ID+"="+id, null);
	}
	
	public List<Alimento> getAll() {
		List<Alimento> alimentos = new ArrayList<Alimento>();

		Cursor cursor = db.query(TABLE_NAME,
				null, null, null, null, null, null);

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
	
	public Cursor getAllData() {
		
		return db.query(TABLE_NAME,
				null, null, null, null, null, AlimentoDAO.COLUMN_NAME_NOME+" COLLATE NOCASE");
		
	}
	
	public Alimento getById(long id){
		Alimento alimento = new Alimento();
		Cursor cursor = db.query(TABLE_NAME,
				null, AlimentoDAO.COLUMN_NAME_ID+"="+String.valueOf(id), null, null, null, null);

		if (cursor != null){
			cursor.moveToFirst();
			alimento = cursorToAlimento(cursor);
		}
		// Make sure to close the cursor
		cursor.close();
		return alimento;
	}
	
	private Alimento cursorToAlimento(Cursor cursor) {
		Alimento alimento = new Alimento();
		alimento.setId(cursor.getLong(cursor.getColumnIndex(AlimentoDAO.COLUMN_NAME_ID)));
		alimento.setNome(cursor.getString(cursor.getColumnIndex(AlimentoDAO.COLUMN_NAME_NOME)));
		alimento.setFonte(cursor.getString(cursor.getColumnIndex(AlimentoDAO.COLUMN_NAME_FONTE)));
		alimento.setCarboidrato(cursor.getDouble(cursor.getColumnIndex(AlimentoDAO.COLUMN_NAME_CARBOIDRATO)));
		alimento.setProteina(cursor.getDouble(cursor.getColumnIndex(AlimentoDAO.COLUMN_NAME_PROTEINA)));
		alimento.setGordura(cursor.getDouble(cursor.getColumnIndex(AlimentoDAO.COLUMN_NAME_GORDURA)));
		alimento.setCaloria(cursor.getDouble(cursor.getColumnIndex(AlimentoDAO.COLUMN_NAME_CALORIA)));
		return alimento;
	}
}
