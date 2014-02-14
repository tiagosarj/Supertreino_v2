package br.ufba.matc89.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufba.matc89.dao.GenericDAO;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Binder;
import android.support.v7.appcompat.BuildConfig;
import android.text.TextUtils;
import android.util.Log;
import br.ufba.matc89.provider.SuperTreinoContract;

public class SuperTreinoProvider extends ContentProvider {
	// helper constants for use with the UriMatcher
	private static final int ATLETA_LIST = 1;
	private static final int ATLETA_ID = 2;
	private static final int MEDIDA_LIST = 3;
	private static final int MEDIDA_ID = 4;
	private static final int REFEICAO_LIST = 5;
	private static final int REFEICAO_ID = 6;
	private static final int ALIMENTO_LIST = 7;
	private static final int ALIMENTO_ID = 8;
	private static final int REFEICAO_HAS_ALIMENTO_LIST = 9;
	private static final int REFEICAO_HAS_ALIMENTO_ID = 10;
	private static final int TREINO_LIST = 11;
	private static final int TREINO_ID = 12;
	private static final int EXERCICIO_LIST = 13;
	private static final int EXERCICIO_ID = 14;
	
	private static final UriMatcher URI_MATCHER;
	
	private GenericDAO mDB = null;
	private final ThreadLocal<Boolean> mIsInBatchMode = new ThreadLocal<Boolean>();

