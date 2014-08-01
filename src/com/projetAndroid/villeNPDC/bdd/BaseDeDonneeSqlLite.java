package com.projetAndroid.villeNPDC.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Classe permettant de construire la base de données.
 * @author Maxime
 *
 */
public class BaseDeDonneeSqlLite extends SQLiteOpenHelper{
	 
		private static final String TABLE_VILLE = "table_ville";
		private static final String COL_ID = "ID";
		private static final String COL_NOM_VILLE = "NOM";
		private static final String COL_LATITUDE = "LATITUDE";
		private static final String COL_LONGITUDE = "LONGITUDE";
	 
		private static final String CREATE_BDD = "CREATE TABLE " + TABLE_VILLE 
		+ " ("
		+ COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " 
		+ COL_NOM_VILLE + " TEXT NOT NULL,"
		+ COL_LATITUDE + " TEXT NOT NULL,"
		+ COL_LONGITUDE + " TEXT NOT NULL"
		+ "); ";
		
	 
		public BaseDeDonneeSqlLite(Context context, String name, CursorFactory factory, int version) {
			super(context, name, factory, version);
		}
	 

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_BDD);
			
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE " + TABLE_VILLE + ";");
			onCreate(db);
			
		}
}
