package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Juge extends Personnage{
  //retourne les derniers degats infligés par la cible a elle meme

public Juge(ArrayList<Personnage> personnages, String nom) {
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
    System.out.println(nom + " remets l'ordre a " + cible.getNom() + " !");
    cible.recevoirDegats(cible.derniersdegats());
}
}
