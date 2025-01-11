package personnages;

import java.util.ArrayList;

import autre.Personnage;

public class Bouffon extends Personnage{
    //competence aléatoire

public Bouffon(ArrayList<Personnage> personnages, String nom) {
    super(personnages,nom, 100, 10);
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
    System.out.println(nom + " lance les dés,il tombe sur le compétence :");
    double chance = Math.random();
    if(chance < 0.1){
        System.out.println("explosion!");
        cible.recevoirDegats(60);
        derniersdegats = 60;
    }else if(chance < 0.3){
        System.out.println("soin!");
        recevoirPv(30);
    }else if(chance < 0.5){
        System.out.println("coup de cloche sur:" + cible.getNom() + " !");
        cible.setEtourdie(1);
    }else if(chance < 0.7){
        System.out.println("effet de foule!"); 
        for (Personnage p : personnages) {
            if (!p.getNom().equals(getNom())){
                p.recevoirDegats(15);
            }
        }
        derniersdegats = 15;
    }else{
        System.out.println("morsure de rat sur:" + cible.getNom() + " !");
        cible.empoisoné(5,15);
    }
}
}
