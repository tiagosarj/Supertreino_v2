package br.ufba.matc89.controller;

import java.util.List;

import android.content.Context;
import br.ufba.matc89.dao.UsuarioDAO;
import br.ufba.matc89.model.Usuario;

public class UsuarioController implements Controller<Usuario>{

	public static Usuario usuario = new Usuario();	
	
	
	@Override
	public boolean save(Context ctx){
		boolean sucess = false;
		UsuarioDAO dUsuario = new UsuarioDAO(ctx);
		
		if(dUsuario.save(usuario)){
			AtletaController atletaControl = new AtletaController();
			sucess = atletaControl.save(ctx);
		}
		return sucess;
	}	
	
	public boolean validarUsuario(Context ctx){
		boolean valido = false;
				
		if(jaTemUsuario(ctx) && Security.logar(usuario, ctx)){
			
			AtletaController.atleta = new AtletaController().get(ctx); 
			valido = true;
		}
		
		return valido;
	}
	
	public static boolean jaTemUsuario(Context ctx){
		boolean existe = false;
		UsuarioDAO dUsuario = new UsuarioDAO(ctx);
		
		if(dUsuario.existeUsuario())
		{
			existe = true;
		}	
		
		return existe;
	}

	@Override
	public Usuario get(long id, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario get(Usuario entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getList(Usuario entity, Context context) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
