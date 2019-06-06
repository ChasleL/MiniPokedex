package com.example.pokeapi.Controller;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.example.pokeapi.Model.Pokemon;
import com.example.pokeapi.Model.RestPokemonResponse;
import com.example.pokeapi.PokemonRestApi;
import com.example.pokeapi.View.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainController {
    private MainActivity view;
    private Pokemon pokemon;

    private PokemonRestApi pokemonRestApi;

    public MainController(MainActivity view, PokemonRestApi pokemonRestApi) {
        this.view = view;
        this.pokemonRestApi = pokemonRestApi;
    }

    public void startRecycler() {
        Call<RestPokemonResponse> call = pokemonRestApi.getPokemonList();
        call.enqueue(new Callback<RestPokemonResponse>() {
            @Override
            public void onResponse(Call<RestPokemonResponse> call, Response<RestPokemonResponse> response) {
                if(response.isSuccessful()) {
                    RestPokemonResponse restPokemonResponse = response.body();
                    List<Pokemon> pokemonList = restPokemonResponse.getResults();
                    view.showList(pokemonList);
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RestPokemonResponse> call, Throwable t) {
                Log.d("API Fail Call start", "onFailure");
            }
        });
    }

    class JsonTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                    Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
                }
                String data = buffer.toString();

                return data;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

    /*public void details(Pokemon pokemon) {
        Call<Pokemon> call = pokemonRestApi.getDetailsList(pokemon.getName());
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    pokemonDetail = response.body();

                } else {
                    System.out.println(response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.d("API Fail Call details", "onFailure");
            }
        });
    }*/
}
