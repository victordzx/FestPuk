package com.example.festpuk.Anonimo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.festpuk.R;
import com.example.festpuk.databinding.ActivityRecuperarContraBinding;
import com.google.firebase.auth.FirebaseAuth;

public class RecuperarContraActivity extends AppCompatActivity {

    ActivityRecuperarContraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperarContraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buContinuar.setOnClickListener(v->{
            String email = binding.edMail.getText().toString().trim();
            if(email.isEmpty()){
                Toast.makeText(this, "Ingrese correo", Toast.LENGTH_SHORT).show();
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        Toast.makeText(this, "Se ha enviado el link de recuperación", Toast.LENGTH_SHORT).show();
                        finish();
                    } else{
                        Toast.makeText(this, "No se ha podido enviar el correo", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });
    }
}