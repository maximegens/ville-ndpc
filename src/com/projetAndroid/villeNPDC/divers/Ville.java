package com.projetAndroid.villeNPDC.divers;

/**
 * Classe Ville, cette classe représente une ville.
 * @author Maxime
 *
 */
public class Ville {

	private int id;
	private String nom;
	private double latitude;
	private double longitude;
	
	public Ville(){}
	
	/**
	 * Constructeur
	 * @param nom le nom de la ville
	 * @param latitude sa latitude
	 * @param longitude sa longitude
	 */
	public Ville(String nom, double latitude, double longitude) {
		super();
		this.nom = nom;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String toString() {
		return "Ville [id=" + id + ", nom=" + nom + ", latitude=" + latitude
				+ ", Longitude=" + longitude + "]";
	}
	
}
