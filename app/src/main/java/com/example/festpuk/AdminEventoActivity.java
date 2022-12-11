package com.example.festpuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.festpuk.databinding.ActivityAdminEventoBinding;
import com.example.festpuk.databinding.ActivityFormEventoBinding;
import com.example.festpuk.databinding.FragmentOrgaEventosBinding;

public class AdminEventoActivity extends AppCompatActivity {

    ActivityAdminEventoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buCrearEvento.setOnClickListener(v -> {
            startActivity(new Intent(AdminEventoActivity.this, ActivityFormEvento.class));
        });
    }
}