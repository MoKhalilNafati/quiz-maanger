import java.util.ArrayList;

public class Quiz {
    private String theme;
    private String auteur;
    private Question[] questions; // Liste des questions
    private ArrayList<Etudiant> participants; // Liste des étudiants participants

    // Constructeur
    public Quiz(String theme, String auteur, Question[] questions) {
        this.theme = theme;
        this.auteur = auteur;
        this.questions = questions;
        this.participants = new ArrayList<>();
    }

    // Méthodes pour modifier le thème ou l'auteur
    public void ModifierTheme(String theme) {
        this.theme = theme;
    }

    public void ModifierAuteur(String auteur) {
        this.auteur = auteur;
    }

    // Getters
    public String getTheme() {
        return theme;
    }

    public String getAuteur() {
        return auteur;
    }

    public Question[] getQuestions() {
        return questions;
    }

    // Ajouter un étudiant participant
    public void ajouterParticipant(Etudiant etudiant) {
        participants.add(etudiant);
    }

    // Afficher les résultats des participants
    public void afficherResultats() {
        if (participants.isEmpty()) {
            System.out.println("Aucun participant pour ce quiz.");
        } else {
            System.out.println("\nRésultats des étudiants pour le quiz : " + theme);
            for (Etudiant etudiant : participants) {
                System.out.println(etudiant + " | Score : " + etudiant.getScore(theme) + "/" + etudiant.getScoreMax(theme));
            }
        }
    }

    // Afficher la correction du quiz
    public void afficherCorrection() {
        System.out.println("\nCorrection du quiz : " + theme);
        for (Question question : questions) {
            question.afficherCorrection();
        }
    }


}
