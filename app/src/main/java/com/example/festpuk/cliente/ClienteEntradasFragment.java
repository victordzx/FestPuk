package com.example.festpuk.cliente;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.festpuk.ClienteEntradasEventoActivity;
import com.example.festpuk.R;
import com.example.festpuk.adapter.EntradasAdapter;
import com.example.festpuk.adapter.EventoAdapter;
import com.example.festpuk.databinding.FragmentClienteEventosBinding;
import com.example.festpuk.entity.Entrada;
import com.example.festpuk.entity.Evento;
import com.example.festpuk.interfaces.RecycleviewerInterface;
import com.example.festpuk.save.ClienteSession;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ClienteEntradasFragment extends Fragment implements RecycleviewerInterface {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RecyclerView recyclerView;
    private boolean notCreated = true;
    FragmentClienteEventosBinding binding;
    EntradasAdapter entradasAdapter;
    Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("eventos/" + firebaseAuth.getUid());
        View view = inflater.inflate(R.layout.fragment_cliente_entradas, container, false);
        recyclerView = view.findViewById(R.id.recycleViewer);
        recyclerView.setHasFixedSize(true);
        Context context = super.getContext();
        entradasAdapter = new EntradasAdapter(ClienteSession.getEntradas(), context, this);

        if (notCreated) {
            notCreated = false;
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Evento> entradaList = new ArrayList<>();
                    for (DataSnapshot children : snapshot.getChildren()) {
                        Evento entrada = children.getValue(Evento.class);
                        if (entrada != null) {
                            entrada.setKey(children.getKey());
                        }
                        entradaList.add(entrada);
                    }
                    ClienteSession.setEntradas(entradaList);
                    entradasAdapter.setEntradas(ClienteSession.getEntradas());
                    recyclerView.setAdapter(entradasAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            entradasAdapter.setEntradas(ClienteSession.getEntradas());
            recyclerView.setAdapter(entradasAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }

        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), ClienteEntradasEventoActivity.class);
        intent.putExtra("entrada", ClienteSession.getEntradas().get(position));
        startActivity(intent);
    }
}