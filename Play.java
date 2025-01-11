package modes;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import autre.Personnage;
import personnages.Bouffon;
import personnages.Cannonier;
import personnages.Chevalier;
import personnages.Chimiste;
import personnages.Demon;
import personnages.Fou;
import personnages.Garde;
import personnages.Guerrier;
import personnages.Infirmiére;
import personnages.Juge;
import personnages.Mage;
import personnages.Magicien;
import personnages.Manipulateur;
import personnages.Mime;
import personnages.Mimiqueur;
import personnages.Parrieur;
import personnages.Rachat;
import personnages.Roi;
import personnages.Sorciere;
import personnages.Voleur;

public class Play {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> noms = new ArrayList<>();
        noms.add("Arthur");
        noms.add("Lancelot");
        noms.add("Merlin");
        noms.add("Guenièvre");
        noms.add("Perceval");
        noms.add("Gauvain");
        noms.add("Tristan");
        noms.add("Iseut");
        noms.add("Mordred");
        noms.add("Galahad");
        noms.add("Viviane");
        noms.add("Nimue");
        noms.add("Ector");
        noms.add("Bors");

        int niveau=1;
        int experience=0;
        
        ArrayList<Personnage> personnages = new ArrayList<>();

        int lancer = 1;
        int compteurparties = 0;
        int compteurvictoires = 0;

        while (lancer == 1) {
            compteurparties++;

            genérerrobots(personnages,noms);
        
            genererjoueur(personnages, scanner);
            
            Personnage joueur=personnages.get(personnages.size()-1);
            commencerpartie(personnages, scanner, joueur,niveau ,experience,compteurvictoires);

            System.out.println("Voulez vous lancer une autre partie?");
            System.out.println("1 - Oui");
            System.out.println("0 - Non");
            lancer = scanner.nextInt();
        }

        System.out.println("Session terminée !");
        System.out.println("vouz avez atteint le niveau " + niveau + " !");
        System.out.println("vous avez lancé " + compteurparties + " parties,vous avez gagné " + compteurvictoires + " de celles ci !");
        
        scanner.close();
}

static Personnage genérer(int classe,ArrayList<Personnage> personnages,String nom){
    Personnage personnage = null;

    switch (classe) {
        case 0 -> personnage = new Mage(personnages, nom);
        case 1 -> personnage = new Voleur(personnages, nom);
        case 2 -> personnage = new Roi(personnages, nom);
        case 3 -> personnage = new Mimiqueur(personnages, nom);
        case 4 -> personnage = new Bouffon(personnages, nom);
        case 5 -> personnage = new Chevalier(personnages, nom);
        case 6 -> personnage = new Chimiste(personnages, nom);
        case 7 -> personnage = new Fou(personnages, nom);
        case 8 -> personnage = new Guerrier(personnages, nom);
        case 9 -> personnage = new Mime(personnages, nom);
        case 10 -> personnage = new Infirmiére(personnages, nom);
        case 11 -> personnage = new Juge(personnages, nom);
        case 12 -> personnage = new Garde(personnages, nom);
        case 13 -> personnage = new Parrieur(personnages, nom);
        case 14 -> personnage = new Magicien(personnages, nom);
        case 15 -> personnage = new Demon(personnages, nom);
        case 16 -> personnage = new Sorciere(personnages, nom);
        case 17 -> personnage = new Cannonier(personnages, nom);
        case 18 -> personnage = new Manipulateur(personnages, nom);
        case 19 -> personnage = new Rachat(personnages, nom);
    }
    return personnage;
}

