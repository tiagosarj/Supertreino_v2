package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.ufba.matc89.controller.Security;
import br.ufba.matc89.model.Usuario;

public class UsuarioDAO extends GenericDAO{
	
	static{ 
		TABLE_NAME = "usuario";		
	}
	
	public UsuarioDAO(Context ctx){
		//add breakpoint aqui
		super(ctx,DB_NAME,DB_VERSION,SQL_COMMAND_CREATE, SQL_COMMAND_DELETE);			
	}
	
	public boolean save(Usuario usuario){
		//add breakpoint aqui
		boolean sucess = false;
		long id = usuario.getId();
		
		if(id > 0){
			sucess = update(usuario);
		}else{
			
			sucess = insert(usuario);
		}
		
		return sucess;
	}
	
	public boolean insert(Usuario usuario){
		boolean sucess = false;
		
		ContentValues values = new ContentValues();
				
		values.put("nome",  usuario.getNome());
		values.put("login", usuario.getLogin());
		values.put("senha", usuario.getSenha());
		values.put("email", usuario.getEmail());
		
		long id = db.insert(TABLE_NAME, null, values);
		
		//caso o usuário seja adicionado na tabela, passará a ser o usuário logado.
		if(id > 0){
			Usuario usuarioLogado = new Usuario();
			
			usuarioLogado = usuario;
			usuarioLogado.setSenha("");
			usuarioLogado.setId(id);
			Security.setUsuarioLogado(usuarioLogado);
			sucess = true;
		}else{
			setErro("Erro no banco. Os dados de usuario não foram salvos", "insert");
		}
		return sucess;
	}	
	
	public boolean update(Usuario usuario){
		boolean sucess = false;
		
		
		return sucess;
	}
	
	public Usuario get(Usuario usuario){
		Usuario userLogado = new Usuario();
		String where = "login='"+usuario.getLogin()+"' and senha='"+usuario.getSenha()+"'";
		Cursor c = db.query(true, TABLE_NAME, null, where, null, null, null, null, null);
		
		if(c.getCount() > 0){
			c.moveToFirst();
			
			userLogado = getUsuarioByRegistro(c);
			
//			int indexColId = c.getColumnIndex("id");
//			int indexColName = c.getColumnIndex("nome");
//			int indexColLogin = c.getColumnIndex("login");
//			int indexColSenha = c.getColumnIndex("senha");
//			int indexColEmail = c.getColumnIndex("email");
//			
//			userLogado.setId(c.getInt(indexColId));
//			userLogado.setNome(c.getString(indexColName));
//			userLogado.setLogin(c.getString(indexColLogin));
//			userLogado.setSenha(c.getString(indexColSenha));
//			userLogado.setEmail(c.getString(indexColEmail));		
		}		
		c.close();
		
		return userLogado;
	}
	
	public boolean existeUsuario(){
		boolean existe = false;
		
		Cursor c = db.query(TABLE_NAME, null, "id>0", null, null, null, null);
		getList();
		if(c.getCount() > 0){
			c.close();
			existe = true;
		}
		return existe;
	}
	
	public List<Usuario> getList() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		Cursor c = db.query(TABLE_NAME, null, "id>0", null, null, null, null);
				
		if(c.getCount() > 0){
			c.moveToFirst();
			for(int i = 0; i < c.getCount(); i++){
				usuarios.add(getUsuarioByRegistro(c));
				c.moveToNext();
			}
			c.close();
		}	
		//teste
		for(Usuario u: usuarios){
			System.out.println(u.getId());
			System.out.println(u.getEmail());
			System.out.println(u.getSenha());
		}
		return usuarios;
	}
	
	private Usuario getUsuarioByRegistro(Cursor c){
		Usuario usuario = new Usuario();
		
		int indexColId = c.getColumnIndex("id");
		int indexColName = c.getColumnIndex("nome");
		int indexColLogin = c.getColumnIndex("login");
		int indexColSenha = c.getColumnIndex("senha");
		int indexColEmail = c.getColumnIndex("email");
		
		usuario.setId(c.getInt(indexColId));
		usuario.setNome(c.getString(indexColName));
		usuario.setLogin(c.getString(indexColLogin));
		usuario.setSenha(c.getString(indexColSenha));
		usuario.setEmail(c.getString(indexColEmail));
		
		return usuario;
	}		
}
