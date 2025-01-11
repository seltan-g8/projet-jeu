package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Chimiste extends Personnage{
    //empoisonne la cible

public Chimiste(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 70, 12);
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
    System.out.println(nom + " empoisonne " + cible.getNom() + " !");
    cible.empoisoné(5,15);
}
}