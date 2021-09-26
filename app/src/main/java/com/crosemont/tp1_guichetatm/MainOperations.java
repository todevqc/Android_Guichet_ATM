package com.crosemont.tp1_guichetatm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import controler.Guichet;

public class MainOperations extends AppCompatActivity{

  static String valeur = "0";

  private MainOperations closeAppPopup;

  private static final String cleValue = "Value";
  //  Affichage du resume des comptes
  private EditText valueOperation;
  //  Radio Button  pour operation et type de compte
  private RadioButton rbOperation;
  private RadioButton rbCompte;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_operations);

    //recuperation des variables transmises depuis l'activité precedante
    Bundle extras = getIntent().getExtras();
    int nipUtilisateur = Integer.parseInt(extras.getString("nipUtilisateur"));
    Guichet guichetATM = new Guichet();

    //instantiation de l'activité de popup fermeture
    this.closeAppPopup = this;

    //pour garantir la sauvgarde du contenu de la variable au passage des modes de vues (portrait - paysage)
    if (savedInstanceState != null) {
      valeur = savedInstanceState.getString(cleValue, "0");
    }

    Button btn_Logout = (Button) findViewById(R.id.btnLogout);
    Button btn_0 = (Button) findViewById(R.id.btn0);
    Button btn_1 = (Button) findViewById(R.id.btn1);
    Button btn_2 = (Button) findViewById(R.id.btn2);
    Button btn_3 = (Button) findViewById(R.id.btn3);
    Button btn_4 = (Button) findViewById(R.id.btn4);
    Button btn_5 = (Button) findViewById(R.id.btn5);
    Button btn_6 = (Button) findViewById(R.id.btn6);
    Button btn_7 = (Button) findViewById(R.id.btn7);
    Button btn_8 = (Button) findViewById(R.id.btn8);
    Button btn_9 = (Button) findViewById(R.id.btn9);
    Button btn_Virgule = (Button) findViewById(R.id.btnVirgule);
    Button btn_C = (Button) findViewById(R.id.btnC);

    valueOperation = (EditText) findViewById(R.id.valueOperation);
    valueOperation.setEnabled(false);
    valueOperation.setText(valeur);

    //  recuperation des Radio Groupe des types d'operations et des types de comptes
    RadioGroup rgOperation = (RadioGroup) findViewById(R.id.rgOperation);
    RadioGroup rgCompte = (RadioGroup) findViewById(R.id.rgCompte);

    Button btn_Soumettre = (Button) findViewById(R.id.btnSoumettre);
    Button btn_EtatCompte = (Button) findViewById(R.id.btnEtatCompte);

    TextView resume_operation = (TextView) findViewById(R.id.resumOperation);

    /*
     *  Action du bouton de deconnexion avec popup
     */
    btn_Logout.setOnClickListener(new View.OnClickListener() {
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

    /*
     *  Actions sur les différents boutons de chiffres
     */
    btn_0.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("0"); } });
    btn_1.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("1"); } });
    btn_2.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("2"); } });
    btn_3.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("3"); } });
    btn_4.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("4"); } });
    btn_5.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("5"); } });
    btn_6.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("6"); } });
    btn_7.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("7"); } });
    btn_8.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("8"); } });
    btn_9.setOnClickListener(new View.OnClickListener() { public void onClick(View v) { saisieChiffre("9"); } });

    /*
     *  Action sur le bouton virgule avec empêchement de doublure de virgule
     *  et gestion des autres possibilités
     */
    btn_Virgule.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        if (valeur.equals("")) {
          valeur = "0,";
        }else if(!valeur.contains(".")){
          valeur = valeur + ".";
        }
        valueOperation.setText(valeur);
      }
    });

    /*
     *  Action sur le bouton c pour remise a zero du champs
     */
    btn_C.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        valeur = "0";
        valueOperation.setText(valeur);
      }
    });

    /*
     *  Action sur le bouton soumettre qui valide une operation
     *  appele aux testes et verifications avant d'autorisé une operation sur un compte
     */
    btn_Soumettre.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        rbOperation = findViewById(rgOperation.getCheckedRadioButtonId());
        rbCompte = findViewById(rgCompte.getCheckedRadioButtonId());
        double montant = Double.parseDouble(valeur);
        if(montant > 0) {

          if (rbOperation.getText().equals("Dépot")) {
            deposerMontant(guichetATM, nipUtilisateur, montant);
          }

          if (rbOperation.getText().equals("Retrait")) {
            retirerMontant(guichetATM, nipUtilisateur, montant);
          }

          if (rbOperation.getText().equals("Virement")) {
            virerMontant(guichetATM, nipUtilisateur, montant);
          }

        }else{
          errorToast("Montant = 0 $ \nentrez un nouveau montant");
        }
        resume_operation.setText("");
      }
    });

    /*
     *  Action sur le bouton Etat des comptes
     *  affiche l'etat des compte de l'utilisateuir connecté a l'application
     */
    btn_EtatCompte.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        resume_operation.setText("Solde compte Chèque : "
            +guichetATM.getListeComptesCheque().get(guichetATM.positionCompteCheque(nipUtilisateur)).getSoldeCompte()
            +" $\nSolde compte Épargne : "
            +guichetATM.getListeComptesEpargne().get(guichetATM.positionCompteEpargne(nipUtilisateur)).getSoldeCompte()+" $");
      }
    });

  }

  /*
   *  sauvegarde des données au passage du mode portrait a paysage
   */
  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putString(cleValue, valeur);
  }

  @Override
  protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    if (savedInstanceState != null) {
      valeur = savedInstanceState.getString(cleValue, "0");
    }
  }

  /*
   *  Méthode d'affichage des chiffres dans le champs du montant de l'operation
   */
  public void saisieChiffre(String unChiffre) {
    if (valeur.equals("0")) {
      valeur = unChiffre;
    } else {
      valeur = valeur + unChiffre;
    }
    valueOperation.setText(valeur);
  }

  /*
   *  Dépôt sur le compte approprié
   */
  public void deposerMontant(Guichet leGuichet, int leNip, double leMontant) {
    if (rbCompte.getText().equals("Cheque")) {
      succesToast("Dépot de " + valeur + " $\nsur le compte " + rbCompte.getText()
          + "\nnouveau solde : " + leGuichet.depotCheque(leNip, leMontant));
    } else if (rbCompte.getText().equals("Épargne")) {
      succesToast("Dépot de " + valeur + " $\nsur le compte " + rbCompte.getText()
          + "\nnouveau solde : " + leGuichet.depotEpargne(leNip, leMontant));
    }
  }

  /*
   *  Retrait depuis le compte approprié
   */
  public void retirerMontant(Guichet leGuichet, int leNip, double leMontant) {
    if(leMontant <= 1000){
      if(leMontant%10 == 0){
        if (rbCompte.getText().equals("Cheque")) {
          if(leGuichet.getListeComptesCheque().get(leGuichet.positionCompteEpargne(leNip)).getSoldeCompte()>=leMontant) {
            succesToast("Retrait de : " + valeur + " $\ndu compte " + rbCompte.getText()
                + "\nNouveau solde : "+leGuichet.retraitCheque(leNip, leMontant)+" $");
          }else{
            errorToast("Solde du compte " + rbCompte.getText() + " insuffisant\nRetrait non autorisé");
          }
        }
        if (rbCompte.getText().equals("Épargne")) {
          if(leGuichet.getListeComptesEpargne().get(leGuichet.positionCompteEpargne(leNip)).getSoldeCompte()>=leMontant) {
            succesToast("Retrait de : " + valeur + " $ \ndu compte " + rbCompte.getText()
                + "\nNouveau solde : "+leGuichet.retraitEpargne(leNip, leMontant)+" $");
          }else{
            errorToast("Solde du compte " + rbCompte.getText() + " insuffisant\nRetrait non autorisé");
          }
        }
      }else{
        errorToast("Le montant doit etre\nun multiple de 10");
      }
    }else{
      errorToast("Retrait superieur a 1000$\nnon autorisé");
    }
  }

  /*
   *  Virement depuis l’un des comptes vers le compte coché
   */
  public void virerMontant(Guichet leGuichet, int leNip, double leMontant) {
    if(leMontant <= 100000){
      if (rbCompte.getText().equals("Cheque")) {
        if(leGuichet.retraitEpargne(leNip, leMontant)>=0) {
          leGuichet.depotCheque(leNip, leMontant);
        }else{
          errorToast("Solde insuffisant\nVirement non autorisé");
        }
      }
      if (rbCompte.getText().equals("Épargne")) {
        if(leGuichet.retraitCheque(leNip, leMontant)>=0) {
          leGuichet.depotEpargne(leNip, leMontant);
        }else{
          errorToast("Solde insuffisant\nVirement non autorisé");
        }
      }
    }
  }


  /*
   *  Toast de succés
   */
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

  /*
   *  Toast d'erreur
   */
  public void errorToast(String text_content) {
    LayoutInflater inflater = getLayoutInflater();
    View layout = inflater.inflate(R.layout.toast_error, (ViewGroup)findViewById(R.id.toast_error_layout));
    TextView content = layout.findViewById(R.id.toast_error_content);
    content.setText(text_content);
    Toast toast = new Toast(getApplicationContext());
    toast.setGravity(Gravity.BOTTOM,0,100);
    toast.setDuration(Toast.LENGTH_LONG);
    toast.setView(layout);
    toast.show();
  }
}