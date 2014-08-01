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

import com.projetAndroid.villeNPDC.R;
import com.projetAndroid.villeNPDC.adapter.VilleAdapter;
import com.projetAndroid.villeNPDC.bdd.BaseDeDonneeDAO;
import com.projetAndroid.villeNPDC.divers.Constantes;
import com.projetAndroid.villeNPDC.divers.Ville;

/**
 * Classe MainActivity, cette classe correspond au premier écran d'affichage du
 * téléphone
 * 
 * @author Maxime
 * 
 */
public class MainActivity extends Activity {

	public ListView listeViewVille;
	public List<Ville> lesvilles;
	VilleAdapter adapter;
	BaseDeDonneeDAO bdd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listeViewVille = (ListView) findViewById(R.id.listViewVille);
		lesvilles = new ArrayList<Ville>();
		bdd = new BaseDeDonneeDAO(this);
		bdd.open();

		if (bdd.getNbOfVille() == 0) {
			remplirBDD();
		}
		
		lesvilles = bdd.getAllVille();
		adapter = new VilleAdapter(this, lesvilles);
		listeViewVille.setAdapter(adapter);

		/**
		 * Application d'un listener lors d'un clic long sur la listView.
		 * Ce clic permet de situer sur une google map la position de la ville.
		 */
		listeViewVille.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

					public boolean onItemLongClick(AdapterView<?> arg0, View v,int index, long arg3) {
						Ville ville = bdd.getVilleByID(index + 1);
						
						Uri localisationVille = Uri.parse("geo:"
								+ String.valueOf(ville.getLatitude()) + ","
								+ String.valueOf(ville.getLongitude())
								+ Constantes.ZOOM_GOOGLE_MAP);
						Intent intent = new Intent(Intent.ACTION_VIEW,
								localisationVille);
						startActivity(intent);
						return true;
					}
				});

		/**
		 * Application d'un listener lors d'un clic sur une ville.
		 * Ce clic permet de passer sur l'activity SliderActivity afin de choisir une distance par rapporté la ville choisie.
		 */
		listeViewVille.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long row) {
				Intent sliderIntent = new Intent(MainActivity.this,
						SliderActivity.class);
				sliderIntent.putExtra("idVille", position + 1);
				startActivity(sliderIntent);
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		bdd.close();
	}

	@Override
	public void onPause() {
		super.onPause();
		bdd.close();
	}

	@Override
	public void onResume() {
		super.onResume();
		bdd.open();
	}

	/**
	 * Fonction permettant de remplir la base de donn�es.
	 */
	public void remplirBDD() {
		Ville v1 = new Ville("Lille", 50.6292500, 3.0572560);
		Ville v2 = new Ville("Boulogne sur Mer", 50.7252310, 1.6133340);
		Ville v3 = new Ville("Calais", 50.9512900, 1.8586860);
		Ville v4 = new Ville("Dunkerque", 51.0345600, 2.3752019);
		Ville v5 = new Ville("Le Touquet", 50.5212760, 1.5906750);
		Ville v6 = new Ville("Roubaix", 50.6927049, 3.1778470);
		Ville v7 = new Ville("Tourcoing", 50.7249930, 3.1620700);
		Ville v8 = new Ville("Valenciennes", 50.3571130, 3.5183320);
		Ville v9 = new Ville("Lens", 50.4289300, 2.8318300);
		Ville v10 = new Ville("Maubeuge", 50.2802280, 3.9674000);
		Ville v11 = new Ville("Arras", 50.2910020, 2.7775350);
		Ville v12 = new Ville("Saint Omer", 50.7501150, 2.2522080);

		bdd.insertVille(v1);
		bdd.insertVille(v2);
		bdd.insertVille(v3);
		bdd.insertVille(v4);
		bdd.insertVille(v5);
		bdd.insertVille(v6);
		bdd.insertVille(v7);
		bdd.insertVille(v8);
		bdd.insertVille(v9);
		bdd.insertVille(v10);
		bdd.insertVille(v11);
		bdd.insertVille(v12);
	}
}
