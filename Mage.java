package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Mage extends Personnage {
    //lance un sort de foudre sur un adversaire le forcant a passer son tour et lui infligeant des degats au prochain tour

public Mage(ArrayList<Personnage> personnages, String nom) {
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
    System.out.println(nom + " invoque une tempête magique sur: " + cible.getNom() + " !");
    cible.setEtourdie(2);
    cible.empoisoné(1,10);
}
}
