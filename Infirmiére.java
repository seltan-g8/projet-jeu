package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Infirmiére extends Personnage{
  //competence permets dauguementer le nombre de pv
  //si la competence est utiliser 3 fois de suite, le personnage est instantanement eliminé

public Infirmiére(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}

private int compteurdesoin = 0; 

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    cible.recevoirDegats(degats);
    derniersdegats = degats;
    compteurdesoin = 0; 
}

@Override
public void utiliserCompetence(Personnage cible) {
    if(compteurdesoin < 3){
        System.out.println(nom + " se soigne !");
        recevoirPv(40);
        compteurdesoin++;
    }else{
        System.out.println(nom + " a utilisé sa compétence 3 fois de suite et est éliminé !");
        encaisserdegats(150);
    }
}
}

