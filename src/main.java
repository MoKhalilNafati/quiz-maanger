import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

public class main {
    private static GestionnaireQuiz gestionnaireQuiz = new GestionnaireQuiz();
    private static Etudiant etudiant = null;
    private static ArrayList<Etudiant> etudiants = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== MENU PRINCIPALE ===");
            System.out.println("1. Espace Enseignant");
            System.out.println("2. Espace Étudiant");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");

            int choix = saisirEntier(scanner, 1, 3, "Veuillez entrer un choix valide entre 1 et 3.");

            switch (choix) {
                case 1 -> menuEnseignant(scanner);
                case 2 -> menuEtudiant(scanner);
                case 3 -> {
                    System.out.println("Merci d'avoir utilisé le gestionnaire de quiz. À bientôt !");
                    return;
                }
            }
        }
    }
//Mode Enseignant
    private static void menuEnseignant(Scanner scanner) {
        String mdp = "java000";
        System.out.print("Entrez le mot de passe pour accéder au mode enseignant : ");
        String mdpSaisi = scanner.nextLine();

        if (!mdpSaisi.equals(mdp)) {
            System.out.println("Mot de passe incorrect. Retour au menu principal.");
            return;
        }
        
        int choix;
        do {
            System.out.println("\n=== ESPACE ENSEIGNANT ===");
            System.out.println("1. Ajouter un nouveau quiz");
            System.out.println("2. Voir tous les quiz");
            System.out.println("3. Supprimer un quiz");
            System.out.println("4. Afficher les résultats des étudiants pour un quiz");
            System.out.println("5. Modifier un quiz");
            System.out.println("6. Voir les statistiques d'un quiz");
            System.out.println("7. Retour au menu principal");
            System.out.print("Votre choix : ");

            choix = saisirEntier(scanner, 1, 7, "Veuillez entrer un choix valide entre 1 et 7.");

            switch (choix) {
                case 1 -> ajouterQuiz(scanner);
                case 2 -> gestionnaireQuiz.afficherQuiz();
                case 3 -> supprimerQuiz(scanner);
                case 4 -> afficherResultatsEtudiants(scanner);
                case 5 -> ModificationQuiz(scanner);
                case 6 -> afficherStatistiques(scanner);
                case 7 -> System.out.println("Retour au menu principal.");
            }
        } while (choix != 7);
    }

    private static void ajouterQuiz(Scanner scanner) {
        System.out.print("Entrez le thème du quiz : ");
        String theme = scanner.nextLine();
        System.out.print("Entrez le nom de l'auteur : ");
        String auteur = scanner.nextLine();

        System.out.print("Combien de questions contient ce quiz ? ");
        int nbQuestions = saisirEntier(scanner, 1, 50, "Le nombre de questions doit être entre 1 et 50.");

        Question[] questions = creerQuestions(scanner, nbQuestions);

        Quiz quiz = new Quiz(theme, auteur, questions);
        gestionnaireQuiz.ajouterQuiz(quiz);
        System.out.println("Quiz ajouté avec succès !");
    }

    private static Question[] creerQuestions(Scanner scanner, int nbQuestions) {
        Question[] questions = new Question[nbQuestions];
        for (int i = 0; i < nbQuestions; i++) {
            System.out.print("Texte de la question " + (i + 1) + " : ");
            String texteQuestion = scanner.nextLine();

            System.out.print("Combien d'options pour cette question ? ");
            int nbOptions = saisirEntier(scanner, 2, 10, "Le nombre d'options doit être entre 2 et 10.");

            Option[] options = creerOptions(scanner, nbOptions);
            questions[i] = new Question(i + 1, texteQuestion, options);
        }
        return questions;
    }

    private static Option[] creerOptions(Scanner scanner, int nbOptions) {
        Option[] options = new Option[nbOptions];
        for (int j = 0; j < nbOptions; j++) {
            System.out.print("Texte de l'option " + (j + 1) + " : ");
            String texteOption = scanner.nextLine();

            System.out.print("Cette option est-elle correcte ? (true/false) : ");
            boolean estValide = saisirBoolean(scanner);

            options[j] = new Option(texteOption, estValide);
        }
        return options;
    }

    private static void supprimerQuiz(Scanner scanner) {
        System.out.print("Entrez le thème du quiz à supprimer : ");
        String theme = scanner.nextLine();
        gestionnaireQuiz.supprimerQuiz(theme);
    }

    private static void afficherResultatsEtudiants(Scanner scanner) {
        System.out.print("Entrez le thème du quiz pour afficher les résultats : ");
        String theme = scanner.nextLine();
        gestionnaireQuiz.afficherResultatsEtudiants(theme);
    }
    
    private static void ModificationQuiz(Scanner scanner) {
    	System.out.print("Entrez le thème du quiz à modifier : ");
        String theme = scanner.nextLine();
        gestionnaireQuiz.modifierQuiz(theme, scanner);
    }
    
    private static void afficherStatistiques(Scanner scanner){
    	System.out.print("Entrez le thème du quiz : ");
        String theme = scanner.nextLine();
        gestionnaireQuiz.afficherStatistiques(theme);
    }
    
