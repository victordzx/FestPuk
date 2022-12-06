package com.example.festpuk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.festpuk.R;
import com.example.festpuk.entity.Evento;
import com.example.festpuk.interfaces.RecycleviewerInterface;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class EventoAdapter extends RecyclerView.Adapter<EventoAdapter.EventoViewHolder> {
    private List<Evento> eventos;
    private LayoutInflater inflater;
    private Context context;
    private final RecycleviewerInterface recycleviewerInterface;

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_eventos_lista, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {

        Evento evento = eventos.get(position);
        holder.evento = evento;

        ImageView imagen;
        TextView titulo, fecha;

        imagen = holder.itemView.findViewById(R.id.imEvento);
        titulo = holder.itemView.findViewById(R.id.textTituloEvento);
        fecha = holder.itemView.findViewById(R.id.textFechaEvento);

        titulo.setText(evento.getTitulo());
        fecha.setText(evento.getFecha());

        //StorageReference imageRef = FirebaseStorage.getInstance().getReference().child("evento/"+evento.getFotourl());
        //Glide.with(context).load(imageRef).into(imagen);
    }

    public EventoAdapter(List<Evento> itemList, Context context, RecycleviewerInterface recycleviewerInterface) {
        this.eventos = itemList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.recycleviewerInterface = recycleviewerInterface;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {
        Evento evento;

        public EventoViewHolder(@NonNull View view) {

            super(view);

            view.setOnClickListener(v->{
                if(recycleviewerInterface !=null){
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        recycleviewerInterface.onItemClick(pos);
                    }
                }
            });
        }

    }
}