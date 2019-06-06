package com.example.pokeapi.Model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RestPokemonResponse {
    private Integer count;
    private List<Pokemon> results;

    public List<Pokemon> getResults() {
        return results;
    }
}
