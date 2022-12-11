package com.example.festpuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.festpuk.cliente.ClienteEntradasFragment;
import com.example.festpuk.cliente.ClienteEventosFragment;
// import com.example.festpuk.cliente.ClienteHistorialFragment;
import com.example.festpuk.cliente.ClientePerfilFragment;
import com.example.festpuk.databinding.*;
import com.example.festpuk.cliente.ClienteEventosFragment;
import com.example.festpuk.save.ClienteSession;
import com.example.festpuk.save.Session;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;


public class ClienteMainActivity extends AppCompatActivity {

    ActivityClienteMainBinding binding;

    ClienteEventosFragment eventosFragment;
    ClienteEntradasFragment entradasFragment;
    ClientePerfilFragment perfilFragment;

    public static int navValue = 0;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClienteMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //PushNotifications.start(getApplicationContext(), "f59f82eb-bf93-4bd5-a98d-36c4dd416194");
        //PushNotifications.addDeviceInterest(FirebaseAuth.getInstance().getUid());
        drawerLayout = binding.drawerLayout;

        TextView accountInfo = binding.lateralNav.getHeaderView(0).findViewById(R.id.info);
        //accountInfo.setText(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail());
        accountInfo.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        binding.lateralNav.setNavigationItemSelectedListener(item -> {
            if(item.getItemId()==R.id.logout){
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                FirebaseAuth.getInstance().signOut();
                ClienteSession.finish();
                startActivity(intent);
            }
            return false;
        });

        if (eventosFragment == null) {
            eventosFragment = new ClienteEventosFragment();
            entradasFragment = new ClienteEntradasFragment();
            perfilFragment = new ClientePerfilFragment();
        }
        switch (navValue) {
            case 0:
                replaceFragment(eventosFragment);
                break;
           case 1:
                replaceFragment(entradasFragment);
                break;
           case 2:
                replaceFragment(perfilFragment);
                break;
        }


        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.eventos) {
                replaceFragment(eventosFragment);
                navValue = 0;
            } else if (id == R.id.solicitudes) {
                replaceFragment(entradasFragment);
                navValue = 1;
            } else if (id == R.id.perfil) {
                replaceFragment(perfilFragment);
                navValue = 2;
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout){
            Toast.makeText(this, "Chau", Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.user){
            if(drawerLayout.isDrawerOpen(GravityCompat.END)){
                drawerLayout.closeDrawer(GravityCompat.END);
            } else{
                drawerLayout.openDrawer(GravityCompat.END);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        eventosFragment = null;
        entradasFragment = null;
        perfilFragment = null;
        super.onDestroy();
    }
}