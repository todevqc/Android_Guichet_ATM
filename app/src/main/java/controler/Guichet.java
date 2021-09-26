package controler;

import java.io.Serializable;
import java.util.ArrayList;

public class Guichet implements Serializable {
  private ArrayList<Client> listeClients = new ArrayList<>();
  private ArrayList<Cheque> listeComptesCheque = new ArrayList<>();
  private ArrayList<Epargne> listeComptesEpargne = new ArrayList<>();

  public Guichet() {
    initialiserGuichet();
  }

  public void initialiserGuichet() {

    // REMPLISSAGE COMPLETEMENT ARBITRAIRE DES VARIABLES ArrayListe

    //    Creation des clients
    this.listeClients.add(new Client("eve", "Gagnant", "eve", 123));
    this.listeClients.add(new Client("alice", "margoton", "alice", 456));
    this.listeClients.add(new Client("bob", "bartrat", "bob", 789));

    //    Creation des comptes bancaires cheques
    listeComptesCheque.add(new Cheque(listeClients.get(0).getNumeroNIP(), listeClients.get(0).getNumeroNIP(), 5400));
    listeComptesCheque.add(new Cheque(listeClients.get(1).getNumeroNIP(), listeClients.get(1).getNumeroNIP(), 2500));
    listeComptesCheque.add(new Cheque(listeClients.get(2).getNumeroNIP(), listeClients.get(2).getNumeroNIP(), 8500));

    //    Creation des comptes bancaires epargnes
    listeComptesEpargne.add(new Epargne(listeClients.get(0).getNumeroNIP(), listeClients.get(0).getNumeroNIP(), 100));
    listeComptesEpargne.add(new Epargne(listeClients.get(1).getNumeroNIP(), listeClients.get(1).getNumeroNIP(), 1000));
    listeComptesEpargne.add(new Epargne(listeClients.get(2).getNumeroNIP(), listeClients.get(2).getNumeroNIP(), 10000));

  }

  public boolean validerUtilisateur(String nomUtilisateur, String nip) {
    for (Client unClient : listeClients) {
      if (unClient.getNomUtilisateur().equalsIgnoreCase(nomUtilisateur)
          && String.valueOf(unClient.getNumeroNIP()).equalsIgnoreCase(nip)) {
        return true;
      }
    }
    return false;
  }

  public double retraitCheque(int nip, double montant) {
    int position = positionCompteCheque(nip);
    listeComptesCheque.get(position).retrait(montant);
    return listeComptesCheque.get(position).getSoldeCompte();
  }

  public double retraitEpargne(int nip, double montant) {
    int position = positionCompteEpargne(nip);
    listeComptesEpargne.get(position).retrait(montant);
    return listeComptesEpargne.get(position).getSoldeCompte();
  }

  public double depotCheque(int nip, double montant) {
    int position = positionCompteCheque(nip);
    listeComptesCheque.get(position).depot(montant);
    return listeComptesCheque.get(position).getSoldeCompte();
  }

  public double depotEpargne(int nip, double montant) {
    int position = positionCompteEpargne(nip);
    listeComptesEpargne.get(position).depot(montant);
    return listeComptesEpargne.get(position).getSoldeCompte();
  }


  public ArrayList<Client> getListeClients() { return listeClients; }

  public ArrayList<Cheque> getListeComptesCheque() { return listeComptesCheque; }

  public ArrayList<Epargne> getListeComptesEpargne() { return listeComptesEpargne; }

  public int positionCompteCheque(int nip){
    for (int i = 0; i < listeComptesCheque.size() ; i++){
      if(listeComptesCheque.get(i).getNumeroCompte() == nip){
        return i;
      }
    }
    return -1;
  }

  public int positionCompteEpargne(int nip){
    for (int i = 0; i < listeComptesEpargne.size() ; i++){
      if(listeComptesEpargne.get(i).getNumeroCompte() == nip){
        return i;
      }
    }
    return -1;
  }
}
