package com.example.myaudiobooksfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Vector;

public class AdaptadorLibros extends RecyclerView.Adapter<AdaptadorLibros.ViewHolder>{
    private  Vector<Libro> vectorLibros;
    private  Context contexto;
    private  LayoutInflater inflador;

    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.onLongClickListener = onLongClickListener;
    }

    public void setOnclickListener(View.OnClickListener onclickListener) {
        this.onclickListener = onclickListener;
    }

    private View.OnLongClickListener onLongClickListener;
    private View.OnClickListener onclickListener;


    public AdaptadorLibros(Context contexto,
                           Vector<Libro> vectorLibros) {
        inflador = (LayoutInflater) contexto.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        this.vectorLibros = vectorLibros;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = inflador.inflate(R.layout.item_list, null);

        v.setOnClickListener(this.onclickListener);
        v.setOnLongClickListener(this.onLongClickListener);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Libro libro = vectorLibros.get(position);

        holder.portada.setImageResource(libro.recursoImagen);
        holder.titulo.setText(libro.titulo);
    }

    @Override
    public int getItemCount() {
        return vectorLibros.size();
    }

    //Creamos nuestro ViewHolder, con los tipos de elementos a modificar
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView portada;
        public TextView titulo;

        public ViewHolder(View itemView)
        {
            super(itemView);
            portada = (ImageView) itemView.findViewById(R.id.portada);
            portada.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            titulo = (TextView) itemView.findViewById(R.id.titulo);
        }
    }

    public static class MainActivity extends AppCompatActivity {
        SelectorFragment listaLibros;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            listaLibros=new SelectorFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragment,listaLibros).commit();
        }
    }

    public static class Libro {
        private String titulo;
        private String autor;
        private int recursoImagen;
        private String urlAudio;
        private String genero;   // Género literario
        private Boolean novedad; // Es una novedad
        private Boolean leido;
        // Leído por el usuario
        public final static String G_TODOS = "Todos los géneros";
        public final static String G_EPICO = "Poema épico";
        public final static String G_S_XIX = "Literatura siglo XIX";
        public final static String G_SUSPENSE = "Suspense";

        public final static String[] G_ARRAY = new String[]{G_TODOS, G_EPICO, G_S_XIX, G_SUSPENSE};

        public Libro() {}
        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getAutor() {
            return autor;
        }

        public void setAutor(String autor) {
            this.autor = autor;
        }

        public int getRecursoImagen() {
            return recursoImagen;
        }

        public void setRecursoImagen(int recursoImagen) {
            this.recursoImagen = recursoImagen;
        }

        public String getUrlAudio() {
            return urlAudio;
        }

        public void setUrlAudio(String urlAudio) {
            this.urlAudio = urlAudio;
        }

        public String getGenero() {
            return genero;
        }

        public void setGenero(String genero) {
            this.genero = genero;
        }

        public Boolean getNovedad() {
            return novedad;
        }

        public void setNovedad(Boolean novedad) {
            this.novedad = novedad;
        }

        public Boolean getLeido() {
            return leido;
        }

        public void setLeido(Boolean leido) {
            this.leido = leido;
        }

        public Libro(String titulo, String autor,
                     int recursoImagen, String urlAudio,
                     String genero, Boolean novedad,
                     Boolean leido) {
            this.titulo = titulo;
            this.autor = autor;
            this.recursoImagen = recursoImagen;
            this.urlAudio = urlAudio;
            this.genero = genero;
            this.novedad = novedad;
            this.leido = leido;
        }
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link SelectorFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class SelectorFragment extends Fragment {
        ArrayList<Libro> lista;
        public RecyclerView recyclerLibro;

        public GridLayoutManager layoutManager;
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public SelectorFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentLibros.
         */
        // TODO: Rename and change types and number of parameters
        public static SelectorFragment newInstance(String param1, String param2) {
            SelectorFragment fragment = new SelectorFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            View vista=inflater.inflate(R.layout.fragment_libros,container,false);
            lista=new ArrayList<>();
            recyclerLibro= (RecyclerView) vista.findViewById(R.id.recyclerId);
            recyclerLibro.setLayoutManager((new LinearLayoutManager(getContext())));
            llenarDatos();
            AdpatadorLibro adpter=new AdpatadorLibro(lista);
            recyclerLibro.setAdapter(adpter);
            adpter.setClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(),"Selecciona: "+lista.get(recyclerLibro
                            .getChildAdapterPosition(view)).getTitulo(),Toast.LENGTH_SHORT).show();
                }
            });
            return vista;
        }

        private void llenarDatos() {
            final String SERVIDOR =
                    "http://www.dcomg.upv.es/~jtomas/android/audiolibros/";
            lista.add(new Libro("Kappa", "Akutagawa", R.drawable.kappa, SERVIDOR + "kappa.mp3", Libro.G_S_XIX, false, false));
            lista.add(new Libro("Avecilla", "Alas Clarín, Leopoldo", R.drawable.avecilla, SERVIDOR + "avecilla.mp3", Libro.G_S_XIX, true, false));
            lista.add(new Libro("Divina Comedia", "Dante", R.drawable.divina_comedia, SERVIDOR + "divina_comedia.mp3", Libro.G_EPICO, true, false));
            lista.add(new Libro("Viejo Pancho, El", "Alonso y Trelles, José", R.drawable.viejo_pancho, SERVIDOR + "viejo_pancho.mp3", Libro.G_S_XIX, true, true));
            lista.add(new Libro("Canción de Rolando", "Anónimo", R.drawable.cancion_rolando, SERVIDOR + "cancion_rolando.mp3", Libro.G_EPICO, false, true));
            lista.add(new Libro("Matrimonio de sabuesos", "Agata Christie", R.drawable.matrim_sabuesos, SERVIDOR + "matrim_sabuesos.mp3", Libro.G_SUSPENSE, false, true));
            lista.add(new Libro("La iliada", "Homero", R.drawable.la_iliada, SERVIDOR + "la_iliada.mp3", Libro.G_EPICO, true, false));

        }
    }

    public static class AdpatadorLibro extends RecyclerView.Adapter<AdpatadorLibro.ViewHolderLibros> implements View.OnClickListener {

        ArrayList<Libro> libros;
        private View.OnClickListener listener;
        public AdpatadorLibro(ArrayList<Libro> libros){
            this.libros=libros;
        }
        @NonNull
        @Override
        public ViewHolderLibros onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);
            vista.setOnClickListener(this);
            return new ViewHolderLibros(vista);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolderLibros holder, int position) {
            holder.Nombre.setText(libros.get(position).getTitulo());
            holder.imagen.setImageResource(libros.get(position).getRecursoImagen());
        }

        @Override
        public int getItemCount() {
            return libros.size();
        }

        @Override
        public void onClick(View view) {
            if (listener!=null){
                listener.onClick(view);
            }
        }
        public void setClickListener(View.OnClickListener listener){
            this.listener=listener;
        }

        public class ViewHolderLibros extends RecyclerView.ViewHolder {
            ImageView imagen;
            TextView Nombre;
            public ViewHolderLibros(@NonNull View itemView) {
                super(itemView);
                imagen=(ImageView) itemView.findViewById(R.id.portada);
                Nombre=(TextView) itemView.findViewById(R.id.titulo);
            }
        }
    }
}
