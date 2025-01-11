package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Parrieur extends Personnage{
  //reduit les chances de recevoir des degats;mais si il perds ses chances;
  //il recoit ces degats multipliés depandants de la reduction

public Parrieur(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}

private int maledictionduparrieur = 1;
private float chanceduparrieur = 1;

@Override
public void recevoirDegats(int degats) {
    if(0<bouclier){
        bouclier--;
        System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant:" + bouclier + " !");
        malchanceduparrieur(degats/2);
    }else{
        malchanceduparrieur(degats);
    }
    if(lien != this){
        if(lien.estVivant()){
            lien.encaisserdegats(degats);
        }
    }
}

@Override
public void degatsdefindetour(){
    if(0<empoisoné || chanceduparrieur <= 0.0625){
        System.out.println(getNom() + " a recu des dégats de fin de tour !");
        int ttl = 0;
        if(0<empoisoné){
            ttl+=degatspoison;
            empoisoné --;
        }
        if(chanceduparrieur <= 0.0625f){
            ttl+=20;
        }
        encaisserdegats(ttl);
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
    System.out.println(nom + " prends ses chances !");
    chanceduparrieur = chanceduparrieur/2;
    maledictionduparrieur = maledictionduparrieur*2;
}

public void malchanceduparrieur(int degats){
    if(chanceduparrieur < 1){
        if(chanceduparrieur < Math.random()){
            System.out.println("la chance du parrieur a protegé " + getNom() + " !");
        }else{
            System.out.println(getNom() + "as perdu sa chance du parrieur !");
            encaisserdegats(degats*maledictionduparrieur);
        }
    }else{
        encaisserdegats(degats);
    }
}
}
