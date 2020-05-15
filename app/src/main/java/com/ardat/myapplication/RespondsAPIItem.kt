package com.ardat.myapplication
import com.google.gson.annotations.SerializedName

data class RespondsAPIItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("rt_score")
    val rtScore: String,
    @SerializedName("title")
    val title: String
)