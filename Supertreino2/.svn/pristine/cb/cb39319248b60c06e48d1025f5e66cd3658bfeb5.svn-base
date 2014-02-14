package br.ufba.matc89;

import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import br.ufba.matc89.dao.TreinoDAO;
import br.ufba.matc89.provider.SuperTreinoContract;
import br.ufba.matc89.adapter.TreinoCursorAdapter;

public class TreinoFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	
	/**
	* The unique identifier of the loader.
	*/
	private static final int TREINOLIST_LOADER = 11;
	
	TreinoDAO dbTreino;
	private TreinoCursorAdapter treinoAdapter;
	
	private MyTransactionListener transactionListener;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
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
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		
		treinoAdapter = new TreinoCursorAdapter(getActivity(), null);
		this.setListAdapter(treinoAdapter);
		registerForContextMenu(getListView());
		getLoaderManager().initLoader(TREINOLIST_LOADER, null, this);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		dbTreino = new TreinoDAO(getActivity());
		return super.onCreateView(inflater, container, savedInstanceState);
    }
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		getActivity().openContextMenu(v);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo){
		super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE, R.id.editar, Menu.NONE, "Editar");
        menu.add(Menu.NONE, R.id.excluir, Menu.NONE, "Excluir");
		//MenuInflater inflater = getActivity().getMenuInflater();
		//inflater.inflate(R.menu.treino_options, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		int itemId = item.getItemId();
		if (itemId == R.id.editar) {
			Bundle bundle = new Bundle();
			bundle.putLong("id_treino", info.id);
			Fragment fragment = new TreinoAddEditFragment();
			fragment.setArguments(bundle);
			transactionListener.showFragment(R.id.frame_container, fragment, null, this, true, false);
			return true;
		} else if (itemId == R.id.excluir) {
			dbTreino.delete_byID(info.id);
			//loadList();
			return true;
		} else {
			return super.onContextItemSelected(item);
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.treino, menu);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
		if (itemId == R.id.adicionar) {
			Fragment fragment = new TreinoAddEditFragment();
			transactionListener.showFragment(R.id.frame_container, fragment, null, this, true, false);
			return true;
		} else {
			Log.e("TreinoFragment", "Error in creating fragment");
			return super.onOptionsItemSelected(item);
		}
    }
	
//	protected void loadList(){
//		treinoAdapter = new TreinoCursorAdapter(getActivity(), dbTreino.getAll());
//		setListAdapter(treinoAdapter);
//	}

	@Override
   public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
		if (getActivity() != null) {
			return new CursorLoader(
        		 getActivity(),
        		 SuperTreinoContract.Treinos.CONTENT_URI,
        		 SuperTreinoContract.Treinos.PROJECTION_ALL,
        		 null,
        		 null,
        		 SuperTreinoContract.Treinos.SORT_ORDER_DEFAULT);
      	} else {
      		return null;
      	}
   }

	@Override
   public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (getListAdapter() != null) {
			((TreinoCursorAdapter)this.getListAdapter()).swapCursor(cursor);
      	}
   }

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		((TreinoCursorAdapter)this.getListAdapter()).swapCursor(null);
	}
}