	static {
		URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "atletas", ATLETA_LIST);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "atletas/#", ATLETA_ID);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "medidas", MEDIDA_LIST);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "medidas/#", MEDIDA_ID);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "refeicoes", REFEICAO_LIST);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "refeicoes/#", REFEICAO_ID);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "alimentos", ALIMENTO_LIST);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "alimentos/#", ALIMENTO_ID);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "refeicao_has_alimentos", REFEICAO_HAS_ALIMENTO_LIST);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "refeicao_has_alimentos/#", REFEICAO_HAS_ALIMENTO_ID);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "treinos", TREINO_LIST);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "treinos/#", TREINO_ID);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "exercicios", EXERCICIO_LIST);
		URI_MATCHER.addURI(SuperTreinoContract.AUTHORITY, "exercicios/#", EXERCICIO_ID);
	}
	
	@Override
	public boolean onCreate() {
		mDB = new GenericDAO(getContext());
		return true;
	}

	@Override
	public String getType(Uri uri) {
		switch (URI_MATCHER.match(uri)) {
			case REFEICAO_LIST:
				return SuperTreinoContract.Refeicoes.CONTENT_TYPE;
			case REFEICAO_ID:
				return SuperTreinoContract.Refeicoes.CONTENT_ITEM_TYPE;
			case ALIMENTO_LIST:
				return SuperTreinoContract.Alimentos.CONTENT_TYPE;
			case ALIMENTO_ID:
				return SuperTreinoContract.Alimentos.CONTENT_ITEM_TYPE;
			case REFEICAO_HAS_ALIMENTO_LIST:
				return SuperTreinoContract.RefeicaoHasAlimentos.CONTENT_TYPE;
			case REFEICAO_HAS_ALIMENTO_ID:
				return SuperTreinoContract.RefeicaoHasAlimentos.CONTENT_ITEM_TYPE;
			case EXERCICIO_LIST:
				return SuperTreinoContract.Exercicios.CONTENT_TYPE;
			case EXERCICIO_ID:
				return SuperTreinoContract.Exercicios.CONTENT_ITEM_TYPE;
			case TREINO_LIST:
				return SuperTreinoContract.Treinos.CONTENT_TYPE;
			case TREINO_ID:
				return SuperTreinoContract.Treinos.CONTENT_ITEM_TYPE;
			case MEDIDA_LIST:
				return SuperTreinoContract.Medidas.CONTENT_TYPE;
			case MEDIDA_ID:
				return SuperTreinoContract.Medidas.CONTENT_ITEM_TYPE;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		doAnalytics(uri, "query");

		SQLiteDatabase db = mDB.getReadableDatabase();
		SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
		boolean useAuthorityUri = false;
		switch (URI_MATCHER.match(uri)) {
			case REFEICAO_LIST:
				builder.setTables(DbSchema.TBL_REFEICOES);
		      	if (TextUtils.isEmpty(sortOrder)) {
		    	  	sortOrder = SuperTreinoContract.Refeicoes.SORT_ORDER_DEFAULT;
		      	}
				break;
			case REFEICAO_ID:
				builder.setTables(DbSchema.TBL_REFEICOES);
				// limit query to one row at most:
				builder.appendWhere(SuperTreinoContract.Refeicoes._ID + " = "
						+ uri.getLastPathSegment());
				break;
			case ALIMENTO_LIST:
				builder.setTables(DbSchema.TBL_ALIMENTOS);
		      	if (TextUtils.isEmpty(sortOrder)) {
		    	  	sortOrder = SuperTreinoContract.Alimentos.SORT_ORDER_DEFAULT;
		      	}
				break;
			case ALIMENTO_ID:
				builder.setTables(DbSchema.TBL_ALIMENTOS);
				// limit query to one row at most:
				builder.appendWhere(SuperTreinoContract.Alimentos._ID + " = "
						+ uri.getLastPathSegment());
				break;
			case REFEICAO_HAS_ALIMENTO_LIST:
				//StringBuilder sb = new StringBuilder();
				//sb.append(DbSchema.TBL_REFEICAO_ALIMENTO);
				//sb.append(" LEFT OUTER JOIN ");
				//sb.append(DbSchema.TBL_ALIMENTOS);
				//sb.append(" ON (");
				//sb.append(SuperTreinoContract.RefeicaoHasAlimentos.addPrefix(SuperTreinoContract.RefeicaoHasAlimentos.ID_ALIMENTO));
				//sb.append(" = ");
				//sb.append(SuperTreinoContract.Alimentos.addPrefix(SuperTreinoContract.Alimentos._ID));
				//sb.append(")");
				//String table = sb.toString();
				//builder.setTables(table);
				//Map<String, String> columnMap = new HashMap<String,String>();
				//columnMap.put("alimento._id","alimento._id AS alimento_id");
				//columnMap.put("refeicao_alimento._id","refeicao_alimento._id AS _id");
				//builder.setProjectionMap(columnMap);
				builder.setTables(DbSchema.TBL_REFEICAO_ALIMENTO);
		      	if (TextUtils.isEmpty(sortOrder)) {
		    	  	sortOrder = SuperTreinoContract.RefeicaoHasAlimentos.SORT_ORDER_DEFAULT;
		      	}
				break;
			case REFEICAO_HAS_ALIMENTO_ID:
				builder.setTables(DbSchema.TBL_REFEICAO_ALIMENTO);
				// limit query to one row at most:
				builder.appendWhere(SuperTreinoContract.RefeicaoHasAlimentos._ID + " = "
						+ uri.getLastPathSegment());
				break;
			case TREINO_LIST:
				builder.setTables(DbSchema.TBL_TREINOS);
		      	if (TextUtils.isEmpty(sortOrder)) {
		    	  	sortOrder = SuperTreinoContract.Treinos.SORT_ORDER_DEFAULT;
		      	}
				break;
			case TREINO_ID:
				builder.setTables(DbSchema.TBL_TREINOS);
				// limit query to one row at most:
				builder.appendWhere(SuperTreinoContract.Treinos._ID + " = "
						+ uri.getLastPathSegment());
				break;
			case EXERCICIO_LIST:
				builder.setTables(DbSchema.TBL_EXERCICIOS);
		      	if (TextUtils.isEmpty(sortOrder)) {
		    	  	sortOrder = SuperTreinoContract.Exercicios.SORT_ORDER_DEFAULT;
		      	}
				break;
			case EXERCICIO_ID:
				builder.setTables(DbSchema.TBL_EXERCICIOS);
				// limit query to one row at most:
				builder.appendWhere(SuperTreinoContract.Exercicios._ID + " = "
						+ uri.getLastPathSegment());
				break;
			case MEDIDA_LIST:
				builder.setTables(DbSchema.TBL_MEDIDAS);
		      	if (TextUtils.isEmpty(sortOrder)) {
		    	  	sortOrder = SuperTreinoContract.Medidas.SORT_ORDER_DEFAULT;
		      	}
				break;
			case MEDIDA_ID:
				builder.setTables(DbSchema.TBL_MEDIDAS);
				// limit query to one row at most:
				builder.appendWhere(SuperTreinoContract.Medidas._ID + " = "
						+ uri.getLastPathSegment());
				break;
			default:
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		Cursor cursor = builder.query(db, projection, selection, selectionArgs,
				null, null, sortOrder);
		// if we want to be notified of any changes:
		if(useAuthorityUri) {
		   cursor.setNotificationUri(getContext().getContentResolver(), SuperTreinoContract.CONTENT_URI);
	   	} else {
		   	cursor.setNotificationUri(getContext().getContentResolver(), uri);
	   	}
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		doAnalytics(uri, "insert");
		if (URI_MATCHER.match(uri) != REFEICAO_LIST &&
				URI_MATCHER.match(uri) != ALIMENTO_LIST &&
				URI_MATCHER.match(uri) != REFEICAO_HAS_ALIMENTO_LIST &&
				URI_MATCHER.match(uri) != EXERCICIO_LIST &&
				URI_MATCHER.match(uri) != MEDIDA_LIST &&
				URI_MATCHER.match(uri) != TREINO_LIST) {
			throw new IllegalArgumentException(
					"Unsupported URI for insertion: " + uri);
		}
		SQLiteDatabase db = mDB.getWritableDatabase();
		if (URI_MATCHER.match(uri) == ALIMENTO_LIST) {
			long id = db.insert(DbSchema.TBL_ALIMENTOS, null, values);
			return getUriForId(id, uri);
		} else if (URI_MATCHER.match(uri) == REFEICAO_LIST) {
			long id = db.insert(DbSchema.TBL_REFEICOES, null, values);
			return getUriForId(id, uri);
		} else if (URI_MATCHER.match(uri) == REFEICAO_HAS_ALIMENTO_LIST) {
			long id = db.insert(DbSchema.TBL_REFEICAO_ALIMENTO, null, values);
			return getUriForId(id, uri);
		} else if (URI_MATCHER.match(uri) == EXERCICIO_LIST) {
			long id = db.insert(DbSchema.TBL_EXERCICIOS, null, values);
			return getUriForId(id, uri);
		} else if (URI_MATCHER.match(uri) == TREINO_LIST) {
			long id = db.insert(DbSchema.TBL_TREINOS, null, values);
			return getUriForId(id, uri);
		} else if (URI_MATCHER.match(uri) == MEDIDA_LIST) {
			long id = db.insert(DbSchema.TBL_MEDIDAS, null, values);
			return getUriForId(id, uri);
		} else {
			// this insertWithOnConflict is a special case; CONFLICT_REPLACE
			// means that an existing entry which violates the UNIQUE constraint
			// on the item_id column gets deleted. That is this INSERT behaves
			// nearly like an UPDATE. Though the new row has a new primary key.
			// See how I mentioned this in the Contract class.
			//long id = db.insertWithOnConflict(DbSchema.TBL_PHOTOS, null, values, SQLiteDatabase.CONFLICT_REPLACE);
			//return getUriForId(id, uri);
			return null;
		}
	}
	
	private Uri getUriForId(long id, Uri uri) {
      if (id > 0) {
         Uri itemUri = ContentUris.withAppendedId(uri, id);
         if (!isInBatchMode()) {
            // notify all listeners of changes and return itemUri:
            getContext().
                  getContentResolver().
                        notifyChange(itemUri, null);
         }
         return itemUri;
      }
      // s.th. went wrong:
      throw new SQLException("Problem while inserting into uri: " + uri);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		doAnalytics(uri, "update");
		SQLiteDatabase db = mDB.getWritableDatabase();
		int updateCount = 0;
		String idStr;
		String where;
		switch (URI_MATCHER.match(uri)) {
			case REFEICAO_LIST:
				updateCount = db.update(DbSchema.TBL_REFEICOES, values, selection,
						selectionArgs);
				break;
			case REFEICAO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Refeicoes._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				updateCount = db.update(DbSchema.TBL_REFEICOES, values, where,
						selectionArgs);
				break;
			case ALIMENTO_LIST:
				updateCount = db.update(DbSchema.TBL_ALIMENTOS, values, selection,
						selectionArgs);
				break;
			case ALIMENTO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Alimentos._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				updateCount = db.update(DbSchema.TBL_ALIMENTOS, values, where,
						selectionArgs);
				break;
			case REFEICAO_HAS_ALIMENTO_LIST:
				updateCount = db.update(DbSchema.TBL_REFEICAO_ALIMENTO, values, selection,
						selectionArgs);
				break;
			case REFEICAO_HAS_ALIMENTO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.RefeicaoHasAlimentos._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				updateCount = db.update(DbSchema.TBL_REFEICAO_ALIMENTO, values, where,
						selectionArgs);
				break;
			case TREINO_LIST:
				updateCount = db.update(DbSchema.TBL_TREINOS, values, selection,
						selectionArgs);
				break;
			case TREINO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Treinos._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				updateCount = db.update(DbSchema.TBL_TREINOS, values, where,
						selectionArgs);
				break;
			case EXERCICIO_LIST:
				updateCount = db.update(DbSchema.TBL_EXERCICIOS, values, selection,
						selectionArgs);
				break;
			case EXERCICIO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Exercicios._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				updateCount = db.update(DbSchema.TBL_EXERCICIOS, values, where,
						selectionArgs);
				break;
			case MEDIDA_LIST:
				updateCount = db.update(DbSchema.TBL_MEDIDAS, values, selection,
						selectionArgs);
				break;
			case MEDIDA_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Medidas._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				updateCount = db.update(DbSchema.TBL_MEDIDAS, values, where,
						selectionArgs);
				break;
			default:
				// no support for updating photos!
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		// notify all listeners of changes:
		if (updateCount > 0 && !isInBatchMode()) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return updateCount;
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		doAnalytics(uri, "delete");
		   
		SQLiteDatabase db = mDB.getWritableDatabase();
		int delCount = 0;
		String idStr;
		String where;
		switch (URI_MATCHER.match(uri)) {
			case REFEICAO_LIST:
				delCount = db.delete(DbSchema.TBL_REFEICOES, selection, selectionArgs);
				break;
			case REFEICAO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Refeicoes._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				delCount = db.delete(DbSchema.TBL_REFEICOES, where, selectionArgs);
				break;
			case ALIMENTO_LIST:
				delCount = db.delete(DbSchema.TBL_ALIMENTOS, selection, selectionArgs);
				break;
			case ALIMENTO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Alimentos._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				delCount = db.delete(DbSchema.TBL_ALIMENTOS, where, selectionArgs);
				break;
			case REFEICAO_HAS_ALIMENTO_LIST:
				delCount = db.delete(DbSchema.TBL_REFEICAO_ALIMENTO, selection, selectionArgs);
				break;
			case REFEICAO_HAS_ALIMENTO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.RefeicaoHasAlimentos._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				delCount = db.delete(DbSchema.TBL_REFEICAO_ALIMENTO, where, selectionArgs);
				break;
			case EXERCICIO_LIST:
				delCount = db.delete(DbSchema.TBL_EXERCICIOS, selection, selectionArgs);
				break;
			case EXERCICIO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Exercicios._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				delCount = db.delete(DbSchema.TBL_EXERCICIOS, where, selectionArgs);
				break;
			case TREINO_LIST:
				delCount = db.delete(DbSchema.TBL_TREINOS, selection, selectionArgs);
				break;
			case TREINO_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Treinos._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				delCount = db.delete(DbSchema.TBL_TREINOS, where, selectionArgs);
				break;
			case MEDIDA_LIST:
				delCount = db.delete(DbSchema.TBL_MEDIDAS, selection, selectionArgs);
				break;
			case MEDIDA_ID:
				idStr = uri.getLastPathSegment();
				where = SuperTreinoContract.Medidas._ID + " = " + idStr;
				if (!TextUtils.isEmpty(selection)) {
					where += " AND " + selection;
				}
				delCount = db.delete(DbSchema.TBL_MEDIDAS, where, selectionArgs);
				break;
			default:
				// no support for deleting photos or entities -
				// photos are deleted by a trigger when the item is deleted
				throw new IllegalArgumentException("Unsupported URI: " + uri);
		}
		// notify all listeners of changes:
		if (delCount > 0 && !isInBatchMode()) {
			getContext().getContentResolver().notifyChange(uri, null);
		}
		return delCount;
	}

	private boolean isInBatchMode() {
		return mIsInBatchMode.get() != null && mIsInBatchMode.get();
   	}

	/**
	 * I do not really use analytics, but if you export
	 * your content provider it makes sense to do so, to get
	 * a feeling for client usage. Especially if you want to
	 * _change_ something which might break existing clients,
	 * please check first if you can safely do so.
	 */
	private void doAnalytics(Uri uri, String event) {
	   if (BuildConfig.DEBUG) {
         Log.v("cpsample", event + " -> " + uri);
         Log.v("cpsample", "caller: " + detectCaller());
	   }
	}
	
   /** 
    * You can use this for Analytics. 
    * 
    * Be aware though: This might be costly if many apps 
    * are running.
    */
   private String detectCaller() {
      // found here:
      // https://groups.google.com/forum/#!topic/android-developers/0HsvyTYZldA
      int pid = Binder.getCallingPid();
      return getProcessNameFromPid(pid);
   }

   /**
    * Returns the name of the process the pid belongs to. Can be null if neither
    * an Activity nor a Service could be found.
    * @param givenPid
    * @return
    */
   private String getProcessNameFromPid(int givenPid) {
      ActivityManager am = (ActivityManager) getContext().getSystemService(
            Activity.ACTIVITY_SERVICE);
      List<ActivityManager.RunningAppProcessInfo> lstAppInfo = am
            .getRunningAppProcesses();
      for (ActivityManager.RunningAppProcessInfo ai : lstAppInfo) {
         if (ai.pid == givenPid) {
            return ai.processName;
         }
      }
      // added to take care of calling services as well:
      List<ActivityManager.RunningServiceInfo> srvInfo = am
            .getRunningServices(Integer.MAX_VALUE);
      for (ActivityManager.RunningServiceInfo si : srvInfo) {
         if (si.pid == givenPid) {
            return si.process;
         }
      }
      return null;
   }

}