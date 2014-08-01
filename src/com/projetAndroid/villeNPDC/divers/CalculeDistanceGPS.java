package com.projetAndroid.villeNPDC.divers;

/**
 * Classe CalculeDistanceGPS, permet de calculer la distance entre deux villes grâce à leurs coordonnées GPS.
 * @author Maxime
 *
 */
public class CalculeDistanceGPS {

	/**
	 * @param args
	 */
	double latitudePointA;
	double longitudePointA;
	double latitudePointB;
	double longitudePointB;
	int r = 6366;
	
	
	public CalculeDistanceGPS() {
		super();
	}

	public double calculeDistanceEnKM(double latitudePointA, double longitudePointA,double latitudePointB, double longitudePointB){
		
		this.latitudePointA = latitudePointA;
		this.longitudePointA = longitudePointA;
		this.latitudePointB = latitudePointB;
		this.longitudePointB = longitudePointB;
		
		double latA = Math.toRadians(latitudePointA);
		double lonA = Math.toRadians(longitudePointA);
		double latB = Math.toRadians(latitudePointB);
		double lonB = Math.toRadians(longitudePointB);
		double distance = distanceVolOiseauEntre2PointsAvecPrecision(latA,lonA, latB, lonB);
		return distance * r;
	}
	
	/**
	 * Distance entre 2 points GPS
	 * 
	 * La distance mesurée le long d'un arc de grand cercle entre deux points
	 * dont on connait les coordonnées {lat1,lon1} et {lat2,lon2} est donnée par
	 * : d=acos(sin(lat1)*sin(lat2)+cos(lat1)*cos(lat2)*cos(lon1-lon2)) Le tout
	 * * 6366 pour l'avoir en km
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double distanceVolOiseauEntre2PointsSansPrecision(
			double lat1, double lon1, double lat2, double lon2) {

		// d=acos(sin(lat1)*sin(lat2)+cos(lat1)*cos(lat2)*cos(lon1-lon2))

		return

		Math.acos(

		Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2)
				* Math.cos(lon1 - lon2));

	}

	/**
	 * Distance entre 2 points GPS
	 * 
	 * La distance mesurée le long d'un arc de grand cercle entre deux points
	 * dont on connait les coordonnées {lat1,lon1} et {lat2,lon2} est donnée par
	 * : La formule, mathématiquement équivalente, mais moins sujette aux
	 * erreurs d'arrondis pour les courtes distances est : *
	 * d=2*asin(sqrt((sin((lat1-lat2)/2))^2 + cos(lat1)*cos(lat2)*(sin((lon1-
	 * lon2)/2))^2)) Le tout * 6366 pour l'avoir en km
	 * 
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double distanceVolOiseauEntre2PointsAvecPrecision(
			double lat1, double lon1, double lat2, double lon2) {

		// d=2*asin(sqrt((sin((lat1-lat2)/2))^2 +
		// cos(lat1)*cos(lat2)*(sin((lon1- lon2)/2))^2))

		return

		2 * Math.asin(

		Math.sqrt(

		Math.pow((Math.sin((lat1 - lat2) / 2)), 2)

		+

		Math.cos(lat1) * Math.cos(lat2) *

		(Math.pow(Math.sin(((lon1 - lon2) / 2)), 2))

		)

		);

	}

}