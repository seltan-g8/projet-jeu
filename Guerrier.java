package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Guerrier extends Personnage {
    //auguementes les degats de la pochaine attaque par un facteur

public Guerrier(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 100, 10);
}

private int facteurdecharge = 1;

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    cible.recevoirDegats(degats*facteurdecharge);
    derniersdegats = degats*facteurdecharge;
    facteurdecharge = 1;
}

@Override
public void utiliserCompetence(Personnage cible) {
    System.out.println(nom + " utilise sa compétence spéciale : Coup de rage !");
    facteurdecharge = facteurdecharge*2;
}
}
