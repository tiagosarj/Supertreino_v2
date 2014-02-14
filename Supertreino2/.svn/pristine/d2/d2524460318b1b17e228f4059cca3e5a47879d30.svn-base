package br.ufba.matc89.dao;

import java.util.ArrayList;
import java.util.List;

import br.ufba.matc89.util.ErroUtil;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class GenericDAO extends SQLiteOpenHelper {
	
	//Cada alteração na estrutura das tabelas(definicao de colunas) esse valor deve ser incrementado
	//para que a api do android entenda que o banco foi alterado e remova as definições antigas atualizando
	//com as novas.
	protected static final int DB_VERSION = 1;
	protected static final String DB_NAME = "supertreino";
	
	protected static List<String> SQL_COMMAND_DELETE;
	protected static List<String> SQL_COMMAND_CREATE;
	
	static{
		SQL_COMMAND_DELETE = new ArrayList<String>();
		SQL_COMMAND_CREATE = new ArrayList<String>();
		
		SQL_COMMAND_CREATE.add("create table atleta("
							+"_id integer primary key autoincrement,"
							+"genero text not null"							
							+");");
		
		SQL_COMMAND_CREATE.add("create table medida ("
							+"_id integer primary key autoincrement,"
							+"peso real,"
							+"altura real,"
							+"cintura real,"
							+"quadril real,"
							+"subescapular real,"
							+"tripicial real,"
							+"peitoral real,"
							+"axilar_media real,"
							+"supra_iliaca real,"
							+"abdominal real,"
							+"antebraco real,"
							+"data_afericao text"
							+");");
		
		SQL_COMMAND_CREATE.add("create table alimento( "
							+"_id integer primary key autoincrement,"
							+"nome text not null,"
							+"fonte text,"
							+"carboidrato real,"
							+"proteina real,"
							+"gordura real,"
							+"caloria real"
							+");");
		
		SQL_COMMAND_CREATE.add("create table refeicao("
							+"_id integer primary key autoincrement,"
							+"nome text"
							+");");
		
		SQL_COMMAND_CREATE.add("create table refeicao_alimento("
							+"_id integer primary key autoincrement,"
							+"id_refeicao integer not null,"
							+"id_alimento integer not null,"
							+"quantidade real not null,"
							+"foreign key(id_refeicao) references refeicao(_id),"
							+"foreign key(id_alimento) references alimento(_id)"
							+");");
		
		SQL_COMMAND_CREATE.add("create table treino("
							+"_id integer primary key autoincrement,"
							+"nome text not null"
							+");");
		
		SQL_COMMAND_CREATE.add("create table exercicio("
							+"_id integer primary key autoincrement,"
							+"nome text not null,"
							+"qnt_serie integer,"
							+"qnt_repeticao text"
							+");");
		
		//O add de remocao deve seguir a ordem de dependencia entre as tabelas.
		//A primeira tabela a ser deletada deve ser aquela da qual nenhuma dependa,
		//ou seja, aquela que não seja referenciada por nenhuma, e segue
		//a ordem inversa do add da criacao.
		SQL_COMMAND_DELETE.add("drop table if exists exercicio");
		SQL_COMMAND_DELETE.add("drop table if exists treino");		
		SQL_COMMAND_DELETE.add("drop table if exists refeicao");
		SQL_COMMAND_DELETE.add("drop table if exists alimento");
		SQL_COMMAND_DELETE.add("drop table if exists medida");
		SQL_COMMAND_DELETE.add("drop table if exists atleta");
	}
	
	public GenericDAO(Context context) {
		super(context, DB_NAME, null, DB_VERSION);				
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Generic", "Criacao do banco");
		
		for(int i = 0; i < SQL_COMMAND_CREATE.size(); i++){
			String cmd = SQL_COMMAND_CREATE.get(i);
			
			Log.i("Generic", cmd);
			db.execSQL(cmd);
		}		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String txtLog = "Atualizando da versao "+oldVersion+ " para a versao "+newVersion;
		
		for (int j = 0; j < SQL_COMMAND_DELETE.size(); j++) {
			Log.w("Generic", txtLog);
			Log.i("Generic", SQL_COMMAND_DELETE.get(j));
			db.execSQL(SQL_COMMAND_DELETE.get(j));
		}
		onCreate(db);		
	}
	
	protected void setErro(String msg, String local){
		ErroUtil.erroMensagem = msg;
		ErroUtil.erroLocal = local;
	}
}
