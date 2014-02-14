package br.ufba.matc89.controller;

import java.util.List;

import android.content.Context;
import br.ufba.matc89.dao.AtletaDAO;
import br.ufba.matc89.dao.MedidaDAO;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Medida;

public class MedidaController implements Controller<Medida>{
	public static Medida medida = new Medida(0f,0f);

	@Override
	public boolean save(Context ctx) {
		boolean sucess = false;
		
		MedidaDAO dMedida = new MedidaDAO(ctx);
		if(dMedida.save(medida, AtletaDAO.atletaLogado.getId())){
			sucess = true;		
		}
		return sucess;
	}

	public List<Medida> get(Atleta atleta, Context ctx) {
		
		MedidaDAO daoMedida = new MedidaDAO(ctx);
		List<Medida> medidas = daoMedida.getList(atleta);
		return medidas;
	}
	
	public static Medida getMedidaAtual(List<Medida> medidas){
		
		Medida medidaAtual = medidas.get(0);		
		
		for(Medida m:medidas){
			if(m.getDataAfericao().after(medidaAtual.getDataAfericao())){
				medidaAtual = m;				
			}	
		}
		return medidaAtual;
	}

	@Override
	public Medida get(long id, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Medida get(Medida entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Medida> getList(Medida entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
