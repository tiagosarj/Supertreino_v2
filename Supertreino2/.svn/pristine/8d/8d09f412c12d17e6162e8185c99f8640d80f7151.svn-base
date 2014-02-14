package br.ufba.matc89;

import java.util.ArrayList;
import java.util.List;

import br.ufba.matc89.model.Alimento;
import br.ufba.matc89.model.RefeicaoHasAlimento;
import br.ufba.matc89.provider.SuperTreinoContract;
import br.ufba.matc89.util.NumberUtil;
import br.ufba.matc89.adapter.AlimentoCursorAdapter;
import br.ufba.matc89.adapter.AlimentosCursorAdapter;
import br.ufba.matc89.dao.RefeicaoHasAlimentoDAO;
import br.ufba.matc89.dao.AlimentoDAO;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;

public class RefeicaoHasAlimentoAddEditFragment extends Fragment implements LoaderCallbacks<Cursor> {

	private static List<Alimento> alimentos = new ArrayList<Alimento>();
	
	protected EditText rha_quantidade;
	protected EditText rha_id_alimento;
	protected Long rha_id_refeicao;
	protected Spinner viewAlimentos;
	protected Button salvarButton;
	protected RefeicaoHasAlimento rha = new RefeicaoHasAlimento();
	
	RefeicaoHasAlimentoDAO dbRefeicaoHasAlimento;
	AlimentoDAO dbAlimento;
	AlimentosCursorAdapter alimentoAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_refeicao_has_alimento_add_edit, container, false);
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		dbRefeicaoHasAlimento = new RefeicaoHasAlimentoDAO(getActivity());
		dbAlimento = new AlimentoDAO(getActivity());
		
		rha_quantidade = (EditText)getView().findViewById(R.id.rha_quantidade);
		viewAlimentos = (Spinner) getView().findViewById(R.id.cbAlimentos);
		
		alimentos = dbAlimento.getAllList();
		//ArrayAdapter<Alimento> adp = new ArrayAdapter<Alimento>(getActivity(),android.R.layout.simple_dropdown_item_1line, alimentos);
		alimentoAdapter = new AlimentosCursorAdapter(getActivity(), null);
		viewAlimentos.setAdapter(alimentoAdapter); 
		//viewAlimentos.setAdapter(adp);
		
        Bundle bundle=getArguments();
        if(bundle != null){
	        rha_id_refeicao = bundle.getLong("id_refeicao",-1);
	        if(rha_id_refeicao == -1){
	        	getActivity().getSupportFragmentManager().popBackStack();
	        }
	        long id_rha = bundle.getLong("id_rha",-1);
			if(id_rha != -1) {
				rha = dbRefeicaoHasAlimento.getById(id_rha);
				
				rha_quantidade.setText(rha.getQuantidade().toString());
				//viewAlimentos.
				//rha_id_alimento.setText(rha.getIdAlimento());
			}
        }
		
		salvarButton = (Button) getView().findViewById(R.id.salvar);
		salvarButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				attemptSave();
			}
		});
	}
	
	public void attemptSave(){
		boolean cancel = false;
		View focusView = null;
		
		if (TextUtils.isEmpty(rha_quantidade.getText().toString())) {
			rha_quantidade.setError(getString(R.string.error_field_required));
			focusView = rha_quantidade;
			cancel = true;
		}
		//if (TextUtils.isEmpty(rha_id_alimento.getText().toString())) {
		//	rha_id_alimento.setError(getString(R.string.error_field_required));
		//	focusView = rha_id_alimento;
		//	cancel = true;
		//}
		
		if(cancel){
			focusView.requestFocus();
		} else {
			rha.setQuantidade(NumberUtil.parseDouble(rha_quantidade.getText().toString()));
			rha.setIdAlimento(Integer.parseInt(rha_id_alimento.getText().toString()));
			rha.setIdRefeicao(rha_id_refeicao.intValue());
			
			dbRefeicaoHasAlimento.save(rha);
			//TODO usar o controller para salvar rha
			
			getActivity().getSupportFragmentManager().popBackStack();
		}
	}

	@Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
		if (getActivity() != null) {
			return new CursorLoader(
        		 getActivity(),
        		 SuperTreinoContract.Alimentos.CONTENT_URI,
        		 SuperTreinoContract.Alimentos.PROJECTION_ALL,
        		 null,
        		 null,
        		 SuperTreinoContract.Alimentos.SORT_ORDER_DEFAULT);
      	} else {
      		return null;
      	}
    }

	@Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (viewAlimentos.getAdapter() != null) {
			((AlimentosCursorAdapter)viewAlimentos.getAdapter()).swapCursor(cursor);
      	}
    }

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		((AlimentosCursorAdapter)viewAlimentos.getAdapter()).swapCursor(null);
	}
}
