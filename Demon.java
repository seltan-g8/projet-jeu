package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Demon extends Personnage{
  //appliques le sort "pécher" sur la cible et lorcequil ataque une prochaine fois il vole les pv de cette cible par un facteur
  //empilable par autres personnages de catégorie demon
public Demon(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    if(cible.getPeché() == this){
        System.err.println(nom + " exorcise " + cible.getNom());
        cible.recevoirDegats(degats*cible.getGravitée());
        recevoirPv(2*degats*cible.getGravitée());
        derniersdegats = degats*cible.getGravitée();
        cible.setPeché(null);
        cible.setGravitée(1);
    }else{
        cible.recevoirDegats(degats);
        derniersdegats = degats;
    }
    
}

@Override
public void utiliserCompetence(Personnage cible) {
    System.out.println(nom + " mets " + cible.getNom() + " sous peché !");
    cible.setPeché(this);
    cible.setGravitée(cible.getGravitée()+1);
}
}
