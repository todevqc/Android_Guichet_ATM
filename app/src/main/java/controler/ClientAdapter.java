package controler;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.crosemont.tp1_guichetatm.R;

import java.util.ArrayList;

public class ClientAdapter extends ArrayAdapter<Client> {

  private ArrayList<Client> liste_client;
  private Context context;
  private int viewRes;
  private Resources res;

  public ClientAdapter(Context context, int textViewResourceId, ArrayList<Client> liste) {
    super(context, textViewResourceId, liste);
    this.liste_client = liste;
    this.context = context;
    this.viewRes = textViewResourceId;
    this.res = context.getResources();
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    View view = convertView;
    if (view == null) {
      LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context. LAYOUT_INFLATER_SERVICE);
      view = layoutInflater.inflate(viewRes, parent, false);
    }

    final Client unClient = liste_client.get(position);

    if (unClient != null) {
      final TextView leNom = (TextView) view.findViewById(R.id.txt_element1);
      final TextView lePrenom = (TextView) view.findViewById(R.id.txt_element2);
      final ImageView imageView = (ImageView) view.findViewById(R.id.icon);
      imageView.setImageResource(R.drawable.ic_client);


      leNom.setText(res.getString(R.string.nom) + " " + unClient.getNom());
      lePrenom.setText(res.getString(R.string.prenom) + " " + unClient.getPrenom());
    }
    return view;
  }
}