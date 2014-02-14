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

import br.ufba.matc89.dao.ExercicioDAO;
import br.ufba.matc89.provider.SuperTreinoContract;
import br.ufba.matc89.adapter.ExercicioCursorAdapter;

public class ExercicioFragment extends ListFragment implements LoaderCallbacks<Cursor> {
	
	/**
	* The unique identifier of the loader.
	*/
	private static final int EXERCICIOLIST_LOADER = 11;
	
	ExercicioDAO dbExercicio;
	private ExercicioCursorAdapter exercicioAdapter;
	
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
		
		exercicioAdapter = new ExercicioCursorAdapter(getActivity(), null);
		this.setListAdapter(exercicioAdapter);
		registerForContextMenu(getListView());
		getLoaderManager().initLoader(EXERCICIOLIST_LOADER, null, this);
	}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		dbExercicio = new ExercicioDAO(getActivity());
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
		//inflater.inflate(R.menu.exercicio_options, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item){
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		int itemId = item.getItemId();
		if (itemId == R.id.editar) {
			Bundle bundle = new Bundle();
			bundle.putLong("id_exercicio", info.id);
			Fragment fragment = new ExercicioAddEditFragment();
			fragment.setArguments(bundle);
			transactionListener.showFragment(R.id.frame_container, fragment, null, this, true, false);
			return true;
		} else if (itemId == R.id.excluir) {
			dbExercicio.delete_byID(info.id);
			//loadList();
			return true;
		} else {
			return super.onContextItemSelected(item);
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.exercicio, menu);
	}
	
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
		if (itemId == R.id.adicionar) {
			Fragment fragment = new ExercicioAddEditFragment();
			transactionListener.showFragment(R.id.frame_container, fragment, null, this, true, false);
			return true;
		} else {
			Log.e("ExercicioFragment", "Error in creating fragment");
			return super.onOptionsItemSelected(item);
		}
    }
	
//	protected void loadList(){
//		exercicioAdapter = new ExercicioCursorAdapter(getActivity(), dbExercicio.getAll());
//		setListAdapter(exercicioAdapter);
//	}

	@Override
   public Loader<Cursor> onCreateLoader(int loaderId, Bundle args) {
		if (getActivity() != null) {
			return new CursorLoader(
        		 getActivity(),
        		 SuperTreinoContract.Exercicios.CONTENT_URI,
        		 SuperTreinoContract.Exercicios.PROJECTION_ALL,
        		 null,
        		 null,
        		 SuperTreinoContract.Exercicios.SORT_ORDER_DEFAULT);
      	} else {
      		return null;
      	}
   }

	@Override
   public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		if (getListAdapter() != null) {
			((ExercicioCursorAdapter)this.getListAdapter()).swapCursor(cursor);
      	}
   }

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		((ExercicioCursorAdapter)this.getListAdapter()).swapCursor(null);
	}
}
