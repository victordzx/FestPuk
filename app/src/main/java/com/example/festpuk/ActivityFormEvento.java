package com.example.festpuk;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.festpuk.databinding.ActivityFormEventoBinding;
import com.example.festpuk.entity.Evento;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ActivityFormEvento extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseStorage storage;
    StorageReference storageReference;
    EditText nombreEvento, descripcionEvento, fechaEvento, ubicacionEvento, contactoEvento,precioEntrada, stock, fotourl;
    ActivityFormEventoBinding binding;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFormEventoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        nombreEvento = findViewById(R.id.edNombreEvento);
        descripcionEvento = findViewById(R.id.edDescripcionEvento);
        fechaEvento = findViewById(R.id.edFechaEvento);
        ubicacionEvento = findViewById(R.id.edUbicacionEvento);
        contactoEvento = findViewById(R.id.edContactoEvento);
        precioEntrada = findViewById(R.id.edPrecioEntrada);
        stock = findViewById(R.id.edStock);
        setupSubirImagen();

        binding.buSiguiente.setOnClickListener(v -> {
            AnadirEvento();
            startActivity(new Intent(ActivityFormEvento.this, ValidacionEventoActivity.class));
        });
    }

    private void setupSubirImagen(){
        binding.subirFotoEvento.setOnClickListener(v->{
            subirImagen();
        });
    }

    private void subirImagen(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        galleryLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> galleryLauncher
            = registerForActivityResult(
            new ActivityResultContracts
                    .StartActivityForResult(),
            result -> {
                if (result.getResultCode()
                        == RESULT_OK) {
                    Intent data = result.getData();
                    // do your operation from here....
                    if (data != null
                            && data.getData() != null) {
                        Uri selectedImageUri = data.getData();
                        Bitmap selectedImageBitmap;
                        try {
                            selectedImageBitmap
                                    = MediaStore.Images.Media.getBitmap(
                                    this.getContentResolver(),
                                    selectedImageUri);
                            binding.subirFotoEvento.setImageBitmap(
                                    selectedImageBitmap);

                            binding.subirIcon.setVisibility(View.GONE);
                            binding.subirTexto.setVisibility(View.GONE);
                            imageUri = selectedImageUri;
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

    public void AnadirEvento(){
        String nombreEventoStr = nombreEvento.getText().toString().trim();
        String descripcionEventoStr = descripcionEvento.getText().toString().trim();
        String fechaEventoStr = fechaEvento.getText().toString().trim();
        String ubicacionEventoStr = ubicacionEvento.getText().toString().trim();
        String contactoEventoStr = contactoEvento.getText().toString().trim();
        String precioEntradaStr = precioEntrada.getText().toString().trim();
        double precioEntradaDouble = Double.parseDouble(precioEntradaStr);
        String stockStr = stock.getText().toString().trim();
        int stockInt = Integer.parseInt(stockStr);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        DatabaseReference refeventos = ref.child("evento");

        Evento evento = new Evento(nombreEventoStr, fechaEventoStr, descripcionEventoStr, ubicacionEventoStr, contactoEventoStr, precioEntradaDouble, stockInt, imageUri.toString());

        String imagenesEventoStr = imageUri.toString();

        String key = refeventos.push().getKey();
        refeventos.child(key).child("titulo").setValue(nombreEventoStr);
        refeventos.child(key).child("contacto").setValue(contactoEventoStr);
        refeventos.child(key).child("descripcion").setValue(descripcionEventoStr);
        refeventos.child(key).child("ubicacion").setValue(ubicacionEventoStr);
        refeventos.child(key).child("stock").setValue(stockInt);
        refeventos.child(key).child("fecha").setValue(fechaEventoStr);
        refeventos.child(key).child("fotourl").setValue(imagenesEventoStr);
        refeventos.child(key).child("precio").setValue(precioEntradaDouble);

        Toast.makeText(ActivityFormEvento.this, "Guardado!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}