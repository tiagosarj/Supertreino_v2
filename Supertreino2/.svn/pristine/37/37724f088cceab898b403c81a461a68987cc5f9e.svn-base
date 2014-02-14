package br.ufba.matc89.controller;

import android.app.Activity;
import android.content.Context;
import br.ufba.matc89.dao.UsuarioDAO;
import br.ufba.matc89.model.Usuario;

public class Security extends Activity{
	private static Usuario usuarioLogado;

	public static boolean logar(Usuario usuario, Context ctx){
		boolean validado = false;
		
		UsuarioDAO dUsuario = new UsuarioDAO(ctx);
		usuarioLogado = dUsuario.get(usuario);
		
		if(usuarioLogado.getId() > 0){
			validado = true;			
		}
		
		return validado;
	}
	
	public static void setUsuarioLogado(Usuario usuario){
		usuarioLogado = usuario;
	}
	
	public static Usuario getUsuarioLogado(){
		return usuarioLogado;
	}
}
