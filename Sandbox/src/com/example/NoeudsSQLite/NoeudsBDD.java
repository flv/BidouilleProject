package com.example.NoeudsSQLite;

import com.example.sqllite.Livre;
import com.example.sqllite.MaBaseSQLite;

import Utils.Utils;
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

	
	
	/* --------------------------------------------------------------
	 * Methodes d'acces aux données contenues dans la table Noeuds 
	 *  
	 * --------------------------------------------------------------
	 */
	public int getNbNoeuds()
	{
		Cursor curs = bdd.rawQuery("select * from " + TABLE_NOEUDS + ";", null);
		return curs.getCount();
	}

	public void insertNoeud(Noeud noeud)
	{
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_NOM, noeud.getNom());
		values.put(COL_PERE, noeud.getPere());
		values.put(COL_QRCODE, noeud.getContenuQrcode());
		values.put(COL_META, noeud.getMeta());
		//on insère l'objet dans la BDD via le ContentValues
		bdd.insert(TABLE_NOEUDS, null, values);
		
		Cursor c = bdd.rawQuery("select * from " + TABLE_NOEUDS + " where "+ COL_NOM + " = " + noeud.getNom() + " and " +
				COL_QRCODE + " = " + noeud.getContenuQrcode() + ";",
				null);
		noeud.setId(c.getInt(NUM_COL_CLE));
	}

	public long updateNoeud(Noeud ancienNoeud, Noeud nouveauNoeud) throws NoMatchableNodeException
	{
		
		//Création d'un ContentValues (fonctionne comme une HashMap)
		ContentValues values = new ContentValues();
		//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
		values.put(COL_NOM, nouveauNoeud.getNom());
		values.put(COL_PERE, nouveauNoeud.getPere());
		values.put(COL_QRCODE, nouveauNoeud.getContenuQrcode());
		values.put(COL_META, nouveauNoeud.getMeta());
		
		Cursor c = bdd.rawQuery("select * from " + TABLE_NOEUDS + " where " + COL_CLE + " = " + ancienNoeud.getId() + ";", null);
		if (c.getCount() == 0)
		{
			throw new NoMatchableNodeException("Impossible de mettre à jour un noeud non inséré dans la bd");
		}
		//on insère l'objet dans la BDD via le ContentValues
		return bdd.update(TABLE_NOEUDS, values, COL_CLE + "=" + ancienNoeud.getId(), null);
	}
	
	public void removeNoeud(Noeud noeud) throws NoMatchableNodeException{
		//Suppression d'un noeud de la BDD
		Cursor c = bdd.rawQuery("select * from " + TABLE_NOEUDS + " where " + COL_CLE + " = " + noeud.getId() + ";" 
				, null);
		if (c.getCount() == 0)
		{
			throw new NoMatchableNodeException("Le noeud à supprimmer n'est pas présent dans la BD");
		}
		bdd.delete(TABLE_NOEUDS, COL_CLE + " = " + noeud.getId(), null);
		c = bdd.rawQuery("select * from " + TABLE_META + "where " + COL_CLE_META + " = " + noeud.getId() + ";", null);
		if (c.getCount() != 0)
		{
			Metadata[] metas = getMetasById(noeud.getId());
			for (Metadata meta : metas)
			{
					removeMeta(meta);
			}
		}
		
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
	
	public Noeud getNoeudByCode(String code)
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_NOEUDS + " where " + COL_QRCODE + " = " +code + ";", null);
		return cursorToNoeud(c);
	}

	//Cette méthode permet de convertir un cursor en un noeud
	public Noeud cursorToNoeud(Cursor c){
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

		//On retourne le noeud
		return noeud;
	}
	
	/* ----------------------------------------------------------------
	 * 
	 * Méthodes d'accès aux données concernant les métadonnées
	 * 
	 * ----------------------------------------------------------------
	 */
	
	public int getNbMeta()
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_META + ";", null);
		return c.getCount();
	}
	
	public void insertMeta(Metadata meta) throws NoMatchableNodeException
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_NOEUDS + "where " + COL_CLE + " = " + meta.getId() +";",
				null);
		if (c.getCount() == 0)
		{
			throw new NoMatchableNodeException("Pas de noeud auquel associer la métadonnée"); 
		}
		else 
		{
			//Création d'un ContentValues (fonctionne comme une HashMap)
			ContentValues values = new ContentValues();
			//on lui ajoute une valeur associé à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
			values.put(COL_CLE_META, meta.getId());
			values.put(COL_TYPE, meta.getType());
			values.put(COL_CONTENU, meta.getData());
			//on insère l'objet dans la BDD via le ContentValues
			bdd.insert(TABLE_META, null, values);
		}
	}
	
	public void removeMeta(Metadata meta) throws NoMatchableNodeException
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_META + "where " + COL_CLE_META + " = " + meta.getId() +
				" and " + COL_TYPE + " = " + meta.getType() +";",
				null);
		if (c.getCount() == 0)
		{
			throw new NoMatchableNodeException("Cette metadonnée n'est pas présente dans la bd"); 
		}	
		else 
		{
			bdd.delete(TABLE_META, COL_CLE_META + " = " + meta.getId() + " and " + COL_TYPE + " = " + meta.getType() , null);			
		}
	}
	
	public Metadata[] getMetasById(int id)
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_META + " where " + COL_CLE_META + " = " + id + ";", null);
		int nbRes = c.getCount();
		if (nbRes == 0)
		{
			return null;
		}
		Metadata[] metas = new Metadata[this.getNbMeta()];
		for (int i = 0; i < nbRes; i ++)
		{
			c.move(i);
			metas[i] = cursorToMeta(c);
		}
		c.close();
		return metas;
	}
	
	public Metadata getMetaByIdType(int id, String type)
	{
		Cursor c = bdd.rawQuery("select * from " + TABLE_META + " where " 
								+ COL_CLE_META  + " = " + id + " and " 
								+ COL_TYPE + " = " + type + ";", 
								null);
		if (c.getCount() == 0) 
		{
			return null;
		}
		
		c.moveToFirst();
		return cursorToMeta(c);
	}

	public Metadata cursorToMeta(Cursor c) {
		// TODO Auto-generated method stub
		Metadata meta = new Metadata();
		
		meta.setId(c.getInt(NUM_COL_CLE_META));
		meta.setType(c.getString(NUM_COL_TYPE));
		meta.setData(c.getString(NUM_COL_CONTENU));
		
		return meta;
	}
	

}
