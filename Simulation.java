package modes;
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

public class Simulation {
    public static void main(String[] args) {
        Random random = new Random();
        ArrayList<Personnage> personnages = new ArrayList<>();
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
        noms.add("Lionel");

        System.out.println("Les personnages générés :");
            for(String nom : noms){
                int randomClassIndex = random.nextInt(19);
                Personnage personnage = null;
    
                switch (randomClassIndex) {
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
            personnages.add(personnage);
            System.out.println(nom + " - " + personnage.getClass().getSimpleName());
        }
        
        System.out.println("\nDébut de la simulation !");
        
        while(personnages.size() > 1){
            for(int i = 0; i < personnages.size(); i++){
                System.out.println("");
                Personnage courant = personnages.get(i);

                if(courant.getEtourdie() == 0){
                    Personnage cible = courant;
                    while(cible == courant){
                        cible = personnages.get(random.nextInt(personnages.size()));
                    }

                    if (random.nextBoolean()) {
                        courant.attaquer(cible);
                    }else{
                        courant.utiliserCompetence(cible);
                    }
                }else{
                    System.out.println(courant.getNom() + " est étourdi , il passe son tour");
                    courant.consommerétourdis();
                }
                courant.degatsdefindetour();

                personnages.removeIf(p -> !p.estVivant());

                if(personnages.size() == 1){
                    System.out.println("\nLe gagnant est " + personnages.get(0).getNom() + " !");
                    return;
                }
            }
        }
    }
}
