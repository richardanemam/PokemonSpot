package com.pokemon.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pokemon.domain.model.PokemonProfile

class PokemonDiffCallback(
    private val oldPokemonList: List<PokemonProfile>,
    private val newPokemonList: List<PokemonProfile>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldPokemonList.size

    override fun getNewListSize(): Int = newPokemonList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldPokemonList[oldItemPosition]
        val new = newPokemonList[newItemPosition]
        return old.name == new.name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val old = oldPokemonList[oldItemPosition]
        val new = newPokemonList[newItemPosition]
        return (old.name == new.name) && (old.imageUrl == old.imageUrl)
    }
}