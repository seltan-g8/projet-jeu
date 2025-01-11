package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Mime extends Personnage{
  //competence lui permets de bloquer les 3 prochains tours de la cible

public Mime(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);;
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    cible.recevoirDegats(degats);
    derniersdegats = degats;
}

@Override
public void utiliserCompetence(Personnage cible) {
    System.out.println(nom + " mets en sourdine " + cible.getNom() + "!");
    cible.setEtourdie(3);
}
}
