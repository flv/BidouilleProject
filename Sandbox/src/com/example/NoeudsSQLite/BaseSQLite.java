package com.example.NoeudsSQLite;

import Data.DatabaseConstants;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BaseSQLite extends SQLiteOpenHelper {

	private static final String TABLE_NOEUDS = DatabaseConstants.TABLE_NOEUDS;
	private static final String COL_CLE = DatabaseConstants.COL_CLE;
	private static final String COL_NOM = DatabaseConstants.COL_NOM;
	private static final String COL_QRCODE = DatabaseConstants.COL_QRCODE;
	private static final String COL_PERE = DatabaseConstants.COL_PERE;
	private static final String COL_META = DatabaseConstants.COL_META;
	
	private static final String TABLE_META = DatabaseConstants.TABLE_META;
	private static final String COL_CLE_META = DatabaseConstants.COL_CLE_META;
	private static final String COL_TYPE = DatabaseConstants.COL_TYPE;
	private static final String COL_CONTENU = DatabaseConstants.COL_CONTENU;

	public static final String CREATE_NOEUDS = "CREATE TABLE " + TABLE_NOEUDS + " ("
			+ COL_CLE + " INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ COL_NOM + " TEXT NOT NULL , "
			+ COL_QRCODE + " TEXT NOT NULL , "
			+ COL_PERE + " NUM , "
			+ COL_META + " NUM "
			+ ");";
	
	public static final String CREATE_META = "CREATE TABLE " + TABLE_META + " ("
			+ COL_CLE_META + " NUM NOT NULL , " 
			+ COL_TYPE + " TEXT NOT NULL , "
			+ COL_CONTENU + " TEXT NOT NULL ,"
			+ " PRIMARY KEY ("+ COL_CLE_META + " , " + COL_TYPE +" )" 
			+ " );";

	public BaseSQLite (Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//on créé la table à partir de la requête écrite dans la variable CREATE_BDD
		db.execSQL(CREATE_NOEUDS);
		db.execSQL(CREATE_META);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
		//comme ça lorsque je change la version les id repartent de 0
		db.execSQL("DROP TABLE " + TABLE_NOEUDS + ";");
		onCreate(db);
	}
}
