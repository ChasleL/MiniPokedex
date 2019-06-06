package com.example.pokeapi.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.pokeapi.Controller.MainController;
import com.example.pokeapi.Injection;
import com.example.pokeapi.Model.Pokemon;
import com.example.pokeapi.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.OnNoteListener {

    private List<Pokemon> pokemons;
    //Déclaration des variables.
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainController controller = new MainController(this, Injection.getRestApiInstance());
        controller.startRecycler();
    }

    public void showList(List<Pokemon> pokemonList) {
        //Initialisation de la variable recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        //Optimisation des performances Merci la documentation.
        recyclerView.setHasFixedSize(true);
        // Layout Manager = Manage l'affichage. Ici en liste Verticale
        //Initialisation de la variable layoutManager
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        pokemons = pokemonList;
        //Initialisation de la variable mAdapter
        mAdapter = new PokemonAdapter(pokemonList, this);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, PokemonDetailActivity.class);
        intent.putExtra("url", pokemons.get(position).getUrl());
        startActivity(intent);
    }
}
