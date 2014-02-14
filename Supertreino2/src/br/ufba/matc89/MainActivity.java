package br.ufba.matc89;

import java.util.ArrayList;

import br.ufba.matc89.adapter.NavDrawerListAdapter;
import br.ufba.matc89.model.NavDrawerItem;

import android.os.Bundle;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements MyTransactionListener {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    // armazena a tag do fragment ativo
    private String activeFragTag;
    
    // nav drawer title
    private CharSequence mDrawerTitle;
 
    // used to store app title
    private CharSequence mTitle;
 
    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
 
    private ArrayList<NavDrawerItem> navDrawerItems;
    private NavDrawerListAdapter adapter;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        mTitle = mDrawerTitle = getTitle();
 
        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
 
        // nav drawer icons from resources
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
 
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slider_menu);
 
        navDrawerItems = new ArrayList<NavDrawerItem>();
 
        // adding nav drawer items to array
        // Relatorio
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        // Atleta
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        // Dieta
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        // Alimento
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1)));
        // Medida
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        // Treino
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        // Exercicio
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        
        // Recycle the typed array
        navMenuIcons.recycle();
 
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(this,navDrawerItems);
        mDrawerList.setAdapter(adapter);
        
        // setting the list's click listener
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
 
        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
 
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(mTitle);
                // calling onPrepareOptionsMenu() to show action bar icons
                supportInvalidateOptionsMenu();
            }
 
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(mDrawerTitle);
                // calling onPrepareOptionsMenu() to hide action bar icons
                supportInvalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
 
        if (savedInstanceState == null) {
            // on first time display view for first nav item
            displayView(2);
        }
    }

    /**
     * Slide menu item click listener
     * */
    private class SlideMenuClickListener implements ListView.OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// display view for selected nav drawer item
            displayView(arg2);
		}
    }
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int itemId = item.getItemId();
		if (itemId == R.id.action_settings) {
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
    }
 
    /***
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }
    
    /**
    * Diplaying fragment view for selected nav drawer list item
    * */
   private void displayView(int position) {
       // update the main content by replacing fragments
       Fragment fragment = null;
       switch (position) {
       case 0:
           //fragment = new HomeFragment();
           break;
       case 1:
           //fragment = new FindPeopleFragment();
           break;
       case 2:
           fragment = new RefeicaoFragment();
           break;
       case 3:
           fragment = new AlimentoFragment();
           break;
       case 4:
           fragment = new MedidaFragment();
           break;
       case 5:
           fragment = new TreinoFragment();
           break;
       case 6:
    	   fragment = new ExercicioFragment();
           break;
       default:
           break;
       }

       if (fragment != null) {
    	   showFragment(R.id.frame_container, fragment, fragment.getTag(), null, false , true);
           //showFragment(R.id.frame_container,fragment, fragment.getTag().toString(), null, false);

           // update selected item and title, then close the drawer
           mDrawerList.setItemChecked(position, true);
           mDrawerList.setSelection(position);
           setTitle(navMenuTitles[position]);
           mDrawerLayout.closeDrawer(mDrawerList);
       } else {
           // error in creating fragment
           Log.e("MainActivity", "Error in creating fragment");
       }
   }
 
    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }
 
    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }
 
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    @Override
    public void showFragment(int container, Fragment fragment, String tag, Fragment lastFragment, boolean addToBackStack, boolean onBackStackTop) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if(onBackStackTop){
        	if(fragmentManager.getBackStackEntryCount() > 0){
 	           FragmentManager.BackStackEntry first = fragmentManager.getBackStackEntryAt(0);
 	           fragmentManager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if ( addToBackStack ) {
            transaction.addToBackStack( tag );
        }
        
        transaction.replace(container, fragment);

        transaction.commit();

        // set the active tag
        activeFragTag = tag;
    }
}