package br.ufba.matc89.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.ufba.matc89.R;
import br.ufba.matc89.model.Dieta;

public class DietaListAdapter extends BaseAdapter {

	private  Context context;
	private List<Dieta> dietas;
	public DietaListAdapter(Context context, List<Dieta> dietas){
		
		this.context = context;
		this.dietas = dietas;		
	}
	
	@Override
	public int getCount() {
		return dietas.size();
	}

	@Override
	public Object getItem(int index) {
		
		return dietas.get(index);
	}

	@Override
	public long getItemId(int index) {
	
		return dietas.get(index).getId();
	}
	
	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		Dieta dieta = dietas.get(index);
		
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.activity_dietas, null);
		
		TextView nomeDieta = (TextView) view.findViewById(R.id.dietaNome);
		
		nomeDieta.setText(dieta.getNome());
		
		return view;
	}

}
