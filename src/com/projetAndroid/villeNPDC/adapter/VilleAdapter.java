package com.projetAndroid.villeNPDC.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projetAndroid.villeNPDC.R;
import com.projetAndroid.villeNPDC.divers.Ville;

/**
 * Adaptateur permettant de mettre en forme et d'afficher chaque item, dans un style personnalisé, de la listeView pour la page d'accueil.
 * @author Maxime
 *
 */
public class VilleAdapter extends BaseAdapter {

	List<Ville> lesvilles = new ArrayList<Ville>();
	
	LayoutInflater inflater;

	public VilleAdapter(Context context,List<Ville> lesvilles) {
		inflater = LayoutInflater.from(context);
		this.lesvilles = lesvilles;
	}
	@Override
	public int getCount() {
		
		return lesvilles.size();
	}

	@Override
	public Object getItem(int position) {
	
		return lesvilles.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		TextView villeNom;
		TextView villeLatitude;
		TextView villeLongitude;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.liste_view_ville, null);

			holder.villeNom = (TextView)convertView.findViewById(R.id.villeNom);
			holder.villeLatitude = (TextView)convertView.findViewById(R.id.villeLatitude);
			holder.villeLongitude = (TextView)convertView.findViewById(R.id.villeLongitude);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.villeNom.setText(lesvilles.get(position).getNom());
		holder.villeLatitude.setText("Latitude : "+String.valueOf(lesvilles.get(position).getLatitude()));
		holder.villeLongitude.setText("Longitude : "+String.valueOf(lesvilles.get(position).getLongitude()));
		return convertView;
	}
}
