package controler;

public class Epargne extends Compte{

  final double tauxInteret = 1.0125;

  public Epargne(int numeroNIP, int numeroCompte, double soldeCompte){
    super(numeroNIP, numeroCompte, soldeCompte);
  }

  public void paiementInterets(){
    this.setSoldeCompte(this.getSoldeCompte()*tauxInteret);
  }
    
}
