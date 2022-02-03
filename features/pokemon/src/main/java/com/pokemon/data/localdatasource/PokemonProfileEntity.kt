package com.pokemon.data.localdatasource

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class PokemonProfileEntity(
    @NonNull @PrimaryKey val name: String,
    @ColumnInfo(name = "type") val type: String?,
    @ColumnInfo(name = "imageUrl") val imageUrl: String?,
    @ColumnInfo(name = "weight") val weight: Int?,
    @ColumnInfo(name = "height") val height: Int?,
    @ColumnInfo(name = "baseExperience") val baseExperience: Int?,
    @ColumnInfo(name = "abilities") val abilities: List<String>?,
    @ColumnInfo(name = "moves") val moves: List<String>
)