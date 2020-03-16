package com.intuitchallenge.intuitcodingchallenge.viewmodel

import androidx.lifecycle.ViewModel
import com.intuitchallenge.intuitcodingchallenge.R
import com.intuitchallenge.intuitcodingchallenge.model.breed_response.CatBreed
import com.intuitchallenge.intuitcodingchallenge.model.breed_response.CatBreedWrapper
import com.intuitchallenge.intuitcodingchallenge.network.CatRetrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CatViewModel: ViewModel() {

    companion object{
        val abysColor = R.color.abysColor
        val otherColor =  R.color.otherColor
    }

    fun searchCat(breedId: String, includeBreeds: Boolean, pageCount: Int) =
        CatRetrofit.searchCat(breedId, includeBreeds, pageCount)
            .subscriber()

    fun getFullListOfCats() = CatRetrofit.getFullListOfCats()
        .subscriber()

    fun Observable<List<CatBreed>>.subscriber() : Observable<ArrayList<CatBreedWrapper>>{
        return this.subscribeOn(Schedulers.io())
            .map{response->
                val newList = ArrayList<CatBreedWrapper>()
                for(item in response){
                    if(item.breeds?.get(0)?.name=="Abyssinian"){
                        newList.add(CatBreedWrapper(item, abysColor))
                    }else{
                        newList.add(CatBreedWrapper(item, otherColor))
                    }
                }
                newList
            }
            .observeOn(AndroidSchedulers.mainThread())
    }
}