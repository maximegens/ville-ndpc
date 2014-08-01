package com.projetAndroid.villeNPDC.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projetAndroid.villeNPDC.R;
import com.projetAndroid.villeNPDC.divers.Constantes;
import com.projetAndroid.villeNPDC.divers.VilleProche;

/**
 * Adaptateur permettant de mettre en forme et d'afficher chaque item, dans un
 * style personnalisé, de la listeView pour la page d'affichage des villes les
 * plus proches de la ville sélectionné précédemment.
 * 
 * @author Maxime
 * 
 */
public class VilleProcheAdapter extends BaseAdapter {

	List<VilleProche> lesVillesProchesAvecDistance;
	LayoutInflater inflater;

	String masque;
	DecimalFormat decimalFormat;

	public VilleProcheAdapter(Context context,
			List<VilleProche> lesVillesProches) {
		inflater = LayoutInflater.from(context);
		lesVillesProchesAvecDistance = lesVillesProches;
		masque = new String(Constantes.FORMAT_ARRONDI);
		decimalFormat = new DecimalFormat(masque);
	}

	@Override
	public int getCount() {

		return lesVillesProchesAvecDistance.size();
	}

	@Override
	public Object getItem(int position) {

		return lesVillesProchesAvecDistance.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		TextView villeNomProche;
		TextView villeDeDepart;
		TextView villeDistance;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.liste_view_ville_proche,
					null);

			holder.villeNomProche = (TextView) convertView
					.findViewById(R.id.villeNomProche);
			holder.villeDeDepart = (TextView) convertView
					.findViewById(R.id.villeDeDepart);
			holder.villeDistance = (TextView) convertView
					.findViewById(R.id.villeDistance);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.villeNomProche.setText(lesVillesProchesAvecDistance.get(position).getVilleArrive().getNom());
		holder.villeDeDepart.setText("Depuis : "+ lesVillesProchesAvecDistance.get(position).getVilleDeDepart().getNom());
		holder.villeDistance.setText("Distance : "+decimalFormat.format(lesVillesProchesAvecDistance.get(position).getDistanceEnKm()) + " km");

		return convertView;

	}
}