static void commencerpartie(ArrayList<Personnage> personnages,Scanner scanner,Personnage joueur,int niveau,int experience,int compteurvictoires){
    Random random = new Random();
    System.out.println("\nDébut de la partie !");

        while (1 < personnages.size()) {
            for (int i = 0; i < personnages.size(); i++) {
                System.out.println("");
                Personnage courant = personnages.get(i);
                if(courant.getNom() == "joueur") {
                    if(joueur.getEtourdie()==0){
                        decisionjoueur(personnages,scanner,joueur);
                        joueur.degatsdefindetour();
                    }else{
                        System.out.println("le joueur est étourdi , il passe son tour");
                        joueur.consommerétourdis();
                    }
                }else{
                    Personnage cible = courant;
                    while (cible == courant){
                        cible = personnages.get(random.nextInt(personnages.size()));
                    }
                    if(courant.getEtourdie() == 0){
                        if (random.nextBoolean()) {
                            courant.attaquer(cible);
                        } else {
                            courant.utiliserCompetence(cible);
                        }
                    }else{
                        System.out.println(courant.getNom() + " est étourdi , il passe son tour");
                        courant.consommerétourdis();
                    }
                    courant.degatsdefindetour();
                }

                personnages.removeIf(p -> !p.estVivant());

                if(personnages.size() == 1) {
                    System.out.println("\nLe gagnant est " + personnages.get(0).getNom() + " !");
                    if(personnages.get(0).getNom()=="joueur"){
                        compteurvictoires++;
                        gagnerExperience(experience,125,niveau);
                    }else{
                        System.out.println("vous avez perdu cette partie");
                    }
                    System.out.println("Le combat est terminé !");
                    return;
                }
            }
        }
}

static void genérerrobots(ArrayList<Personnage> personnages,ArrayList<String> noms){
    Random random = new Random();
    System.out.println("\nLes personnages générés :");
    for (String nom : noms) {
        int randomClassIndex = random.nextInt(19);
        Personnage personnage = null;
        personnage = genérer(randomClassIndex,personnages,nom);
        personnages.add(personnage);
        System.out.println(nom + " - " + personnage.getClass().getSimpleName());
        }
}

static void genererjoueur(ArrayList<Personnage> personnages,Scanner scanner){
    int choix = -1;
        while(choix < 0){
            System.out.println("Choisissez votre personnage :");
            System.out.println("1 - Mage");
            System.out.println("2 - Voleur");
            System.out.println("3 - Roi");
            System.out.println("4 - Mimiqueur");
            System.out.println("5 - Bouffon");
            System.out.println("6 - Chevalier");
            System.out.println("7 - Chimiste");
            System.out.println("8 - Fou");
            System.out.println("9 - Guerrier");
            System.out.println("10 - Mime");
            System.out.println("11 - Infirmiére");
            System.out.println("12 - Juge");
            System.out.println("13 - Garde");
            System.out.println("14 - Parrieur");
            System.out.println("15 - Magicien");
            System.out.println("16 - Demon");
            System.out.println("17 - Sorciere");
            System.out.println("18 - Cannonier");
            System.out.println("19 - Manipulateur");
            System.out.println("20 - Rachat");
            choix = scanner.nextInt()-1;
        }
        Personnage joueur;
        joueur = genérer(choix, personnages, "joueur");
        personnages.add(joueur);
}

static void decisionjoueur(ArrayList<Personnage>personnages,Scanner scanner,Personnage joueur){
    System.out.println("Choisissez un adversaire :");
    int cible = -1;
    Personnage p = joueur;

    while ( cible < 0 || personnages.size()-1 < cible){
        for (int i = 0; i < personnages.size()-1; i++) {
            p = personnages.get(i);
            System.out.println(i+1 + " - " + p.getNom());
        }
        cible = scanner.nextInt()-1;
    }
    System.out.println("vous avez choisi d'attaquer : " + personnages.get(cible).getNom() + "!");
    
    int action = 3;
    while (action > 2){
        System.out.println("Choisissez une action :");
        System.out.println("1. Attaque normale");
        System.out.println("2. Compétence");
        action = scanner.nextInt();
    }

    if (action == 1) {
        joueur.attaquer(personnages.get(cible));
    }else{
        joueur.utiliserCompetence(personnages.get(cible));
    }
}

static void gagnerExperience(int experience,int xp,int niveau) {
    experience += xp;
    if (experience >= 100) {
        niveau++;
        experience -= 100;
        System.out.println("vouz avez atteint le niveau " + niveau + " !");
    }
}

}
