package com.crosemont.tp1_guichetatm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import controler.Guichet;

public class MainActivity extends AppCompatActivity {

  //declaration popup fermeture
  private MainActivity closeAppPopup;

  static int essaie = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    //initialisation du guichet avec remplissage du tableau d'utilisateurs
    Guichet guichetATM = new Guichet();

    //instantiation de l'activité de popup fermeture
    this.closeAppPopup = this;

    EditText user = (EditText) findViewById(R.id.editUser);
    EditText psw = (EditText) findViewById(R.id.editNIP);

    Button btnSignin = (Button) findViewById(R.id.btnSignin);

    btnSignin.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {

        String utilisateur = user.getText().toString();
        String motpasse = psw.getText().toString();
        if (utilisateur.isEmpty() || motpasse.isEmpty()) {
          errorToast("Utilisateur et mot de passe requis");
        } else if (guichetATM.validerUtilisateur(utilisateur, motpasse)) {
          //initialisation d'un intent qui va nous transferer vers une autre activité
          Intent intentOperations = new Intent(getApplicationContext(), MainOperations.class);
          //ajout d'informations a transmettre a la nouvelle activité
          intentOperations.putExtra("nipUtilisateur", motpasse);

          startActivity(intentOperations);
          // ne pas utiliser les finish(), pour revenir a l'espace d'authentification au moment du logout
        } else if ((utilisateur.equalsIgnoreCase("Admin")) || (motpasse.equalsIgnoreCase("D001"))) {
          Intent intentAdmin = new Intent(getApplicationContext(), AdministrationSpace.class);
          startActivity(intentAdmin);
        } else {
          essaie++;
          String error_message = "Utilisateur ou mot de passe incorrect.\nil vous reste " + (3 - essaie) + " tentatives.";
          errorToast(error_message);
        }
        //  test de tentative et fermeture après 3 essaie
        if (essaie == 3) {
          AlertDialog.Builder closePopup = new AlertDialog.Builder(closeAppPopup);
          closePopup.setTitle("Alerte Securité");
          closePopup.setMessage("Vous avez utiliser des informations erroné\nveuillez essayer plus tard !");
          closePopup.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              finish();
              System.exit(0);
            }
          });
          closePopup.show();
        }
      }
    });

  }

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