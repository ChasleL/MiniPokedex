package com.example.pokeapi.View;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pokeapi.Controller.MainController;
import com.example.pokeapi.R;

public class PokemonDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);
        TextView url = findViewById(R.id.url);
        String detailUrl = getIntent().getStringExtra("url");
        url.setText(detailUrl);
    }
}
