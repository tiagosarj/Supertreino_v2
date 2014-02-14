package br.ufba.matc89.controller;

import java.util.List;

import android.content.Context;
import br.ufba.matc89.dao.DietaDAO;
import br.ufba.matc89.model.Dieta;
import br.ufba.matc89.util.ErroUtil;

public class DietaController implements Controller<Dieta>{
	public static Dieta dieta = new Dieta(); 
	
	
	public DietaController(){
		dieta.setNome("Café da Manhã");		
	}
	@Override
	public boolean save(Context ctx) {
		
		return false;
	}

	public List<Dieta> getList(Context ctx){
		DietaDAO dDieta = new DietaDAO(ctx);
		
		List<Dieta> dietas = dDieta.getList(AtletaController.atleta);
		
		if(dietas.size() == 0){
			dDieta.save(dieta);
			dietas = dDieta.getList(AtletaController.atleta);
		}
		
		if(dietas.size() == 0){
			ErroUtil.erroMensagem = "Não existe nenhuma dieta cadastrada.";
			ErroUtil.erroLocal = "DietaController.getList";
		}
		return dietas;
	}
	@Override
	public Dieta get(long id, Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Dieta get(Dieta entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Dieta> getList(Dieta entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
