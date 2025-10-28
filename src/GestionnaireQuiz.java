import java.util.ArrayList;
import java.util.Scanner;


public class GestionnaireQuiz {
    private ArrayList<Quiz> quizList;

    public GestionnaireQuiz() {
        this.quizList = new ArrayList<>();
    }

    public void ajouterQuiz(Quiz quiz) {
        quizList.add(quiz);
        System.out.println("Quiz ajouté : " + quiz.getTheme());
    }

    public void afficherQuiz() {
        if (quizList.isEmpty()) {
            System.out.println("Aucun quiz disponible.");
        } else {
            System.out.println("\nListe des Quiz disponibles :");

            for (int i = 0; i < quizList.size(); i++) {
                Quiz quiz = quizList.get(i);
                System.out.println("Thème : " + quiz.getTheme() + " | Auteur : " + quiz.getAuteur());

                // Récupérer les questions du quiz
                Question[] questions = quiz.getQuestions();
                for (int j = 0; j < questions.length; j++) {
                    Question question = questions[j];
                    System.out.println("\tQuestion " + (j + 1) + ": " + question.getTexte());

                    Option[] options = question.getOptions();
                    for (int k = 0; k < options.length; k++) {
                        Option option = options[k];
                        System.out.print("\t\tOption " + (k + 1) + ": " + option.getTexte());
                        if (option.isValide()) {
                            System.out.print(" (Correcte)");
                        }
                        System.out.println();
                    }
                }
                System.out.println();
            }
        }
    }


    public void supprimerQuiz(String theme) {
        Quiz quiz = getQuizParTheme(theme);
        if (quiz != null) {
            quizList.remove(quiz);
            System.out.println("Le quiz " + theme + " a été supprimé.");
        } else {
            System.out.println("Aucun quiz trouvé avec le thème : " + theme);
        }
    }

    public Quiz getQuizParTheme(String theme) {
        for (int i = 0; i < quizList.size(); i++) {
            if (quizList.get(i).getTheme().equalsIgnoreCase(theme)) {
                return quizList.get(i);
            }
        }
        return null;
    }


    public void afficherResultatsEtudiants(String theme) {
        Quiz quiz = getQuizParTheme(theme);
        if (quiz != null) {
            quiz.afficherResultats();
        } else {
            System.out.println("Aucun quiz trouvé avec le thème : " + theme);
        }
    }

    public void afficherCorrectionQuiz(String theme) {
        Quiz quiz = getQuizParTheme(theme);
        if (quiz != null) {
            quiz.afficherCorrection();
        } else {
            System.out.println("Aucun quiz trouvé avec le thème : " + theme);
        }
    }
    public void afficherQuizAvecStatut(Etudiant etudiant) {
        if (quizList.isEmpty()) {
            System.out.println("Aucun quiz disponible.");
            return;
        }

        System.out.println("\nListe des Quiz disponibles :");
        for (int i = 0; i < quizList.size(); i++) {
            Quiz quiz = quizList.get(i);
            String statut;
            if (etudiant.DejaParticipe(quiz.getTheme())) {
                statut = "Déjà participé";
            } else {
                statut = "Non participé";
            }

            System.out.println("Thème : " + quiz.getTheme() + " | Auteur : " + quiz.getAuteur() + " | Statut : " + statut);
        }
    }
    
    public void modifierQuiz(String theme, Scanner scanner) {
        Quiz quiz = getQuizParTheme(theme);

        if (quiz == null) {
            System.out.println("Aucun quiz trouvé avec le thème : " + theme);
            return;
        }

        System.out.println("Modification du quiz : " + theme);

        System.out.println("Souhaitez-vous modifier le thème du quiz ? (true/false)");
        if (main.saisirBoolean(scanner)) {
            System.out.print("Nouveau thème : ");
            String nouveauTheme = scanner.nextLine();
            quiz.ModifierTheme(nouveauTheme);
        }

        System.out.println("Souhaitez-vous modifier l'auteur du quiz ? (true/false)");
        if (main.saisirBoolean(scanner)) {
            System.out.print("Nouvel auteur : ");
            String nouvelAuteur = scanner.nextLine();
            quiz.ModifierAuteur(nouvelAuteur);
        }

        System.out.println("Souhaitez-vous modifier les questions du quiz ? (true/false)");
        if (main.saisirBoolean(scanner)) {
            Question[] questions = quiz.getQuestions();

            for (int i = 0; i < questions.length; i++) {
                System.out.println("\nModification de la question " + (i + 1) + " :");
                Question question = questions[i];

                System.out.println("Texte actuel : " + question.getTexte());
                System.out.print("Nouveau texte (ou laissez vide pour ne pas modifier) : ");
                String nouveauTexte = scanner.nextLine();
                if (!nouveauTexte.isEmpty()) {
                    question.ModifierTexte(nouveauTexte);
                }

                System.out.println("Souhaitez-vous modifier les options ? (true/false)");
                if (main.saisirBoolean(scanner)) {
                    Option[] options = question.getOptions();
                    for (int j = 0; j < options.length; j++) {
                        System.out.println("Option " + (j + 1) + " actuelle : " + options[j].getTexte() + " | Correcte : " + options[j].isValide());
                        System.out.print("Nouveau texte (ou laissez vide pour ne pas modifier) : ");
                        String nouveauTexteOption = scanner.nextLine();
                        if (!nouveauTexteOption.isEmpty()) {
                            options[j].ModifierTexte(nouveauTexteOption);
                        }
                        System.out.print("Changer si l'option est correcte ? (true/false) : ");
                        options[j].Modifier_estValide(main.saisirBoolean(scanner));
                    }
                }
            }
        }

        System.out.println("Le quiz a été modifié avec succès.");
    }
    
    public void afficherStatistiques(String theme) {
        Quiz quiz = getQuizParTheme(theme);
        
        if (quiz != null) {
            System.out.println("\n=== Statistiques du Quiz : " + quiz.getTheme() + " ===");

            Question[] questions = quiz.getQuestions();
            for (int i = 0; i < questions.length; i++) {
                Question question = questions[i];
                
                int totalResponses = question.TotalReponses();
                int correctResponses = question.NombreBonnesReponses();
                double correctPercentage = question.TauxBonnesReponses();


                System.out.println("Question " + (i + 1) + ": " + question.getTexte());
                System.out.println(" - Total de réponses : " + totalResponses);
                System.out.println(" - Réponses correctes : " + correctResponses);
                System.out.printf(" - Taux de bonnes réponses : %.2f%%\n", correctPercentage);
            }
        } else {
            System.out.println("Aucun quiz trouvé avec le thème : " + theme);
        }
    }


}
