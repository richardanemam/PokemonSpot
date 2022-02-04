package com.pokemon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.common.extensions.loadImage
import com.pokemon.databinding.ItemPokemonBinding
import com.pokemon.domain.model.PokemonProfile

internal class PokemonProfileAdapter(
    private val pokemonList: List<PokemonProfile>,
    private val onItemClicked: (PokemonProfile) -> Unit
) : RecyclerView.Adapter<PokemonProfileAdapter.PokemonProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonProfileViewHolder {
        val itemViewBinding =
            ItemPokemonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PokemonProfileViewHolder(itemViewBinding)
    }

    override fun onBindViewHolder(holder: PokemonProfileViewHolder, position: Int) =
        holder.bindViews(pokemonList[position])

    override fun getItemCount(): Int = pokemonList.size

    inner class PokemonProfileViewHolder(private val binding: ItemPokemonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindViews(pokemon: PokemonProfile) {
            pokemon.imageUrl?.let { binding.ivPokemon.loadImage(it) }
            binding.tvPokemonName.text = pokemon.name
            binding.btnViewProfileDetails.setOnClickListener {
                onItemClicked(pokemon)
            }
        }
    }
}