package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Voleur extends Personnage {
    //passive permets de mettre des coups crtitiques ,si il perds cette chance il perds des pv
    //competence auguemente la chance du coup crit

public Voleur(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 70, 12);
}

private float chancecrit = 0.2f;

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    if (Math.random() < chancecrit) {
        System.out.println(nom + " inflige un coup critique !");
        cible.recevoirDegats(2*degats);
        derniersdegats = 2*degats;
    }else{
        cible.recevoirDegats(degats);
        derniersdegats = degats;
        recevoirDegats(10);
    }
}

@Override
public void utiliserCompetence(Personnage cible) {
    System.out.println(nom + " inflige un coup critique !");
    chancecrit += 0.2;
}
}
