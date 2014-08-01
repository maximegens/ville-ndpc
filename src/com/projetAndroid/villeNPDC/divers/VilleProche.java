package com.projetAndroid.villeNPDC.divers;

/**
 * Classe VilleProche, cette classe représente et associe deux villes qui sont proche de X km
 * @author Maxime
 *
 */
public class VilleProche {

	Ville VilleDeDepart;
	Ville VilleArrive;
	double distanceEnKm;
	
	/**
	 * Constructeur
	 * @param villeDeDepart La ville de départ
	 * @param villeArrive La ville d'arrivée
	 * @param distanceEnKm La distance en KM entre les deux villes
	 */
	public VilleProche(Ville villeDeDepart, Ville villeArrive,
			double distanceEnKm) {
		super();
		VilleDeDepart = villeDeDepart;
		VilleArrive = villeArrive;
		this.distanceEnKm = distanceEnKm;
	}
	
	public Ville getVilleDeDepart() {
		return VilleDeDepart;
	}
	public void setVilleDeDepart(Ville villeDeDepart) {
		VilleDeDepart = villeDeDepart;
	}
	public Ville getVilleArrive() {
		return VilleArrive;
	}
	public void setVilleArrive(Ville villeArrive) {
		VilleArrive = villeArrive;
	}
	public double getDistanceEnKm() {
		return distanceEnKm;
	}
	public void setDistanceEnKm(double distanceEnKm) {
		this.distanceEnKm = distanceEnKm;
	}
}
