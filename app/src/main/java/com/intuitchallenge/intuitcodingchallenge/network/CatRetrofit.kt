package com.intuitchallenge.intuitcodingchallenge.network

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object CatRetrofit {
    val BASE_URL = "https://api.thecatapi.com"

    val catService : CatService by lazy {
        retrofitInstance.create(CatService::class.java)
    }

    val retrofitInstance : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun searchCat(breedId: String, includeBreeds: Boolean, pageCount: Int)
            = catService.searchCat(breedId, includeBreeds, pageCount)

    fun getFullListOfCats() = catService.getFullList()

}