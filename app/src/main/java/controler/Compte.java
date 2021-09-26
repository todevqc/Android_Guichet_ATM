package controler;

public class Compte {
  private int numeroNIP;
  private int numeroCompte;
  private double soldeCompte;

  public Compte(int numeroNIP, int numeroCompte, double soldeCompte) {
    this.numeroNIP = numeroNIP;
    this.numeroCompte = numeroCompte;
    this.soldeCompte = soldeCompte;
  }

  public void retrait (double montant){
    this.soldeCompte -= montant;
  }

  public void depot (double montant){
    this.soldeCompte += montant;
  }

  public int getNumeroCompte() {
    return numeroCompte;
  }

  public double getSoldeCompte() {
    return soldeCompte;
  }

  public void setSoldeCompte(double soldeCompte) {
    this.soldeCompte = soldeCompte;
  }
}
