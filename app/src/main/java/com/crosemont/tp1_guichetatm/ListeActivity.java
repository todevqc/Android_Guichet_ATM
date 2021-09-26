package com.crosemont.tp1_guichetatm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import controler.ChequeAdapter;
import controler.ClientAdapter;
import controler.EpargneAdapter;
import controler.Guichet;

public class ListeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_liste);

    Guichet guichetATM = new Guichet();
    Bundle extras = getIntent().getExtras();
    String typeListe = extras.getString("liste");
    if(typeListe.equalsIgnoreCase("client")){

      ClientAdapter adapter = new ClientAdapter(this, R.layout.element_liste_layout, guichetATM.getListeClients());
      final ListView liste = (ListView) findViewById(R.id.listeContainer);
      final TextView listeTitle = (TextView) findViewById(R.id.listeTitle);

      listeTitle.setText("Liste des clients");
      liste.setAdapter(adapter);

    }else if(typeListe.equalsIgnoreCase("compteCheque")){

      ChequeAdapter adapter = new ChequeAdapter(this, R.layout.element_liste_layout, guichetATM.getListeComptesCheque());
      final ListView liste = (ListView) findViewById(R.id.listeContainer);
      final TextView listeTitle = (TextView) findViewById(R.id.listeTitle);

      listeTitle.setText("Liste des comptes chéques");
      liste.setAdapter(adapter);
    }else if(typeListe.equalsIgnoreCase("compteEpargne")){

      EpargneAdapter adapter = new EpargneAdapter(this, R.layout.element_liste_layout, guichetATM.getListeComptesEpargne());
      final ListView liste = (ListView) findViewById(R.id.listeContainer);
      final TextView listeTitle = (TextView) findViewById(R.id.listeTitle);

      listeTitle.setText("Liste des comptes épargnes");
      liste.setAdapter(adapter);
    }

  }
}