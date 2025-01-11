package personnages;

import java.util.ArrayList;

import autre.Personnage;

public class Rachat extends Personnage{
  //"rachéte" des pv en echangeant avec celles de sa cible si elle en a plus 
  // sinon recevoir la diference en pv

public Rachat(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    cible.recevoirDegats(degats);
    derniersdegats = degats;
}

@Override
public void utiliserCompetence(Personnage cible) {
    System.out.println(nom + " utilise sa compétence sur " + cible.getNom());
    int pv=pointsDeVie;
    if(pointsDeVie<cible.getPv()){
        recevoirPv(cible.getPv()-pointsDeVie);
        cible.recevoirDegats(cible.getPv()-pv);
    }else{
        recevoirPv(pointsDeVie-cible.getPv());
    }
}
}
