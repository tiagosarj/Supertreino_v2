package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Medida;
import br.ufba.matc89.util.DateUtil;

public class MedidaDAO extends GenericDAO{
	static{
		TABLE_NAME = "medida";		
	}
	public MedidaDAO(Context ctx){
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);						
	}
	
	public boolean save(Medida medida, long idAtleta){
		boolean sucess = false;
		long id = medida.getId();
		
		if(id > 0){
			sucess = update(medida);
		}else{
			sucess = insert(medida, idAtleta);
		}
		
		return sucess;
	}
	
	public boolean insert(Medida medida, long idAtleta){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		values = getValues(medida);
		values.put("id_atleta", idAtleta);
			
		if(medida.getDataAfericao() != null){
			values.put("data_afericao", DateUtil.getDataSimples(medida.getDataAfericao()));
		}else{
			values.put("data_afericao", DateUtil.getDataSimples(new Date()));
		}
		
		long id = db.insert(TABLE_NAME, null, values);
		
		if(id > 0){
			
			sucess = true;
		}else{
			setErro("Erro no banco. Os dados de medida não foram salvos", "insert");
		}
		return sucess;
	}
	
	public boolean update(Medida medida){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
		
		values = getValues(medida);
		String where = "id=?";
		String[] whereArgs = new String[]{String.valueOf(medida.getId())};
		
		if(db.update(TABLE_NAME, values, where, whereArgs)>0){
			sucess = true;
		}else{
			setErro("Nenhum registro foi atualizado", "Medida.update");			
		}
		
		return sucess;
	}
	
	public List<Medida> getList(String where){
		
		List<Medida> medidas = new ArrayList<Medida>();
		
		Cursor c = db.query(TABLE_NAME, null, where, null, null, null, null);
				
		if(c.getCount() > 0){
			c.moveToFirst();
			for(int i = 0; i < c.getCount(); i++){
				medidas.add(getMedidaByRegistro(c));
				c.moveToNext();
			}
			c.close();
		}	
		return medidas;
	}
	
	public List<Medida> getList(Atleta atleta){
		
		List<Medida> medidas = new ArrayList<Medida>();
		String where = "id_atleta="+atleta.getId();
		medidas = getList(where);
				
		return medidas;
	}	
	
	private Medida getMedidaByRegistro(Cursor c){
		Medida medida = new Medida(0f,0f);
				
		int indexColId = c.getColumnIndex("id");
		int indexColIdAtleta = c.getColumnIndex("id_atleta");
		int indexColPeso = c.getColumnIndex("peso");
		int indexColAltura = c.getColumnIndex("altura");
		int indexColCintura = c.getColumnIndex("cintura");
		int indexColQuadril = c.getColumnIndex("quadril");
		int indexColSubscapular = c.getColumnIndex("subscapular");
		int indexColTripicial = c.getColumnIndex("tripicial");
		int indexColPeitoral = c.getColumnIndex("peitoral");
		int indexColAxilar_media = c.getColumnIndex("axilar_media");
		int indexColSupra_iliaca = c.getColumnIndex("supra_iliaca");
		int indexColAbdominal = c.getColumnIndex("abdominal");
		int indexColAntebraco = c.getColumnIndex("antebraco");
		int indexColDataAfericao = c.getColumnIndex("data_afericao");
		medida.setId(c.getInt(indexColId));
		
		HashMap<String, Long> foreignKey = new HashMap<String, Long>();
		foreignKey.put("id_atleta", c.getLong(indexColIdAtleta));
		
		medida.setId_other(foreignKey);
		medida.setPeso(c.getFloat(indexColPeso));
		medida.setAltura(c.getFloat(indexColAltura));
		medida.setCintura(c.getFloat(indexColCintura));
		medida.setQuadril(c.getFloat(indexColQuadril));
		medida.setSubscapular(c.getFloat(indexColSubscapular));
		medida.setTripicial(c.getFloat(indexColTripicial));
		medida.setPeitoral(c.getFloat(indexColPeitoral));
		medida.setAxilar_media(c.getFloat(indexColAxilar_media));
		medida.setSupra_iliaca(c.getFloat(indexColSupra_iliaca));
		medida.setAbdominal(c.getFloat(indexColAbdominal));
		medida.setAntebraco(c.getFloat(indexColAntebraco));
		medida.setDataAfericao(DateUtil.getDate(c.getString(indexColDataAfericao)));
		
		return medida;
	}
	
	private ContentValues getValues(Medida medida){
		ContentValues values = new ContentValues();
		values.put("peso", medida.getPeso());
		values.put("altura", medida.getAltura());
		values.put("cintura", medida.getCintura());
		values.put("quadril", medida.getQuadril());
		values.put("subscapular", medida.getSubscapular());
		values.put("tripicial", medida.getTripicial());
		values.put("peitoral", medida.getPeitoral());
		values.put("axilar_media", medida.getAxilar_media());
		values.put("supra_iliaca", medida.getSupra_iliaca());
		values.put("abdominal", medida.getAbdominal());
		values.put("antebraco", medida.getAntebraco());
		
		return values;
	}
}
