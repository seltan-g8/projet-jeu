package personnages;
import java.util.ArrayList;

import autre.Personnage;

public class Roi extends Personnage{
    //aprés lactivation de sa competence il reduit ses chances de recevoir des degats;
    //avec un facteur de charge;si la chance est de zero il receveras des degats a chaque tour

public Roi(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 70, 12);
}

private float chancedégat = 1;

@Override
public void recevoirDegats(int degats) {
    if(chancedégat < 1){
        if(Math.random() < chancedégat) {
        if(0<bouclier){
            bouclier--;
            System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant: " + bouclier + " !");
            encaisserdegats(degats/2);
        }else{
            encaisserdegats(degats);
        }
        chancedégat = 1;  
    }else{
        System.out.println(nom + " n'as pas recu de dégats grace a sa compétence du Roi. Points de vie restants : " + pointsDeVie);
    }
    }else{
        if(0<bouclier){
            bouclier--;
            System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant: " + bouclier + " !");
            encaisserdegats(degats/2);
        }else{
            encaisserdegats(degats);
        }
    }
    if(lien != this){
        if(lien.estVivant()){
            lien.encaisserdegats(degats);
        }
    }
}

@Override
public void degatsdefindetour(){
    if(chancedégat == 0 || 0<empoisoné){
        System.out.println(getNom() + " a recu des dégats de fin de tour !");
        int ttl = 0;
        if(chancedégat <= 0){
            ttl+=20;
        }
        if(0<empoisoné){
            empoisoné --;
            ttl+=degatspoison;
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
    System.out.println(nom + " utilise sa compétence !");
    if(0.2f <= chancedégat){
        chancedégat -= 0.2;
    }
}
}
