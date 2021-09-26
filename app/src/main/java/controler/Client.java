package controler;

public class Client {
  private String nom;
  private String prenom;
  private String nomUtilisateur;
  private int numeroNIP;

  public Client(String nom, String prenom, String nomUtilisateur, int numeroNIP){
    this.nom = nom;
    this.prenom = prenom;
    this.nomUtilisateur = nomUtilisateur;
    this.numeroNIP = numeroNIP;
  }

  public String getNom(){
    return nom;
  }

  public String getPrenom(){
    return prenom;
  }

  public String getNomUtilisateur(){
    return nomUtilisateur;
  }

  public int getNumeroNIP(){
    return numeroNIP;
  }

  @Override
  public String toString(){
    return "Nom Client :    " + nom +
        "\nPrenom Client : " + prenom;
  }
}
