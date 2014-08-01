package com.projetAndroid.villeNPDC.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.projetAndroid.villeNPDC.R;
import com.projetAndroid.villeNPDC.adapter.VilleProcheAdapter;
import com.projetAndroid.villeNPDC.bdd.BaseDeDonneeDAO;
import com.projetAndroid.villeNPDC.divers.CalculeDistanceGPS;
import com.projetAndroid.villeNPDC.divers.Ville;
import com.projetAndroid.villeNPDC.divers.VilleProche;

/**
 * Classe VisualisationVilleProcheActivity, cette classe permet de consulter la liste des villes dans un rayon de x Km choisi précédement et d'afficher la liste et l'itinéraire
 * vers ces villes.
 * @author Maxime
 *
 */
public class VisualisationVilleProcheActivity extends Activity {

	ListView listeVilleProche;
	TextView titre;
	List<Ville> lesVilles;
	List<VilleProche> lesVillesProchesAvecDistance;
	VilleProcheAdapter adapterProche;
	CalculeDistanceGPS calculeDistanceGPS;
	BaseDeDonneeDAO bdd;
	
	/**
	 * Fonction pour capturer la pression du bouton "BACK".
	 */
	public void onBackPressed(){
		Intent intent = new Intent(VisualisationVilleProcheActivity.this, MainActivity.class);
    	startActivity(intent);
    	finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualisation_ville_proche);
		
		Bundle b = getIntent().getExtras();
		int idVille = b.getInt("idVille");
		int distanceChoisi = b.getInt("distance");
		
		lesVilles = new ArrayList<Ville>();
		lesVillesProchesAvecDistance = new ArrayList<VilleProche>();
		calculeDistanceGPS = new CalculeDistanceGPS();	
		titre = (TextView)findViewById(R.id.titreVilleProche);
		listeVilleProche = (ListView)findViewById(R.id.listVilleProche);
		
		bdd = new BaseDeDonneeDAO(this);
		bdd.open();
		Ville villeDeDepart = bdd.getVilleByID(idVille);
		
		/** modification du titre pour afficher la valeur choisi pour la distance **/
		titre.setText(getResources().getString(R.string.titre_ville_proche) +" "+distanceChoisi+"km de "+villeDeDepart.getNom());
		
		/** recupération des villes à moins de X km de la ville choisi **/
		if(bdd.getNbOfVille() > 0 && villeDeDepart != null){
			lesVilles = bdd.getAllVille();
			
			for (Ville laVille : lesVilles) {
					double d = calculDistance(villeDeDepart,laVille);
				if(d <= distanceChoisi && d != 0){
					lesVillesProchesAvecDistance.add(new VilleProche(villeDeDepart, laVille, d));
				}
			}
			adapterProche = new VilleProcheAdapter(this, lesVillesProchesAvecDistance);
			listeVilleProche.setAdapter(adapterProche);
			bdd.close();
		}else
			Toast.makeText(getApplicationContext(), "Attention il n'y aucune ville dans la base de donn�e", Toast.LENGTH_SHORT).show();
		
		/**
		 * Application d'un listener lors d'un clic sur une ville.
		 * Ce clic permet d'afficher un itinéraire vers la ville.
		 */
		listeVilleProche.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long row) {
				double latA = lesVillesProchesAvecDistance.get(position).getVilleDeDepart().getLatitude();
				double lonA = lesVillesProchesAvecDistance.get(position).getVilleDeDepart().getLongitude();
				double latB = lesVillesProchesAvecDistance.get(position).getVilleArrive().getLatitude();
				double lonB = lesVillesProchesAvecDistance.get(position).getVilleArrive().getLongitude();
				
				/** redirection vers un itinéraire google map **/
				Uri itineraireVille = Uri.parse("http://maps.google.com/maps?saddr="+latA+","+lonA+"&daddr="+latB+","+lonB);
            	Intent intent = new Intent(Intent.ACTION_VIEW, itineraireVille);
            	startActivity(intent);
			}
		});
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		bdd.close();
	}
	
	@Override
	public void onPause(){
		super.onPause();
		bdd.close();
	}

	@Override
	public void onResume(){
		super.onResume();
		bdd.open();
	}
	
	/**
	 * Fonction permettant de calculer la distance entre deux villes grâce à ses coordonnées GPS.
	 * @param villeDeDepart l'objet représentant la ville de départ.
	 * @param villeArrive l'objet représentant la ville d'arriv�.
	 * @return
	 */
	protected double calculDistance(Ville villeDeDepart, Ville villeArrive){
		return calculeDistanceGPS.calculeDistanceEnKM(villeDeDepart.getLatitude(), villeDeDepart.getLongitude(), villeArrive.getLatitude(), villeArrive.getLongitude());
	}
}
