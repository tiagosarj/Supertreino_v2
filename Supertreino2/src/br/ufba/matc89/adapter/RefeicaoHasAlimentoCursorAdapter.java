package br.ufba.matc89.adapter;

import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;
import android.widget.TextView;
import br.ufba.matc89.dao.AlimentoDAO;
import br.ufba.matc89.model.Alimento;

import br.ufba.matc89.R;

public class RefeicaoHasAlimentoCursorAdapter extends CursorAdapter{
	
	AlimentoDAO alimentoDAO;

    public RefeicaoHasAlimentoCursorAdapter(Context context, Cursor c){
        super(context, c, 0);
        alimentoDAO = new AlimentoDAO(context);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
    	// when the view will be created for first time,
        // we need to tell the adapters, how each item will look
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View retView = inflater.inflate(R.layout.layout_refeicao_has_alimento, parent, false);
        
        return retView;
    }
    
    @Override
    public void bindView(View view, Context context, Cursor cursor){
    	// here we are setting our data
        // that means, take the data from the cursor and put it in views
    	
    	HashMap<Integer,Alimento> alimentos = alimentoDAO.getAllMap();
    	
    	TextView nome = (TextView) view.findViewById(R.id.alimento_nome);
    	//nome.setText(cursor.getString(cursor.getColumnIndex("refeicao.nome")));
    	nome.setText(alimentos.get(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_alimento")))).getNome());
    	
    	TextView fonte = (TextView) view.findViewById(R.id.alimento_fonte);
    	//fonte.setText(cursor.getString(cursor.getColumnIndex("refeicao.fonte")));
    	nome.setText(alimentos.get(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id_alimento")))).getFonte());
    }
}
