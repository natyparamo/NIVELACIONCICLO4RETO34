package co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Adapters;

import static co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Entity.Libro.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Entity.Libro;
import co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.R;

public class LibroAdapter extends BaseAdapter {
    private Context context;
    private ArrayList <Libro> datos;

    public LibroAdapter(Context context, ArrayList<Libro> datos) {
        this.context = context;
        this.datos = datos;
    }


    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return this.datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_libro, null);
        }

        Libro libro = datos.get (position);
        TextView lbTitulo = convertView.findViewById(R.id.lbTitulo);
        TextView lbCodigo = convertView.findViewById(R.id.lbCodigo);
        TextView lbPaginas = convertView.findViewById(R.id.lbPaginas);
        ImageView imagen= convertView.findViewById(R.id.imagenView);

        lbTitulo.setText(getTitulo());
        lbCodigo.setText(getCodigo());
        lbPaginas.setText(String.valueOf(getPaginas()));

        Picasso.get()
                .load(Libro.getImagenPortada())
                .resize(50, 50)
                .centerCrop()
                .into(imagen);






        return convertView;
    }
}
