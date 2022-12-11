package com.example.festpuk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.festpuk.R;
import com.example.festpuk.entity.Entrada;
import com.example.festpuk.entity.Evento;
import com.example.festpuk.interfaces.RecycleviewerInterface;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class EntradasAdapter extends RecyclerView.Adapter<EntradasAdapter.EntradaViewHolder> {

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
    public EntradaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycle_entradas_lista, parent, false);
        return new EntradaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntradaViewHolder holder, int position) {

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

    public EntradasAdapter(List<Evento> itemList, Context context, RecycleviewerInterface recycleviewerInterface) {
        this.eventos = itemList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.recycleviewerInterface = recycleviewerInterface;
    }

    public void setEntradas(List<Evento> entradas) {
        this.eventos = entradas;
    }

    public class EntradaViewHolder extends RecyclerView.ViewHolder {
        public Evento evento;

        public EntradaViewHolder(@NonNull View view) {

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
