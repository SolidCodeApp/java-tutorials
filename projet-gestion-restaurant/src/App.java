import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class App {

    static final Integer MAX_TABLES = 3;
    static final Integer MAX_PLATES = 5;

    // Codes ANSI pour les couleurs
    static final String red = "\033[31m";
    static final String green = "\033[32m";
    static final String reset = "\033[0m"; // Réinitialiser la couleur

    static final Scanner scanner = new Scanner(System.in);

    static final String[] menuPrincipal = {
            "Faire la queue (signaler sa présence)",
            "Voir les plats",
            "Passer une commande",
            "Demander l'addition",
            "Voir la file d'attente",
            "Voir le statut des tables",
            "Préparer commande & Servir client"
    };

    static final Queue<String> fileDAttente = new LinkedList<String>();

    static ArrayList<String> listeDeSalaries = new ArrayList<>();

    static ArrayList<String> listeDePlats = new ArrayList<>();

    // La clé est l'index de la liste des plats
    static HashMap<Integer, Double> listeDePrix = new HashMap<>();

    static Queue<String> fileDeTables = new LinkedList<>();
    static Stack<String> pileDAssiettes = new Stack<>();

    static Queue<String> clientsAyantCommande = new LinkedList<>();

    // Une commande HashMap<Integer, Integer>
    static HashMap<String, HashMap<Integer, Integer>> commandesEnCours = new HashMap<>();

    static HashMap<String, ArrayList<String>> assiettesOccupees = new HashMap<>();
    static HashMap<String, String> tablesOccupees = new HashMap<>();

    /**
     * ###### ROADMAP ########
     * 1. Configuration des messages (délimiteurs + couleurs des textes)
     * 2. Gestion du menu principal
     * 3. Gestion des clients (file d'attente)
     * 4. Gestion des salariés
     * 5. Gestion du menu du restaurant (plats et prix)
     * 6. Gestion des resources ( Tables et assitettes)
     * 7. Gestion de la prise de commandes
     * 8. Gestion de la préparation de commande et servir le client
     * 9. Gestion de l'addition et du paiement
     * 10. BONUS : Corrections & Refactoring de code & tests foncitonnels (manuels)
     * 
     */
    public static void main(String[] args) throws Exception {
        System.out.println(reset);

        System.out.println("###########################################################");
        System.out.println("########                                             ######");
        System.out.println("########    Bienvenue à Solid Code App Restaurant!   ######");
        System.out.println("########                                             ######");
        System.out.println("###########################################################");

        initialiserSalaries();
        initialiserPlats();
        initialiserPrix();
        initialiserFileDeTables();
        initialiserPileDAssiettes();
        afficherMenuPrincipal();
        lireChoixUtilisateur();

    }

    public static void initialiserPlats() {
        listeDePlats.add("Quiche");
        listeDePlats.add("Burger");
        listeDePlats.add("Frites");
        listeDePlats.add("Pizza");
        listeDePlats.add("Tacos");
        listeDePlats.add("Sushi");
        listeDePlats.add("Lasagnes");
        listeDePlats.add("Cannelloni");
        listeDePlats.add("Tarte au citron");
    }

    public static void initialiserPrix() {
        listeDePrix.put(0, 2.0);
        listeDePrix.put(1, 3.0);
        listeDePrix.put(2, 1.5);
        listeDePrix.put(3, 4.5);
        listeDePrix.put(4, 2.75);
        listeDePrix.put(5, 3.25);
        listeDePrix.put(6, 5.0);
        listeDePrix.put(7, 3.75);
        listeDePrix.put(8, 4.25);
    }

    // Cette méthode permet d'initialiser notre file de tables
    public static void initialiserFileDeTables() {
        for (int i = 1; i <= MAX_TABLES; i++) {
            fileDeTables.add("Table N° " + i);
        }
    }

    // Cette méthode permet d'initialiser notre pile d'assiettes
    public static void initialiserPileDAssiettes() {
        for (int i = 1; i <= MAX_PLATES; i++) {
            pileDAssiettes.push("Assiette N° " + i);
        }
    }

    public static void initialiserSalaries() {
        listeDeSalaries.add("Bob");
        listeDeSalaries.add("Peter");
        listeDeSalaries.add("Alice");
    }

    public static void afficherMenuPrincipal() {
        delimiteur("MENU PRINCIPAL");
        for (int i = 0; i < menuPrincipal.length; i++) {
            afficher(" " + (i + 1) + ". " + menuPrincipal[i]);
        }
    }

    // Méthode qui permet de récupérer le choix de l'utilisateur
    public static void lireChoixUtilisateur() {
        delimiteur("CHOIX UTILISATEUR");

        afficher("Entrez le numéro de votre choix, s'il vous plaît : ");

        try {

            int choix = scanner.nextInt();
            consommerLigneRestante();
            switch (choix) {
                case 1:
                    faireLaQueue();
                    break;
                case 2:
                    afficherLesPlats();
                    break;
                case 3:
                    passerCommande();

                    break;
                case 4:
                    demanderLadditionEtPayer();
                    break;
                case 5:
                    voirLaFileDAttente();
                    break;
                case 6:
                    voirLeStatutDesTables();
                    break;
                case 7:
                    preparerCommandeServirClient();

                    break;

                default:
                    throw new Exception("Option Invalide");
            }
        } catch (Exception error) {
            afficherErreur("Une erreur s'est produite.");
            afficherErreur("L'option choisie semble être invalide. Veuillez réessayer");

            if (error.getMessage() != "Option Invalide") {
                consommerLigneRestante();
            }

            lireChoixUtilisateur();
        }
    }

    public static void consommerLigneRestante() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }

    // Cette méthode permet de voir le statut des tables
    public static void voirLeStatutDesTables() {
        delimiteur("STATUT DES TABLES");
        afficherSucces("Vous avez choisi de consulter le statut des tables.");
        for (Map.Entry<String, String> entry : tablesOccupees.entrySet()) {
            afficher(" " + entry.getKey() + " : " + entry.getValue() + " -  Occupée");
        }

        for (String table : fileDeTables) {
            afficher(" " + table + " - Disponible");

        }
        revenirAuMenuPrincipal();
    }

    public static void voirLaFileDAttente() {
        delimiteur("FILE D'ATTENTE");
        afficherSucces("Vous avez choisi de consulter la file d'attente.");
        if (fileDAttente.isEmpty()) {
            afficher("Aucun client dans la file d'attente.");
        } else {
            int numeroClient = 1;
            for (String nomClient : fileDAttente) {
                afficher(" " + numeroClient + ". " + nomClient);
                numeroClient++;
            }
        }
        revenirAuMenuPrincipal();
    }

    public static void afficherLesPlats() {
        afficherSucces("Vous avez choisi de voir les plats.");
        afficherMenuRestaurant();
        revenirAuMenuPrincipal();
    }

    public static void demanderLadditionEtPayer() {
        delimiteur("DEMANDE D'ADDITION & PAIMENT");
        afficherSucces("Vous avez choisi de demander l'addition.");
        String nomClient = recupererNomUtilisateur();

        if (nomClient != null) {

            // Le client doit avoir commandé
            if (!commandesEnCours.containsKey(nomClient)) {
                afficherErreur(nomClient + " n'a pas été reconnu comme client ayant commandé!");
                revenirAuMenuPrincipal();
                return;
            }

            HashMap<Integer, Integer> commande = commandesEnCours.get(nomClient);
            afficherAddition(nomClient, commande);
            if (tablesOccupees.containsKey(nomClient)) {
                payerAddition(nomClient, commande);
            }

            revenirAuMenuPrincipal();
        }
    }

    public static void libererTable(String nomClient) {
        // Néttoyer la table
        String table = tablesOccupees.get(nomClient);
        fileDeTables.offer(table); // Rendre disponible la table

        ArrayList<String> lesAssiettes = assiettesOccupees.get(nomClient);
        for (String assiette : lesAssiettes) {
            pileDAssiettes.push(assiette);
        }

        // Enlever de la liste des assiettes occupé
        assiettesOccupees.remove(nomClient);

        // enlever la table de la liste des tables occupées
        tablesOccupees.remove(nomClient);
    }

    // Cette méthode permet de payer l'addition
    public static void payerAddition(String nomClient, HashMap<Integer, Integer> commande) {
        delimiteur("PAIEMENT");
        afficher("Veuillez saisir le montant de la commande : ");

        try {

            double montantSaisi = scanner.nextDouble();
            consommerLigneRestante();
            double motantAPayer = 0;
            for (Map.Entry<Integer, Integer> entry : commande.entrySet()) {
                double prixUnitaire = listeDePrix.get(entry.getKey());
                double prix = prixUnitaire * entry.getValue();
                motantAPayer += prix;

            }

            if (montantSaisi < motantAPayer) {
                afficherErreur("Le montant payé est inférieur au montant de la commande.");
                payerAddition(nomClient, commande);
                return;
            }

            // Suppression de la commande de la liste des commandes en cours
            commandesEnCours.remove(nomClient);

            // Afficher des message de remerciements
            afficherSucces("Votre paiement a été accepté.");
            afficher("Votre addition est de : " + motantAPayer + " euros.");
            afficherSucces("Merci pour votre commande. A bientôt!");

            // ibérer la table
            libererTable(nomClient);

        } catch (Exception exception) {
            consommerLigneRestante();
            afficherErreur("Une erreur s'est produite. Veuillez réessayer.");
            payerAddition(nomClient, commande);
        }
    }

    // Cette méthode nous permet d'afficher l'addition
    public static void afficherAddition(String nomClient, HashMap<Integer, Integer> commande) {
        delimiteur("ADDITION");
        afficher("Commande de " + nomClient);

        double total = 0;

        for (Map.Entry<Integer, Integer> entry : commande.entrySet()) {
            int indicePlat = entry.getKey();
            int quantite = entry.getValue();
            String plat = listeDePlats.get(indicePlat);
            double prixUnitaire = listeDePrix.get(indicePlat);
            double prix = prixUnitaire * quantite;

            afficher(" - " + quantite + " " + plat + " : " + prix + " euros (TTC).");
            total += prix;

        }
        afficher("Total à payer : " + total + " euros (TTC)");
    }

    // Cette méthode permet de préparer commande et servir client
    public static void preparerCommandeServirClient() {
        delimiteur("PREPARATION DE COMMANDE & SERVIR CLIENT");

        afficherSucces("Vous avez choisi de préparer commande et servir client.");

        String nomSalarie = recupererNomUtilisateur();

        if (nomSalarie != null) {

            if (!listeDeSalaries.contains(nomSalarie)) {
                afficherErreur("Vous n'avez pas été reconnu comme salarié.");
                revenirAuMenuPrincipal();
                return;

            }

            // Il doit y avoir des commandes en attente
            if (clientsAyantCommande.isEmpty()) {
                afficherErreur("Il n'y a pas de commandes en attente.");
                revenirAuMenuPrincipal();
                return;
            }

            // Il doit y avoir une table disponible
            if (fileDeTables.isEmpty()) {
                afficherErreur("Il n'y a pas de tables disponibles.");
                revenirAuMenuPrincipal();
                return;

            }

            if (pileDAssiettes.isEmpty()) {
                afficherErreur("Il n'y a pas d'assiettes disponibles.");
                revenirAuMenuPrincipal();
                return;
            }

            String nomClient = clientsAyantCommande.peek();
            HashMap<Integer, Integer> commande = commandesEnCours.get(nomClient);
            int assiettesAUtiliser = calculerNombreAssiettes(commande);

            if (pileDAssiettes.size() < assiettesAUtiliser) {
                afficherErreur("Il n'y a pas assez d'assiettes disponibles pour cette commande.");
                revenirAuMenuPrincipal();
                return;
            }

            preparerCommande(nomSalarie, commande, assiettesAUtiliser);
            livrerCommande(nomSalarie, commande, nomClient);
            revenirAuMenuPrincipal();
        }
    }

    // Cette méthode permet de livrer une commande
    public static void livrerCommande(String nomSalarie, HashMap<Integer, Integer> commande, String nomClient) {
        delimiteur("LIVRAISON COMMANDE");
        afficher("Livraison de commande en cours par : " + nomSalarie);
        afficherDetailsCommande(nomClient);

        String table = fileDeTables.poll();
        tablesOccupees.put(nomClient, table);

        afficherSucces("Livraison de la commande de " + nomClient + " sur la table : " + table);
        afficherSucces("Commande livrée avec succès!");
    }

    // Cette méthode permet de préparer la commande d'un client
    public static void preparerCommande(String nomSalarie, HashMap<Integer, Integer> commande, int nombreAssiettes) {
        delimiteur("PREPARATION DE COMMANDE");
        afficher("Commande en cours de préparation par : " + nomSalarie);

        String nomClient = clientsAyantCommande.poll();
        afficherDetailsCommande(nomClient);

        ArrayList<String> assiettesUtilisees = new ArrayList<>();

        for (int i = 0; i < nombreAssiettes; i++) {
            assiettesUtilisees.add(pileDAssiettes.pop());
        }
        assiettesOccupees.put(nomClient, assiettesUtilisees);

        int attente = 5000; // Tempt d'attente 5 secondes
        afficher("La commande sera prête dans : " + (attente / 1000) + " secondes...");
        try {
            Thread.sleep(attente);
        } catch (Exception exception) {
            // Ne rien faire
        }
        afficherSucces("Commande prête pour : " + nomClient);

    }

    // Cette méthode permet de calculer le nombre d'assiettes à utiliser par cette
    // commande
    public static int calculerNombreAssiettes(HashMap<Integer, Integer> commande) {
        int nombreAssiettes = 0;
        // Parcourir le HashMap commande
        for (int quantite : commande.values()) {
            nombreAssiettes += quantite;
        }
        return nombreAssiettes;
    }

    // Cette méthode nous permet de récupérer la position d'un client dans la file
    // d'attente
    public static int getPositionClientDansFile(String nomClient) {
        if (!fileDAttente.contains(nomClient)) {
            return -1;
        }
        int position = 0;
        for (String client : fileDAttente) {
            if (client.equals(nomClient)) {
                return position;
            }
            position++;
        }
        return position;
    }

    // Cette méthode permet de passer commande
    public static void passerCommande() {
        delimiteur("PASSAGE DE COMMANDE");
        afficherSucces("Vous avez choisi de passer commande.");

        // Demander son nom au client
        String nom = recupererNomUtilisateur();

        if (nom != null) {

            if (!fileDAttente.isEmpty()) {
                if (!fileDAttente.peek().equals(nom)) {
                    afficherErreur(
                            "Vous ne pouvez pas passer commande tant que vous n'êtes pas le premier dans la file d'attente");
                    afficher("Veuillez attendre que vous soyez le premier.");

                    int position = getPositionClientDansFile(nom);
                    if (position >= 0) {
                        afficher("Vous êtes le numéro : " + (position + 1) + ".");
                    } else {
                        afficher("Vous n'êtes pas dans la file d'attente. Veuillez faire la queue.");
                    }
                    revenirAuMenuPrincipal();
                    return;
                }
            }
            afficherMenuRestaurant();
            HashMap<Integer, Integer> commande = new HashMap<Integer, Integer>();
            boolean stop = false;
            do {
                lireChoixPlat(commande);
                afficher("Voulez-vous commander un autre plat? (O/N)");
                String reponse = scanner.nextLine();
                stop = reponse.equalsIgnoreCase("N");

            } while (!stop);

            // Ajouter cette commande à la file d'attente
            clientsAyantCommande.add(nom);
            fileDAttente.remove(nom);
            commandesEnCours.put(nom, commande);

            // Afficher un message succès
            afficherSucces("Votre commande a été prise en compte.");
            afficherSucces("Vous être le numéro : " + clientsAyantCommande.size());

            afficherDetailsCommande(nom);
            revenirAuMenuPrincipal();
        }
    }

    // Cette méthode permet d'afficher les détails d'une commande
    public static void afficherDetailsCommande(String nomClient) {
        delimiteur("DÉTAILS DE COMMANDE");
        HashMap<Integer, Integer> commande = commandesEnCours.get(nomClient);
        afficher("Nom du client : " + nomClient);
        afficher("Commande : ");
        for (Map.Entry<Integer, Integer> entry : commande.entrySet()) {
            afficher(listeDePlats.get(entry.getKey()) + " : " + entry.getValue() + " unités");
        }
    }

    // Cette méthode permet de lire le choid de l'utilisateur en terme de plat
    public static void lireChoixPlat(HashMap<Integer, Integer> commande) {
        delimiteur("CHOIX PLATS");
        afficher("Entrez le numéro du plat, s'il vous plaît : ");

        try {

            int numeroPlat = scanner.nextInt();
            consommerLigneRestante();

            String plat = listeDePlats.get(numeroPlat - 1);

            if (plat == null) {
                throw new Exception("Option Invalide");
            }

            afficherSucces("Vous avez choisi le plat : " + plat + ".");

            int quantite = lireChoixQuantite(plat);
            commande.put(numeroPlat - 1, quantite);

        } catch (Exception error) {
            afficherErreur("Une erreur s'est produite.");
            afficherErreur("Le numéro de plat semble être invalide. Veuillez réessayer");
            if (error.getMessage() != "Option Invalide") {
                consommerLigneRestante();
            }
            lireChoixPlat(commande);
        }
    }

    static int lireChoixQuantite(String plat) {
        afficher("Entrez le nombre de " + plat + "s souhaité(e)s : ");

        try {
            int quantite = scanner.nextInt();
            consommerLigneRestante();
            if (quantite <= 0) {
                throw new Exception("Quantité Invalide");
            }
            return quantite;
        } catch (InputMismatchException exception) {
            consommerLigneRestante();
            afficherErreur("Quantité invalide. Veuillez réessayer.");
            return lireChoixQuantite(plat);
        }

        catch (Exception exception) {
            if (!exception.getMessage().equals("Quantité Invalide")) {
                consommerLigneRestante();
            }
            afficherErreur("Quantité invalide. Veuillez réessayer.");
            return lireChoixQuantite(plat);
        }
    }

    public static String recupererNomUtilisateur() {
        afficher("Entrez votre nom ou appuyez sur 'Entrée' pour revenir au menu principal : ");

        String nom = scanner.nextLine();

        if (nom.trim().isEmpty()) {
            afficherMenuPrincipal();
            lireChoixUtilisateur();
            return null;
        }
        return nom;

    }

    public static void faireLaQueue() {
        delimiteur("FILE D'ATTENTE");
        afficherSucces("Vous avez choisi de faire la queue.");
        String nom = recupererNomUtilisateur();

        if (nom != null) {
            fileDAttente.offer(nom);
            afficherSucces(nom + ", Vous avez été ajouté à la file d'attente");
            afficher("Vous êtes le numéro : " + fileDAttente.size() + ".");
            revenirAuMenuPrincipal();
        }
    }

    public static void revenirAuMenuPrincipal() {
        afficher("\nAppuyer sur la touche 'Entrée' du clavier pour revenir au menu principal.\n");
        consommerLigneRestante();
        afficherMenuPrincipal();
        lireChoixUtilisateur();
    }

    public static void afficherMenuRestaurant() {
        delimiteur("MENU RESTAURANT");
        for (int i = 0; i < listeDePlats.size(); i++) {
            afficher(" " + (i + 1) + ". " + listeDePlats.get(i) + " - " + listeDePrix.get(i) + " euros TTC.");
        }
    }

    // Méthode qui permet d'afficher un message
    public static void afficher(String message) {
        System.out.print(reset);
        System.out.println(message);
    }

    // Méthode qui permet d'afficher un message d'erreur
    public static void afficherErreur(String message) {
        System.out.print(red);
        System.out.println(message);
        System.out.print(reset);
    }

    // Méthode qui permet d'afficher un message de succès
    public static void afficherSucces(String message) {
        System.out.print(green);
        System.out.println(message);
        System.out.print(reset);
    }

    // Méthode qui permet de délimiter une opération
    public static void delimiteur(String titre) {
        afficher("\n==================== " + titre + " =======================\n");
    }
}