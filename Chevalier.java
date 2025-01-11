package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Chevalier  extends Personnage{
//aprés lactivation de sa competence lors du prochain tour si il subit une attaque(et pas une competence) 
//et que celle si ne le tues pas,il renvoi les degats par un facteur de charge lors de son prochain tour 

public Chevalier(ArrayList<Personnage> personnages, String nom) {
  super(personnages, nom, 100, 10);
}

private int facteurdecharge = 1;
private boolean chargeduchevalier = false;

@Override
public void recevoirDegats(int degats) {
    if(0<bouclier){
        bouclier--;
        System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant:" + bouclier + " !");
        encaisserdegats(degats/2);
        benedictionduchevalier();
    }else{
        encaisserdegats(degats);
        benedictionduchevalier();
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
public void utiliserCompetence(Personnage cible){
    facteurdecharge = facteurdecharge*2;
    System.out.println(nom + " active sa compétence de retour,nouveau facteur:" + facteurdecharge + " !");
    if (chargeduchevalier){
        chargeduchevalier = true;
    }
}

private void benedictionduchevalier(){
    if(chargeduchevalier && 1 < facteurdecharge && derniereataquepar != null){
        chargeduchevalier = false;
        derniereataquepar.recevoirDegats(derniereataquepar.derniersdegats()*facteurdecharge);
        facteurdecharge = 1;
    }
}
}

