package com.crosemont.tp1_guichetatm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import controler.Guichet;

public class AdministrationSpace extends AppCompatActivity {

  private AdministrationSpace closeAppPopup;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_administration_space);

    //instantiation de l'activité de popup fermeture
    this.closeAppPopup = this;

    Guichet guichetATM = new Guichet();

    Button btnLogout = (Button) findViewById(R.id.btn_Logout);
    Button btnInteret = (Button) findViewById(R.id.btn_interet);
    Button btnCompteCheque = (Button) findViewById(R.id.btn_compte_cheque);
    Button btnCompteEpargne = (Button) findViewById(R.id.btn_compte_epargne);
    Button btnListeClient = (Button) findViewById(R.id.btn_liste_client);

    btnLogout.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        AlertDialog.Builder closePopup = new AlertDialog.Builder(closeAppPopup);
        closePopup.setTitle(Html.fromHtml("<font color='#C22B31'>Quitter</font>"));
        closePopup.setMessage(Html.fromHtml("<font color='#00'>Voulez vous vraiment quitter l'application?</font>"));
        closePopup.setPositiveButton(Html.fromHtml("<font color='#1892AD'>OUI</font>"), new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int arg1) {
            finish();
            System.exit(0);
          }
        });
        closePopup.setNegativeButton(Html.fromHtml("<font color='#1892AD'>NON</font>"), new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int arg1) {
          }
        });
        closePopup.create();
        closePopup.show();
      }
    });

    btnInteret.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        String affiche = "Paiement des intérêts\n";
        for (int i = 0; i < guichetATM.getListeComptesEpargne().size() ; i++){
          guichetATM.getListeComptesEpargne().get(i).paiementInterets();
          affiche +=  "Compte n°: "+guichetATM.getListeComptesEpargne().get(i).getNumeroCompte()
              +" - Solde : "+ guichetATM.getListeComptesEpargne().get(i).getSoldeCompte()+"\n";
        }
        succesToast(affiche);
      }
    });

    btnCompteCheque.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intentListeCheque = new Intent(getApplicationContext(), ListeActivity.class);
        //ajout d'informations a transmettre a la nouvelle activité
        intentListeCheque.putExtra("liste", "compteCheque");
        startActivity(intentListeCheque);
      }
    });

    btnCompteEpargne.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intentListeCheque = new Intent(getApplicationContext(), ListeActivity.class);
        //ajout d'informations a transmettre a la nouvelle activité
        intentListeCheque.putExtra("liste", "compteEpargne");
        startActivity(intentListeCheque);
      }
    });

    btnListeClient.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        Intent intentListeClient = new Intent(getApplicationContext(), ListeActivity.class);
        //ajout d'informations a transmettre a la nouvelle activité
        intentListeClient.putExtra("liste", "client");
        startActivity(intentListeClient);
      }
    });
  }


  public void succesToast(String text_content) {
    LayoutInflater inflater = getLayoutInflater();
    View layout = inflater.inflate(R.layout.toast_succes, (ViewGroup)findViewById(R.id.toast_succes_layout));
    TextView content = layout.findViewById(R.id.toast_succes_content);
    content.setText(text_content);
    Toast toast = new Toast(getApplicationContext());
    toast.setGravity(Gravity.BOTTOM,0,100);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setView(layout);
    toast.show();
  }
}