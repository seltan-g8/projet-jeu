package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Fou extends Personnage{
    //la competence change en fonction des Pv

public Fou(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}
  
@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    cible.recevoirDegats(degats);
}
  
@Override
public void utiliserCompetence(Personnage cible) {
    System.out.println(nom + " a ");
    if(100 < pointsDeVie){
        System.out.println("plus de 66% points de vie, il mets des degats élevés sur : " + cible.getNom() + " !");
        cible.recevoirDegats(60);
        derniersdegats = 60;
    }else if(50 < pointsDeVie){
        System.out.println("plus de 33% points de vie, il empoisonne : " + cible.getNom() + " !");
        cible.empoisoné(2,10);
    }else{
        System.err.println("moin de 33% de points de vie, il en vole 10% de chez : " + cible.getNom() + " !");
        cible.recevoirDegats(cible.getPv()/10);
        recevoirPv(cible.getPv()/10);
    }
}
}
