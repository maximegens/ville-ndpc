package com.projetAndroid.villeNPDC.bdd;

import java.util.ArrayList;
import java.util.List;

import com.projetAndroid.villeNPDC.divers.Ville;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Classe contenant les fonctions propre à la base de donn�es.
 * @author Maxime
 *
 */
public class BaseDeDonneeDAO {

	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "ville.db";
 
	private static final String TABLE_VILLE = "table_ville";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_NOM_VILLE = "NOM";
	private static final int NUM_COL_NOM_VILLE = 1;
	private static final String COL_LATITUDE = "LATITUDE";
	private static final int NUM_COL_LATITUDE = 2;
	private static final String COL_LONGITUDE = "LONGITUDE";
	private static final int NUM_COL_LONGITUDE = 3;


	private SQLiteDatabase bdd;
 
	private BaseDeDonneeSqlLite baseDeDonneeSqlLite;
 
	public BaseDeDonneeDAO(Context context){
		baseDeDonneeSqlLite = new BaseDeDonneeSqlLite(context, NOM_BDD, null, VERSION_BDD);
	}
 
	public void open(){
		bdd = baseDeDonneeSqlLite.getWritableDatabase();
	}
 
	public void close(){
		bdd.close();
	}
 
	public SQLiteDatabase getBDD(){
		return bdd;
	}
 
	public long insertVille(Ville ville){
		ContentValues values = new ContentValues();
		values.put(COL_NOM_VILLE, ville.getNom());
		values.put(COL_LATITUDE, ville.getLatitude());
		values.put(COL_LONGITUDE, ville.getLongitude());
		return bdd.insert(TABLE_VILLE, null, values);
	}
 
	public int updateVille(int id, Ville ville){
		ContentValues values = new ContentValues();
		values.put(COL_NOM_VILLE, ville.getNom());
		values.put(COL_LATITUDE, ville.getLatitude());
		values.put(COL_LONGITUDE, ville.getLongitude());
		return bdd.update(TABLE_VILLE, values, COL_ID + " = " +id, null);
	}
 
	public int removeVilleWithID(int id){
		return bdd.delete(TABLE_VILLE, COL_ID + " = " +id, null);
	}
 
	public Ville getVilleByName(String nom){
		Cursor c = bdd.query(TABLE_VILLE, new String[] {COL_ID, COL_NOM_VILLE,COL_LATITUDE,COL_LONGITUDE}, COL_NOM_VILLE + " LIKE \"" + nom +"\"", null, null, null, null);
		return cursorToVille(c);
	}
	
	public Ville getVilleByID(int id){
		Cursor c = bdd.query(TABLE_VILLE, new String[] {COL_ID, COL_NOM_VILLE,COL_LATITUDE,COL_LONGITUDE}, COL_ID + " LIKE \"" + id +"\"", null, null, null, null);
		return cursorToVille(c);
	}

	public List<Ville> getAllVille(){
		Cursor c = bdd.query(TABLE_VILLE, new String[] {COL_ID, COL_NOM_VILLE,COL_LATITUDE,COL_LONGITUDE}, null, null, null, null, null);
		return cursorsToVille(c);
	}
	
	public int getNbOfVille(){
		Cursor c = bdd.query(TABLE_VILLE, new String[] {COL_ID, COL_NOM_VILLE,COL_LATITUDE,COL_LONGITUDE}, null, null, null, null, null);
		return c.getCount();
	}
	
	
	
	private Ville cursorToVille(Cursor c){
		if (c.getCount() == 0)
			return null;
 
		c.moveToFirst();
		Ville ville = new Ville();
		ville.setId(c.getInt(NUM_COL_ID));
		ville.setNom(c.getString(NUM_COL_NOM_VILLE));
		ville.setLatitude(c.getDouble(NUM_COL_LATITUDE));
		ville.setLongitude(c.getDouble(NUM_COL_LONGITUDE));
		c.close();
 
		return ville;
	}
	
	private List<Ville> cursorsToVille(Cursor c)
	{
		
		List<Ville> liste = new ArrayList<Ville>();
		  if (c.getCount() == 0) 
		  {
			   Log.e("BDD DAO", "Aucune donnee a transtyper"); 
			   return null; 
		  } 
		  else 
		  { 
			  c.moveToFirst(); 
			  
			   for (int i = 0; i < c.getCount(); i++)  
			   { 
				Ville ville= new Ville();
				ville.setId(c.getInt(NUM_COL_ID));
				ville.setNom(c.getString(NUM_COL_NOM_VILLE));
				ville.setLatitude(c.getDouble(NUM_COL_LATITUDE));
				ville.setLongitude(c.getDouble(NUM_COL_LONGITUDE));
				liste.add(ville); 
			    c.moveToNext(); 
			   }

			}
		  	c.close();
			   return liste; 
		  } 
 	}
