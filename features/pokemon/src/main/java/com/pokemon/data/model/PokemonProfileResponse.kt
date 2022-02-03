package com.pokemon.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PokemonProfileResponse(
    @SerializedName("abilities") val abilities: List<PokemonAbilitiesResponse>?,
    @SerializedName("base_experience") val baseExperience: Int?,
    @SerializedName("forms") val forms: List<NameUrlResponse>?,
    @SerializedName("game_indices") val gameIndices: List<GameIndicesResponse>?,
    @SerializedName("height") val height: Int?,
    @SerializedName("held_items") val heldItems: List<HeldItemsResponse>?,
    @SerializedName("id") val id: Int?,
    @SerializedName("is_default") val isDefault: Boolean?,
    @SerializedName("location_area_encounters") val locationAreaEncounters: String?,
    @SerializedName("moves") val moves: List<MovesResponse>?,
    @SerializedName("name") val name: String?,
    @SerializedName("order") val order: Int?,
    @SerializedName("species") val species: List<NameUrlResponse>?,
    @SerializedName("sprites") val sprites: SpritesResponse?,
    @SerializedName("types") val types: List<Type>?,
    @SerializedName("weight") val weight: Int?
) : Parcelable

@Parcelize
data class GameIndicesResponse(
    @SerializedName("game_index") val gameIndex: Int?,
    @SerializedName("version") val version: List<NameUrlResponse>?
) : Parcelable

@Parcelize
data class PokemonAbilitiesResponse(
    @SerializedName("ability") val ability: NameUrlResponse?,
    @SerializedName("is_hidden") val isHidden: Boolean?,
    @SerializedName("slot") val slot: Int?
) : Parcelable

@Parcelize
data class HeldItemsResponse(
    @SerializedName("item") val item: List<NameUrlResponse>?,
    @SerializedName("version_details") val version_details: List<VersionDetailsResponse>?
) : Parcelable

@Parcelize
data class VersionDetailsResponse(
    @SerializedName("rarity") val rarity: Int?,
    @SerializedName("version") val version: List<NameUrlResponse>?
) : Parcelable

@Parcelize
data class MovesResponse(
    @SerializedName("move") val move: List<NameUrlResponse>?,
    @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetailsResponse>

): Parcelable

@Parcelize
data class VersionGroupDetailsResponse(
    @SerializedName("level_learned_at") val levelLearnedAt: Int?,
    @SerializedName("move_learn_method") val moveLearnMethod: List<NameUrlResponse>?,
    @SerializedName("version_group") val versionGroup: List<NameUrlResponse>?
): Parcelable

@Parcelize
data class SpritesResponse(
    @SerializedName("other") val other: OtherResponse
): Parcelable

@Parcelize
data class OtherResponse(
    @SerializedName("official-artwork") val officialArtwork: FrontDefaultResponse?
): Parcelable

@Parcelize
data class FrontDefaultResponse(
    @SerializedName("front_default") val frontDefault: String?
): Parcelable

@Parcelize
data class Type(
    @SerializedName("slot") val slot: Int?,
    @SerializedName("type") val type: NameUrlResponse?
): Parcelable

@Parcelize
data class NameUrlResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("url") val url: String?
) : Parcelable