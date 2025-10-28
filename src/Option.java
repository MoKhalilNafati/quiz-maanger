public class Option {
    private String texte;
    private boolean estValide; //vrai ou faux

    public Option(String texte, boolean estValide) {
        this.texte = texte;
        this.estValide = estValide;
    }
    
    public void ModifierTexte(String nouveauTexte) {
        this.texte = nouveauTexte;
    }
    
    public void Modifier_estValide(boolean nouveau_estValide) {
        this.estValide = nouveau_estValide;
    }

    public String getTexte() {
        return texte;
    }

    public boolean isValide() {
        return estValide;
    }
}
