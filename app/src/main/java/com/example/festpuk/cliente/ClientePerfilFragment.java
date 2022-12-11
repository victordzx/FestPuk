package com.example.festpuk.cliente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.festpuk.R;
import com.example.festpuk.entity.Cuenta;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ClientePerfilFragment extends Fragment {

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference();
    Cuenta cuenta;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String keyCuenta = mAuth.getUid();

        View view = inflater.inflate(R.layout.fragment_cliente_perfil, container, false);
        TextView teNombreApellido = view.findViewById(R.id.teNombreApellido);
        TextView teFechaNac = view.findViewById(R.id.teFechaNac);
        TextView tedni = view.findViewById(R.id.teDNI);
        TextView tecorreo = view.findViewById(R.id.teCorreo);
        TextView terol = view.findViewById(R.id.teRol);

        databaseReference.child("cuenta/"+keyCuenta).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot children : snapshot.getChildren()) {
                    cuenta = children.getValue(Cuenta.class);

                    teNombreApellido.setText(cuenta.getNombre());
                    teFechaNac.setText(cuenta.getFechanac());
                    tedni.setText(cuenta.getDni());
                    tecorreo.setText(cuenta.getCorreo());
                    terol.setText(cuenta.getRol());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return view;
    }
}