package co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import co.edu.unab.misionTIC.nivelacion_ciclo_4_reto_3_4.Entity.Libro;

public class ModificarLibroActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private CollectionReference librosRef;
    private String idLibro;
    private EditText txtCodigo, txtTitulo, txtPaginas, txtImagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_libro);
        idLibro = getIntent().getStringExtra("idLibro");
        db=FirebaseFirestore.getInstance();
        librosRef= db.collection(DataInfo.REF_LIBROS);
        setup();
        cargarDatos();
    }

    private void setup() {
        txtCodigo = findViewById(R.id.txtCodigo);
        txtTitulo = findViewById(R.id.txtTitulo);
        txtPaginas = findViewById(R.id.txtPaginas);
        txtImagen = findViewById(R.id.txtImagen);
    }

    private void cargarDatos() {
        librosRef.document(idLibro).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documento) {
                        if (documento.exists()) {
                            Libro libro = documento.toObject(Libro.class);
                            libro.setId(documento.getId());
                            mostrarDatos(libro);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ModificarLibroActivity.this,
                                "ERROR=" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    private void mostrarDatos (Libro libro){
        txtCodigo.setText(libro.getCodigo());
        txtTitulo.setText(libro.getTitulo());
        txtPaginas.setText(String.valueOf(libro.getPaginas()));
        txtImagen.setText(libro.getImagenPortada());

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
        libro.setId(idLibro);
        libro.setCodigo(codigo);
        libro.setTitulo(titulo);
        libro.setPaginas(Integer.parseInt(paginas));
        libro.setImagenPortada(imagen);

        librosRef.document(idLibro).set(libro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        setResult(RESULT_OK);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ModificarLibroActivity.this,
                                "ERROR="+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void eliminar (View view){
        librosRef.document(idLibro).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        setResult(RESULT_OK);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ModificarLibroActivity.this,
                                "ERROR="+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

    }
}