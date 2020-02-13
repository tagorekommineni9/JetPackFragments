package com.example.jetpackfragments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon {
    @SerializedName("Pokemon")
    @Expose
    private List<Pokemon_> pokemon = null;

    public List<Pokemon_> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<Pokemon_> pokemon) {
        this.pokemon = pokemon;
    }
}
