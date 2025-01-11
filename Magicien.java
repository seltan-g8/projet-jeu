package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Magicien extends Personnage{
  //aprées lactivation de son abilitée,les degats subits sont renvoyés au personnage avec le plus de pv
  //les degats renvoyés ont un facteur de charge 
  
public Magicien(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}

private boolean tourdemagie = false;

@Override
public void recevoirDegats(int degats) {
    if(!tourdemagie){
        if(0<bouclier){
            bouclier--;
            System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant:" + bouclier + " !");
            encaisserdegats(degats/2);
        }else{
            encaisserdegats(degats);
        }	
    }else{
        findeperformance();
    }
    if(lien != this){
        if(lien.estVivant()){
            lien.encaisserdegats(degats);
        }
    }
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
    System.out.println(nom + " débute son tour de magie !");
    if(!tourdemagie){
        tourdemagie = true;
    }
}

private void findeperformance(){
    System.err.println(getNom() + "a terminé son tour !. Dégats renvoyés au personnage avec le plus de points de vie !");
    tourdemagie = false;
    Personnage maxhp = personnages.get(0);
    for(Personnage p : personnages){
            if(maxhp.getPv() < p.getPv() ){
                    maxhp = p;
            }
    }
    maxhp.setDerniereattaque(this);
    maxhp.recevoirDegats(degats);
}
}
