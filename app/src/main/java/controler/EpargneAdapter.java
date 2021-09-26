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

public class EpargneAdapter extends ArrayAdapter<Epargne> {

  private ArrayList<Epargne> liste_epargne;
  private Context context;
  private int viewRes;
  private Resources res;

  public EpargneAdapter(Context context, int textViewResourceId, ArrayList<Epargne> liste) {
    super(context, textViewResourceId, liste);
    this.liste_epargne = liste;
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

    final Epargne unEpargne = liste_epargne.get(position);

    if (unEpargne != null) {
      final TextView leNumero = (TextView) view.findViewById(R.id.txt_element1);
      final TextView leSolde = (TextView) view.findViewById(R.id.txt_element2);
      final ImageView imageView = (ImageView) view.findViewById(R.id.icon);
      imageView.setImageResource(R.drawable.ic_epargne);

      leNumero.setText(res.getString(R.string.numero) + " " + unEpargne.getNumeroCompte());
      leSolde.setText(res.getString(R.string.solde) + " " + unEpargne.getSoldeCompte());
    }
    return view;
  }
}