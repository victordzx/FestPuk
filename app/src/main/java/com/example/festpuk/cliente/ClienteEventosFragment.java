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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.example.festpuk.ClienteEventoActivity;
import com.example.festpuk.R;
import com.example.festpuk.adapter.EventoAdapter;
import com.example.festpuk.databinding.FragmentClienteEventosBinding;
import com.example.festpuk.entity.Evento;
import com.example.festpuk.interfaces.RecycleviewerInterface;
import com.example.festpuk.save.ClienteSession;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class ClienteEventosFragment extends Fragment implements RecycleviewerInterface {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FragmentClienteEventosBinding binding;
    RecyclerView recyclerView;
    EventoAdapter eventoAdapter;
    Context context;

    private boolean notCreated = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentClienteEventosBinding.inflate(getLayoutInflater());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("evento");
        View view = inflater.inflate(R.layout.fragment_cliente_eventos, container, false);
        recyclerView = view.findViewById(R.id.recycleViewer);
        recyclerView.setHasFixedSize(true);
        context = super.getContext();
        eventoAdapter = new EventoAdapter(ClienteSession.getEventos(), context, this);

        if (notCreated) {
            notCreated = false;

            reference.orderByChild("stock").startAt(1).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    List<Evento> eventoList = new ArrayList<>();
                    for (DataSnapshot children : snapshot.getChildren()) {
                        Evento evento = children.getValue(Evento.class);
                        assert evento != null;
                        evento.setKey(children.getKey());
                        eventoList.add(evento);
                    }
                    ClienteSession.setEventos(eventoList);
                    eventoAdapter.setEventos(ClienteSession.getEventos());
                    recyclerView.setAdapter(eventoAdapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            eventoAdapter.setEventos(ClienteSession.getEventos());
            recyclerView.setAdapter(eventoAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        return view;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), ClienteEventoActivity.class);
        intent.putExtra("evento", ClienteSession.getEventos().get(position));
        startActivity(intent);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}