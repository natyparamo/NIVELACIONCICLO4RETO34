package co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Entity.Libro;

public class AgregarLibroActivity extends AppCompatActivity {
    private EditText txtCodigo, txtTitulo, txtPaginas, txtImagen;
    private FirebaseFirestore db;
    private CollectionReference libroRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_libro);
        db=FirebaseFirestore.getInstance();
        libroRef= db.collection(DataInfo.REF_LIBROS);
        setup();
    }
    private void setup (){
        txtCodigo=findViewById(R.id.txtCodigo);
        txtTitulo=findViewById(R.id.txtTitulo);
        txtPaginas= findViewById(R.id.txtPaginas);
        txtImagen=findViewById(R.id.txtImagen);
    }

    public void volver (View view){
        setResult(RESULT_CANCELED);
        finish();
    }
    public void guardar (View view){
        String codigo = txtCodigo.getText().toString();
        String titulo= txtTitulo.getText().toString();
        String paginas = txtPaginas.getText().toString();
        String imagen = txtImagen.getText().toString();

        Libro libro = new Libro();
        libro.setCodigo(codigo);
        libro.setTitulo(titulo);
        libro.setPaginas(Integer.parseInt(paginas));
        libro.setImagenPortada(imagen);

        libroRef.add(libro)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        setResult(RESULT_OK);
                        finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AgregarLibroActivity.this,
                                "ERROR="+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
}