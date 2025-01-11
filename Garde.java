package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Garde extends Personnage{
  //lui donne un bouclier qui limmunise contre les 3 prochaines attaques

public Garde(ArrayList<Personnage> personnages, String nom) {
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
    System.out.println(nom + " se protége !");
    bouclier +=1;
    if(2 < bouclier){
        bouclier = 2;
    }
    System.out.println("bouclier restant:" + bouclier + " !");
}
}
