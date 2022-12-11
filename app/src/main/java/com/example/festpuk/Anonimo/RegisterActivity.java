package com.example.festpuk.Anonimo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.festpuk.ClienteMainActivity;
import com.example.festpuk.OrganizadorMainActivity;
import com.example.festpuk.R;
import com.example.festpuk.databinding.ActivityRegisterBinding;
import com.example.festpuk.entity.Cuenta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.arrowBack.setOnClickListener(v -> finish());
        binding.buContinuar.setOnClickListener(v -> saverUser());
    }

    private void saverUser() {
        String nombreapellido = binding.edNombreApellido.getText().toString().trim();
        String fechanac = binding.edFechaNacimiento.getText().toString().trim();
        String dnistring = binding.edDNI.getText().toString().trim();
        int dni = Integer.parseInt(dnistring);
        String email = binding.edCorreo.getText().toString().trim();
        String pass1 = binding.edPassword1.getText().toString();
        String pass2 = binding.edPassword2.getText().toString();

        int radioButtonGender = binding.raGenero.getCheckedRadioButtonId();
        RadioButton radioButton1 = binding.raGenero.findViewById(radioButtonGender);

        int radioButtonRol = binding.raRol.getCheckedRadioButtonId();
        RadioButton radioButton2 = binding.raRol.findViewById(radioButtonRol);

        boolean error = false;

        if (radioButton1 == null) {
            error=true;
            Toast.makeText(this, "No se ha seleccionado género", Toast.LENGTH_SHORT).show();
        }

        if (radioButton2 == null) {
            error=true;
            Toast.makeText(this, "No se ha seleccionado rol", Toast.LENGTH_SHORT).show();
        }


        if (!pass1.equals(pass2)) {
            Toast.makeText(this, "Las contraseñas no concuerdan", Toast.LENGTH_SHORT).show();
            return;
        }
        if(email.isEmpty()){
            binding.edCorreo.setError("Llene este campo");
        }

        if (dnistring.length() != 8) {
            binding.edDNI.setError("Llene correctamente el campo");
            error = true;
        }
        if (nombreapellido.isEmpty()) {
            error = true;
            binding.edNombreApellido.setError("Llene este campo");
        }
        if (fechanac.isEmpty()) {
            error = true;
            binding.edFechaNacimiento.setError("Llene este campo");
        }
        if (email.isEmpty()) {
            error = true;
            binding.edCorreo.setError("Llene este campo");
        }
        if (pass1.isEmpty()) {
            error = true;
            binding.edPassword1.setError("Llene el campo");
            binding.edPassword2.setError("Llene el campo");
        }
        if (error) {
            return;
        }

        String gender = radioButton1.getText().toString();
        String rol = radioButton2.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(t -> {
                    if (t.isSuccessful()) {
                        Cuenta cuenta = new Cuenta(rol, email, nombreapellido, fechanac, gender, dni);
                        FirebaseDatabase.getInstance().getReference("cuenta").child(FirebaseAuth.getInstance().getUid()).setValue(cuenta);
                        Toast.makeText(this, "Se ha creado la cuenta", Toast.LENGTH_SHORT).show();
                        if(Objects.equals(cuenta.getRol(), "Organizador")){
                            goHomeOrga();
                        } else{
                            goHomeCliente();
                        };
                    } else
                        Toast.makeText(this, "Tu contraseña es muy debil", Toast.LENGTH_SHORT).show();
                }
        );
    }

    private void goHomeOrga(){
        Intent intent = new Intent(this, OrganizadorMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void goHomeCliente(){
        Intent intent = new Intent(this, ClienteMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}