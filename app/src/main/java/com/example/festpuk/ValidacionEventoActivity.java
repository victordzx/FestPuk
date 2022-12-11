package com.example.festpuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.festpuk.databinding.ActivityValidacionEventoBinding;

public class ValidacionEventoActivity extends AppCompatActivity {

    private int PERMISOS_CODE = 100;
    private int CAMARA_CODE = 102;
    ActivityValidacionEventoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacion_evento);

        if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{Manifest.permission.CAMERA},PERMISOS_CODE);
        }

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,CAMARA_CODE);

        binding.btnEnviarFoto.setOnClickListener(v->{
            goMain();
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==PERMISOS_CODE && grantResults.length > 0){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                // Se piden los permisos nuevamente
                if (checkSelfPermission(Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{Manifest.permission.CAMERA},PERMISOS_CODE);
                }
            }
        }
    }

    private void goMain(){
        Intent intent = new Intent(this, OrganizadorMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}