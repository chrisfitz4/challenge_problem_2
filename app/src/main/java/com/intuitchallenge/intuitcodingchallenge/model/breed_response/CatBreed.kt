package com.intuitchallenge.intuitcodingchallenge.model.breed_response

import com.intuitchallenge.intuitcodingchallenge.model.breed_response.Breed

data class CatBreed(
    val breeds: List<Breed?>?,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)