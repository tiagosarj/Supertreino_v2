package br.ufba.matc89.controller;

import java.util.List;

import android.content.Context;
import br.ufba.matc89.dao.AtletaDAO;
import br.ufba.matc89.model.Atleta;
import br.ufba.matc89.model.Entity;

public class AtletaController implements Controller<Atleta>{
	public static Atleta atleta = new Atleta("");
	
	public boolean save(Context ctx){
		boolean sucess = false;
		AtletaDAO dAtleta = new AtletaDAO(ctx);
		
		if(dAtleta.save(atleta, Security.getUsuarioLogado().getId())){
			
			MedidaController medidaControl = new MedidaController();
			sucess = medidaControl.save(ctx);
		}
		
		return sucess;
	}

	public Atleta get(Context ctx){
		
		AtletaDAO dAtleta = new AtletaDAO(ctx);		
		Atleta atleta = dAtleta.getByIdUsuario();
		
		return atleta;
	}
	/**
	 * 
	 * @return um atleta com todos os atributos setados. Esse método pode resolver todos os seus
	 * problemas = )
	 */
	public Atleta montar(Context ctx){
		
		Atleta atleta = get(ctx);
		
		MedidaController medidaControl = new MedidaController();
		
		atleta.setHistoricoMedidas(medidaControl.get(atleta, ctx));	
		
		atleta.setMedidaAtual(MedidaController.getMedidaAtual(atleta.getHistoricoMedidas()));
		
		
		return atleta;
	}

	@Override
	public Atleta get(long id, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Atleta get(Atleta entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Atleta> getList(Atleta entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}	
}