//Mode Etudiant
    private static void menuEtudiant(Scanner scanner) {
        if (etudiant == null) {
            System.out.println("\n=== CONNEXION ETUDIANT ===\n");
            System.out.print("Entrez votre NCIN : ");
            int ncin = saisirEntier(scanner, 10000000, 99999999, "Veuillez entrer un numéro valide de 8 chiffres.");

            // Vérifier si l'étudiant existe deja
            etudiant = trouverEtudiant(ncin);
            if (etudiant != null) {
                System.out.println("Bienvenue de retour, " + etudiant.getPrenom() + " " + etudiant.getNom() + " !");
            } else {
                // Créer un nouvel étudiant
                creerEtudiant(scanner, ncin);
            }
        }

        while (true) {
            System.out.println("\n=== ESPACE ÉTUDIANT ===");
            System.out.println("1. Voir la liste des quiz disponibles");
            System.out.println("2. Participer à un quiz");
            System.out.println("3. Consulter mes scores");
            System.out.println("4. Consulter la correction d'un quiz");
            System.out.println("5. Retour au menu principal");
            System.out.print("Votre choix : ");

            int choix = saisirEntier(scanner, 1, 5, "Veuillez entrer un choix valide entre 1 et 5.");

            switch (choix) {
                case 1 -> gestionnaireQuiz.afficherQuizAvecStatut(etudiant);
                case 2 -> participerAuQuiz(scanner);
                case 3 -> etudiant.afficherScores();
                case 4 -> consulterCorrection(scanner);
                case 5 -> {
                    System.out.println("Deconnexion.");
                    etudiant = null; // Deconnexion
                    return;
                }
            }
        }
    }

    private static Etudiant trouverEtudiant(int ncin) {
        for (int i = 0; i < etudiants.size(); i++) {
            if (etudiants.get(i).getNcin() == ncin) {
                return etudiants.get(i);
            }
        }
        return null;
    }
    
    private static void creerEtudiant(Scanner scanner, int ncin) {
        System.out.print("Entrez votre nom : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez votre prénom : ");
        String prenom = scanner.nextLine();

        etudiant = new Etudiant(nom, prenom, ncin);
        etudiants.add(etudiant); // Ajouter l'étudiant à la liste
        System.out.println("Compte créé avec succès. Bienvenue, " + prenom + " " + nom + " !");
    }
    
    private static void participerAuQuiz(Scanner scanner) {
        System.out.print("Entrez le thème du quiz auquel vous voulez participer : ");
        String theme = scanner.nextLine();
        Quiz quiz = gestionnaireQuiz.getQuizParTheme(theme);

        if (quiz == null) {
            System.out.println("Ce quiz n'existe pas.");
            return;
        }

        if (etudiant.DejaParticipe(theme)) {
            System.out.println("Vous avez déjà participé à ce quiz.");
            return;
        }

        int score = 0;
        System.out.println("Début du quiz : " + quiz.getTheme()+" (une seule reponse est vrai)");
        Question[] questions = quiz.getQuestions();
        for (int i = 0; i < questions.length; i++) {
            Question question = questions[i];
            question.afficherQuestion();
            System.out.print("Votre réponse : ");
            int reponse = saisirEntier(scanner, 1, question.getOptions().length, "Veuillez entrer un numéro d'option valide.");
            question.ajouterReponseEtudiant(reponse);
            if (question.estBonneReponse(reponse)) {
                score++;
            }
        }


        System.out.println("Quiz terminé ! Votre score : " + score + "/" + quiz.getQuestions().length);
        etudiant.setScore(quiz.getTheme(), score, quiz.getQuestions().length);
        quiz.ajouterParticipant(etudiant);
    }

    private static void consulterCorrection(Scanner scanner) {
        System.out.print("Entrez le thème du quiz pour consulter la correction : ");
        String theme = scanner.nextLine();

        if (!etudiant.DejaParticipe(theme)) {
            System.out.println("Vous devez d'abord participer à ce quiz pour voir la correction.");
            return;
        }

        gestionnaireQuiz.afficherCorrectionQuiz(theme);
    }
    
//controle Saisie
    private static int saisirEntier(Scanner scanner, int min, int max, String messageErreur) {
        int valeur;
        while (true) {
            try {
                valeur = scanner.nextInt();
                scanner.nextLine();
                if (valeur >= min && valeur <= max) {
                    return valeur;
                }
                System.out.println(messageErreur);
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un nombre entier.");
                scanner.nextLine();
            }
        }
    }

    static boolean saisirBoolean(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true") || input.equals("false")) {
                return Boolean.parseBoolean(input);
            }
            System.out.println("Veuillez entrer 'true' ou 'false'.");
        }
    }
}
