package com.example.myrestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Activity_registrar extends AppCompatActivity {

    private EditText editTextNombre, editTextApellidos, editTextCorreo, editTextContraseña;
    private Spinner spinnerEdad;
    private Button buttonRegistrar;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);


        editTextNombre = findViewById(R.id.editTextNombre);
        editTextApellidos = findViewById(R.id.editTextApellidos);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        spinnerEdad = findViewById(R.id.spinnerEdad);
        editTextContraseña = findViewById(R.id.editTextContraseña);
        buttonRegistrar = findViewById(R.id.buttonRegistrar);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();



        List<String> edades = new ArrayList<>();
        for (int i = 9; i <= 99; i++) {
            edades.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, edades);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEdad.setAdapter(adapter);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });

    }


    private void registrarUsuario() {
        String nombre = editTextNombre.getText().toString();
        String apellidos = editTextApellidos.getText().toString();
        String correo = editTextCorreo.getText().toString();
        String edad = spinnerEdad.getSelectedItem().toString();
        String contraseña = editTextContraseña.getText().toString();


        mAuth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("control", "Llegó aquí");

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),
                            "Registro realizado correctamente!",
                            Toast.LENGTH_LONG).show();
                    agregarDatos(new Usuario(nombre,apellidos,correo,edad) ,mAuth.getCurrentUser().getUid());


                    // si el usuario se ha creado volvemos al Activity Principal para que se pueda logear
                    Intent intent
                            = new Intent(Activity_registrar.this,
                            MainActivity.class);
                    startActivity(intent);
                } else {

                    // En este punto algo ha fallado, lo notificaremos
                    Log.d("else", task.getException().toString());

                    Toast.makeText(
                                    getApplicationContext(),
                                    "El registro ha fallado!!"
                                            + " Intente mas tarde...",
                                    Toast.LENGTH_LONG)
                            .show();
                }
            }
        });



    }

    public void agregarDatos(Usuario usuario, String userID){


        Map<String, Object> usuarioNuevo = new HashMap<>();
        usuarioNuevo.put("nombre", usuario.getNombre());
        usuarioNuevo.put("correo", usuario.getCorreo());
        usuarioNuevo.put("apellido", usuario.getApellido());
        usuarioNuevo.put("edad", usuario.getEdad());

        db.collection("Usuarios").document(userID)
                .set(usuarioNuevo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Completar", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Completar", "Error writing document", e);
                    }
                });

    }

    public void atras(View view){

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}