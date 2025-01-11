package autre;
import java.util.ArrayList;

public abstract class Personnage implements Attaquable {
protected ArrayList<Personnage> personnages;
protected String nom;
protected int pointsDeVie;
protected int degats;
protected int bouclier;
protected int derniersdegats;
protected int étourdie;
protected int empoisoné;
protected int degatspoison;
protected int gravitée;
protected Personnage peché;
protected Personnage lien;
protected Personnage derniereataquepar;

public Personnage(ArrayList<Personnage> personnages, String nom, int pointsDeVie, int degats) {
	this.nom = nom;
	this.personnages = personnages;
	this.pointsDeVie = pointsDeVie;
	this.degats = degats;
	this.bouclier = 0;
	this.derniersdegats = 0;
	this.étourdie = 0;
	this.empoisoné = 0;
	this.degatspoison = 0;
	this.gravitée = 1;
	this.peché = null;
	this.lien = this;
	this.derniereataquepar = null;
}

public void encaisserdegats(int degats){
	pointsDeVie -= degats;
	System.out.println(nom + " a reçu " + degats + " points de dégâts. Points de vie restants : " + pointsDeVie); 
	if(!estVivant()){
		System.out.println(getNom() + " est éliminé !");
	}
}

public void recevoirDegats(int degats) {
	if(0<bouclier){
		bouclier--;
		System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant: " + bouclier + " !");
		encaisserdegats(degats/2);
	}else{
		encaisserdegats(degats);
	}
	if(lien != this){
		if(lien.estVivant()){
			lien.encaisserdegats(degats);
		}
	}
}

public void recevoirPvDirect(int Pv){
	pointsDeVie += Pv;
	if(150 < pointsDeVie){
		pointsDeVie = 150;
	}
	System.out.println(nom + " a reçu " + Pv + " points de vie. Points de vie restants : " + pointsDeVie);
}

public void recevoirPv(int Pv){
	recevoirPvDirect(Pv);
		if(lien != this){
			if(lien.estVivant()){
				lien.recevoirPvDirect(Pv);
			}
	}
}

public void setEtourdie(int duration) {
	if(étourdie < duration){
		étourdie = duration;
	}
}

public int getEtourdie(){
	return étourdie;
}

public void consommerétourdis(){
	étourdie--;
}

public void empoisoné(int durée,int concentration) {
	empoisoné = durée;
	degatspoison = concentration;
}

public void degatsdefindetour(){
	if(0<empoisoné){
		empoisoné --;
		encaisserdegats(degatspoison);
	}
}

public void setDerniereattaque(Personnage p){
	derniereataquepar = p;
}

public void setlien(Personnage p){
	lien = p;
}

public Personnage getlien(){
	return lien;
}

public int getPv(){
	return pointsDeVie;
}

public int derniersdegats(){
	return derniersdegats;
}

public void setPeché(Personnage p){
	peché = p;
}

public Personnage getPeché(){
	return peché;
}

public void setGravitée(int g){
	gravitée = g;
}

public int getGravitée(){
	return gravitée;
}

public boolean estVivant() {
	return pointsDeVie > 0;
}

public String getNom() {
	return nom;
}
}
