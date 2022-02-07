package com.pokemon.presentation.extensions

import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.pokemon.R

fun LinearLayout.addCustomItemView(
    title: String,
    description: String?
) {
    val itemView = LayoutInflater.from(this.context).inflate(R.layout.item_pokemon_details, this, false)
    val tvTitle = itemView.findViewById<TextView>(R.id.tv_pokemon_details_title)
    val tvDescription = itemView.findViewById<TextView>(R.id.tv_pokemon_details_description)

    tvTitle.text = title
    description?.let { tvDescription.text = it }

    this.addView(itemView)
}