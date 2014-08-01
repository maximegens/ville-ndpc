package com.projetAndroid.villeNPDC.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.projetAndroid.villeNPDC.R;
import com.projetAndroid.villeNPDC.bdd.BaseDeDonneeDAO;
import com.projetAndroid.villeNPDC.divers.Constantes;

/**
 * Classe SliderActivity, cette classe correspond à l'affichage du slider
 * permettant de choisir la distance par rapporté la ville  sélectionner sur
 * l'écran d'accueil.
 * 
 * @author Maxime
 * 
 */
public class SliderActivity extends Activity {

	public SeekBar sliderDistance;
	public TextView distanceText;
	public TextView descriptionSlider;
	public Button validerDistance;
	int distance;
	BaseDeDonneeDAO bdd;
	final int incrementation = Constantes.INCREMENTATION_SLIDER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slider);
		Bundle b = getIntent().getExtras();
		final int idVille = b.getInt("idVille");

		sliderDistance = (SeekBar) findViewById(R.id.sliderDistanceVille);
		distanceText = (TextView) findViewById(R.id.distanceVille);
		descriptionSlider = (TextView) findViewById(R.id.descriptionSlider);
		validerDistance = (Button) findViewById(R.id.buttonValiderDistance);

		bdd = new BaseDeDonneeDAO(this);
		bdd.open();
		String nomVilleChoisi = bdd.getVilleByID(idVille).getNom();
		bdd.close();
		
		/** modification de la description pour afficher le nom de la ville choisie **/
		descriptionSlider.setText(getResources().getString(R.string.description_slider) +" de "+nomVilleChoisi);
		
		sliderDistance.setMax(200);
		sliderDistance.incrementProgressBy(10);
		sliderDistance.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						progress = ((int) Math.round(progress / incrementation))
								* incrementation;
						seekBar.setProgress(progress);
						distanceText.setText(String.valueOf(progress)
								+ Constantes.KM_SLIDER);
						distance = progress;
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
					}

					public void onStopTrackingTouch(SeekBar seekBar) {
					}
				});

		validerDistance.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent sliderIntent = new Intent(SliderActivity.this,
						VisualisationVilleProcheActivity.class);
				sliderIntent.putExtra("idVille", idVille);
				sliderIntent.putExtra("distance", distance);
				startActivity(sliderIntent);
				finish();
			}
		});
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}
}
