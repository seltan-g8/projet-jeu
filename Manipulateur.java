package personnages;
import java.util.ArrayList;
import java.util.Random;

import autre.Personnage;

public class Manipulateur extends Personnage{
  //force la cible a utiliser sa compétence sur un personnage random
  //PS:cette catégorie a été inspirée d'un prototype défectueux de la catégorie mimiqueur

public Manipulateur(ArrayList<Personnage> personnages, String nom) {
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
    Random random = new Random();
    Personnage p = cible;
    while (cible.getNom().equals(p.getNom()) || nom == p.getNom()){
        p = personnages.get(random.nextInt(personnages.size()));
    }
    System.out.println(nom + " a forcé " + cible.getNom() + " a utiliser sa compétence sur " + p.getNom() + " !");
    cible.utiliserCompetence(p);
}
}
