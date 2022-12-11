package co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Adapters.LibroAdapter;
import co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Entity.Libro;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference LibrosRef;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        LibrosRef = db.collection(DataInfo.REF_LIBROS);
        lista=findViewById(R.id.lista);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Libro libro = (Libro) lista.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ModificarLibroActivity.class);
                intent.putExtra("idLibro", libro.getId());
                llamadoActividad.launch(intent);

            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cargarDatos();
    }
    private void cargarDatos(){
        LibrosRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    ArrayList<Libro> libros = new ArrayList<>();
                    for(QueryDocumentSnapshot documento: task.getResult()){
                        //Log.d(DataInfo.TAG, String.valueOf(documento));
                        Libro libro = documento.toObject(Libro.class);
                        libro.setId(documento.getId());
                        libros.add(libro);
                    }
                    mostrarDatos(libros);
                }
            }
        });
    }
    private void mostrarDatos (ArrayList<Libro> libros){
        LibroAdapter adapter = new LibroAdapter(
                MainActivity.this,
                libros
        );
        lista.setAdapter(adapter);
    }
    public void salir (View view){
        finish();
    }
    public void agregar (View view){
        Intent intent = new Intent(MainActivity.this,
                AgregarLibroActivity.class);
        llamadoActividad.launch (intent);
    }
    ActivityResultLauncher<Intent> llamadoActividad = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK){
                        cargarDatos();
                    }
                }
            }
    );
}