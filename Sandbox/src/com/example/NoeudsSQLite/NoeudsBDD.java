package com.example.NoeudsSQLite;

import com.example.sqllite.Livre;
import com.example.sqllite.MaBaseSQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NoeudsBDD {

	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "Synchrotags.db";

	private static final String TABLE_NOEUDS = "table_noeuds";
	private static final String COL_CLE = "CLE";
	private static final int NUM_COL_CLE = 0;
	private static final String COL_NOM = "NOM";
	private static final int NUM_COL_NOM = 1;
	private static final String COL_QRCODE = "QRCODE";
	private static final int NUM_COL_QRCODE = 2;
	private static final String COL_PERE = "PERE";
	private static final int NUM_COL_PERE = 3;
	private static final String COL_META = "META";
	private static final int NUM_COL_META = 4;

	private static final String TABLE_META = "table_meta";
	private static final String COL_CLE_META = "CLE";
	private static final int NUM_COL_CLE_META = 0;
	private static final String COL_TYPE = "TYPE_METADONNEE";
	private static final int NUM_COL_TYPE = 1;
	private static final String COL_CONTENU = "CONTENU_METADONNEE";
	private static final int NUM_COL_CONTENU = 2;

	private SQLiteDatabase bdd;

	private BaseSQLite maBaseSQLite;

	public NoeudsBDD(Context context){
		//On créer la BDD et sa table
		maBaseSQLite = new BaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}

	public void raz(){
		// Remise à zéro de la bdd
		maBaseSQLite.onUpgrade(bdd, VERSION_BDD, VERSION_BDD);
	}

	public void open(){
		//on ouvre la BDD en écriture
		bdd = maBaseSQLite.getWritableDatabase();
	}

	public void close(){
		//on ferme l'accès à la BDD
		bdd.close();
	}

	public SQLiteDatabase getBDD(){
		return bdd;
	}

	public int getNbNoeuds()
	{
		Cursor curs = bdd.rawQuery("select * from " + TABLE_NOEUDS + ";", null);
		return curs.getCount();
	}

	public long insertNoeud(Noeud noeud)
	{
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_NOM, noeud.getNom());
		values.put(COL_PERE, noeud.getPere());
		values.put(COL_QRCODE, noeud.getContenuQrcode());
		values.put(COL_META, noeud.getMeta());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.insert(TABLE_NOEUDS, null, values);
	}

	public long updateNoeud(Noeud noeud, int cleToUpdate)
	{
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_NOM, noeud.getNom());
		values.put(COL_PERE, noeud.getPere());
		values.put(COL_QRCODE, noeud.getContenuQrcode());
		values.put(COL_META, noeud.getMeta());
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.update(TABLE_NOEUDS, values, COL_CLE + "=" + cleToUpdate, null);
	}
	
	public int removeNoeudWithID(int id){
		//Suppression d'un livre de la BDD grâce à l'ID
		return bdd.delete(TABLE_NOEUDS, COL_CLE + " = " +id, null);
	}

	public Noeud getNoeudById(int id)
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_NOEUDS + " where " + COL_CLE + " = " + id + ";" , null);
		return cursorToNoeud(c);
	}
	
	public Noeud getNoeudByNom(String nom)
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_NOEUDS + " where " + COL_NOM + " = " + nom + ";", null);
		return cursorToNoeud(c);
	}	

	//Cette méthode permet de convertir un cursor en un livre
	private Noeud cursorToNoeud(Cursor c){
		//si aucun élément n'a été retourné dans la requête, on renvoie null
		if (c.getCount() == 0)
			return null;

		//Sinon on se place sur le premier élément
		c.moveToFirst();
		//On créé un livre
		Noeud noeud = new Noeud();
		//on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
		noeud.setNom(c.getString(NUM_COL_NOM));
		noeud.setPere(c.getInt(NUM_COL_PERE));
		noeud.setContenuQrcode(c.getString(NUM_COL_QRCODE));
		noeud.setMeta(c.getInt(NUM_COL_META));
		//On ferme le cursor
		c.close();

		//On retourne le livre
		return noeud;
	}

}
