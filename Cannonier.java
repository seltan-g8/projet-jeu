package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class  Cannonier extends Personnage{
  //lors de lactivation de son abilitée il gagne une bouclier ;
  //lors de lactivation de labilité pour la 3eme fois il tire avec son bouclier infligeant des dégats lourds

public Cannonier(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}


private int chargecannon = 0;

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    cible.recevoirDegats(degats);
    derniersdegats = degats;
}

@Override
public void utiliserCompetence(Personnage cible) {
    if(chargecannon < 3){
        System.out.println(nom + " arme son cannon !");
        chargecannon++;
        bouclier +=1;
        if(2 < bouclier){
            bouclier = 2;
        }
        System.out.println("bouclier restant:" + bouclier + " !");
    }else{
        System.out.println(nom + " tire sur " + cible.getNom() + " !");
        cible.recevoirDegats(70);
        chargecannon = 0;
    }
}
}
