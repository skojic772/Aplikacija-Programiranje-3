package ffos.zadatak_p3.ontologija_knjige;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


//Za zakomentiratu Filter metodu treba vratiti "implements Filterable"
public class Adapter extends RecyclerView.Adapter<Adapter.Red>  {

    private List<Ontologija_knjiga> podaci;
    private List<Ontologija_knjiga> podaciTemp;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public Adapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        podaci = new ArrayList<>();
    }

    @Override
    public Red onCreateViewHolder(ViewGroup roditelj, int viewType) {
        podaciTemp = new ArrayList<>(podaci);
        View view = mInflater.inflate(R.layout.red_liste, roditelj, false);
        return new Red(view);
    }


    @Override
    public void onBindViewHolder(Red red, int position) {
        Ontologija_knjiga o = podaci.get(position);
        red.nazivKnjige.setText(o.getNazivKnjige());
        red.autor.setText(o.getAutor());
        red.zanr.setText(o.getZanr());
        red.biljeska.setText(o.getBiljeska());

    }

    @Override
    public int getItemCount() {
        return podaci==null ? 0 : podaci.size();
    }


    // Pohranjuje i reciklira pogled kako se prolazi kroz listu
    public class Red extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView nazivKnjige;
        private TextView autor;
        private TextView zanr;
        private TextView biljeska;

        Red(View itemView) {
            super(itemView);
            nazivKnjige = itemView.findViewById(R.id.nazivKnjige);
            autor = itemView.findViewById(R.id.autor);
            zanr = itemView.findViewById(R.id.zanr);
            biljeska = itemView.findViewById(R.id.biljeska);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }


    public Ontologija_knjiga getItem(int id) {
        return podaci.get(id);
    }

    public void setPodaci(List<Ontologija_knjiga> itemList) {
        this.podaci = itemList;
    }


    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }


    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


};



