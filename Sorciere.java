package personnages;
import java.util.ArrayList;
import java.util.Random;

import autre.Personnage;

public class Sorciere extends Personnage{
  //mets le sort "lien d'ame" sur 2 personnages differents (lun dentre eux est choisis aleatoirement et peut etre soi);
  //lors des 5 prochains tours;si l'un des personages ciblés recoits des degats;lautre recoits les memes
  //(vis vers ca pour laugementation de pv)
public Sorciere(ArrayList<Personnage> personnages, String nom) {
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
    Personnage lié = cible;
    if(cible.getlien() == cible){
        while (cible.getNom().equals(lié.getNom()) || lié.getlien() != lié){
            lié = personnages.get(random.nextInt(personnages.size()));
        }
        System.out.println(nom + " a relié " + cible.getNom() + " et " + lié.getNom() + " !");
        lié.setlien(cible);
        cible.setlien(lié);
    }else{
        System.out.println(cible.getNom() + " est déja lié a : " + cible.getlien().getNom() + " !");
    }
}
}
