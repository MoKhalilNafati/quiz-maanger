import java.util.ArrayList;

public class Question {
    private int numero;               // Identifiant de la question
    private String texte;             // Le texte de la question
    private Option[] options;         // Liste des options pour cette question
    private ArrayList<Integer> reponsesEtudiants; // Liste des réponses des étudiants

    public Question(int numero, String texte, Option[] options) {
        this.numero = numero;
        this.texte = texte;
        this.options = options;
        this.reponsesEtudiants = new ArrayList<>(); // Initialisation de l'ArrayList
    }

    public void ModifierTexte(String nouveauTexte) {
        this.texte = nouveauTexte;
    }

    public int getNumero() {
        return numero;
    }

    public String getTexte() {
        return texte;
    }

    public Option[] getOptions() {
        return options;
    }

    // Méthode pour afficher la question et ses options
    public void afficherQuestion() {
        System.out.println("Question " + numero + ": " + texte);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i].getTexte());
        }
    }

    // Méthode pour afficher la correction de la question
    public void afficherCorrection() {
        System.out.println("Correction de la question " + numero + ": " + texte);
        for (int i = 0; i < options.length; i++) {
            String texteOption = options[i].getTexte();
            if (options[i].isValide()) {
                System.out.println((i + 1) + ". " + texteOption + " (Correcte)");
            } else {
                System.out.println((i + 1) + ". " + texteOption);
            }
        }
    }

    // Méthode pour vérifier si la réponse d'un étudiant est correcte
    public boolean estBonneReponse(int reponse) {
        if (reponse < 1 || reponse > options.length) {
            System.out.println("Réponse invalide. Veuillez entrer un numéro valide.");
            return false;
        }
        return options[reponse - 1].isValide();
    }

    // Méthode pour ajouter la réponse d'un étudiant
    public void ajouterReponseEtudiant(int reponse) {
        reponsesEtudiants.add(reponse);
    }


    //le nombre total de réponses
    public int TotalReponses() {
        return reponsesEtudiants.size();
    }

    //le nombre de bonnes réponses
    public int NombreBonnesReponses() {
        int bonnesReponses = 0;
        for (int i = 0; i < reponsesEtudiants.size(); i++) {
            int reponse = reponsesEtudiants.get(i);
            if (estBonneReponse(reponse)) {
                bonnesReponses++;
            }
        }

        return bonnesReponses;
    }

    // Getter pour le taux des bonnes réponses en pourcentage
    public double TauxBonnesReponses() {
        int totalReponses = reponsesEtudiants.size();
        
        if (totalReponses == 0) return 0.0;
        
        int bonnesReponses = NombreBonnesReponses();
        return (double) bonnesReponses / totalReponses * 100;
    }
}
