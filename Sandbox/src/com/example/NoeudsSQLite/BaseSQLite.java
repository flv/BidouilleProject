package com.example.NoeudsSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BaseSQLite extends SQLiteOpenHelper {

	private static final String TABLE_NOEUDS = "table_noeuds";
	private static final String COL_CLE = "CLE";
	private static final String COL_NOM = "NOM";
	private static final String COL_QRCODE = "QRCODE";
	private static final String COL_PERE = "PERE";
	private static final String COL_META = "META";
	
	private static final String TABLE_META = "table_meta";
	private static final String COL_CLE_META = "CLE";
	private static final String COL_TYPE = "TYPE_METADONNEE";
	private static final String COL_CONTENU = "CONTENU_METADONNEE";

	private static final String CREATE_NOEUDS = "CREATE TABLE " + TABLE_NOEUDS + " ("
			+ COL_CLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NOM + " TEXT NOT NULL, "
			+ COL_QRCODE + " TEXT NOT NULL, " + COL_PERE + "INTEGER, " + COL_META + "INTEGER);";
	
	private static final String CREATE_META = "CREATE TABLE " + TABLE_META + " ("
			+ COL_CLE_META + "INTEGER NOT NULL " + COL_TYPE + "TEXT NOT NULL, "
			+ COL_CONTENU + "TEXT NOT NULL, PRIMARYKEY("+ COL_CLE_META + ", " + COL_TYPE +"));";

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
