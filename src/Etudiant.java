public class Etudiant {
    private String nom;
    private String prenom;
    private int ncin;
    private String[] themes;
    private int[] scores;
    private int[] scoresMax;
    private int nombreQuiz;

    public Etudiant(String nom, String prenom, int ncin) {
        this.nom = nom;
        this.prenom = prenom;
        this.ncin = ncin;
        this.themes = new String[100];
        this.scores = new int[100];
        this.scoresMax = new int[100];
        this.nombreQuiz = 0;
    }

    public void setScore(String theme, int score, int scoreMax) {

        if (nombreQuiz < themes.length) {
            themes[nombreQuiz] = theme;
            scores[nombreQuiz] = score;
            scoresMax[nombreQuiz] = scoreMax;
            nombreQuiz++;
        } else {
            System.out.println("Impossible d'ajouter un nouveau score : limite atteinte.");
        }
    }

    public int getScore(String theme) {
        for (int i = 0; i < nombreQuiz; i++) {
            if (themes[i].equalsIgnoreCase(theme)) {
                return scores[i];
            }
        }
        return -1; 
    }
    public int getScoreMax(String theme) {
        for (int i = 0; i < nombreQuiz; i++) {
            if (themes[i].equalsIgnoreCase(theme)) {
                return scoresMax[i];
            }
        }
        return -1; 
    }

    public boolean DejaParticipe(String theme) {
        for (int i = 0; i < nombreQuiz; i++) {
            if (themes[i].equalsIgnoreCase(theme)) {
                return true;
            }
        }
        return false;
    }

    public void afficherScores() {
        if (nombreQuiz == 0) {
            System.out.println("Aucun score enregistré.");
        } else {
            System.out.println("Vos scores :");
            for (int i = 0; i < nombreQuiz; i++) {
                System.out.println("Quiz : " + themes[i] + " | Score : " + scores[i] +" / "+ scoresMax[i]);
            }
        }
    }
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getNcin() {
        return ncin;
    }
    
    @Override
    public String toString() {
        return prenom + " " + nom + " | NCIN: " + ncin;
    }


}
