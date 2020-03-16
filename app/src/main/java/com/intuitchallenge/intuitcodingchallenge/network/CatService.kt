package com.intuitchallenge.intuitcodingchallenge.network

import com.intuitchallenge.intuitcodingchallenge.model.breed_response.CatBreed
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CatService {

    @GET("v1/images/search")
    fun searchCat(
        @Query("breed_ids") breedId: String,
        @Query("include_breeds") includeBreeds: Boolean,
        @Query("limit") pageCount : Int
    ) : Observable<List<CatBreed>>

    @GET("/v1/breeds/")
    fun getFullList() : Observable<List<CatBreed>>
}