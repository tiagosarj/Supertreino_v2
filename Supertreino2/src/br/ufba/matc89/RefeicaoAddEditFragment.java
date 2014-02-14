package br.ufba.matc89;

import br.ufba.matc89.model.Refeicao;
import br.ufba.matc89.provider.SuperTreinoContract;
import br.ufba.matc89.adapter.AlimentoCursorAdapter;
import br.ufba.matc89.adapter.RefeicaoHasAlimentoCursorAdapter;
import br.ufba.matc89.dao.RefeicaoDAO;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.text.TextUtils;

public class RefeicaoAddEditFragment extends Fragment implements LoaderCallbacks<Cursor> {
	
	/**
	* The unique identifier of the loader.
	*/
	private static final int RHALIST_LOADER = 18;
	
	private RefeicaoHasAlimentoCursorAdapter rhaAdapter;
	
	private MyTransactionListener transactionListener;
	
	protected EditText refeicao_nome;
	protected Long id_refeicao = Long.parseLong("-1");
	protected ListView listView;
	protected Button salvarButton;
	protected Button addAlimentoButton;
	protected Refeicao refeicao = new Refeicao();
	RefeicaoDAO dbRefeicao;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.fragment_refeicao_add_edit, container, false);
		
		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		dbRefeicao = new RefeicaoDAO(getActivity());
		
		refeicao_nome = (EditText)getView().findViewById(R.id.refeicao_nome);
		listView = (ListView) getView().findViewById(R.id.listViewRHA);
		listView.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id){
				getActivity().openContextMenu(view);
			}
		});
		rhaAdapter = new RefeicaoHasAlimentoCursorAdapter(getActivity(), null);
		listView.setAdapter(rhaAdapter);
		registerForContextMenu(listView);
		getLoaderManager().initLoader(RHALIST_LOADER, null, this);
		
        Bundle bundle=getArguments();
        if(bundle != null){
	        id_refeicao = bundle.getLong("id_refeicao",-1);
			if (id_refeicao != -1) {
				refeicao = dbRefeicao.getById(id_refeicao);
				
				refeicao_nome.setText(refeicao.getNome());
			}
        }
		
        addAlimentoButton = (Button) getView().findViewById(R.id.add_alimento);
        addAlimentoButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				attemptAddAlimento();
			}
		});
        
		salvarButton = (Button) getView().findViewById(R.id.salvar);
		salvarButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				attemptSave();
			}
		});
	}
	
	@Override
	public void onAttach(Activity activity) {
	    super.onAttach(activity);
	    if (activity instanceof MyTransactionListener) {
	    	transactionListener = (MyTransactionListener) activity;
	    } else {
	      throw new ClassCastException(activity.toString()
	          + " must implemenet MyListFragment.OnItemSelectedListener");
	    }
	}

	@Override
	public void onDetach() {
	    super.onDetach();
	    transactionListener = null;
	}
	
	
	public void attemptAddAlimento(){
		boolean cancel = false;
		View focusView = null;
		
		if(TextUtils.isEmpty(refeicao_nome.getText().toString())) {
			refeicao_nome.setError(getString(R.string.error_field_required));
			focusView = refeicao_nome;
			cancel = true;
		}
		
		if(cancel){
			focusView.requestFocus();
		} else {
			refeicao.setNome(refeicao_nome.getText().toString());
			
			id_refeicao = dbRefeicao.save(refeicao);
			//TODO usar o controller para salvar refeicao
			
			Bundle bundle = new Bundle();
			bundle.putLong("id_refeicao", id_refeicao);
			Fragment fragment = new RefeicaoHasAlimentoAddEditFragment();
			fragment.setArguments(bundle);
			transactionListener.showFragment(R.id.frame_container, fragment, null, this, true, false);
		}
	}
	
	public void attemptSave(){
		boolean cancel = false;
		View focusView = null;
		
		if(TextUtils.isEmpty(refeicao_nome.getText().toString())) {
			refeicao_nome.setError(getString(R.string.error_field_required));
			focusView = refeicao_nome;
			cancel = true;
		}
		
		if(cancel){
			focusView.requestFocus();
		} else {
			refeicao.setNome(refeicao_nome.getText().toString());
			
			dbRefeicao.save(refeicao);
			//TODO usar o controller para salvar refeicao
			
			getActivity().getSupportFragmentManager().popBackStack();
		}
	}
	
	@Override
    public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
		if (getActivity() != null) {
			return new CursorLoader(
        		 getActivity(),
        		 SuperTreinoContract.RefeicaoHasAlimentos.CONTENT_URI,
        		 SuperTreinoContract.RefeicaoHasAlimentos.PROJECTION_ALL,
        		 null,
        		 null,
        		 SuperTreinoContract.RefeicaoHasAlimentos.SORT_ORDER_DEFAULT);
      	} else {
      		return null;
      	}
    }

	@Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (listView.getAdapter() != null) {
			((RefeicaoHasAlimentoCursorAdapter)listView.getAdapter()).swapCursor(cursor);
      	}
    }

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		((RefeicaoHasAlimentoCursorAdapter)listView.getAdapter()).swapCursor(null);
	}
}
