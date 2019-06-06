package com.example.pokeapi;

import com.example.pokeapi.Model.Pokemon;
import com.example.pokeapi.Model.RestPokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokemonRestApi {
    @GET("pokemon/?limit=900")
    Call<RestPokemonResponse> getPokemonList();

    @GET("pokemon/{name}")
    Call<Pokemon> getDetailsList(@Path("name") String name);
}
