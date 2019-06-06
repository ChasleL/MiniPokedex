package com.example.pokeapi.View;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pokeapi.Model.Pokemon;
import com.example.pokeapi.R;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

    private List<Pokemon> pokemonList;
    private OnNoteListener myOnNoteListener;

    public void add(int position, Pokemon item) {
        pokemonList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        pokemonList.remove(position);
        notifyItemRemoved(position);
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PokemonAdapter(List<Pokemon> pokemonList, OnNoteListener onNoteListener) {
        this.pokemonList = pokemonList;
        this.myOnNoteListener = onNoteListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(view, myOnNoteListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Pokemon currentPokemon = pokemonList.get(position);
        holder.txtFirstLine.setText(currentPokemon.getName().substring(0,1).toUpperCase() + currentPokemon.getName().substring(1));
        holder.txtFooter.setText("Pokemon n°: " + (position+1));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        public TextView txtFirstLine;
        public TextView txtFooter;
        public View layout;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            layout = view;
            txtFirstLine = view.findViewById(R.id.firstLine);
            txtFooter = view.findViewById(R.id.secondLine);
            this.onNoteListener = onNoteListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}