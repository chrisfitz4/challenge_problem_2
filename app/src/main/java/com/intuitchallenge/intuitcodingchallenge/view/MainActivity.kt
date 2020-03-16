package com.intuitchallenge.intuitcodingchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.intuitchallenge.intuitcodingchallenge.R
import com.intuitchallenge.intuitcodingchallenge.adapter.CatRecyclerViewAdapter
import com.intuitchallenge.intuitcodingchallenge.model.breed_response.CatBreedWrapper
import com.intuitchallenge.intuitcodingchallenge.viewmodel.CatViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : CatViewModel
    private val compositeDisposable = CompositeDisposable()
    private var catAdapter : CatRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(CatViewModel::class.java)
        catAdapter = CatRecyclerViewAdapter(this.applicationContext)
        catRecyclerView.adapter = catAdapter
        compositeDisposable.add(
            viewModel.searchCat("beng", true, 5)
                .catSubscriber()
        )
        searchButton.setOnClickListener{
            val inputText = catSearchQuery.text.toString().trim()
            if(inputText.isNotEmpty()){
                viewModel.searchCat(inputText, true, 10)
                    .catSubscriber()
            }
        }
    }

    private fun Observable<ArrayList<CatBreedWrapper>>.catSubscriber(): Disposable{
        return this.subscribe({catResonse->
            if(catResonse.size==0){
                Toast.makeText(this@MainActivity,
                        "Hmmm, There were no results for this search",
                        Toast.LENGTH_LONG)
                    .show()
            }
            catAdapter?.listOfCatBreed = catResonse
            catRecyclerView.scrollToPosition(0)
            catAdapter?.notifyDataSetChanged()
        },{throwable->
            Log.d("TAG", "onCreate: error: ${throwable.message}")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
        catAdapter = null
    }
}
