package personnages;
import java.util.ArrayList;
import java.util.Random;

import autre.Personnage;

public class Mimiqueur extends Personnage{
    //copie la copetence dun personnage au choix pendat un et seul tour
    //PS:cette classe ma forcé a changer beacoup de code pour son optimisation et la balance du jeu
    //par exemple la possibilitée de stacker les facteurs de charge qui peut -backfire- si il copie un roi
    //jai opté pour le stack car copier des competences comme celle du voleur chaque tour peut etre embettant mais jai
    //balancé avec le backfire du facteurdecharge

public Mimiqueur(ArrayList<Personnage> personnages, String nom) {
    super(personnages, nom, 80, 15);
}

private Personnage copié;
private boolean copie = false;
private int compteurdesoin = 0;
private int facteurdecharge = 1;
private int chargecannon = 0;
private int maledictionduparrieur = 1;
private float chanceduparrieur = 1;
private float chancedégat = 1;
private float chancecrit = 0;
private boolean tourdemagie = false;
private boolean chargeduchevalier = false;

@Override
public void recevoirDegats(int degats) {
    if(!tourdemagie){
        if(chancedégat < 1){  
            if(Math.random() < chancedégat) {
                if(0<bouclier){
                    bouclier--;
                    System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant: " + bouclier + " !");
                    malchanceduparrieur(degats/2);
                    benedictionduchevalier();
                }else{
                    malchanceduparrieur(degats);
                    benedictionduchevalier();
                }
            }else{
                System.out.println(nom + " n'as pas recu de dégats grace a sa compétence du Roi. Points de vie restants : " + pointsDeVie);
            }	
        }else{
            if(0<bouclier){
                bouclier--;
                System.out.println(nom + " a sacrifier un bouclier et a gardé cette attaque,bouclier restant: " + bouclier + " !");
                malchanceduparrieur(degats/2);
                benedictionduchevalier();
            }else{
                malchanceduparrieur(degats);
                benedictionduchevalier();
            }
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
public void degatsdefindetour(){
    if(chancedégat == 0 || 0<empoisoné || chanceduparrieur <= 0.0625){
        System.out.println(getNom() + " a recu des dégats de fin de tour !");
        int ttl = 0;
        if(chancedégat <= 0){
            ttl+=20;
        }
        if(0<empoisoné){
            empoisoné --;
            ttl+=degatspoison;
        }
        if(chanceduparrieur <= 0.0625f){
            ttl+=20;
        }
        encaisserdegats(ttl);
    }
}

private void malchanceduparrieur(int degats){
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

private void benedictionduchevalier(){
    if(chargeduchevalier && 1 < facteurdecharge && derniereataquepar != null){
        chargeduchevalier = false;
        derniereataquepar.recevoirDegats(derniereataquepar.derniersdegats()*facteurdecharge);
        facteurdecharge = 1;
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

@Override
public void attaquer(Personnage cible) {
    cible.setDerniereattaque(this);
    System.out.println(nom + " attaque " + cible.getNom() + " !");
    if(0 < chancecrit){
        if (Math.random() < chancecrit) {
            System.out.println(nom + " inflige un coup critique !");
            if(cible.getPeché() == this){
                System.err.println(nom + " exorcise " + cible.getNom());
                cible.recevoirDegats(2*degats*cible.getGravitée());
                recevoirPv(2*degats*cible.getGravitée());
                derniersdegats = 2*degats*cible.getGravitée();
                cible.setPeché(null);
                cible.setGravitée(1);
            }else{
                cible.recevoirDegats(2*degats);
                derniersdegats = 2*degats;
            }
        }else{
            if(cible.getPeché() == this){
                System.err.println(nom + " exorcise " + cible.getNom());
                cible.recevoirDegats(degats*cible.getGravitée());
                recevoirPv(degats*cible.getGravitée());
                derniersdegats = degats*cible.getGravitée();
                cible.setPeché(null);
                cible.setGravitée(1);
            }else{
                cible.recevoirDegats(degats);
                derniersdegats = degats;
            }
            recevoirDegats(10);
        }
    }else{
        if(cible.getPeché() == this){
            System.err.println(nom + " exorcise " + cible.getNom());
            cible.recevoirDegats(degats*cible.getGravitée());
            recevoirPv(degats*cible.getGravitée());
            derniersdegats = degats*cible.getGravitée();
            cible.setPeché(null);
            cible.setGravitée(1);
        }else{
            cible.recevoirDegats(degats);
            derniersdegats = degats;
        }
    }
    facteurdecharge = 1;
    compteurdesoin = 0; 
}

@Override
public void utiliserCompetence(Personnage cible) {
    if(copie){
        UtiliserlacompetenceCopiée(cible);
        copie = false;
    }else{
        if(!(cible instanceof Mimiqueur)){
            System.out.println(nom + " a copié la compétence de " + cible.getNom() + "!");
            facteurdecharge = 1;
            copié = cible;
            copie= true;
        }else{
            System.out.println(getNom() + "a copié" + cible.getNom() + "il ne copie rien alors !");
        }
    }
}

private void UtiliserlacompetenceCopiée(Personnage cible){
    if(copié instanceof Mage){
        System.out.println(nom + " invoque une tempête magique sur: " + cible.getNom() + " !");
        cible.setEtourdie(2);
        cible.empoisoné(1,10);
    }
    if(copié instanceof Voleur){
        System.out.println(nom + " inflige un coup critique !");
        chancecrit += 0.2;
    }
    if(copié instanceof Roi){
        System.out.println(nom + " utilise sa compétence !");
        if(0.2f <= chancedégat){
            chancedégat -= 0.2;
        }
    }
    if(copié instanceof Bouffon){
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
            System.out.println("coup de cloche sur:" + cible.getNom() +" !");
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
    if(copié instanceof Chevalier){
        facteurdecharge = facteurdecharge*2;
        System.out.println(nom + " active sa compétence de retour,nouveau facteur:" + facteurdecharge + " !");
        if (!chargeduchevalier){
            chargeduchevalier = true;
        }
    }
    if(copié instanceof Chimiste){
        System.out.println(nom + " empoisonne " + cible.getNom() + " !");
        cible.empoisoné(5,15);
    }
    if(copié instanceof Fou){
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
    if(copié instanceof Guerrier){
        System.out.println(nom + " utilise sa compétence spéciale : Coup de rage !");
        facteurdecharge = facteurdecharge*2;
    }
    if(copié instanceof Mime){
        System.out.println(nom + " mets en sourdine " + cible.getNom() + "!");
        cible.setEtourdie(3);
    }
    if(copié instanceof Infirmiére){
        if(compteurdesoin < 3){
            System.out.println(nom + " se soigne !");
            recevoirPv(40);
            compteurdesoin++;
        }else{
            System.out.println(nom + " a utilisé sa compétence 3 fois de suite et est éliminé !");
            encaisserdegats(150);
        }
    }
    if(copié instanceof Juge){
        System.out.println(nom + " remets l'ordre a " + cible.getNom() + " !");
        cible.recevoirDegats(cible.derniersdegats());
    }
    if(copié instanceof Garde){
        System.out.println(nom + " se protége !");
        bouclier +=1;
        if(2 < bouclier){
            bouclier = 2;
        }
        System.out.println("bouclier restant:" + bouclier + " !");
    }
    if(copié instanceof Parrieur){
        System.out.println(nom + " prends ses chances !");
        chanceduparrieur = chanceduparrieur/2;
        maledictionduparrieur = maledictionduparrieur*2;
    }
    if(copié instanceof Magicien){
        System.out.println(nom + " débute son tour de magie !");
        if(!tourdemagie){
            tourdemagie = true;
        }
    }
    if(copié instanceof Demon){
        System.out.println(nom + " mets " + cible.getNom() + " sous peché !");
        cible.setPeché(this);
        cible.setGravitée(cible.getGravitée()+1);
    }
    if(copié instanceof Sorciere){
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
    if(copié instanceof Cannonier) {
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
    if(copié instanceof Manipulateur) {
        Random random = new Random();
        Personnage p = cible;
        while (cible.getNom().equals(p.getNom()) || nom == p.getNom()){
            p = personnages.get(random.nextInt(personnages.size()));
        }
        System.out.println(nom + " a forcé " + cible.getNom() + " a utiliser sa compétence sur " + p.getNom() + " !");
        cible.utiliserCompetence(p);
    }
    if(copié instanceof Rachat) {
        System.out.println(nom + " utilise sa compétence sur " + cible.getNom());
        int pv=pointsDeVie;
        if(pointsDeVie<cible.getPv()){
            recevoirPv(cible.getPv()-pointsDeVie);
            cible.recevoirDegats(cible.getPv()-pv);
        }else{
            recevoirPv(pointsDeVie-cible.getPv());
        }
    }
}
}
